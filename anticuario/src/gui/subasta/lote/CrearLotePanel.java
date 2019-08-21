package gui.subasta.lote;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import aplicacion.Aplicacion;
import articulos.Articulo;
import articulos.subastable.Lote;
import gui.Constraint;
import gui.ReturnButton;
import gui.test.App;
import persona.usuario.Empleado;

public class CrearLotePanel extends JPanel {

	private ReturnButton retButton = App.getRetButton().copy();
	private JRadioButton opcion1;
	private JRadioButton opcion2;
	private JRadioButton opcion3;
	private JTextField search;
	private JButton buscar;
	private JLabel total;
	private JButton crear;
	private JButton descartar;
	private JButton annadir;
	private DefaultListModel actuales;
	private DefaultListModel articulos;
	private JList listArticulos;
	private JList listActuales;
	private Aplicacion app;

	private JLabel nombre;
	private JTextField nameLote;

	public void setControlador(ActionListener buscar, ActionListener crear, ActionListener descartar,
			ActionListener annadir) {
		this.buscar.addActionListener(buscar);
		this.crear.addActionListener(crear);
		this.descartar.addActionListener(descartar);
		this.annadir.addActionListener(annadir);
	}

	public CrearLotePanel(Aplicacion app) {
		this.app = app;
		this.setLayout(new GridBagLayout());
		Constraint c = new Constraint();
		c.setInsets(3, 3, 3, 3);

		opcion1 = new JRadioButton("por id");
		opcion2 = new JRadioButton("por precio");
		opcion3 = new JRadioButton("por fecha");
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcion1);
		grupo.add(opcion2);
		grupo.add(opcion3);
		search = new JTextField(10);
		buscar = new JButton("Buscar");

		JLabel precioTotal = new JLabel("Precio total:");
		total = new JLabel("27 €");
		nombre = new JLabel("Nombre:");
		nameLote = new JTextField(20);
		crear = new JButton("Crear lote");
		JLabel label1 = new JLabel("lote:");
		descartar = new JButton("Descartar");
		actuales = new DefaultListModel();
		listActuales = new JList(actuales);
		JScrollPane listaActuales = new JScrollPane(listActuales);

		JLabel label2 = new JLabel("articulos:");
		annadir = new JButton("añadir");
		articulos = new DefaultListModel();
		for (Articulo a : this.app.getArticulos()) {
			if (a.isDisponible() && !a.isVendido())
				articulos.addElement(a.getDescripcion());
		}
		listArticulos = new JList(articulos);
		JScrollPane listaArticulos = new JScrollPane(listArticulos);

		JPanel busqueda = new JPanel();
		busqueda.setLayout(new GridBagLayout());
		JPanel total = new JPanel();
		total.setLayout(new GridBagLayout());
		JPanel articulosAct = new JPanel();
		articulosAct.setLayout(new GridBagLayout());
		JPanel articulosTot = new JPanel();
		articulosTot.setLayout(new GridBagLayout());

		// añadimos los elementos del panel de busqueda a dicho panel
		c.alignLeft();
		c.setPos(0, 0);
		busqueda.add(search, c);
		c.setPos(0, 1);
		busqueda.add(opcion1, c);
		c.setPos(0, 2);
		busqueda.add(opcion2, c);
		c.setPos(0, 3);
		busqueda.add(opcion3, c);
		c.alignCenter();
		c.setPos(0, 4);
		c.setIpad(10, 10);
		c.setInsets(10, 0, 0, 10);
		busqueda.add(buscar, c);
		c.setInsets(0, 0, 0, 0);
		c.setIpad(0, 0);

		c.setWeight(0.1, 0);

		// añadimos los elementos del panel de articulos actuales
		c.setPos(0, 0);
		c.setArea(1, 1);
		articulosAct.add(label1);
		c.setPos(1, 0);
		articulosAct.add(descartar);
		c.setPos(0, 1);
		c.allFill();
		c.setArea(2, 10);
		c.setWeight(1, 1);
		articulosAct.add(listaActuales, c);
		c.setWeight(0, 0);

		c.setArea(1, 1);
		// añadimos los elementos del panel de articulos restantes
		c.setPos(0, 0);
		c.noFill();
		articulosTot.add(label2, c);
		c.setPos(1, 0);
		articulosTot.add(annadir, c);
		c.setPos(0, 1);
		c.fill = GridBagConstraints.BOTH;
		c.setArea(2, 10);
		c.setWeight(1, 1);
		articulosTot.add(listaArticulos, c);
		c.setWeight(0.1, 0.1);
		/*
		 * c.setPos(0, 50); articulosTot.add(precioTotal, c);
		 */

		c.allFill();
		c.setArea(1, 1);
		// ahora añadimos todos los paneles al panel total
		c.setPos(0, 0);
		this.add(busqueda, c);
		c.setPos(1, 0);
		c.setHeight(12);
		this.add(articulosAct, c);
		c.setPos(2, 0);
		this.add(articulosTot, c);
		c.setPos(0, 1);
		c.setHeight(1);
		c.noFill();
		c.weighty = 0.1;
		this.add(retButton, c);
		c.setArea(1, 1);
		c.setWeight(0, 0);
		c.hFill();
		c.alignLeft();
		c.setPos(3, 8);
		c.setWeight(0.2, 0.2);
		this.add(nombre, c);
		c.setPos(4, 8);
		this.add(nameLote, c);
		c.setPos(4, 9);
		this.add(crear, c);
		c.setWeight(0.1, 0.1);
	}

	/**
	 * @return the name
	 */
	public JTextField getNameLote() {
		return nameLote;
	}

	public JRadioButton getOpcion1() {
		return opcion1;
	}

	public JRadioButton getOpcion2() {
		return opcion2;
	}

	public JRadioButton getOpcion3() {
		return opcion3;
	}

	public JTextField getSearch() {
		return search;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public JLabel getTotal() {
		return total;
	}

	public JButton getCrear() {
		return crear;
	}

	public JButton getDescartar() {
		return descartar;
	}

	public JButton getAnnadir() {
		return annadir;
	}

	public DefaultListModel getActuales() {
		return actuales;
	}

	public DefaultListModel getArticulos() {
		return articulos;
	}

	/**
	 * @return the listArticulos
	 */
	public JList getListArticulos() {
		return listArticulos;
	}

	/**
	 * @return the listActuales
	 */
	public JList getListActuales() {
		return listActuales;
	}

	public void updateTables() {
		// actualizamos la tabla de empleados
		List<Articulo> articulos = app.getArticulos();
		this.getActuales().removeAllElements();
		this.getArticulos().removeAllElements();
		for (Articulo e : articulos) {
			if (e.isDisponible() && !e.isVendido())
				this.getArticulos().addElement(e.getDescripcion());
		}
	}

}
