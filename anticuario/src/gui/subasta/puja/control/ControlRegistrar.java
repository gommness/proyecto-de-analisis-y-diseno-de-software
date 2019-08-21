package gui.subasta.puja.control;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import excepciones.ExistenceException;
import excepciones.anticuarioExcepciones.ContratoException;
import excepciones.anticuarioExcepciones.PujaException;
import gui.control.Control;
import gui.subasta.puja.PujaMenu;
import persona.cliente.Cliente;
import subasta.Subasta;

public class ControlRegistrar extends Control {

	public ControlRegistrar(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		PujaMenu pj = (PujaMenu) panel;
		Cliente c = app.findCliente("", (String) pj.getListCliente().getSelectedValue(), "");
		Integer  i = (Integer) pj.getListSubasta().getSelectedValue();
		if(c == null || i == null)
			return;
		Subasta s =app.getSubastas().get(i-1);
		try {
			s.registrarPuja(c, Double.parseDouble(pj.getPuja().getText()));
			System.out.println("Puja realizada");
		} catch (NumberFormatException | InvalidEmailAddressException | FailedInternetConnectionException
				| PujaException | ExistenceException | ContratoException e1) {
			return;
		}
	}

}
