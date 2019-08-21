package gui.venderMenu;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import aplicacion.Aplicacion;
import articulos.Articulo;
import gui.Constraint;
import gui.ReturnButton;
import gui.test.App;
import persona.cliente.Cliente;
import persona.usuario.Empleado;

public class VenderMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6093772609325953904L;
	private JTextField searchCliente;
	private JRadioButton opcion1Cliente;
	private JRadioButton opcion2Cliente;
	private JButton buscarCliente;
	private DefaultTableModel modeloDatosCliente;
	private DefaultTableModel modeloDatosArticulo;
	private JRadioButton opcion1Articulo;
	private JRadioButton opcion2Articulo;
	private JRadioButton opcion3Articulo;
	private JTextField searchArticulo;
	private JButton buscarArticulo;
	private JButton venderButton;
	private ReturnButton returnButton = App.getRetButton().copy();
	private JTable clientesLista;
	private JTable articulosLista;
	private Aplicacion app;

	public void setControlador(ActionListener buscarCliente, ActionListener buscarArticulo, ActionListener vender) {
		this.buscarCliente.addActionListener(buscarCliente);
		this.buscarArticulo.addActionListener(buscarArticulo);
		this.venderButton.addActionListener(vender);
	}

	public JTextField getSearchCliente() {
		return searchCliente;
	}

	public JRadioButton getOpcion1Cliente() {
		return opcion1Cliente;
	}

	public JRadioButton getOpcion2Cliente() {
		return opcion2Cliente;
	}

	public JButton getBuscarCliente() {
		return buscarCliente;
	}

	public DefaultTableModel getModeloDatosCliente() {
		return modeloDatosCliente;
	}

	public DefaultTableModel getModeloDatosArticulo() {
		return modeloDatosArticulo;
	}

	public JRadioButton getOpcion1Articulo() {
		return opcion1Articulo;
	}

	public JRadioButton getOpcion2Articulo() {
		return opcion2Articulo;
	}

	public JRadioButton getOpcion3Articulo() {
		return opcion3Articulo;
	}

	public JTextField getSearchArticulo() {
		return searchArticulo;
	}

	public JButton getBuscarArticulo() {
		return buscarArticulo;
	}

	public JButton getVenderButton() {
		return venderButton;
	}

	public ReturnButton getReturnButton() {
		return returnButton;
	}

	public VenderMenu(Aplicacion app) {
		this.app = app;

		this.setLayout(new GridBagLayout());
		Constraint c = new Constraint();

		venderButton = new JButton("vender");

		JLabel label1 = new JLabel("Clientes");
		searchCliente = new JTextField(30);
		opcion1Cliente = new JRadioButton("buscar por nombre");
		opcion2Cliente = new JRadioButton("buscar por dni");
		ButtonGroup grupo1 = new ButtonGroup();
		grupo1.add(opcion1Cliente);
		grupo1.add(opcion2Cliente);
		buscarCliente = new JButton("buscar");

		ArrayList<Cliente> array = app.getClientes();
		String[] titulosCliente = { "nombre", "dni", "direccion", "c.Postal", "telefono","mail", "contrato" };
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (Cliente cliente : array) {
			data.add(cliente.filaPersona());
		}
		Object[][] filascliente = data.stream().toArray(Object[][]::new);
		modeloDatosCliente = new DefaultTableModel(filascliente, titulosCliente);
		clientesLista = new JTable(modeloDatosCliente);
		JScrollPane listaClientes = new JScrollPane(clientesLista);

		JLabel label2 = new JLabel("articulos");
		searchArticulo = new JTextField(30);
		opcion1Articulo = new JRadioButton("buscar por id");
		opcion2Articulo = new JRadioButton("buscar por precio");
		opcion3Articulo = new JRadioButton("buscar por fecha");
		ButtonGroup grupo2 = new ButtonGroup();
		grupo2.add(opcion1Articulo);
		grupo2.add(opcion2Articulo);
		buscarArticulo = new JButton("buscar");

		ArrayList<Articulo> array2 = app.getArticulos();
		String[] titulosArticulo = { "id", "descripcion", "año origen", "fecha adq", "precio adq", "precio", "vendido",
				"disponible", "descuento", "peso", "volumen", "gastos envio", "autor", "certificado", "tipo",
				"subastable" };
		ArrayList<Object[]> data2 = new ArrayList<Object[]>();
		for (Articulo art : array2) {
			if (art.isVendido() == false && art.isDisponible() == true)
				data2.add(art.filaArticulo());
		}
		Object[][] filasArticulo = data2.stream().toArray(Object[][]::new);
		modeloDatosArticulo = new DefaultTableModel(filasArticulo, titulosArticulo);
		articulosLista = new JTable(modeloDatosArticulo);
		JScrollPane listaArticulos = new JScrollPane(articulosLista);

		c.setWeight(1, 0);
		c.setInsets(0, 10, 10, 0);
		c.allFill();
		c.setPos(0, 0);
		c.setWidth(2);
		c.alignLeft();
		this.add(label1, c);
		c.setWidth(1);
		c.alignCenter();
		c.setPos(2, 0);
		this.add(searchCliente, c);
		c.setPos(2, 1);
		this.add(opcion1Cliente, c);
		c.setPos(2, 2);
		this.add(opcion2Cliente, c);
		c.setPos(3, 0);
		this.add(buscarCliente, c);
		c.setPos(0, 3);
		c.alignTopLeft();
		c.setWidth(5);
		c.setWeight(1, 1);
		this.add(listaClientes, c);
		c.setWeight(1, 0);
		c.setWidth(1);
		c.alignCenter();

		c.setPos(0, 4);
		c.setWidth(2);
		c.alignLeft();
		this.add(label2, c);
		c.setWidth(1);
		c.alignCenter();
		c.setPos(2, 4);
		this.add(searchArticulo, c);
		c.setPos(3, 4);
		this.add(buscarArticulo, c);
		c.setPos(2, 5);
		this.add(opcion1Articulo, c);
		c.setPos(2, 6);
		this.add(opcion2Articulo, c);
		c.setPos(2, 7);
		this.add(opcion3Articulo, c);
		c.setPos(0, 8);
		c.alignTopLeft();
		c.setWidth(5);
		c.setWeight(1, 1);
		this.add(listaArticulos, c);
		c.setWeight(1, 0);
		c.setWidth(3);
		c.alignCenter();
		c.allFill();
		c.setPos(1, 9);
		c.setIpad(0, 20);
		this.add(venderButton, c);
		c.setIpad(0, 0);
		c.setPos(0, 10);
		c.noFill();
		c.setWeight(0, 0);
		c.setWidth(1);
		this.add(returnButton, c);

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTable getListaArticulos() {
		return articulosLista;
	}

	public JTable getListaClientes() {
		return clientesLista;
	}

	public void updateTables() {
		// actualizamos la tabla de clientes
		List<Cliente> clientes = app.getClientes();
		for (int i = this.getModeloDatosCliente().getRowCount()-1; i > -1 ; i--)
			this.getModeloDatosCliente().removeRow(i);
		for (Cliente e : clientes) {
			this.getModeloDatosCliente().addRow(e.filaPersona());
		}
		List<Articulo> articulos = app.getArticulos();
		for (int i = this.getModeloDatosArticulo().getRowCount()-1; i > -1; i--)
			this.getModeloDatosArticulo().removeRow(i);
		for (Articulo a : articulos) {
			if(a.isDisponible() && !a.isVendido())
			this.getModeloDatosArticulo().addRow(a.filaArticulo());
		}
	}

}
