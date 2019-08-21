package gui.venderMenu.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import articulos.Articulo;
import gui.articulo.ArticuloMenu;
import gui.cliente.ClienteMenu;
import gui.control.Control;
import gui.venderMenu.VenderMenu;
import lector.Lector;
import persona.cliente.Cliente;

public class ControlBuscarCliente extends Control {

	public ControlBuscarCliente(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Cliente> la;
		VenderMenu a = (VenderMenu) panel;
		if (a.getOpcion1Cliente().isSelected()) {
			la = app.buscarCliente("", a.getSearchCliente().getText(), "");
		} else if (a.getOpcion2Cliente().isSelected()) {
			la = app.buscarCliente(a.getSearchCliente().getText(), "", "");
		}
		else {
			//la = app.getClientes();
			la = app.getClientes();
		}
		if (a.getModeloDatosCliente().getRowCount() > 0) {
		    for (int i = a.getModeloDatosCliente().getRowCount() - 1; i > -1; i--) {
		        a.getModeloDatosCliente().removeRow(i);
		    }
		}
		for (Cliente c : la) {
			Object[] fila = c.filaPersona();
			a.getModeloDatosCliente().addRow(fila);
		}
		
	}

}
