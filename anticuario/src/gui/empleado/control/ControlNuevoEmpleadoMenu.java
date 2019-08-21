package gui.empleado.control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;
import gui.test.App;
import persona.cliente.Cliente;
import persona.usuario.Empleado;

public class ControlNuevoEmpleadoMenu extends Control {

	public ControlNuevoEmpleadoMenu(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// crear cliente:
		Empleado empleado = new Empleado(null, null, null, 0, null, null, 0);
		App.getEmpleadoModificar().setEmpleado(empleado);
		App.getEmpleadoModificar().loadEmpleadoData();
		((CardLayout) this.con.getLayout()).show(this.con, "EmpleadoModificar");
	}

}
