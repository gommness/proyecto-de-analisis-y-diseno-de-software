package persona.cliente;

import java.util.ArrayList;
import java.util.Arrays;

import excepciones.ExistenceException;
import persona.Persona;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de personas de tipo cliente
 */
public class Cliente extends Persona {
	private static final long serialVersionUID = 2183119312574719998L;
	private Contrato contrato;

	/**
	 * @param dni
	 *            el dni del cliente
	 * @param nombre
	 *            el nombre del cliente
	 * @param direccion
	 *            la direccion del cliente
	 * @param codigoPostal
	 *            el codigo postal del cliente
	 * @param telefono
	 *            el numero de telefono del cliente
	 * @param mail
	 *            el correo electronico del cliente
	 */
	public Cliente(String dni, String nombre, String direccion, int codigoPostal, String telefono, String mail) {
		super(dni, nombre, direccion, codigoPostal, telefono, mail);
		this.contrato = null;
	}

	/**
	 * @return el contrato del cliente
	 */
	public Contrato getContrato() {
		return contrato;
	}

	/**
	 * @param contrato
	 *            a asignar
	 */
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	/**
	 * @return si al contrato se le puede aplicar el descuento
	 */
	public boolean aplicarDescuento() {
		if (this.contrato != null)
			return this.contrato.aplicarDescuento();
		else
			return false;
	}

	/**
	 * @return el contrato estandar creado
	 */
	public Contrato crearContratoEstandar() {
		this.contrato = new Estandar();
		return this.contrato;
	}

	/**
	 * @return el contrato preferente creado
	 */
	public Contrato crearContratoPreferente() {
		this.contrato = new Preferente();
		return this.contrato;
	}

	/**
	 * Este metodo te cambia de un contrato a otro
	 * 
	 * @throws ExistenceException
	 */
	public void cambiarContrato() throws ExistenceException {
		boolean notif;
		double cuota;
		if (this.contrato == null)
			throw new ExistenceException("este cliente no tiene contrato");
		cuota = this.contrato.getCuotaInscripcion();
		notif = this.contrato.isNotificacion();
		if (cuota == Estandar.getCuota()) {
			this.contrato = new Preferente();
		} else if (cuota == Preferente.getCuota()) {
			this.contrato = new Estandar();
		}
		this.contrato.setNotificacion(notif);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persona.Persona#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + this.contrato;
	}

	@Override
	public Object[] filaPersona() {
		ArrayList<Object> a = new ArrayList<>(Arrays.asList(super.filaPersona()));
		/* campos a discutir */
		a.add(contrato);
		return a.stream().toArray(Object[]::new);
	}
}
