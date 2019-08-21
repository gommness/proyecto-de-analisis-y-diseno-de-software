package excepciones.anticuarioExcepciones;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Excepcion asociada a las ventas
 */
public class VentaException extends AnticuarioException {

	private static final long serialVersionUID = 1638917262545519320L;

	public VentaException(String msg) {
		super(msg);
	}

}
