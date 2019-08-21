package gui.subasta.puja.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.cliente.ClienteMenu;
import gui.control.Control;
import gui.subasta.puja.PujaMenu;
import persona.cliente.Cliente;

public class ControlBuscarCliente_Puja extends Control {

	public ControlBuscarCliente_Puja(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String la = null;
		PujaMenu a = (PujaMenu) panel;
		if (a.getOpcion1().isSelected()) {
			la = app.findCliente("", a.getSearch().getText(), "").getNombre();
		} else if (a.getOpcion2().isSelected()) {
			la = app.findCliente(a.getSearch().getText(), "", "").getNombre();
		}
		if (a.getClientes().size() > 0) {
			for (int i = a.getClientes().size() - 1; i > -1; i--) {
				a.getClientes().remove(i);
			}
		}
		if (la != null)
			a.getClientes().addElement(la);
	}

}
