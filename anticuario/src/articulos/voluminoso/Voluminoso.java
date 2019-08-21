package articulos.voluminoso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import articulos.Articulo;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de articulos de tipo Voluminoso
 */
public class Voluminoso extends Articulo {

	private static final long serialVersionUID = -8765253865733057249L;
	private double peso;
	private Dimension dimensiones;
	private double gastosEnvio;

	/**
	 * @param descripcion
	 *            la descripcion del articulo
	 * @param ano
	 *            el a�o de origen del articulo
	 * @param fechaAdquisicion
	 *            la fecha en la que el anticuario adquirio el articulo
	 * @param coste
	 *            el coste de adquisicion del articulo
	 * @param precio
	 *            el precio de venta del articulo
	 * @param vendido
	 *            si el articulo ha sido vendido (true) o no (false)
	 * @param disponible
	 *            si el articulo esta disponible (true) o no (false)
	 * @param peso
	 *            el peso del articulo
	 * @param dimension
	 *            un objeto con las dimensiones del articulo
	 */
	public Voluminoso(String descripcion, String ano, LocalDate fechaAdquisicion, double coste, double precio,
			boolean vendido, boolean disponible, double peso, Dimension dimension) {
		super(descripcion, ano, fechaAdquisicion, coste, precio, vendido, disponible);
		this.peso = peso;
		this.dimensiones = dimension;
		if (dimensiones.getAlto() > 2 || dimensiones.getAncho() > 2 || dimensiones.getLargo() > 2) {
			this.gastosEnvio = 50;
		} else {
			if (this.peso <= 15) {
				this.gastosEnvio = 20;
			} else {
				int num = (int) Math.ceil((peso - 15) / 5);
				this.gastosEnvio = 20 + num * 2;
			}
		}
	}

	/**
	 * @param descripcion
	 *            la descripcion del articulo
	 * @param ano
	 *            el a�o de origen del articulo
	 * @param fechaAdquisicion
	 *            la fecha en la que el anticuario adquirio el articulo
	 * @param coste
	 *            el coste de adquisicion del articulo
	 * @param precio
	 *            el precio de venta del articulo
	 * @param vendido
	 *            si el articulo ha sido vendido (true) o no (false)
	 * @param disponible
	 *            si el articulo esta disponible (true) o no (false)
	 * @param peso
	 *            el peso del articulo
	 * @param alto
	 *            los cm de altura del articulo
	 * @param ancho
	 *            los cm de anchura del articulo
	 * @param largo
	 *            los cm de largura del articulo
	 */
	public Voluminoso(String descripcion, String ano, LocalDate fechaAdquisicion, double coste, double precio,
			boolean vendido, boolean disponible, double peso, double alto, double ancho, double largo) {
		this(descripcion, ano, fechaAdquisicion, coste, precio, vendido, disponible, peso,
				new Dimension(alto, ancho, largo));
	}

	/**
	 * @return los gastos de envio segun el algoritmo de sus dimensiones o peso
	 */
	@Override
	public double calcularGastosEnvio() {
		return this.gastosEnvio;
	}

	/**
	 * @return el peso del articulo voluminoso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 *            el peso a asignar
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * @return las dimensiones del voluminoso
	 */
	public Dimension getDimensiones() {
		return dimensiones;
	}

	/**
	 * @param dimensiones
	 *            las dimensiones a asignar
	 */
	public void setDimensiones(Dimension dimensiones) {
		this.dimensiones = dimensiones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see articulos.Articulo#toString()
	 */
	public String toString() {
		return super.toString() + " " + this.peso + " " + this.dimensiones;
	}
	@Override public Object[] filaArticulo(){
		Object[] o = super.filaArticulo();
		o[9] = this.peso;
		o[10] = "" + this.dimensiones.getAlto() + "x" + this.dimensiones.getLargo() + "x" + this.dimensiones.getAncho();
		o[11] = "" + this.gastosEnvio;
		return o;
	}
}
