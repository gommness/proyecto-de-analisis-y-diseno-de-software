package gui.cliente;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.Constraint;
import gui.ReturnButton;
import gui.test.App;
import persona.cliente.Cliente;
import persona.usuario.Empleado;

public class ClienteModificar extends JPanel {

	public ReturnButton getRetButton() {
		return retButton;
	}

	private static final long serialVersionUID = -3245409336726403574L;

	private ReturnButton retButton = App.getRetButton().copy();
	private JTextField nombre;
	private JTextField dni;
	private JTextField mail;
	private JTextField postal;
	private JTextField direccion;
	private JTextField telefono;
	private JButton actualizar;
	private Cliente cliente = null;
	private JLabel actualNombre = null;
	private JLabel actualDni = null;
	private JLabel actualMail = null;
	private JLabel actualDireccion = null;
	private JLabel actualPostal = null;
	private JLabel actualTelefono = null;
	private JPanel data;
	private JPanel fields;
	private JLabel labelTelefono;
	private JLabel labelPostal;
	private JLabel labelDireccion;
	private JLabel labelMail;
	private JLabel labelDni;
	private JLabel labelNombre;
	private JComboBox contrato;
	private JCheckBox notif;
	private JCheckBox actualNotif;

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void loadClienteData() {
		data.removeAll();
		actualNotif = new JCheckBox("Notificaciones");
		actualNotif.setEnabled(false);

		if (cliente != null) {
			actualNombre = new JLabel(this.cliente.getNombre());
			actualDni = new JLabel(this.cliente.getDni());
			actualMail = new JLabel(this.cliente.getMail());
			actualPostal = new JLabel("" + this.cliente.getCodigoPostal());
			actualDireccion = new JLabel(this.cliente.getDireccion());
			actualTelefono = new JLabel(this.cliente.getTelefono());
			if (this.cliente.getContrato() != null) {
				actualNotif.setSelected(this.cliente.getContrato().isNotificacion());
				notif.setSelected(this.cliente.getContrato().isNotificacion());
			} else {
				actualNotif.setSelected(false);
				notif.setSelected(false);
			}
		} else {
			actualNombre = new JLabel("");
			actualDni = new JLabel("");
			actualMail = new JLabel("");
			actualPostal = new JLabel("");
			actualDireccion = new JLabel("");
			actualTelefono = new JLabel("");
			actualNotif.setSelected(false);
			notif.setSelected(false);
		}

		Constraint c = new Constraint();
		c.setIpad(0, 7);
		c.alignTopLeft();
		c.setPos(0, 0);
		data.add(labelNombre, c);
		c.setPos(0, 1);
		data.add(labelDni, c);
		c.setPos(0, 2);
		data.add(labelMail, c);
		c.setPos(0, 3);
		data.add(labelDireccion, c);
		c.setPos(0, 4);
		data.add(labelPostal, c);
		c.setPos(0, 5);
		data.add(labelTelefono, c);
		c.setPos(1, 0);
		data.add(actualNombre, c);
		c.setPos(1, 1);
		data.add(actualDni, c);
		c.setPos(1, 2);
		data.add(actualMail, c);
		c.setPos(1, 3);
		data.add(actualDireccion, c);
		c.setPos(1, 4);
		data.add(actualPostal, c);
		c.setPos(1, 5);
		data.add(actualTelefono, c);
		c.setPos(0, 6);
		data.add(actualNotif,c);
	}

	public JComboBox getContrato() {
		return contrato;
	}

	public ClienteModificar() {

		notif = new JCheckBox("Notificaciones");
		fields = new JPanel();// los campos a rellenar
		data = new JPanel();// los campos con los datos actuales
		this.setLayout(new GridBagLayout());
		fields.setLayout(new GridBagLayout());
		data.setLayout(new GridBagLayout());
		Constraint c = new Constraint();

		String[] aux = { "sin contrato", "estandar", "preferente" };
		contrato = new JComboBox(aux);
		// creamos los labels para indicar que es cada campo
		labelNombre = new JLabel("nombre: ");
		labelDni = new JLabel("dni: ");
		labelMail = new JLabel("mail: ");
		labelDireccion = new JLabel("direccion: ");
		labelPostal = new JLabel("codigo postal: ");
		labelTelefono = new JLabel("telefono: ");
		JLabel labelNombre2 = new JLabel("nombre: ");
		JLabel labelDni2 = new JLabel("dni: ");
		JLabel labelMail2 = new JLabel("mail: ");
		JLabel labelDireccion2 = new JLabel("direccion: ");
		JLabel labelPostal2 = new JLabel("codigo postal: ");
		JLabel labelTelefono2 = new JLabel("telefono: ");
		// si hay un empleado cargado en el panel, carga sus datos
		this.loadClienteData();
		// prepara los JTextFields para que se pueda escribir en ellos
		this.nombre = new JTextField(30);
		this.dni = new JTextField(9);
		this.mail = new JTextField(30);
		this.postal = new JTextField(6);
		this.direccion = new JTextField(30);
		this.telefono = new JTextField(15);
		this.actualizar = new JButton("actualizar datos");

		// procedemos a meter los datos!!
		// primero en el panel data

		;
		// ahora en el panel fields
		c.setInsets(3, 0, 0, 3);
		c.setPos(0, 0);
		fields.add(labelNombre2, c);
		c.setPos(0, 1);
		fields.add(labelDni2, c);
		c.setPos(0, 2);
		fields.add(labelMail2, c);
		c.setPos(0, 3);
		fields.add(labelDireccion2, c);
		c.setPos(0, 4);
		fields.add(labelPostal2, c);
		c.setPos(0, 5);
		fields.add(labelTelefono2, c);
		// text fields
		c.setPos(1, 0);
		c.alignLeft();
		fields.add(nombre, c);
		c.setPos(1, 1);
		fields.add(dni, c);
		c.setPos(1, 2);
		fields.add(mail, c);
		c.setPos(1, 3);
		fields.add(direccion, c);
		c.setPos(1, 4);
		fields.add(postal, c);
		c.setPos(1, 5);
		fields.add(telefono, c);
		c.setPos(1, 6);
		fields.add(contrato, c);
		c.setPos(0, 7);
		fields.add(notif, c);
		c.allFill();
		c.setArea(2, 3);
		c.setPos(0, 8);
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

	public JCheckBox getNotif() {
		return notif;
	}

	public void setControlador(ActionListener c) {
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
	 * @return the mail
	 */
	public JTextField getMail() {
		return mail;
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
	public Cliente getCliente() {
		return cliente;
	}

	public void updateTables() {
		if (this.cliente != null) {
			this.actualTelefono.setText(this.cliente.getTelefono());
			this.actualMail.setText(this.cliente.getMail());
			this.actualDni.setText(this.cliente.getDni());
			this.actualNombre.setText(this.cliente.getNombre());
			this.actualPostal.setText("" + this.cliente.getCodigoPostal());
			this.actualDireccion.setText(this.cliente.getDireccion());
		}
	}

}
