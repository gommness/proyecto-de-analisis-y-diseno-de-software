package ventas;

import articulos.voluminoso.Voluminoso;
import excepciones.anticuarioExcepciones.ArticuloException;
import excepciones.anticuarioExcepciones.VentaException;
import persona.cliente.Cliente;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de ventas a domicilio
 */
public class VentaDomicilio extends Venta {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3573951043405766112L;

	/**
	 * @param cliente
	 *            de la venta a domicilio
	 * @param voluminoso
	 *            de la venta a domicilio
	 * @throws ArticuloException
	 */
	public VentaDomicilio(Cliente cliente, Voluminoso voluminoso) throws ArticuloException {
		super(cliente, voluminoso);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ventas.Venta#calcularPrecio()
	 */
	@Override
	public double calcularPrecio() {
		double output;
		output = this.getArticulo().getPrecioObjetivo();
		output += this.getArticulo().calcularGastosEnvio();
		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ventas.Venta#vender()
	 */
	@Override
	public void vender() throws VentaException {
		double precioVenta;
		double precioCompra;
		if (this.isCobrado())
			throw new VentaException("esta venta ya se ha cobrado");
		precioVenta = this.calcularPrecio();
		precioCompra = this.getArticulo().getCosteAdquisicion();
		this.getArticulo().setVendido(true);
		this.getArticulo().setDisponible(false);
		this.setCobrado(true);
		this.setGanancia(precioVenta - precioCompra);
	}
}
