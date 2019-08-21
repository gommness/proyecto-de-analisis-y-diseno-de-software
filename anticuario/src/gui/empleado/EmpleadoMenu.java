package gui.empleado;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
import persona.usuario.Empleado;

public class EmpleadoMenu extends ComunMenu {

	private ReturnButton retButton = App.getRetButton().copy();
	private Aplicacion app;
	private JTable empleados;
	
	public EmpleadoMenu(Aplicacion app) {
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
		
		
		List<Empleado> empleados = app.getEmpleados();
		ArrayList<Object[]> array = new ArrayList<>();
		String[] titulos = { "nombre", "dni", "seg social", "mail", "contraseña", "sueldo", "direccion", "telefono" };
		for(Empleado e : empleados){
			array.add(e.filaPersona());
		}
		Object[][] filas = array.stream().toArray(Object[][]::new);
		
		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		modeloDatos = new DefaultTableModel(filas, titulos);
		// Crear la tabla, pasando el modelo como parámetro
		this.empleados = new JTable(modeloDatos);
		JScrollPane listaEmpleados = new JScrollPane(this.empleados);

		// creo el elemento del panel de crear empleado
		nuevo = new JButton("Nuevo Empleado");
		modificar = new JButton("modificar Empleado");
		buscar = new JButton("Buscar Empleado");
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
		c.setPos(3,0);
		this.add(buscar, c);
		c.alignLeft();
		c.setPos(0, 3);
		c.setArea(2, 1);
		this.add(listaEmpleados, c);
		c.setPos(0, 4);
		c.setArea(1, 1);
		this.add(retButton,c);

	}

	public JTable getEmpleados() {
		return empleados;
	}

	public void setControlador(ActionListener c, ActionListener d, ActionListener e) {
		this.nuevo.addActionListener(c);
		this.modificar.addActionListener(d);
		this.buscar.addActionListener(e);
	}

	public void updateTables() {
		//actualizamos la tabla de empleados
		List<Empleado> empleados = app.getEmpleados();
		for(int i = this.getModeloDatos().getRowCount()-1; i > -1;i--)
			this.getModeloDatos().removeRow(i);
		for(Empleado e : empleados){
			this.getModeloDatos().addRow(e.filaPersona());
		}
	}
}
