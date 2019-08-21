package gui.subasta.control;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import excepciones.anticuarioExcepciones.SubastaException;
import gui.control.Control;
import gui.subasta.SubastaMenu;
import gui.test.App;
import subasta.Subasta;

public class ControlCancelarSubasta extends Control {

	public ControlCancelarSubasta(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SubastaMenu sm = (SubastaMenu) panel;
		int index=sm.getTabla().getSelectedRow();
		Subasta s=app.findSubasta((int)sm.getTabla().getValueAt(index, 0));
		try {
			s.cancelarSubasta();
		} catch (SubastaException | InvalidEmailAddressException | FailedInternetConnectionException e1) {
			// TODO Auto-generated catch block
			return;
		}
		App.updateTables();
	}

}
