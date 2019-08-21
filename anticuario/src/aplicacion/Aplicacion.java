/**
 * 
 */
package aplicacion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import articulos.Articulo;
import articulos.subastable.Lote;
import articulos.subastable.ObjetoSubasta;
import articulos.voluminoso.Voluminoso;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import excepciones.ExistenceException;
import excepciones.IntervaloException;
import excepciones.anticuarioExcepciones.ArticuloException;
import excepciones.anticuarioExcepciones.SubastaException;
import persona.cliente.Cliente;
import persona.usuario.Empleado;
import persona.usuario.Gerente;
import persona.usuario.Usuario;
import subasta.Estado;
import subasta.Subasta;
import ventas.Venta;
import ventas.VentaDomicilio;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Esta clase es la aplicacion, gestiona todos los datos del sistema
 */
public class Aplicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario user;
	private ArrayList<Venta> ventas;
	private ArrayList<Articulo> articulos;
	private ArrayList<Subasta> subastas;
	private ArrayList<Lote> lotes;
	private Gerente gerente;
	private ArrayList<Empleado> empleados;
	private ArrayList<Cliente> clientes;

	/**
	 * @param ventas
	 *            una lista con todas las ventas
	 * @param articulos
	 *            una lista con todos los articulos
	 * @param subastas
	 *            una lista con todas las subastas
	 * @param lotes
	 *            una lista con todos los lotes
	 * @param gerente
	 *            la gerente
	 * @param empleados
	 *            una lista con todos los empleados
	 * @param clientes
	 *            una lista con todos los clientes
	 */
	public Aplicacion(ArrayList<Venta> ventas, ArrayList<Articulo> articulos, ArrayList<Subasta> subastas,
			ArrayList<Lote> lotes, Gerente gerente, ArrayList<Empleado> empleados, ArrayList<Cliente> clientes) {
		this.ventas = ventas;
		this.articulos = articulos;
		this.subastas = subastas;
		this.lotes = lotes;
		this.gerente = gerente;
		this.empleados = empleados;
		this.clientes = clientes;
	}

	/**
	 * @return un arraylist con todos los objetos subastables
	 */
	public ArrayList<ObjetoSubasta> getSubastables(){
		ArrayList<ObjetoSubasta> output = new ArrayList<>(this.getLotes());
		ArrayList<Articulo> aux = this.getArticulos();
		for(Articulo a : aux){
			if(a instanceof ObjetoSubasta)
				output.add((ObjetoSubasta)a);
		}
		return output;
	}
	
	/**
	 * 
	 */
	public Aplicacion() {
		this.ventas = new ArrayList<Venta>();
		this.articulos = new ArrayList<Articulo>();
		this.subastas = new ArrayList<Subasta>();
		this.lotes = new ArrayList<Lote>();
		this.gerente = null;
		this.empleados = new ArrayList<Empleado>();
		this.clientes = new ArrayList<Cliente>();
	}

	/**
	 * Este metodo se encarga de cargar los datos de la aplicacion desde un
	 * fichero .obj cuyo nombre se especifica por argumento
	 * 
	 * @param fileName
	 *            el nombre del fichero, debe acabar en .obj
	 * @return la aplicacion con todos los datos cargados
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Aplicacion load(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream iis = new ObjectInputStream(new FileInputStream(fileName));
		Aplicacion app = (Aplicacion) iis.readObject();
		Articulo.setCantidadArticulos((int) iis.readObject());
		Usuario.setNUsuarios((int) iis.readObject());
		Venta.setNVentas((int) iis.readObject());
		Subasta.setNSubastas((int) iis.readObject());
		iis.close();
		return app;
	}

	/**
	 * Este metodo se encarga de guardar los datos de la aplicacion en un
	 * fichero .obj cuyo nombre se especifica por argumento
	 * 
	 * @param fileName
	 *            el nombre del fichero, debe acabar en .obj
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(String fileName) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(this);
		oos.writeObject(Articulo.getCantidadArticulos());
		oos.writeObject(Usuario.getNUsuarios());
		oos.writeObject(Venta.getNVentas());
		oos.writeObject(Subasta.getNSubastas());
		oos.close();
	}

	/**
	 * Este metodo devuelve si se ha introducido una cuenta y contrase�a valida.
	 * Y consecuentemente, si se ha entrado correctamente
	 * 
	 * @param cuenta
	 *            la cuenta con la que se pretende entrar
	 * @param contrasena
	 *            la contrasena de dicha cuenta
	 * @return en caso de que se pueda entrar, true. En caso contrario, false
	 */
	public Usuario login(String cuenta, String contrasena) {
		if (this.user != null)
			return null;
		if (this.gerente.getCuenta().contentEquals(cuenta) && this.gerente.getContrasena().contentEquals(contrasena)) {
			this.user = this.gerente;
			return this.gerente;
		}
		for (Usuario u : this.empleados) {
			if (u.getCuenta().contentEquals(cuenta) && u.getContrasena().contentEquals(contrasena)) {
				this.user = u;
				return u;
			}
		}
		return null;
	}

	/**
	 * este metodo se encarga de hacer lougout del usuario que estaba conectado
	 */
	public void logout() {
		this.user = null;
	}

	/**
	 * @param cliente
	 *            a dar de alta
	 * @return el cliente
	 */
	public Cliente darAltaCliente(Cliente cliente) {
		if (!this.clientes.contains(cliente)) {
			this.clientes.add(cliente);
			return cliente;
		}
		return null;
	}

	/**
	 * Esta funcion anade un objeto de tipo cliente a la lista de clientes y a
	 * la vez, devuelve dicho cliente
	 * 
	 * @param dni
	 *            el dni del cliente
	 * @param nombre
	 *            el nombre del cliente
	 * @param direccion
	 *            la direccion de la vivienda del cliente
	 * @param codigoPostal
	 *            el codigo postal del cliente
	 * @param telefono
	 *            el telefono del cliente
	 * @param mail
	 *            el correo electronico del cliente
	 * @return el cliente creado
	 */
	public Cliente darAltaCliente(String dni, String nombre, String direccion, int codigoPostal, String telefono,
			String mail) {
		Cliente cliente = new Cliente(dni, nombre, direccion, codigoPostal, telefono, mail);
		return this.darAltaCliente(cliente);
	}

	/**
	 * Este metodo sirve para buscar clientes dentro de la lista de clientes de
	 * la aplicacion. se buscaran aquellos clientes cuyo dni, nombre y telefono
	 * coincidan con los especificados En caso de no querer especificar alguno
	 * de esos campos, se debera introducir ""
	 * 
	 * @param dni
	 *            el dni de los clientes a buscar
	 * @param nombre
	 *            el nombre de los clientes a buscar
	 * @param telefono
	 *            el telefono de los clientes a buscar
	 * @return una lista con los clientes que cumplen los criterios de busqueda
	 */
	public ArrayList<Cliente> buscarCliente(String dni, String nombre, String telefono) {
		ArrayList<Cliente> resultado = new ArrayList<Cliente>();
		for (Cliente c : this.clientes) {
			if (dni == null || c.getDni().equalsIgnoreCase(dni) || dni.contentEquals(""))
				if (nombre == null || c.getNombre().equalsIgnoreCase(nombre) || nombre.contentEquals(""))
					if (telefono == null || c.getTelefono().equalsIgnoreCase(telefono) || telefono.contentEquals(""))
						resultado.add(c);
		}
		return resultado;
	}

	/**
	 * Esta funcion te devuelve un cliente si este es el unico que pasa ciertos
	 * filtros
	 * 
	 * @param dni
	 *            del cliente
	 * @param nombre
	 *            del cliente
	 * @param telefono
	 *            del cliente
	 * @return un cliente
	 */
	public Cliente findCliente(String dni, String nombre, String telefono) {
		ArrayList<Cliente> resultado = buscarCliente(dni, nombre, telefono);
		if (resultado.size() == 1)
			return resultado.get(0);
		return null;
	}

	/**
	 * A esta funcion se le puede pasar el numero de filtros que desees y el
	 * resto ponerlo a "" y asi se puede buscar con un numero de filtros
	 * variables
	 * 
	 * @param dni
	 *            del empleado
	 * @param nombre
	 *            del empleado
	 * @param telefono
	 *            del empleado
	 * @return un arrayList con los empleados que pasan dichos filtros
	 */
	public ArrayList<Empleado> buscarEmpleado(String dni, String nombre, String telefono) {
		ArrayList<Empleado> resultado = new ArrayList<Empleado>();
		for (Empleado c : this.empleados) {
			if (c.getDni().equalsIgnoreCase(dni) || dni.contentEquals(""))
				if (c.getNombre().equalsIgnoreCase(nombre) || nombre.contentEquals(""))
					if (c.getTelefono().equalsIgnoreCase(telefono) || telefono.contentEquals(""))
						resultado.add(c);
		}
		return resultado;
	}

	/**
	 * Esta funcion te devuelve un empleado si este es el unico que pasa ciertos
	 * filtros
	 * 
	 * @param dni
	 *            del empleado
	 * @param nombre
	 *            del empleado
	 * @param telefono
	 *            del empleado
	 * @return un empleado
	 */
	public Empleado findEmpleado(String dni, String nombre, String telefono) {
		ArrayList<Empleado> resultado = buscarEmpleado(dni, nombre, telefono);
		if (resultado.size() == 1)
			return resultado.get(0);
		return null;
	}

	/**
	 * @param e
	 *            el empleado a dar de alta
	 * @return el empleado
	 */
	public Empleado darAltaEmpleado(Empleado e) {
		if (!this.empleados.contains(e)) {
			this.empleados.add(e);
			return e;
		}
		return null;
	}

	/**
	 * Este metodo se encarga de crear un Empleado e incluirlo en la lista de
	 * empleados de la aplicacion
	 * 
	 * @param dni
	 *            el dni del empleado
	 * @param nombre
	 *            el nombre del empleado
	 * @param direccion
	 *            la direccion del empleado
	 * @param codigoPostal
	 *            el codigo postal del empleado
	 * @param telefono
	 *            el telefono del empleado
	 * @param mail
	 *            la direccion de correo electronico del empleado
	 * @param nSeguridadSocial
	 *            el numero de la seguridad social del empleado
	 * @return el empleado
	 */
	public Empleado darAltaEmpleado(String dni, String nombre, String direccion, int codigoPostal, String telefono,
			String mail, int nSeguridadSocial) {
		Empleado empleado = new Empleado(dni, nombre, direccion, codigoPostal, telefono, mail, nSeguridadSocial);
		return this.darAltaEmpleado(empleado);
	}

	/**
	 * Este metodo se encarga de eliminar de la lista de empleados a un empleado
	 * 
	 * @param e
	 *            el empleado que se desea eliminar
	 * @throws ExistenceException
	 *             en caso de que el empleado a dar de baja no exista
	 */
	public void darBajaEmpleado(Empleado e) throws ExistenceException {
		if (this.empleados.contains(e) == false)
			throw new ExistenceException("ese empleado no existe");
		this.empleados.remove(e);
	}

	/**
	 * Este metodo se encarga de iniciar una subasta y almacenarla en la lista
	 * de subastas de la aplicacion
	 * 
	 * @param nDias
	 *            el numero de dias que durara la subasta
	 * @param cuota
	 *            la cuota que han de pagar los clientes para participar en la
	 *            subasta
	 * @param precio
	 *            el precio inicial de la subasta
	 * @param fechaInicio
	 *            la fecha en la que se inicia la subasta
	 * @param o
	 *            el objeto que se desea subastar
	 * @return la subasta creada
	 * @throws FailedInternetConnectionException
	 * @throws InvalidEmailAddressException
	 * @throws ArticuloException
	 */
	public Subasta iniciarSubasta(int nDias, double cuota, double precio, LocalDate fechaInicio, Articulo o)
			throws InvalidEmailAddressException, FailedInternetConnectionException, ArticuloException {
		Subasta subasta = new Subasta(nDias, cuota, precio, fechaInicio, o);
		this.subastas.add(subasta);
		if (!this.articulos.contains(o))
			this.articulos.add(o);
		subasta.iniciarSubasta(this.clientes);

		return subasta;
	}

	/**
	 * Este metodo sirve para buscar las subastas entre dos fechas especificadas
	 * 
	 * @param fechaInicio
	 *            la primera fecha del intervalo
	 * @param fechaFin
	 *            la ultima fecha del intervalo
	 * @return la lista con las subastas que cumplen la condicion
	 * @throws IntervaloException
	 *             en caso de que el orden de las fechas este invertido
	 */
	public ArrayList<Subasta> buscarSubasta(LocalDate fechaInicio, LocalDate fechaFin) throws IntervaloException {
		ArrayList<Subasta> resultado = new ArrayList<Subasta>();
		if (fechaInicio.isAfter(fechaFin))
			throw new IntervaloException("El orden de las fechas esta invertido");
		for (Subasta s : this.subastas) {
			if (s.getFechaFin().isAfter(fechaInicio) && s.getFechaFin().isBefore(fechaFin))
				resultado.add(s);
		}
		return resultado;
	}

	/**
	 * Este metodo busca una subasta por su id
	 * 
	 * @param id
	 *            de la subasta
	 * @return la subasta
	 */
	public Subasta findSubasta(int id) {
		for (Subasta s : this.subastas)
			if (s.getId() == id)
				return s;
		return null;
	}

	/**
	 * Este metodo sirve para crear una venta de un articulo a un cliente y
	 * almacenarla en la lista de ventas de la aplicacion
	 * 
	 * @param c
	 *            el cliente al que se le vende
	 * @param a
	 *            el articulo que se vende
	 * @return la venta creada
	 * @throws ArticuloException
	 */
	public Venta crearVenta(Cliente c, Articulo a) throws ArticuloException {
		Venta venta = new Venta(c, a);
		if (!this.clientes.contains(c))
			this.clientes.add(c);
		if (!this.articulos.contains(a))
			this.articulos.add(a);
		this.ventas.add(venta);
		return venta;
	}

	/**
	 * Este metodo sirve para crear una venta de un articulo a un cliente y
	 * almacenarla en la lista de ventas de la aplicacion
	 * 
	 * @param c
	 *            el cliente al que se le vende
	 * @param v
	 *            el articulo que se vende
	 * @return la venta creada
	 * @throws ArticuloException
	 */
	public Venta crearVentaDomicilio(Cliente c, Voluminoso v) throws ArticuloException {
		VentaDomicilio venta = new VentaDomicilio(c, v);
		if (!this.clientes.contains(c))
			this.clientes.add(c);
		this.ventas.add(venta);
		return venta;
	}

	/**
	 * Este metodo sirve para buscar una venta realizada entre las dos fechas
	 * especificadas
	 * 
	 * @param fechaInicio
	 *            la primera fecha
	 * @param fechaFin
	 *            la ultimafecha
	 * @return una lista con las ventas entre dichas fechas
	 * @throws IntervaloException
	 *             en caso de que el orden de las fechas este invertido
	 */
	public ArrayList<Venta> buscarVenta(LocalDate fechaInicio, LocalDate fechaFin) throws IntervaloException {
		ArrayList<Venta> resultado = new ArrayList<Venta>();
		if (fechaInicio.isAfter(fechaFin))
			throw new IntervaloException("el orden de las fechas esta invertido");
		if (this.ventas.isEmpty())
			return resultado;
		for (Venta v : this.ventas) {
			if (v.getDate().isAfter(fechaInicio)
					&& v.getDate().isBefore(fechaFin) /* && v.isCobrado() */)
				resultado.add(v);
		}
		return resultado;
	}

	/**
	 * Busca una venta a partir de su id
	 * 
	 * @param id
	 *            de la venta
	 * @return la venta
	 */
	public Venta buscarVenta(int id) {
		for (Venta v : this.ventas)
			if (v.getId() == id)
				return v;
		return null;
	}

	/**
	 * Crea un lote a partir de un numero de articulos pasados por argumento
	 * 
	 * @param articulos
	 *            numero variable de articulos
	 * @return el lote creado
	 */
	public Lote crearLote(String nombre, Articulo... articulos) {
		for (Articulo a : articulos)
			if (!this.articulos.contains(a))
				this.articulos.add(a);
		Lote lote = new Lote(nombre, articulos);
		this.lotes.add(lote);
		return lote;
	}

	/**
	 * Este metodo se encarga de calcular el total de las ganancias entre dos
	 * fechas
	 * 
	 * @param fechaInicio
	 *            la primera fecha
	 * @param fechaFin
	 *            la ultima fecha
	 * @return el valor total de las ganancias entre esas dos fechas
	 * @throws IntervaloException
	 *             en caso de que el orden de las fechas este invertido
	 */
	public double getTotalGanancias(LocalDate fechaInicio, LocalDate fechaFin) throws IntervaloException {
		double total = 0;
		if (fechaInicio.isAfter(fechaFin))
			throw new IntervaloException("el orden de las fechas esta invertido");
		for (Venta v : this.ventas)
			if (v.isCobrado() == true && v.getDate().compareTo(fechaInicio) >= 0
					&& v.getDate().compareTo(fechaFin) <= 0)
				total += v.getGanancia();
		for (Subasta s : this.subastas)
			if (s.getEstado().compareTo(Estado.cerrado) == 0 && s.getFechaFin().compareTo(fechaInicio) >= 0
					&& s.getFechaFin().compareTo(fechaFin) <= 0)
				total += s.getGanancias();
		return total;
	}

	/**
	 * Este metodo sirve para crear un String con un resumen de las ganancias
	 * acumuladas entre dos fechas
	 * 
	 * @param fechaInicio
	 *            la primera fecha
	 * @param fechaFin
	 *            la segunda fecha
	 * @return el String conteniengo dicho resumen
	 * @throws IntervaloException
	 *             en caso de que el orden de las fechas este invertido
	 */
	/*
	 * public String consultarGanancias(LocalDate fechaInicio, LocalDate
	 * fechaFin) throws IntervaloException { if (fechaInicio.isAfter(fechaFin))
	 * throw new IntervaloException("el orden de las fechas esta invertido");
	 * String output = ""; double total = 0; output += "VENTAS:\n\n"; for (Venta
	 * v : this.ventas) if (v.isCobrado() == true &&
	 * v.getDate().compareTo(fechaInicio) >= 0 &&
	 * v.getDate().compareTo(fechaFin) <= 0) { output += v + "\n"; total +=
	 * v.getGanancia(); } output += "\nSUBASTAS:\n\n"; for (Subasta s :
	 * this.subastas) if (s.getEstado().compareTo(Estado.cerrado) == 0 &&
	 * s.getFechaFin().compareTo(fechaInicio) >= 0 &&
	 * s.getFechaFin().compareTo(fechaFin) <= 0) { output += s + "\n"; total +=
	 * s.getGanancias(); } output += "\nTOTAL\t\t" + total; return output; }
	 */
	public Object[][] consultarGanancias(LocalDate fechaInicio, LocalDate fechaFin) throws IntervaloException {
		if (fechaInicio.isAfter(fechaFin))
			throw new IntervaloException("el orden de las fechas esta invertido");
		ArrayList<Object[]> data = new ArrayList<>();

		for (Venta v : this.ventas)
			if (v.isCobrado() == true && v.getDate().compareTo(fechaInicio) >= 0
					&& v.getDate().compareTo(fechaFin) <= 0) {
				data.add(v.filaGanancia());
			}
		for (Subasta s : this.subastas)
			if (s.getEstado().compareTo(Estado.cerrado) == 0 && s.getFechaFin().compareTo(fechaInicio) >= 0
					&& s.getFechaFin().compareTo(fechaFin) <= 0) {
				data.add(s.filaGanancia());
			}
		Object[][] output = data.stream().toArray(Object[][]::new);

		return output;
	}

	/**
	 * Este metodo sirve para buscar articulos segun su id, su precio y su fecha
	 * de adquisicion la lista de articulos devuelta contendra todos los
	 * articulos cuyo id, precio y fecha de adquisicion sean iguales a los
	 * especificados por argumento En caso de que no se quiera especificar la
	 * fecha, se introducira null En caso de que no se quiera especificar el id
	 * o el precio, se introducira 0
	 * 
	 * @param id
	 *            el id de los articulos a buscar
	 * @param precio
	 *            el precio de los productos a buscar
	 * @param fechaAdquisicion
	 *            la fecha de adquisicion de los productos a buscar
	 * @return una lista con los productos que cumplen las condiciones de
	 *         busqueda
	 */
	public ArrayList<Articulo> buscarArticulo(int id, double precio, LocalDate fechaAdquisicion, String nombre) {
		ArrayList<Articulo> resultado = new ArrayList<Articulo>();
		for (Articulo a : this.articulos) {
			if (id == 0 || a.getId() == id)
				if (precio == 0 || a.getPrecioObjetivo() == precio)
					if (fechaAdquisicion == null || a.getFechaAquisicion().isEqual(fechaAdquisicion))
						if (nombre == null || a.getDescripcion().equalsIgnoreCase(nombre) || nombre.equals(""))
							resultado.add(a);
		}
		return resultado;
	}

	/**
	 * Este metodo vale para encontrar un articulo unico que pase todos los
	 * filtros
	 * 
	 * @param id
	 *            del articulo
	 * @param precio
	 *            del articulo
	 * @param fechaAdquisicion
	 *            del articulo
	 * @return el articulo encontrado
	 */
	public Articulo findArticulo(int id, double precio, LocalDate fechaAdquisicion, String nombre) {
		ArrayList<Articulo> fetched = buscarArticulo(id, precio, fechaAdquisicion, nombre);
		if (fetched.size() != 1)
			return null;
		return fetched.get(0);
	}

	/**
	 * este metodo cancela la subasta pasada por argumento
	 * 
	 * @param s
	 *            la subasta a cancelar
	 * @throws InvalidEmailAddressException
	 * @throws FailedInternetConnectionException
	 * @throws SubastaException
	 */
	public void cancelarSubasta(Subasta s)
			throws InvalidEmailAddressException, FailedInternetConnectionException, SubastaException {
		s.cancelarSubasta();
	}

	/**
	 * @return la lista de ventas
	 */
	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	/**
	 * @param ventas
	 *            la lista de ventas a asignar
	 */
	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}

	/**
	 * @return la lista de articulos
	 */
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	/**
	 * @param articulos
	 *            la lista de articulos a asignar
	 */
	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}

	/**
	 * @return la lista de subastas
	 */
	public ArrayList<Subasta> getSubastas() {
		return subastas;
	}

	/**
	 * @param subastas
	 *            la lista de subastas a asignar
	 */
	public void setSubastas(ArrayList<Subasta> subastas) {
		this.subastas = subastas;
	}

	/**
	 * @return la lista de lotes
	 */
	public ArrayList<Lote> getLotes() {
		return lotes;
	}

	/**
	 * @param lotes
	 *            la lista de lotes a asignar
	 */
	public void setLotes(ArrayList<Lote> lotes) {
		this.lotes = lotes;
	}

	/**
	 * @return la gerente
	 */
	public Gerente getGerente() {
		return gerente;
	}

	/**
	 * @param gerente
	 *            la gerente a asignar
	 */
	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	/**
	 * @return la lista de empleados
	 */
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	/**
	 * @param empleados
	 *            la lista de empleados a asignar
	 */
	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	/**
	 * @return la lista de clientes
	 */
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes
	 *            la lista de clientes a asignar
	 */
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the user
	 */
	public Usuario getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(Usuario user) {
		this.user = user;
	}

}
