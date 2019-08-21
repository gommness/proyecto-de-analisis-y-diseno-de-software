package persona;

import java.io.Serializable;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase de personas
 */
public abstract class Persona implements Serializable {
	private static final long serialVersionUID = 7238132347253246819L;
	private String dni;
	private String nombre;
	private String direccion;
	private int codigoPostal;
	private String telefono;
	private String mail;

	/**
	 * @param dni
	 *            el dni de la persona
	 * @param nombre
	 *            el nombre de la persona
	 * @param direccion
	 *            la direccion del domicilio de la persona
	 * @param codigoPostal
	 *            el codigo postal del domicilio de la persona
	 * @param telefono
	 *            el telefono de la persona
	 * @param mail
	 *            el correo electronico de la persona
	 */
	public Persona(String dni, String nombre, String direccion, int codigoPostal, String telefono, String mail) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.mail = mail;
	}

	/**
	 * @return el dni de la persona
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            el dni a asignar
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return el nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            el nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return la direccion del domicilio de la persona
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            la direccion a asignar
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return el codigo postal
	 */
	public int getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal
	 *            el codigo postal a asignar
	 */
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return el telefono de la persona
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            el numero de telefono a asignar
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return el correo electronico de la persona
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            el correo electronico a asignar
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Persona)
			return this.equals((Persona) o);
		return false;
	}

	/**
	 * @param c
	 *            la persona con la se compara igualdad
	 * @return true en caso de que sea igual, false si no lo es
	 */
	public boolean equals(Persona c) {
		return (this.getDni().compareToIgnoreCase(c.getDni()) == 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.dni + " " + this.nombre + " " + this.direccion + " " + this.codigoPostal + " " + this.telefono + " "
				+ this.mail;
	}
	public Object[] filaPersona() {
		Object[] o={this.nombre,this.dni,direccion,codigoPostal,telefono,mail};
		return o;
	}
}
