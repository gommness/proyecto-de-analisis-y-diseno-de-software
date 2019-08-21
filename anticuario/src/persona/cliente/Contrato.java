package persona.cliente;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import excepciones.ExistenceException;
import excepciones.anticuarioExcepciones.ContratoException;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de contratos de personas
 */
public abstract class Contrato implements Serializable {

	private static final long serialVersionUID = 764226902352938341L;
	private boolean notificacion;
	private double cuotaInscripcion;
	private LocalDate fechaInicio;

	/**
	 * el atributo notificacion se pone a true por defecto
	 * @param cuota la cuota del contrato
	 */
	public Contrato(double cuota) {
		this.notificacion = true;
		this.cuotaInscripcion = cuota;
		fechaInicio = LocalDate.now();
	}

	/**
	 * @return si segun el contrato, el cliente quiere notificaciones de
	 *         subastas
	 */
	public boolean isNotificacion() {
		return notificacion;
	}

	/**
	 * @param notificacion
	 *            boolean para activar o desactivar las notificaciones de las
	 *            subastas
	 */
	public void setNotificacion(boolean notificacion) {
		this.notificacion = notificacion;
	}

	/**
	 * @return si se puede aplicar el descuento
	 */
	public boolean aplicarDescuento() {
		return !(this.isCaducado());
	}

	/**
	 * @return la cuota de inscripcion
	 */
	public double getCuotaInscripcion() {
		return cuotaInscripcion;
	}

	/**
	 * @param cuotaInscripcion
	 *            la cuota de inscripcion a asignar
	 */
	public void setCuotaInscripcion(double cuotaInscripcion) {
		this.cuotaInscripcion = cuotaInscripcion;
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
	 * @return si tiene inscripciones gratis
	 */
	public boolean tieneInscripcionGratis() {
		return !(this.isCaducado());
	}

	/**
	 * Este metodo se utilizara para aplicar inscripciones gratis
	 * @throws ExistenceException
	 * @throws ExistenceException
	 */
	public void aplicarInscripcionGratis() throws ExistenceException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMuuuu");
		return "";
	}

	/**
	 * @param euros
	 *            a acumular
	 */
	public void addAcumulados(double euros) {

	}

	/**
	 * comprueba si un contrato esta caducado
	 * 
	 * @return true si esta caducado, false si no
	 */
	public boolean isCaducado() {
		return LocalDate.now().isAfter(fechaInicio.plusYears(1));
	}

	/**
	 * @throws ContratoException
	 */
	public void renovarContrato() throws ContratoException {
		if (this.isCaducado())
			this.fechaInicio = LocalDate.now();
		else
			throw new ContratoException("Este contrato aun no esta caducado");
	}

}
