package gui.cliente.control;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.Aplicacion;
import excepciones.ExistenceException;
import gui.cliente.ClienteModificar;
import gui.control.Control;
import gui.empleado.EmpleadoModificar;
import gui.test.App;
import persona.cliente.Cliente;
import persona.cliente.Estandar;
import persona.cliente.Preferente;
import persona.usuario.Empleado;

public class ControlActualizar extends Control {

	public ControlActualizar(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ClienteModificar cli = (ClienteModificar) panel;
		Cliente cliente = cli.getCliente();
		cliente.setNombre(cli.getNombre().getText());
		cliente.setDni(cli.getDni().getText());
		cliente.setMail(cli.getMail().getText());
		cliente.setDireccion(cli.getDireccion().getText());
		try {
			cliente.setCodigoPostal(Integer.parseInt(cli.getPostal().getText()));
		} catch (NumberFormatException ex) {
			cliente.setCodigoPostal(-1);
		}
		cliente.setTelefono(cli.getTelefono().getText());
		if (((String) cli.getContrato().getSelectedItem()).equals("sin contrato")) {
			cliente.setContrato(null);
		} else if (((String) cli.getContrato().getSelectedItem()).equals("estandar")) {
			if (cliente.getContrato() == null)
				cliente.setContrato(new Estandar());
			else if (!(cliente.getContrato() instanceof Estandar))
				try {
					cliente.cambiarContrato();
				} catch (ExistenceException e1) {
				}
			cliente.getContrato().setNotificacion(cli.getNotif().isSelected());
		} else if (((String) cli.getContrato().getSelectedItem()).equals("preferente")) {
			if (cliente.getContrato() == null)
				cliente.setContrato(new Preferente());
			else if (!(cliente.getContrato() instanceof Preferente))
				try {
					cliente.cambiarContrato();
				} catch (ExistenceException e1) {
				}
			cliente.getContrato().setNotificacion(cli.getNotif().isSelected());
		}
		if (!app.getClientes().contains(cliente))
			app.getClientes().add(cliente);
		App.updateTables();
		JOptionPane.showMessageDialog(null, "cliente modificado correctamente");
		;
	}

}
