package articulos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import excepciones.anticuarioExcepciones.ArticuloException;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Esta clase abstracta es la de articulos
 */
public abstract class Articulo implements Serializable {

	private static final long serialVersionUID = 4559919221612975741L;

	private static int cantidadArticulos = 0;
	private int id;
	private String descripcion;
	private String anoOrigen;
	private LocalDate fechaAquisicion;
	private double costeAdquisicion;
	private double precioObjetivo;
	private boolean vendido;
	private boolean disponible;

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
	 */
	public Articulo(String descripcion, String anoOrigen, LocalDate fechaAquisicion, double costeAdquisicion,
			double precioObjetivo, boolean vendido, boolean disponible) {
		this.descripcion = descripcion;
		this.anoOrigen = anoOrigen;
		this.fechaAquisicion = fechaAquisicion;
		this.costeAdquisicion = costeAdquisicion;
		this.precioObjetivo = precioObjetivo;
		this.vendido = vendido;
		this.disponible = disponible;
		Articulo.cantidadArticulos++;
		this.id = Articulo.cantidadArticulos;
	}

	/**
	 * Este metodo calculara el descuento de un atributo. En principio, es 0,
	 * pero se sobreescribira en una clase hija para calcularlo correctamente
	 * 
	 * @return el dinero de descuento si se trata de una menudencia, 0 si no lo
	 *         es
	 */
	public double calcularDescuento() {
		return 0;
	}

	/**
	 * 
	 * @return los gastos de envio si se trata de un voluminoso, 0 si no lo es
	 */
	public double calcularGastosEnvio() {
		return 0;
	}

	/**
	 * @return la descripcion del articulo
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            la descripcion a asignar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return el año de origen del articulo
	 */
	public String getAnoOrigen() {
		return anoOrigen;
	}

	/**
	 * @param anoOrigen
	 *            el año de origen a asignar
	 */
	public void setAnoOrigen(String anoOrigen) {
		this.anoOrigen = anoOrigen;
	}

	/**
	 * @return la fecha de adquisicion del articulo
	 */
	public LocalDate getFechaAquisicion() {
		return fechaAquisicion;
	}

	/**
	 * @param fechaAquisicion
	 *            la fechaAquisicion a asignar
	 */
	public void setFechaAquisicion(LocalDate fechaAquisicion) {
		this.fechaAquisicion = fechaAquisicion;
	}

	/**
	 * @return el coste de adquisicion del articulo
	 */
	public double getCosteAdquisicion() {
		return costeAdquisicion;
	}

	/**
	 * @param costeAdquisicion
	 *            el coste de adquisicion a asignar
	 */
	public void setCosteAdquisicion(double costeAdquisicion) {
		this.costeAdquisicion = costeAdquisicion;
	}

	/**
	 * @return el precio de venta
	 */
	public double getPrecioObjetivo() {
		return precioObjetivo;
	}

	/**
	 * @param precioObjetivo
	 *            el precio objetivo a asignar
	 */
	public void setPrecioObjetivo(double precioObjetivo) {
		this.precioObjetivo = precioObjetivo;
	}

	/**
	 * @return devuelve true si el articulo ha sido vendido. false, si no
	 */
	public boolean isVendido() {
		return vendido;
	}

	/**
	 * @param vendido
	 *            el booleano de vendido a asignar
	 */
	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	/**
	 * @return si el articulo esta disponible o en un lote
	 */
	public boolean isDisponible() {
		return disponible;
	}

	/**
	 * @param disponible
	 *            asigna si esta disponible
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	/**
	 * @return the cantidadArticulos
	 */
	public static int getCantidadArticulos() {
		return cantidadArticulos;
	}

	/**
	 * @param n
	 *            el numero de articulos
	 */
	public static void setCantidadArticulos(int n) {
		Articulo.cantidadArticulos = n;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Este metodo sirve para asegurarnos de que un articulo no se puede
	 * subastar. (Se sobreescribira en aquellas clases hijas en las que si se
	 * puedan subastar)
	 * 
	 * @throws ArticuloException
	 */
	public void subastar() throws ArticuloException {
		throw new ArticuloException("no se puede subastar esto");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMuuuu");
		return "" + this.descripcion;
	}
	public Object[] filaArticulo(){
		Object[] o = {this.id,this.descripcion,this.anoOrigen,this.fechaAquisicion,this.costeAdquisicion,this.precioObjetivo,this.vendido,this.disponible,
				""/*descuento*/,""/*peso*/,""/*volumen*/,""/*gastos envio*/,""/*autor*/,""/*certificado*/,""/*tipo*/,""/*subastable*/};
		return o;
		
	}

}