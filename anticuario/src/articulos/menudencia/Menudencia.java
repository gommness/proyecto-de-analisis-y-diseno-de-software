package articulos.menudencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import articulos.Articulo;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Esta clase es de articulos de tipo menudencia
 */
public class Menudencia extends Articulo {

	private static final long serialVersionUID = 261080178604277720L;
	private double descuento;

	/**
	 * @param descripcion
	 *            descripcion del articulo
	 * @param anoOrigen
	 *            año de origen del articulo
	 * @param fechaAquisicion
	 *            fecha de compra del articulo
	 * @param costeAdquisicion
	 *            coste de compra del articulo
	 * @param precioObjetivo
	 *            precio de venta del articulo
	 * @param vendido
	 *            indica si el articulo ha sido vendido
	 * @param disponible
	 *            indica si el articulo esta disponible o en un lote
	 * @param descuento
	 *            el descuento que tiene asociado la menudencia
	 */
	public Menudencia(String descripcion, String anoOrigen, LocalDate fechaAquisicion, double costeAdquisicion,
			double precioObjetivo, boolean vendido, boolean disponible, double descuento) {
		super(descripcion, anoOrigen, fechaAquisicion, costeAdquisicion, precioObjetivo, vendido, disponible);
		this.setDescuento(descuento);
	}

	/**
	 * @return el descuento de la menudencia
	 */
	@Override
	public double calcularDescuento() {
		return this.getPrecioObjetivo() * this.getDescuento() / 100;
	}

	/**
	 * @return el descuento del articulo
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * @param descuento
	 *            el descuento a asignar
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see articulos.Articulo#toString()
	 */
	public String toString() {
		return super.toString() + " " + this.descuento;
	}
	@Override public Object[] filaArticulo(){
		Object[] o = super.filaArticulo();
		o[8] = this.descuento+"%";
		return o;
	}
}
