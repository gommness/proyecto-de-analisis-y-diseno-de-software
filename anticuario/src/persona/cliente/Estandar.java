package persona.cliente;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase personas de tipo gerente
 */
public class Estandar extends Contrato {

	private static final long serialVersionUID = 8900865832955546801L;

	private static double cuota = 25;

	private double acumulados;
	private int inscripcionesGratis;

	/**
	 * los euros acumulados por defecto somienzan siendo 0
	 */
	public Estandar() {
		super(Estandar.cuota);
		this.acumulados = 0;
		this.inscripcionesGratis = 5;
	}

	/**
	 * @return la cantidad de euros acumulados en el contrato
	 */
	public double getAcumulados() {
		return acumulados;
	}

	/**
	 * @param acumulados
	 *            la cantidad de euros acumulados a asignar
	 */
	public void setAcumulados(double acumulados) {
		this.acumulados = acumulados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persona.Contrato#aplicarDescuento()
	 */
	public boolean aplicarDescuento() {
		if (this.acumulados >= 200)
			return true;
		else {
			return false;
		}
	}

	/**
	 * @return el numero de inscripciones gratis
	 */
	public int getInscripcionesGratis() {
		return inscripcionesGratis;
	}

	/**
	 * @param inscripcionesGratis
	 *            las inscripciones gratis a asignar
	 */
	public void setInscripcionesGratis(int inscripcionesGratis) {
		this.inscripcionesGratis = inscripcionesGratis;
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
	 * @see persona.Contrato#tieneInscripcionGratis()
	 */
	public boolean tieneInscripcionGratis() {
		return (this.inscripcionesGratis > 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persona.Contrato#aplicarInscripcionGratis()
	 */
	public void aplicarInscripcionGratis() {
		if (this.tieneInscripcionGratis()) {
			this.inscripcionesGratis--;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persona.Contrato#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "Estandar: " + this.acumulados + "€ acumulados y "
				+ this.inscripcionesGratis + "inscripciones gratis";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persona.Contrato#addAcumulados(double)
	 */
	public void addAcumulados(double euros) {
		this.acumulados += euros;
	}
}
