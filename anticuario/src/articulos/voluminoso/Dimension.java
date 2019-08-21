package articulos.voluminoso;

import java.io.Serializable;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Esta clase Dimension guarda las dimensiones de los voluminosos
 */
public class Dimension implements Serializable {
	private static final long serialVersionUID = -1024617623222272705L;

	private double alto;
	private double ancho;
	private double largo;

	/**
	 * @param alto
	 *            alto a asignar
	 * @param ancho
	 *            ancho a asignar
	 * @param largo
	 *            largo a asignar
	 */
	public Dimension(double alto, double ancho, double largo) {
		super();
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
	}

	/**
	 * @return el alto de la dimension
	 */
	public double getAlto() {
		return alto;
	}

	/**
	 * @param alto
	 *            el alto a asignar
	 */
	public void setAlto(double alto) {
		this.alto = alto;
	}

	/**
	 * @return el ancho de la dimension
	 */
	public double getAncho() {
		return ancho;
	}

	/**
	 * @param ancho
	 *            el ancho a asignar
	 */
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return el largo de la dimension
	 */
	public double getLargo() {
		return largo;
	}

	/**
	 * @param largo
	 *            el largo a asignar
	 */
	public void setLargo(double largo) {
		this.largo = largo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.alto + " " + this.ancho + " " + this.largo;
	}

}
