package gui.empleado.control;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;
import gui.empleado.EmpleadoModificar;
import gui.test.App;
import persona.usuario.Empleado;

public class ControlActualizar extends Control {

	public ControlActualizar(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EmpleadoModificar em = (EmpleadoModificar) panel;
		Empleado empleado = em.getEmpleado();
		System.out.println(em.getNombre().getText() + " " +em.getDni().getText());
		empleado.setNombre(em.getNombre().getText());
		empleado.setDni(em.getDni().getText());
		try {
			empleado.setnSeguridadSocial(Integer.parseInt(em.getSegSocial().getText()));
			empleado.setMail(em.getMail().getText());
			empleado.setCuenta(em.getCuenta().getText());
			empleado.setContrasena(em.getContrasenna().getText());
			empleado.setCodigoPostal(Integer.parseInt(em.getPostal().getText()));
			empleado.setDireccion(em.getDireccion().getText());
			empleado.setTelefono(em.getTelefono().getText());
		} catch (NumberFormatException ex) {
			empleado.setnSeguridadSocial(-1);
			empleado.setCodigoPostal(-1);
		}
		if (!app.getEmpleados().contains(em)) {
			app.getEmpleados().add(empleado);
		}
		App.updateTables();
		JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
		;
	}

}
