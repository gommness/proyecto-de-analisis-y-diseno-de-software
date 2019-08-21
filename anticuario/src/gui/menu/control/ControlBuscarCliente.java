package gui.menu.control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;

public class ControlBuscarCliente extends Control implements ActionListener {

	/**
	 * @param app
	 * @param panel
	 * @param con
	 */
	public ControlBuscarCliente(Aplicacion app, JPanel panel, Container con) {
		super(app,panel,con);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*Nombre de poner panel buscar cliente*/
		((CardLayout)this.con.getLayout()).show(this.con, "ClienteMenu");

	}

}
