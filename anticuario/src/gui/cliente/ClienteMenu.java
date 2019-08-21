package gui.cliente;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import aplicacion.Aplicacion;
import gui.ComunMenu;
import gui.Constraint;
import gui.ReturnButton;
import gui.test.App;
import persona.cliente.Cliente;
import persona.usuario.Empleado;

public class ClienteMenu extends ComunMenu {

	private JTable clientes;
	private Aplicacion app;

	public JTable getClientes() {
		return clientes;
	}

	private static final long serialVersionUID = -7334048957410155330L;

	public ClienteMenu(Aplicacion app) {
		this.app = app;
		this.setLayout(new GridBagLayout());
		Constraint c = new Constraint();
		// creo los contenidos del panel de busqueda
		search = new JTextField(30);
		opcion1 = new JRadioButton("buscar por nombre");
		opcion2 = new JRadioButton("buscar por dni");
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcion1);
		grupo.add(opcion2);

		ArrayList<Cliente> array = app.getClientes();

		String[] titulos = { "nombre", "dni", "direccion", "codigo postal", "telefono", "mail" };
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (Cliente cliente : array) {
			data.add(cliente.filaPersona());
		}
		Object[][] filas = data.stream().toArray(Object[][]::new);
		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		modeloDatos = new DefaultTableModel(filas, titulos);
		// Crear la tabla, pasando el modelo como parámetro
		clientes = new JTable(modeloDatos);
		JScrollPane listaClientes = new JScrollPane(clientes);

		// creo el elemento del panel de crear empleado
		nuevo = new JButton("Nuevo Cliente");
		modificar = new JButton("Modificar datos");
		buscar = new JButton("Buscar Cliente");
		c.setPos(0, 0);
		c.alignLeft();
		this.add(search, c);
		c.setPos(0, 1);
		this.add(opcion1, c);
		c.setPos(0, 2);
		this.add(opcion2, c);
		c.setPos(1, 0);
		c.setArea(1, 2);
		c.alignRight();
		c.vFill();
		this.add(nuevo, c);
		c.setPos(2, 0);
		this.add(modificar, c);
		c.setPos(3, 0);
		this.add(buscar, c);
		c.alignLeft();
		c.setPos(0, 3);
		c.setArea(2, 1);
		this.add(listaClientes, c);
		c.setPos(0, 4);
		this.add(App.getRetButton().copy(), c);

	}

	public void setControlador(ActionListener c, ActionListener d, ActionListener e) {
		this.nuevo.addActionListener(c);
		this.modificar.addActionListener(d);
		this.buscar.addActionListener(e);
	}

	public void updateTables() {
		// actualizamos la tabla de clientes
		List<Cliente> clientes = app.getClientes();
		for (int i = this.getModeloDatos().getRowCount()-1; i > -1; i--)
			this.getModeloDatos().removeRow(i);
		for (Cliente e : clientes) {
			this.getModeloDatos().addRow(e.filaPersona());
		}
	}

}
