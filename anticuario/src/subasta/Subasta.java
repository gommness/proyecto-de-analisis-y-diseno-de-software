/**
 * 
 */
package subasta;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import articulos.Articulo;
import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import excepciones.ExistenceException;
import excepciones.anticuarioExcepciones.ArticuloException;
import excepciones.anticuarioExcepciones.ContratoException;
import excepciones.anticuarioExcepciones.PujaException;
import excepciones.anticuarioExcepciones.SubastaException;
import persona.cliente.Cliente;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de Subasta
 */
public class Subasta implements Serializable {
	private static final long serialVersionUID = -1075365948900162445L;
	private static int nSubastas = 0;
	private int id;
	private int nDias;
	private double cuotaInscripcion;
	private double precioBase;
	private double ganancias;
	private LocalDate fechaInicio;
	private Estado estado;

	private ArrayList<Cliente> participantes;
	private Articulo objetoSubasta;
	private ArrayList<Puja> pujas;
	private ArrayList<Cliente> clientesInteresados;

	/**
	 * @param nDias
	 *            los dias que dura la subasta
	 * @param cuotaInscripcion
	 *            la cuota de inscripcion a la subasta
	 * @param precioBase
	 *            el precio base de la subasta
	 * @param fechaInicio
	 *            la fecha de inicio de la subasta
	 * @param objetoSubasta
	 *            el articulo que se subasta
	 * @throws ArticuloException
	 */
	public Subasta(int nDias, double cuotaInscripcion, double precioBase, LocalDate fechaInicio, Articulo objetoSubasta)
			throws ArticuloException {
		objetoSubasta.subastar();
		this.nDias = nDias;
		this.cuotaInscripcion = cuotaInscripcion;
		this.precioBase = precioBase;
		this.ganancias = 0;
		this.fechaInicio = fechaInicio;
		this.estado = Estado.abierto;
		this.objetoSubasta = objetoSubasta;
		this.pujas = new ArrayList<Puja>();
		this.clientesInteresados = new ArrayList<Cliente>();
		this.participantes = new ArrayList<Cliente>();
		Subasta.nSubastas++;
		this.id = Subasta.nSubastas;

	}

	/**
	 * Se encarga de mandar los mails a todos los clientes con contrato para
	 * notificar que se ha iniciado una subasta
	 * 
	 * @param clientes
	 *            lista de todos los clientes de la aplicacion
	 * @throws InvalidEmailAddressException
	 * @throws FailedInternetConnectionException
	 */
	public void iniciarSubasta(List<Cliente> clientes)
			throws InvalidEmailAddressException, FailedInternetConnectionException {
		for (Cliente c : clientes) {
			if (c.getContrato() != null) {
				EmailSystem.send(c.getMail(), "Nueva subasta", "se ha iniciado una nueva subasta de: " + objetoSubasta);
				if (c.getContrato().isNotificacion())
					this.getClientesInteresados().add(c);

			}

		}
	}

	/**
	 * Cierra una subasta si. Si aun no hay pujas, en lugar de cerrarse, se
	 * cancela
	 * 
	 * @throws FailedInternetConnectionException
	 * @throws InvalidEmailAddressException
	 * @throws SubastaException
	 */
	public void cerrarSubasta()
			throws SubastaException, InvalidEmailAddressException, FailedInternetConnectionException {
		if (this.estado == Estado.abierto && LocalDate.now().isAfter(this.fechaInicio.plusDays(this.nDias))) {
			if (this.pujas.isEmpty()) {
				this.cancelarSubasta();
				return;
			}
			this.estado = Estado.cerrado;
			this.ganancias += this.getUltimaPuja().getValor() - this.objetoSubasta.getCosteAdquisicion();
			for (Cliente c : this.participantes) {
				EmailSystem.send(c.getMail(), "Subasta cerrada", "La subasta se ha terminado");
			}
			EmailSystem.send(this.getUltimaPuja().getCliente().getMail(), "Enhorabuena has ganado la subasta",
					"Has ganado un " + this.objetoSubasta);
		} else
			throw new SubastaException("no se puede cerrar esta subasta");
	}

	/**
	 * cancela la subasta si no hay pujas y no estaba ya cancelada
	 * 
	 * @throws FailedInternetConnectionException
	 * @throws InvalidEmailAddressException
	 * @throws SubastaException
	 */
	public void cancelarSubasta()
			throws SubastaException, InvalidEmailAddressException, FailedInternetConnectionException {
		if (this.estado != Estado.cancelado && this.pujas.isEmpty()) {
			this.estado = Estado.cancelado;
			for (Cliente c : this.clientesInteresados) {
				EmailSystem.send(c.getMail(), "Subasta Cancelada",
						"La subasta del objeto " + this.objetoSubasta + "ha sido cancelada");
			}
		} else
			throw new SubastaException("no se puede cancelar esta suabsta");
	}

	/**
	 * este metodo registra una puja en una subasta, si cumple con las
	 * condiciones de puja valida.
	 * 
	 * @param cliente
	 *            el cliente que hara la puja
	 * @param valor
	 *            el valor de la puja
	 * @throws InvalidEmailAddressException
	 * @throws FailedInternetConnectionException
	 * @throws ExistenceException
	 * @throws ContratoException
	 * @throws PujaException
	 */
	public void registrarPuja(Cliente cliente, double valor) throws InvalidEmailAddressException,
			FailedInternetConnectionException, PujaException, ExistenceException, ContratoException {
		Puja puja = new Puja(valor, cliente);
		registrarPuja(cliente, puja);
	}

	/**
	 * este metodo registra una puja en una subasta, si cumple con las
	 * condiciones de puja valida.
	 * 
	 * @param cliente
	 *            el cliente que hara la puja
	 * @param puja
	 *            la puja que se realiza
	 * @throws InvalidEmailAddressException
	 * @throws FailedInternetConnectionException
	 * @throws ExistenceException
	 * @throws ContratoException
	 * @throws PujaException
	 */
	public void registrarPuja(Cliente cliente, Puja puja) throws InvalidEmailAddressException,
			FailedInternetConnectionException, PujaException, ExistenceException, ContratoException {
		Puja aux;
		double valor;
		if (cliente.getContrato() == null)
			throw new ExistenceException("El cliente no tiene contrato");
		/*
		 * asignamos el valor a superar en funcion de si hay o no puja anterior
		 */
		aux = this.getUltimaPuja();
		if (aux != null)
			valor = aux.getValor();
		else
			valor = this.getPrecioBase();
		/*
		 * Este if comprueba tres cosas: 1. el valor de la nueva puja es valido
		 * (e.d. 0.05 mayor al valor anterior) 2. la subasta aun esta abierta 3.
		 * la puja se realiza antes de que la subasta finalice
		 */
		if (valor + 0.05 <= puja.getValor() && this.estado.compareTo(Estado.abierto) == 0
				&& puja.getFecha().isBefore(this.getFechaFin())) {
			if (!this.participantes.contains(cliente)) {
				this.participantes.add(cliente);
				if (cliente.getContrato().tieneInscripcionGratis())
					cliente.getContrato().aplicarInscripcionGratis();
				else {
					this.ganancias += this.cuotaInscripcion;
				}
			}
			if (!this.clientesInteresados.contains(cliente))
				this.clientesInteresados.add(cliente);
			this.pujas.add(puja);
			String listaPujas = "";
			for (Puja p : this.pujas) {
				listaPujas += p;
			}
			if (LocalDate.now().isBefore(this.fechaInicio.plusDays(this.nDias).minusDays(1))) {
				for (Cliente c : this.clientesInteresados) {
					EmailSystem.send(c.getMail(), "Puja realizada", "Ha sido realizada una puja: " + listaPujas);
				}
			}
		} else {
			throw new PujaException("puja invalida");
		}
	}

	/**
	 * anade un cliente a la lista de clientes interesados
	 * 
	 * @param cliente
	 *            el cliente a anadir
	 */
	public void addClienteInteresado(Cliente cliente) {
		if (!this.clientesInteresados.contains(cliente)) {
			this.clientesInteresados.add(cliente);
		}
	}

	/**
	 * elimina a un cliente de la lista de clientes interesados
	 * 
	 * @param cliente
	 *            el cliente a eliminar
	 */
	public void removeClienteInteresado(Cliente cliente) {
		if (this.clientesInteresados.contains(cliente)) {
			this.clientesInteresados.remove(cliente);
		}
	}

	/**
	 * @return id de la subasta
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the nDias
	 */
	public int getnDias() {
		return nDias;
	}

	/**
	 * @param nDias
	 *            the nDias to set
	 */
	public void setnDias(int nDias) {
		this.nDias = nDias;
	}

	/**
	 * @return the cuotaInscripcion
	 */
	public double getCuotaInscripcion() {
		return cuotaInscripcion;
	}

	/**
	 * @param cuotaInscripcion
	 *            the cuotaInscripcion to set
	 */
	public void setCuotaInscripcion(double cuotaInscripcion) {
		this.cuotaInscripcion = cuotaInscripcion;
	}

	/**
	 * @return the precioBase
	 */
	public double getPrecioBase() {
		return precioBase;
	}

	/**
	 * @param precioBase
	 *            the precioBase to set
	 */
	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	/**
	 * @return the ganancias
	 */
	public double getGanancias() {
		return ganancias;
	}

	/**
	 * @param ganancias
	 *            the ganancias to set
	 */
	public void setGanancias(double ganancias) {
		this.ganancias = ganancias;
	}

	/**
	 * @return the fechaInicio
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return la fecha del fin de la subasta
	 */
	public LocalDate getFechaFin() {
		return this.fechaInicio.plusDays(this.nDias);
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the objetoSubasta
	 */
	public Articulo getObjetoSubasta() {
		return objetoSubasta;
	}

	/**
	 * @param objetoSubasta
	 *            the objetoSubasta to set
	 * @throws ArticuloException
	 */
	public void setObjetoSubasta(Articulo objetoSubasta) throws ArticuloException {
		objetoSubasta.subastar();
		this.objetoSubasta = objetoSubasta;
	}

	/**
	 * @return the ultimaPuja
	 */
	public Puja getUltimaPuja() {
		if (this.pujas.isEmpty())
			return null;
		return this.pujas.get(pujas.size() - 1);
	}

	/**
	 * @return the puja
	 */
	public ArrayList<Puja> getPuja() {
		return pujas;
	}

	/**
	 * @param pujas
	 *            the puja to set
	 */
	public void setPuja(ArrayList<Puja> pujas) {
		this.pujas = pujas;
	}

	/**
	 * @return the clientesInteresados
	 */
	public ArrayList<Cliente> getClientesInteresados() {
		return clientesInteresados;
	}

	/**
	 * @param clientesInteresados
	 *            the clientesInteresados to set
	 */
	public void setClientesInteresados(ArrayList<Cliente> clientesInteresados) {
		this.clientesInteresados = clientesInteresados;
	}

	/**
	 * @return the participantes
	 */
	public ArrayList<Cliente> getParticipantes() {
		return participantes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.id + " " + this.nDias + " " + this.cuotaInscripcion + " " + this.precioBase + " " + this.ganancias
				+ " " + this.fechaInicio + " " + this.estado + " " + this.participantes + " " + this.objetoSubasta
				+ this.pujas + " " + this.clientesInteresados;
	}

	/**
	 * @return el numero total de subastas
	 */
	public static int getNSubastas() {
		return Subasta.nSubastas;
	}

	/**
	 * @param n
	 *            el numero total de subastas a asignar
	 */
	public static void setNSubastas(int n) {
		Subasta.nSubastas = n;
	}
	public Object[] fila(){
		//id, objeto, inicio, fin, cuota,precio base, actual ganador,su dni, puja
		String nombre;
		String dni;
		double valor;
		if (this.getUltimaPuja() == null){
			nombre = "";
			dni = "";
			valor = 0;
		}
		else{
			nombre = this.getUltimaPuja().getCliente().getNombre();
			dni = this.getUltimaPuja().getCliente().getDni();
			valor = this.getUltimaPuja().getValor();
		}
		Object[] o = {this.getId(),this.getObjetoSubasta().getDescripcion(),this.getFechaInicio(),
				this.getFechaFin(),this.getCuotaInscripcion(),nombre,
				this.getPrecioBase(),dni,
				valor};
		return o;
	}
	public Object[] filaGanancia(){
		String name = "";
		if(pujas!=null && pujas.size()>0)
			name = pujas.get(pujas.size()-1).getCliente().getNombre();
		Object[] o={id,"Subasta",ganancias,name,objetoSubasta.getDescripcion(),fechaInicio,nDias};
		return o;
	}
}
