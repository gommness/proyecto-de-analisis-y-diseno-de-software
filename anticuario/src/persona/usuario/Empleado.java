package persona.usuario;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de personas de tipo empleado
 */
public class Empleado extends Usuario {

	private static final long serialVersionUID = 743362463365366331L;

	/**
	 * @param dni
	 *            el dni del usuario
	 * @param nombre
	 *            el nombre del usuario
	 * @param direccion
	 *            la direccion del usuario
	 * @param codigoPostal
	 *            el codigo postal del usuario
	 * @param telefono
	 *            el numero de telefono del usuario
	 * @param mail
	 *            la direccion de correo electronico del usuario
	 * @param nSeguridadSocial
	 *            el numero de la seguridad social del usuario
	 */
	public Empleado(String dni, String nombre, String direccion, int codigoPostal, String telefono, String mail,
			int nSeguridadSocial) {
		super(dni, nombre, direccion, codigoPostal, telefono, mail, nSeguridadSocial);
	}



}
