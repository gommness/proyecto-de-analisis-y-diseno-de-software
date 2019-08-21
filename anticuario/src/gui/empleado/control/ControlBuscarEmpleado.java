package gui.empleado.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import articulos.Articulo;
import gui.articulo.ArticuloMenu;
import gui.control.Control;
import gui.empleado.EmpleadoMenu;
import lector.Lector;
import persona.usuario.Empleado;

public class ControlBuscarEmpleado extends Control {

	public ControlBuscarEmpleado(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Empleado> le;
		EmpleadoMenu emp = (EmpleadoMenu) panel;
		if (emp.getOpcion1().isSelected()) {
			le = app.buscarEmpleado("", emp.getSearch().getText(), "");
		} else if (emp.getOpcion2().isSelected()) {
			le = app.buscarEmpleado(emp.getSearch().getText(),"","");

		} else {
			le = app.getEmpleados();
		}
		if (emp.getModeloDatos().getRowCount() > 0) {
			for (int i = emp.getModeloDatos().getRowCount() - 1; i > -1; i--) {
				emp.getModeloDatos().removeRow(i);
			}
		}
		for (Empleado em : le) {
			Object[] fila = em.filaPersona();
			emp.getModeloDatos().addRow(fila);
		}

	}

}
