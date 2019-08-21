package gui.cliente.control;

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
		ClienteMenu a = (ClienteMenu) panel;
		if (a.getOpcion1().isSelected()) {
			la = app.buscarCliente("", a.getSearch().getText(), "");
		} else if (a.getOpcion2().isSelected()) {
			la = app.buscarCliente(a.getSearch().getText(), "", "");
		}
		else {
			//la = app.getClientes();
			la = app.getClientes();
		}
		if (a.getModeloDatos().getRowCount() > 0) {
		    for (int i = a.getModeloDatos().getRowCount() - 1; i > -1; i--) {
		        a.getModeloDatos().removeRow(i);
		    }
		}
		for (Cliente c : la) {
			Object[] fila = c.filaPersona();
			a.getModeloDatos().addRow(fila);
		}
		
	}

}
