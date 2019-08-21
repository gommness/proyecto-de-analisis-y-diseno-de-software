package gui.subasta.puja.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import excepciones.IntervaloException;
import gui.control.Control;
import gui.subasta.puja.PujaMenu;
import subasta.Subasta;

public class ControlBuscarSubasta_Puja extends Control {

	public ControlBuscarSubasta_Puja(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PujaMenu pj = (PujaMenu) panel;
		Date selectedDate1 = (Date) pj.getDatePickerInicio().getModel().getValue();
		Date selectedDate2 = (Date) pj.getDatePickerFin().getModel().getValue();
		
		if(selectedDate1 == null)
			selectedDate1 = Date.from(Instant.now());
		if(selectedDate2 == null)
			selectedDate2 = Date.from(Instant.now());
		/*
		if(selectedDate2.compareTo(selectedDate1)<0)
			return;*/
		
		LocalDate selectedLocalDate1 = selectedDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate selectedLocalDate2 = selectedDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		if(selectedLocalDate1.isAfter(selectedLocalDate2))
			return;
		
		String la = null;
		pj.getSubastas();
		ArrayList<Subasta> ls;
		try {
			ls = app.buscarSubasta(selectedLocalDate1, selectedLocalDate2);
			if (pj.getSubastas().size() > 0) {
				for (int i = pj.getClientes().size() - 1; i > -1; i--) {
					pj.getSubastas().remove(i);
				}
			}
			for (Subasta s : ls) {
				pj.getSubastas().addElement(s.getId());
			}
		} catch (IntervaloException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
