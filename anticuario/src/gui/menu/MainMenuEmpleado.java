package gui.menu;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.Constraint;

public class MainMenuEmpleado extends MainMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5091436724722992515L;
	private JButton b3;
	private JButton b4;
	private JButton b5;

	public MainMenuEmpleado() {
		Constraint o = new Constraint();
		b3 = new JButton("registrar puja");
		b4 = new JButton("nuevo cliente");
		b5 = new JButton("vender");
		o.alignTopLeft();
		o.setIpad(10, 30);
		o.setInsets(5, 10, 10, 5);
		o.setPos(0, 3);
		this.add(b3,o);
		o.setPos(0, 4);
		this.add(b4,o);
		o.setPos(0, 5);
		this.add(b5,o);
	}
	public void setControlador(ActionListener c,ActionListener d,ActionListener e,ActionListener f,ActionListener g,ActionListener h) {
		super.setControlador(c, d, e);
		this.b3.addActionListener(f);
		this.b4.addActionListener(g);
		this.b5.addActionListener(h);
	}
}
