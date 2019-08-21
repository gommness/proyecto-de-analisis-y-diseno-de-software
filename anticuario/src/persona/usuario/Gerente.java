package persona.usuario;

/**
 * @author Javier Gomez
 *
 */
public class Gerente extends Usuario {
	private static final long serialVersionUID = -6249027922052945369L;

	/**
	 * @param dni
	 *            el dni de la gerente
	 * @param nombre
	 *            el nombre de la gerente
	 * @param direccion
	 *            la direccion del domicilio de la gerente
	 * @param codigoPostal
	 *            el codigo postal del domicilio de la gerente
	 * @param telefono
	 *            el numero de telefono de la gerente
	 * @param mail
	 *            el correo electronico de la gerente
	 * @param nSeguridadSocial
	 *            el numero de la seguridad social de la gerente
	 */
	public Gerente(String dni, String nombre, String direccion, int codigoPostal, String telefono, String mail,
			int nSeguridadSocial) {
		super(dni, nombre, direccion, codigoPostal, telefono, mail, nSeguridadSocial);
	}

}
