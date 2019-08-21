package gui.login;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3843999710121099245L;
	private JPasswordField pwd;
	private JTextField nombre;
	private JButton b;

	public LoginPanel() {
		super();
		this.setLayout(new GridLayout(3, 2));
		// declaracion de componentes
		JLabel l1 = new JLabel("Nombre:");
		JLabel l2 = new JLabel("password:");
		pwd = new JPasswordField(10);
		nombre = new JTextField(20);
		// JTextField pwd = new JTextField(20);
		b = new JButton("login");
		/*b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "hola " + nombre.getText());
			}
		});*/

		this.add(l1);
		this.add(nombre);
		this.add(l2);
		this.add(pwd);
		this.add(b);
	}

	public JPasswordField getPwd() {
		return this.pwd;
	}

	public JTextField getNombre() {
		return this.nombre;
	}

	public JTextField getUser() {
		return this.nombre;
	}

	public void setControlador(ActionListener c) {
		this.b.addActionListener(c);
	}
}
