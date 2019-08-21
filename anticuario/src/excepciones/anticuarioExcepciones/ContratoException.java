/**
 * 
 */
package excepciones.anticuarioExcepciones;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Excepcion asociada a los contratos
 */
public class ContratoException extends Exception {

	/**
		 * 
		 */
	private static final long serialVersionUID = -1796050350646210250L;

	/**
	 * @param msg el mensaje de error
	 */
	public ContratoException(String msg) {
		super(msg);
	}

}
