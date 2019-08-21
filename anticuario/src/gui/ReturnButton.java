package gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import aplicacion.Aplicacion;
import gui.control.Control;
import persona.usuario.Gerente;
import persona.usuario.Usuario;

public class ReturnButton extends JButton{
	
	private Aplicacion app;
	private Container con;
	
	public ReturnButton(Aplicacion app, Container con){
		super("volver");
		this.app = app;
		this.con = con;
		this.addActionListener(new ControlButton(app,null,con));
	}
	
	public ReturnButton copy(){
		return new ReturnButton(this.app,this.con);
	}
	
	public class ControlButton extends Control{

		public ControlButton(Aplicacion app, JPanel panel, Container con) {
			super(app, panel, con);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Usuario usuario = this.app.getUser();
			CardLayout layout;
			if(this.app.getUser() != null){
				if (usuario instanceof Gerente) {
					layout = (CardLayout) this.con.getLayout();
					layout.show(this.con, "MainMenuGerente");
				} else {
					layout = (CardLayout) this.con.getLayout();
					layout.show(this.con, "MainMenuEmpleado");

				}
			}
			
		}
		
	}
	
}
