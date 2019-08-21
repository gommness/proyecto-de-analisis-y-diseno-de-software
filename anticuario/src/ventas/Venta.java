package ventas;

import java.io.Serializable;

/**
 * el metodo vender deberia lanzar una excepcion?
 */

import java.time.LocalDate;

import articulos.Articulo;
import excepciones.anticuarioExcepciones.ArticuloException;
import excepciones.anticuarioExcepciones.VentaException;
import persona.cliente.Cliente;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de ventas
 */
public class Venta implements Serializable {
	private static final long serialVersionUID = 3343278632489504783L;
	private static int nVentas;
	private int id;
	private LocalDate date;
	private double ganancia;
	private boolean cobrado;
	private Cliente cliente;
	private Articulo articulo;

	/**
	 * @param cliente
	 *            que participa en la venta
	 * @param articulo
	 *            que se vende
	 * @throws ArticuloException
	 */
	public Venta(Cliente cliente, Articulo articulo) throws ArticuloException {
		if (!articulo.isDisponible() || articulo.isVendido())
			throw new ArticuloException("no se puede vender este articulo");
		this.cliente = cliente;
		this.articulo = articulo;
		articulo.setDisponible(false);
		this.date = LocalDate.now();
		this.ganancia = 0;
		this.cobrado = false;
		Venta.nVentas++;
		this.id = Venta.nVentas;
	}

	/**
	 * @return el id de la venta
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return la fecha de la venta
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return la ganancia de la venta
	 */
	public double getGanancia() {
		return ganancia;
	}

	/**
	 * @param ganancia
	 *            a asignar
	 */
	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	/**
	 * @return si ha sido cobrada la venta
	 */
	public boolean isCobrado() {
		return cobrado;
	}

	/**
	 * @param cobrado
	 *            true si fue cobrada, false si no
	 */
	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}

	/**
	 * @param articulo
	 *            the articulo to set
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * calcula el precio de la venta, aplicando descuentos de ser posible
	 * 
	 * @return el precio de la venta
	 */
	public double calcularPrecio() {
		double output;
		output = this.articulo.getPrecioObjetivo();
		if (!this.cliente.aplicarDescuento())
			return output;
		output -= this.articulo.calcularDescuento();
		return output;
	}

	/**
	 * finaliza la venta, marcandola como cobrada y el articulo como vendido
	 * @throws VentaException
	 */
	public void vender() throws VentaException {
		double precioVenta;
		double precioCompra;
		if (this.isCobrado())
			throw new VentaException("esta venta ya se ha cobrado");
		precioVenta = this.calcularPrecio();
		precioCompra = this.articulo.getCosteAdquisicion();
		this.articulo.setVendido(true);
		this.articulo.setDisponible(false);
		this.setCobrado(true);
		this.setGanancia(precioVenta - precioCompra);
		if (this.cliente.getContrato() != null)
			this.cliente.getContrato().addAcumulados(this.calcularPrecio());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.id + " " + this.date + " " + this.ganancia + " " + this.cobrado + " " + this.cliente + " "
				+ this.articulo;
	}

	/**
	 * @return el numero total de ventas
	 */
	public static int getNVentas() {
		return Venta.nVentas;
	}

	/**
	 * @param n
	 *            el numero total de ventas a asignar
	 */
	public static void setNVentas(int n) {
		Venta.nVentas = n;
	}
	public Object[] filaGanancia(){
		Object[] o={id,"Venta",ganancia,cliente.getNombre(),articulo.getDescripcion(),date};
		return o;
	}
}
