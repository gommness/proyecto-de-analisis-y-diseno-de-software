package gui.subasta.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import articulos.Articulo;
import articulos.subastable.ObjetoSubasta;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import excepciones.anticuarioExcepciones.ArticuloException;
import gui.control.Control;
import gui.subasta.SubastaMenu;
import gui.test.App;
import subasta.Subasta;

public class ControlCrearSubasta extends Control {

	public ControlCrearSubasta(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SubastaMenu sm = (SubastaMenu) panel;
		/*
		 * int fila=sm.getTabla().getSelectedRow(); if(fila !=-1){ int id =
		 * (int)sm.getTabla().getValueAt(fila, 0); app.findSubasta();
		 */
		ObjetoSubasta o = (ObjetoSubasta) app.findArticulo(0, 0, null, (String) sm.getCombo().getSelectedItem());
		try {
			double cuota = Double.parseDouble(sm.getCuota().getText());
			app.iniciarSubasta(Integer.parseInt(sm.getDuracion().getText()),cuota, o.getPrecioObjetivo(), LocalDate.now(), o);
			/* Falta tratar de añadir la subasta a la tabla al instante */
		} catch (NumberFormatException | InvalidEmailAddressException | FailedInternetConnectionException
				| ArticuloException e1) {
			return;
		}
		App.updateTables();

	}

}
