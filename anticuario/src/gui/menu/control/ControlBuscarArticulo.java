package gui.menu.control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;

public class ControlBuscarArticulo extends Control implements ActionListener {

	
	public ControlBuscarArticulo(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((CardLayout)this.con.getLayout()).show(this.con, "ArticuloMenu");
	}

}
