package gui.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.login.LoginPanel;

public abstract class Control implements ActionListener {
	protected Aplicacion app;
	protected JPanel panel;
	protected Container con;


	/**
	 * @param app
	 * @param panel
	 * @param con
	 */
	public Control(Aplicacion app, JPanel panel, Container con) {
		this.app = app;
		this.panel = panel;
		this.con = con;
	}


}
