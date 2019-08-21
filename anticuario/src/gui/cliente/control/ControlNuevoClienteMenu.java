package gui.cliente.control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.cliente.ClienteMenu;
import gui.cliente.ClienteModificar;
import gui.control.Control;
import gui.test.App;
import persona.cliente.Cliente;

public class ControlNuevoClienteMenu extends Control {

	public ControlNuevoClienteMenu(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// crear cliente:
		Cliente cliente = new Cliente(null, null, null, 0, null, null);
		App.getClienteModificar().setCliente(cliente);
		App.getClienteModificar().loadClienteData();
		((CardLayout) this.con.getLayout()).show(this.con, "ClienteModificar");
	}

}
