package gui.articulo.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import articulos.*;
import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;
import lector.Lector;
import gui.articulo.*;

public class ControlBuscarArticuloMenu extends Control {

	public ControlBuscarArticuloMenu(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
		ArrayList<Articulo> la;
		LocalDate dt;
		ArticuloMenu a = (ArticuloMenu) panel;
		if (!a.getSearch().getText().equals("")) {
			if (a.getOpcion1().isSelected()) {
				la = app.buscarArticulo(Integer.parseInt(a.getSearch().getText()), 0, null, null);
			} else if (a.getOpcion2().isSelected()) {
				la = app.buscarArticulo(0, Double.parseDouble(a.getSearch().getText()), null, null);
			} else if (a.getOpcion3().isSelected()) {
				try {
					dt = Lector.parseFecha(a.getSearch().getText(), "-");
				} catch (NumberFormatException n1) {
					try {
						dt = Lector.parseFecha(a.getSearch().getText(), "/");
					} catch (NumberFormatException n2) {
						try {
							dt = Lector.parseFecha(a.getSearch().getText(), " ");
						} catch (Exception n3) {
							return;
						}
					}
				}
				la = app.buscarArticulo(0, 0, dt, null);
			} else {
				la = app.getArticulos();
			}
		} else {
			la = app.getArticulos();
		}
		if (a.getModeloDatos().getRowCount() > 0) {
			for (int i = a.getModeloDatos().getRowCount() - 1; i > -1; i--) {
				a.getModeloDatos().removeRow(i);
			}
		}
		for (Articulo art : la) {
			Object[] fila = art.filaArticulo();
			a.getModeloDatos().addRow(fila);
		}
		}catch(Exception ex){
			return;
		}
	}

}
