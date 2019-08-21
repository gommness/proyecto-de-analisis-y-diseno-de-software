package gui.subasta.lote.control;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import articulos.subastable.Lote;
import excepciones.anticuarioExcepciones.LoteException;
import gui.control.Control;
import gui.subasta.lote.CrearLotePanel;
import gui.test.App;

public class ControlCrearLote extends Control {

	public ControlCrearLote(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CrearLotePanel c = (CrearLotePanel) panel;
		Lote lote = new Lote(c.getNameLote().getText());
		for (int i = 0; i < c.getActuales().size(); i++) {
			String name = (String) c.getActuales().getElementAt(i);
			try {
				lote.addArticulo(app.findArticulo(0, 0, null, name));
			} catch (LoteException e1) {
				return;
			}
		}
		this.app.getLotes().add(lote);
		App.updateTables();
	}

}
