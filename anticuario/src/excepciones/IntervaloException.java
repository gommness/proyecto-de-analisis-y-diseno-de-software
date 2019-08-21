package excepciones;

import excepciones.anticuarioExcepciones.AnticuarioException;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Excepcion asociada a los intervalos
 */
public class IntervaloException extends AnticuarioException {

	private static final long serialVersionUID = 1638917262545519320L;

	public IntervaloException(String msg) {
		super(msg);
	}

}
