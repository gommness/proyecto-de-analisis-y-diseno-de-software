package gui.subasta.lote.control;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import articulos.Articulo;
import gui.control.Control;
import gui.subasta.lote.CrearLotePanel;

public class ControlAnadir extends Control {

	public ControlAnadir(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CrearLotePanel c = (CrearLotePanel) panel;
		
		String nombre = (String)c.getListArticulos().getSelectedValue();
		/*Articulo a=app.findArticulo(0, 0, null, nombre);*/
		c.getActuales().addElement(nombre);
		c.getArticulos().removeElement(nombre);
	}

}
