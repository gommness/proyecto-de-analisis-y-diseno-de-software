package gui.subasta.lote.control;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;
import gui.subasta.lote.CrearLotePanel;

public class ControlDescartar extends Control {

	public ControlDescartar(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CrearLotePanel c = (CrearLotePanel) panel;
		
		String nombre = (String)c.getListActuales().getSelectedValue();
		/*Articulo a=app.findArticulo(0, 0, null, nombre);*/
		c.getArticulos().addElement(nombre);
		c.getActuales().removeElement(nombre);
	}

}
