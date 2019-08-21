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

public class ControlModificarClienteMenu extends Control {

	public ControlModificarClienteMenu(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//modificar cliente:
		ClienteModificar menu = (ClienteModificar) this.panel;
		Cliente cliente;
		ClienteMenu prevMenu = App.getClienteMenu();
		int row = prevMenu.getClientes().getSelectedRow();
		if(row != -1){
			cliente = this.app.findCliente((String)prevMenu.getClientes().getValueAt(row, 1),
					(String)prevMenu.getClientes().getValueAt(row, 0),
					"");
			if(cliente != null){
				menu.setCliente(cliente);
				menu.loadClienteData();
				((CardLayout)this.con.getLayout()).show(this.con, "ClienteModificar");
			}
		}
	}

}
