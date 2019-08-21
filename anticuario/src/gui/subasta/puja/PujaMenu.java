package gui.subasta.puja;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import aplicacion.Aplicacion;
import gui.ComunMenu;
import gui.Constraint;
import gui.ReturnButton;
import gui.test.App;
import persona.cliente.Cliente;
import subasta.Subasta;

public class PujaMenu extends ComunMenu {

	private ReturnButton retButton = App.getRetButton().copy();
	private DefaultListModel subastas;
	private DefaultListModel clientes;
	private JTextField puja;
	private JButton registrar;
	private JDatePanelImpl inicio;
	private JDatePanelImpl fin;
	private Aplicacion app;
	private JButton botonSubasta;

	private JList listSubasta;

	/**
	 * @return the listSubasta
	 */
	public JList getListSubasta() {
		return listSubasta;
	}

	/**
	 * @return the listCliente
	 */
	public JList getListCliente() {
		return listCliente;
	}

	private JList listCliente;

	private JDatePickerImpl datePickerInicio;
	private JDatePickerImpl datePickerFin;

	public ReturnButton getRetButton() {
		return retButton;
	}

	public DefaultListModel getSubastas() {
		return subastas;
	}

	public DefaultListModel getClientes() {
		return clientes;
	}

	public JTextField getPuja() {
		return puja;
	}

	public JButton getRegistrar() {
		return registrar;
	}

	public JDatePanelImpl getInicio() {
		return inicio;
	}

	public JDatePanelImpl getFin() {
		return fin;
	}

	public PujaMenu(Aplicacion app) {
		this.app = app;
		this.setLayout(new GridBagLayout());
		Constraint c = new Constraint();

		JPanel subastasPanel = new JPanel();
		subastasPanel.setLayout(new GridBagLayout());
		JPanel clientesPanel = new JPanel();
		clientesPanel.setLayout(new GridBagLayout());

		subastas = new DefaultListModel();
		/*
		 * for (int i = 0; i < 20; i++) subastas.addElement("subasta" + i);
		 */
		for (Subasta s : app.getSubastas()) {
			subastas.addElement(s.getObjetoSubasta());
		}
		listSubasta = new JList(subastas);
		JScrollPane listaSubastas = new JScrollPane(listSubasta);
		listaSubastas.setMinimumSize(new Dimension(100,100));
		
		clientes = new DefaultListModel();
		/*
		 * for (int i = 0; i < 20; i++) clientes.addElement("cliente " + i);
		 */

		for (Cliente cliente : app.getClientes()) {
			clientes.addElement(cliente.getNombre());
		}

		listCliente = new JList(clientes);
		JScrollPane listaClientes = new JScrollPane(listCliente);

		JLabel label1 = new JLabel("puja: ");
		puja = new JTextField(6);
		registrar = new JButton("registrar");

		search = new JTextField(20);
		opcion1 = new JRadioButton("buscar por nombre");
		opcion2 = new JRadioButton("buscar por dni");
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcion1);
		grupo.add(opcion2);
		buscar = new JButton("buscar cliente");

		UtilDateModel model1 = new UtilDateModel();
		UtilDateModel model2 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		model1.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		model2.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		inicio = new JDatePanelImpl(model1, p);
		fin = new JDatePanelImpl(model2, p);
		datePickerInicio = new JDatePickerImpl(inicio, null);
		datePickerFin = new JDatePickerImpl(fin, null);
		botonSubasta = new JButton("buscar subasta");

		c.setInsets(3, 3, 3, 3);
		c.alignLeft();
		c.setPos(0, 0);
		subastasPanel.add(inicio, c);
		c.setPos(1, 0);
		subastasPanel.add(fin, c);
		c.setPos(2, 0);
		subastasPanel.add(botonSubasta, c);
		c.setPos(0, 1);
		c.allFill();
		c.setWeight(1, 1);
		c.setHeight(4);
		c.setWidth(3);
		subastasPanel.add(listaSubastas, c);
		c.setWidth(1);
		c.setWeight(0.1, 0.1);
		c.setHeight(1);
		c.noFill();

		c.setPos(0, 0);
		c.setWeight(0.3, 0.3);
		c.alignTopLeft();
		clientesPanel.add(search, c);
		c.setWeight(0.1, 0.1);
		c.setPos(0, 1);
		clientesPanel.add(opcion1, c);
		c.setPos(0, 2);
		clientesPanel.add(opcion2, c);
		c.setPos(0, 3);
		clientesPanel.add(buscar, c);
		c.setPos(0, 4);
		c.allFill();
		c.setWeight(0, 1);
		clientesPanel.add(listaClientes, c);
		c.setWeight(0, 0);
		c.alignCenter();
		c.noFill();

		c.setPos(1, 1);
		clientesPanel.add(label1, c);
		c.setPos(1, 2);
		c.setWeight(0.3, 0.3);
		clientesPanel.add(puja, c);
		c.setWeight(0, 0);
		c.setPos(1, 3);
		c.allFill();
		clientesPanel.add(registrar, c);
		c.noFill();

		c.setPos(0, 0);
		c.allFill();
		c.setWeight(0.3, 0.3);
		c.setWidth(3);
		this.add(subastasPanel, c);
		c.setPos(3, 0);
		c.setWeight(0.5, 0.5);
		this.add(clientesPanel, c);
		c.setPos(0, 1);
		c.setWidth(1);
		c.setWeight(0, 0);
		c.noFill();
		this.add(retButton, c);

	}

	/**
	 * @return the datePickerInicio
	 */
	public JDatePickerImpl getDatePickerInicio() {
		return datePickerInicio;
	}

	/**
	 * @return the datePickerFin
	 */
	public JDatePickerImpl getDatePickerFin() {
		return datePickerFin;
	}

	public void updateTables() {
		this.getSubastas().removeAllElements();
		this.getClientes().removeAllElements();
		List<Subasta> subastas = app.getSubastas();
		List<Cliente> clientes = app.getClientes();
		for (Subasta s : subastas)
			this.getSubastas().addElement(s.getObjetoSubasta());
		for (Cliente c : clientes) {
			if (c.getContrato() != null && c.getContrato().isCaducado() != false)
				this.getClientes().addElement(c.getDni());
		}
	}

	public void setControlador(ActionListener buscarSubasta, ActionListener registrar,ActionListener buscarCliente) {
		this.registrar.addActionListener(registrar);
		this.botonSubasta.addActionListener(buscarSubasta);
		this.buscar.addActionListener(buscarCliente);
	}

}
