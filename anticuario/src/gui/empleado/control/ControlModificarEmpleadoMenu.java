package gui.empleado.control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.cliente.ClienteMenu;
import gui.cliente.ClienteModificar;
import gui.control.Control;
import gui.empleado.EmpleadoMenu;
import gui.empleado.EmpleadoModificar;
import gui.test.App;
import persona.cliente.Cliente;
import persona.usuario.Empleado;

public class ControlModificarEmpleadoMenu extends Control {

	public ControlModificarEmpleadoMenu(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// modificar cliente:
		EmpleadoModificar menu = (EmpleadoModificar) this.panel;
		Empleado empleado;
		EmpleadoMenu prevMenu = App.getEmpleadoMenu();
		int row = prevMenu.getEmpleados().getSelectedRow();
		if (row != -1) {
			empleado = this.app.findEmpleado((String) prevMenu.getEmpleados().getValueAt(row, 1),
					(String) prevMenu.getEmpleados().getValueAt(row, 0), "");
			if (empleado != null) {
				menu.setEmpleado(empleado);
				menu.loadEmpleadoData();
				((CardLayout) this.con.getLayout()).show(this.con, "EmpleadoModificar");
			}
		}
	}

}
