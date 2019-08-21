package gui.venderMenu.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import articulos.*;
import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;
import gui.venderMenu.VenderMenu;
import lector.Lector;
import gui.articulo.*;

public class ControlBuscarArticuloMenu extends Control {

	public ControlBuscarArticuloMenu(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Articulo> la;
		LocalDate dt;
		VenderMenu a = (VenderMenu) panel;
		if (!a.getSearchArticulo().getText().equals("")) {
			if (a.getOpcion1Articulo().isSelected()) {
				la = app.buscarArticulo(Integer.parseInt(a.getSearchArticulo().getText()), 0, null, null);
			} else if (a.getOpcion2Articulo().isSelected()) {
				la = app.buscarArticulo(0, Double.parseDouble(a.getSearchArticulo().getText()), null, null);
			} else if (a.getOpcion3Articulo().isSelected()) {
				try {
					dt = Lector.parseFecha(a.getSearchArticulo().getText(), "-");
				} catch (NumberFormatException n1) {
					try {
						dt = Lector.parseFecha(a.getSearchArticulo().getText(), "/");
					} catch (NumberFormatException n2) {
						try {
							dt = Lector.parseFecha(a.getSearchArticulo().getText(), " ");
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
		if (a.getModeloDatosArticulo().getRowCount() > 0) {
			for (int i = a.getModeloDatosArticulo().getRowCount() - 1; i > -1; i--) {
				a.getModeloDatosArticulo().removeRow(i);
			}
		}
		for (Articulo art : la) {
			if (art.isDisponible() == true && art.isVendido() == false) {
				Object[] fila = art.filaArticulo();
				a.getModeloDatosArticulo().addRow(fila);
			}
		}

	}

}
