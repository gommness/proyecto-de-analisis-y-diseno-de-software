package gui.login.control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import aplicacion.Aplicacion;
import gui.control.Control;
import gui.login.LoginPanel;
import persona.usuario.Gerente;
import persona.usuario.Usuario;

public class ControlLogin extends Control {

	public ControlLogin(Aplicacion app, JPanel panel, Container con) {
		super(app,panel,con);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JPasswordField pwd = ((LoginPanel) this.panel).getPwd();
		JTextField user = ((LoginPanel) this.panel).getNombre();
		System.out.println(user + " " +pwd);
		Usuario usuario = this.app.login(user.getText(), new String(pwd.getPassword()));
		CardLayout layout;
		if (usuario != null) {
			if (usuario instanceof Gerente) {
				layout = (CardLayout) this.con.getLayout();
				layout.show(this.con, "MainMenuGerente");
			} else {
				layout = (CardLayout) this.con.getLayout();
				layout.show(this.con, "MainMenuEmpleado");

			}

		}
		else{ System.out.println("ERROR");}

	}

}
