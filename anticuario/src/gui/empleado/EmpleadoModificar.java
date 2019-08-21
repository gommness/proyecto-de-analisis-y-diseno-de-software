package gui.empleado;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.Constraint;
import gui.ReturnButton;
import gui.test.App;
import persona.usuario.Empleado;

public class EmpleadoModificar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3245409336726403574L;

	private ReturnButton retButton = App.getRetButton().copy();
	private JTextField nombre;
	private JTextField dni;
	private JTextField segSocial;
	private JTextField mail;
	private JTextField cuenta;
	public ReturnButton getRetButton() {
		return retButton;
	}

	public void setRetButton(ReturnButton retButton) {
		this.retButton = retButton;
	}

	private JTextField contrasenna;
	private JTextField postal;
	private JTextField direccion;
	private JTextField telefono;
	private JButton actualizar;
	private Empleado empleado = null;
	private JLabel actualNombre = null;
	private JLabel actualDni = null;
	private JLabel actualSegSocial = null;
	private JLabel actualMail = null;
	private JLabel actualCuenta = null;
	private JLabel actualContrasenna = null;
	private JLabel actualDireccion = null;
	private JLabel actualPostal = null;
	private JLabel actualTelefono = null;
	private JPanel data;
	private JLabel labelNombre = new JLabel("nombre: ");
	private JLabel labelDni = new JLabel("dni: ");
	private JLabel labelSegSocial = new JLabel("seg. social: ");
	private JLabel labelMail = new JLabel("mail: ");
	private JLabel labelCuenta = new JLabel("cuenta: ");
	private JLabel labelContrasenna = new JLabel("contrasenna: ");
	private JLabel labelDireccion = new JLabel("direccion: ");
	private JLabel labelPostal = new JLabel("codigo postal: ");
	private JLabel labelTelefono = new JLabel("telefono: ");

	public void loadEmpleadoData() {
		Constraint c = new Constraint();
		data.removeAll();
		if (empleado != null) {
			actualNombre = new JLabel(this.empleado.getNombre());
			actualDni = new JLabel(this.empleado.getDni());
			actualSegSocial = new JLabel("" + this.empleado.getnSeguridadSocial());
			actualMail = new JLabel(this.empleado.getMail());
			actualCuenta = new JLabel(this.empleado.getCuenta());
			actualContrasenna = new JLabel(this.empleado.getContrasena());
			actualPostal = new JLabel("" + this.empleado.getCodigoPostal());
			actualDireccion = new JLabel(this.empleado.getDireccion());
			actualTelefono = new JLabel(this.empleado.getTelefono());
			c.setPos(1, 0);
			data.add(actualNombre, c);
			c.setPos(1, 1);
			data.add(actualDni, c);
			c.setPos(1, 2);
			data.add(actualSegSocial, c);
			c.setPos(1, 3);
			data.add(actualMail, c);
			c.setPos(1, 4);
			data.add(actualCuenta, c);
			c.setPos(1, 5);
			data.add(actualContrasenna, c);
			c.setPos(1, 6);
			data.add(actualDireccion, c);
			c.setPos(1, 7);
			data.add(actualPostal, c);
			c.setPos(1, 8);
			data.add(actualTelefono, c);
		}
		c.setIpad(0, 7);
		c.alignTopLeft();
		c.setPos(0, 0);
		data.add(labelNombre, c);
		c.setPos(0, 1);
		data.add(labelDni, c);
		c.setPos(0, 2);
		data.add(labelSegSocial, c);
		c.setPos(0, 3);
		data.add(labelMail, c);
		c.setPos(0, 4);
		data.add(labelCuenta, c);
		c.setPos(0, 5);
		data.add(labelContrasenna, c);
		c.setPos(0, 6);
		data.add(labelDireccion, c);
		c.setPos(0, 7);
		data.add(labelPostal, c);
		c.setPos(0, 8);
		data.add(labelTelefono, c);
		
	}
	
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public EmpleadoModificar() {

		/*
		 * BORRAR ESTA LINEA DE CODIGO, SOLO PARA PROBAR SI PINTA LAS COSAS BIEN
		 */
		this.empleado = new Empleado("81549616U", "homer", "calle UAM", 204385, "23452345", "homer@springfield.com",
				908757);

		JPanel fields = new JPanel();// los campos a rellenar
		data = new JPanel();// los campos con los datos actuales
		this.setLayout(new GridBagLayout());
		fields.setLayout(new GridBagLayout());
		data.setLayout(new GridBagLayout());
		Constraint c = new Constraint();

		// creamos los labels para indicar que es cada campo
		JLabel labelNombre2 = new JLabel("nombre: ");
		JLabel labelDni2 = new JLabel("dni: ");
		JLabel labelSegSocial2 = new JLabel("seg. social: ");
		JLabel labelMail2 = new JLabel("mail: ");
		JLabel labelCuenta2 = new JLabel("cuenta: ");
		JLabel labelContrasenna2 = new JLabel("contrasenna: ");
		JLabel labelDireccion2 = new JLabel("direccion: ");
		JLabel labelPostal2 = new JLabel("codigo postal: ");
		JLabel labelTelefono2 = new JLabel("telefono: ");
		// si hay un empleado cargado en el panel, carga sus datos
		// prepara los JTextFields para que se pueda escribir en ellos
		this.nombre = new JTextField(30);
		this.dni = new JTextField(9);
		this.segSocial = new JTextField(15);
		this.mail = new JTextField(30);
		this.cuenta = new JTextField(20);
		this.contrasenna = new JTextField(15);
		this.postal = new JTextField(6);
		this.direccion = new JTextField(30);
		this.telefono = new JTextField(15);
		this.actualizar = new JButton("actualizar datos");

		// procedemos a meter los datos!!
		// primero en el panel data

		this.loadEmpleadoData();
		// ahora en el panel fields
		c.setInsets(3, 0, 0, 3);
		c.setPos(0, 0);
		fields.add(labelNombre2, c);
		c.setPos(0, 1);
		fields.add(labelDni2, c);
		c.setPos(0, 2);
		fields.add(labelSegSocial2, c);
		c.setPos(0, 3);
		fields.add(labelMail2, c);
		c.setPos(0, 4);
		fields.add(labelCuenta2, c);
		c.setPos(0, 5);
		fields.add(labelContrasenna2, c);
		c.setPos(0, 6);
		fields.add(labelDireccion2, c);
		c.setPos(0, 7);
		fields.add(labelPostal2, c);
		c.setPos(0, 8);
		fields.add(labelTelefono2, c);
		// text fields
		c.setPos(1, 0);
		c.alignLeft();
		fields.add(nombre, c);
		c.setPos(1, 1);
		fields.add(dni, c);
		c.setPos(1, 2);
		fields.add(segSocial, c);
		c.setPos(1, 3);
		fields.add(mail, c);
		c.setPos(1, 4);
		fields.add(cuenta, c);
		c.setPos(1, 5);
		fields.add(contrasenna, c);
		c.setPos(1, 6);
		fields.add(direccion, c);
		c.setPos(1, 7);
		fields.add(postal, c);
		c.setPos(1, 8);
		fields.add(telefono, c);
		c.allFill();
		c.setArea(2, 3);
		c.setPos(0, 9);
		fields.add(actualizar, c);

		c.setPos(0, 0);
		c.setWidth(2);
		c.hFill();
		c.alignTopLeft();
		c.setWeight(0.3, 0);
		this.add(data, c);
		c.alignRight();
		c.setWidth(3);
		c.setPos(2, 0);
		c.setWeight(1, 0);
		this.add(fields, c);
		c.setWeight(0.1, 0.1);
		c.noFill();
		c.setWidth(1);
		c.alignLeft();
		c.setPos(0, 1);
		this.add(retButton, c);
		this.setSize(1080, 720);

	}
	public void setControlador(ActionListener c){
		this.actualizar.addActionListener(c);
	}
	/**
	 * @return the nombre
	 */
	public JTextField getNombre() {
		return nombre;
	}

	/**
	 * @return the dni
	 */
	public JTextField getDni() {
		return dni;
	}

	/**
	 * @return the segSocial
	 */
	public JTextField getSegSocial() {
		return segSocial;
	}

	/**
	 * @return the mail
	 */
	public JTextField getMail() {
		return mail;
	}

	/**
	 * @return the cuenta
	 */
	public JTextField getCuenta() {
		return cuenta;
	}

	/**
	 * @return the contrasenna
	 */
	public JTextField getContrasenna() {
		return contrasenna;
	}

	/**
	 * @return the postal
	 */
	public JTextField getPostal() {
		return postal;
	}

	/**
	 * @return the direccion
	 */
	public JTextField getDireccion() {
		return direccion;
	}

	/**
	 * @return the telefono
	 */
	public JTextField getTelefono() {
		return telefono;
	}

	/**
	 * @return the actualizar
	 */
	public JButton getActualizar() {
		return actualizar;
	}

	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

}
