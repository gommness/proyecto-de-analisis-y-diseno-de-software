package gui.ganancias.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import excepciones.IntervaloException;
import gui.control.Control;
import gui.ganancias.GananciasMenu;

public class ControlBuscarGanancias extends Control {

	public ControlBuscarGanancias(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GananciasMenu gm = (GananciasMenu) panel;
		Date selectedDate1 = (Date) gm.getDatePickerInicio().getModel().getValue();
		Date selectedDate2 = (Date) gm.getDatePickerFin().getModel().getValue();
		
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
		
		try {
			Object[][] filas = app.consultarGanancias(selectedLocalDate1, selectedLocalDate2);
			if (gm.getModeloDatos().getRowCount() > 0) {
				for (int i = gm.getModeloDatos().getRowCount() - 1; i > -1; i--) {
					gm.getModeloDatos().removeRow(i);
				}
			}
			for (Object[] o : filas) {
				gm.getModeloDatos().addRow(o);
			}
			gm.getTotal().setText(""+app.getTotalGanancias(selectedLocalDate1, selectedLocalDate2));
		} catch (IntervaloException e1) {
		}
		

	}

}
