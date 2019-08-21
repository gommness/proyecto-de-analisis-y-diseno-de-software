package excepciones.anticuarioExcepciones;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Excepcion asociada a los articulos
 */
public class ArticuloException extends AnticuarioException {

	private static final long serialVersionUID = 1638917262545519320L;

	public ArticuloException(String msg) {
		super(msg);
	}

}
