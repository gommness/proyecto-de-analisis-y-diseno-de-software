package persona.cliente;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de contratos de tipo preferente
 */
public class Preferente extends Contrato {
	private static final long serialVersionUID = 7533478976274835817L;
	private static double cuota = 100;

	public Preferente() {
		super(Preferente.cuota);
	}

	/**
	 * @return the cuota
	 */
	public static double getCuota() {
		return cuota;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persona.Contrato#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "Preferente";
	}
}
