package excepciones.anticuarioExcepciones;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Excepcion asociada a los lotes
 */
public class LoteException extends AnticuarioException {

	private static final long serialVersionUID = 1638917262545519320L;

	public LoteException(String msg) {
		super(msg);
	}

}
