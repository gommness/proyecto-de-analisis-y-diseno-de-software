package gui.menu.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;
import gui.login.LoginPanel;
import gui.test.App;

import java.awt.*;
public class ControlLogout extends Control implements ActionListener {



	/**
	 * @param app
	 * @param panel
	 * @param con
	 */
	public ControlLogout(Aplicacion app, JPanel panel, Container con) {
		super(app,panel,con);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.app.logout();
		try {
			App.getApp().save("ficheros/anticuario.obj");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		((CardLayout)this.con.getLayout()).show(this.con, "Login");
	}

}
