package gui.articulo;

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
import articulos.Articulo;
import gui.ComunMenu;
import gui.Constraint;
import gui.ReturnButton;
import gui.test.App;

public class ArticuloMenu extends ComunMenu {
	private JButton vender;
	private JRadioButton opcion3;
	private ReturnButton retButton = App.getRetButton().copy();

	public ArticuloMenu(Aplicacion app) {
		this.setLayout(new GridBagLayout());
		Constraint c = new Constraint();

		String[] titulos = { "id", "descripcion","año origen","fecha adq" ,"precio adq", "precio", "vendido","disponible","descuento","peso",
				"volumen","gastos envio","autor","certificado","tipo","subastable"};
		ArrayList<Object[]> data = new ArrayList<>();
		List<Articulo> articulos = app.getArticulos();
		for(Articulo a : articulos){
			data.add(a.filaArticulo());
		}
		Object[][] filas = data.stream().toArray(Object[][]::new);
		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		modeloDatos = new DefaultTableModel(filas, titulos);
		// Crear la tabla, pasando el modelo como parámetro
		JTable table = new JTable(modeloDatos);

		JScrollPane tablaArticulos = new JScrollPane(table);
		opcion1 = new JRadioButton("por id");
		opcion2 = new JRadioButton("por precio");
		opcion3 = new JRadioButton("por fecha");
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcion1);
		grupo.add(opcion2);
		grupo.add(opcion3);
		search = new JTextField(10);
		/*modificar = new JButton("modificar");
		vender = new JButton("vender");*/
		buscar = new JButton("buscar");
		c.alignLeft();
		c.setPos(0, 0);
		this.add(search, c);
		c.setPos(0, 1);
		this.add(opcion1, c);
		c.setPos(0, 2);
		this.add(opcion2, c);
		c.setPos(0, 3);
		this.add(opcion3, c);
		c.setPos(0, 4);
		c.setWidth(3);
		c.setWeight(1, 1);
		c.allFill();
		this.add(tablaArticulos, c);
		c.setWeight(0.1, 0.1);
		c.noFill();
		c.setWidth(1);
		c.setPos(0, 5);
		c.setInsets(10, 0, 0, 0);
		c.setIpad(0, 10);
		this.add(retButton,c);
		
		c.alignCenter();
		c.setPos(1, 0);
		this.add(buscar, c);
		/*this.add(modificar, c);
		c.setPos(2, 0);
		this.add(vender, c);*/

	}

	public void setControlador(ActionListener e) {
		/*this.modificar.addActionListener(c);
		this.vender.addActionListener(d);*/
		this.buscar.addActionListener(e);
	}

	/**
	 * @return the vender
	 */
	public JButton getVender() {
		return vender;
	}

	/**
	 * @param vender the vender to set
	 */
	public void setVender(JButton vender) {
		this.vender = vender;
	}

	/**
	 * @return the opcion3
	 */
	public JRadioButton getOpcion3() {
		return opcion3;
	}

	/**
	 * @param opcion3 the opcion3 to set
	 */
	public void setOpcion3(JRadioButton opcion3) {
		this.opcion3 = opcion3;
	}

	

}
