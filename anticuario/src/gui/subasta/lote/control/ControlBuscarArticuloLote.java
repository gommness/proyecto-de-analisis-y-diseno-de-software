package gui.subasta.lote.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import articulos.Articulo;
import gui.articulo.ArticuloMenu;
import gui.control.Control;
import gui.subasta.lote.CrearLotePanel;
import lector.Lector;

public class ControlBuscarArticuloLote extends Control {

	public ControlBuscarArticuloLote(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String la = null;
		CrearLotePanel a = (CrearLotePanel) panel;
		if (!a.getSearch().getText().equals("")) {
			if (a.getOpcion1().isSelected()) {
				la = app.findArticulo(Integer.parseInt(a.getSearch().getText()), 0, null, null).getDescripcion();
			} else if (a.getOpcion2().isSelected()) {
				la = app.findArticulo(0, Double.parseDouble(a.getSearch().getText()), null, null).getDescripcion();
			} else if (a.getOpcion3().isSelected()) {
				LocalDate dt = Lector.parseFecha(a.getSearch().getText());
				la = app.findArticulo(0, 0, dt, null).getDescripcion();
			}
			if (a.getArticulos().size() > 0) {
				for (int i = a.getArticulos().getSize() - 1; i > -1; i--) {
					a.getArticulos().remove(i);
				}
			}
			if (la != null)
				a.getArticulos().addElement(la);
		}
	}

}
