package subasta;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import persona.cliente.Cliente;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de tipo puja de subasta
 */
public class Puja implements Serializable {
	private static final long serialVersionUID = 8655004306848394169L;
	private double valor;
	private LocalDate fecha;
	private Cliente cliente;

	/**
	 * @param valor
	 *            el valor de la puja a crear
	 * @param cliente
	 *            el cliente que ha hecho la puja
	 */
	public Puja(double valor, Cliente cliente) {
		this.fecha = LocalDate.now();
		this.valor = valor;
		this.cliente = cliente;
	}

	/**
	 * @return el valor de la puja
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            el valor a asignarle a la puja
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @return la fecha en la que se creo la puja
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @return el cliente que realizo la puja
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMuuuu");
		return "La puja es esta:\n" + this.cliente + " " + this.fecha.format(dtf) + " " + this.valor;
	}
}
