package persona.usuario;

import java.util.ArrayList;
import java.util.Arrays;

import persona.Persona;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de personas de tipo usuario
 */
public abstract class Usuario extends Persona {
	private static final long serialVersionUID = -8526656825644684166L;
	private String cuenta;
	private String contrasena;
	private int nSeguridadSocial;
	private static int nUsuarios = 0;

	/**
	 * @param dni
	 *            el dni de la persona
	 * @param nombre
	 *            el nombre de la persona
	 * @param direccion
	 *            la direccion de la persona
	 * @param codigoPostal
	 *            el codigo postal de la persona
	 * @param telefono
	 *            el numero de telefono de la persona
	 * @param mail
	 *            el correo electronico de la persona
	 * @param nSeguridadSocial
	 *            el numero de seguridad social de la persona
	 */
	public Usuario(String dni, String nombre, String direccion, int codigoPostal, String telefono, String mail,
			int nSeguridadSocial) {
		super(dni, nombre, direccion, codigoPostal, telefono, mail);
		this.nSeguridadSocial = nSeguridadSocial;
		Usuario.nUsuarios++;
		this.cuenta = Usuario.crearCuenta(nombre, dni);
		this.contrasena = Usuario.crearContrasena(nombre, dni);
	}

	/**
	 * @param nombre
	 *            el nombre del usuario
	 * @param dni
	 *            el dni del usuario
	 * @return una cuenta valida con formato "aaaa-Antic<<n>>-DDDD" siendo aaaa
	 *         las ultimas letras de su nombre (empezando por la numero 3), <
	 *         <n>> el numero de usuario y DDDD las cuatro ultimas letras de su
	 *         dni
	 */
	private static String crearCuenta(String nombre, String dni) {
		/*
		 * String output; if (nombre.length() >= 3) output =
		 * nombre.substring(2).toUpperCase(); else output = nombre; output +=
		 * "-Antic" + Usuario.nUsuarios + "-"; output += dni.substring(5);
		 */
		return nombre;
	}

	/**
	 * @param nombre
	 *            el nombre del usuario
	 * @param dni
	 *            el dni del usuario
	 * @return una contrase�a con formato "ie-nnn-A-MMM" siendo nnn un numero
	 *         del 0 al 999, A la primera letra de su nombre y MMM los tres
	 *         ultimos caracteres de su dni
	 */
	private static String crearContrasena(String nombre, String dni) {
		/*
		 * String output; Random gen = new Random(Usuario.nUsuarios); output =
		 * "ie-" + (gen.nextInt() % 1000) + nombre.substring(0, 0); if
		 * (dni.length() >= 3) output += dni.substring(dni.length() - 3,
		 * dni.length() - 1); else output += dni; return output;
		 */
		return dni;
	}

	/**
	 * @return el nombre de la cuenta del usuario
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @return el numero total de usuarios
	 */
	public static int getNUsuarios() {
		return Usuario.nUsuarios;
	}

	/**
	 * @param n
	 *            el numero total de usuarios a asignar
	 */
	public static void setNUsuarios(int n) {
		Usuario.nUsuarios = n;
	}

	/**
	 * @param cuenta
	 *            el nombre de la cuenta asignar
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return la contraseña de la cuenta de usuario
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena
	 *            la contraseña a asignar
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return el numero de la seguridad social del usuario
	 */
	public int getnSeguridadSocial() {
		return nSeguridadSocial;
	}

	/**
	 * @param nSeguridadSocial
	 *            el numero de la seguridad social a asignar
	 */
	public void setnSeguridadSocial(int nSeguridadSocial) {
		this.nSeguridadSocial = nSeguridadSocial;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persona.Persona#toString()
	 */
	public String toString() {
		return super.toString() + " " + this.cuenta + " " + this.contrasena + " " + this.nSeguridadSocial;
	}

	@Override
	public Object[] filaPersona() {
		ArrayList<Object> a = new ArrayList<>(Arrays.asList(super.filaPersona()));
		a.add(cuenta);
		a.add(contrasena);
		a.add(nSeguridadSocial);
		Object[] o = a.stream().toArray(Object[]::new);
		return o;
	}
}
