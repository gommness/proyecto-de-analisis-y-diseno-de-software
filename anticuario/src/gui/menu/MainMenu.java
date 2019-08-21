package gui.menu;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.Constraint;

public class MainMenu extends JPanel {

	private JButton b0;
	private JButton b1;
	private JButton b2;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5216583211422114203L;

	public MainMenu() {
		this.setLayout(new GridBagLayout());
		Constraint o = new Constraint();
		b0 = new JButton("logout");
		b1 = new JButton("buscar cliente");
		b2 = new JButton("buscar articulo");
		o.alignTopLeft();
		o.setIpad(10, 30);
		o.setInsets(5, 10, 10, 5);
		o.setPos(0, 0);
		this.add(b0,o);
		o.setPos(0, 1);
		this.add(b1,o);
		o.setPos(0, 2);
		this.add(b2,o);
	}

	/**
	 * @return the b0
	 */
	public JButton getB0() {
		return b0;
	}

	/**
	 * @return the b1
	 */
	public JButton getB1() {
		return b1;
	}

	/**
	 * @return the b2
	 */
	public JButton getB2() {
		return b2;
	}

	public void setControlador(ActionListener c,ActionListener d,ActionListener e) {
		this.b0.addActionListener(c);
		this.b1.addActionListener(d);
		this.b2.addActionListener(e);
	}

}
