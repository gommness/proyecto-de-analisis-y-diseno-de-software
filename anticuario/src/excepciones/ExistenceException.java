package excepciones;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Excepcion asociada a la existencia de algo
 */
public class ExistenceException extends Exception {

	private static final long serialVersionUID = -3580292660302212232L;

	public ExistenceException(String msg) {
		super(msg);
	}
}
