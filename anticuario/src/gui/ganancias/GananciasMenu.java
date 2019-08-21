package gui.ganancias;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import aplicacion.Aplicacion;
import gui.Constraint;
import gui.ReturnButton;
import gui.test.App;
import subasta.Estado;
import subasta.Subasta;
import ventas.Venta;

public class GananciasMenu extends JPanel {

	private ReturnButton retButton = App.getRetButton().copy();
	private JDatePanelImpl inicio;
	private JDatePanelImpl fin;
	private JButton buscar;
	private JLabel total;
	private DefaultTableModel modeloDatos;
	private JTable table;

	private JDatePickerImpl datePickerInicio;
	private JDatePickerImpl datePickerFin;

	public GananciasMenu(Aplicacion app) {
		this.setLayout(new GridBagLayout());
		Constraint c = new Constraint();

		String[] titulos = { "id", "tipo", "Ganancias", "Cliente", "Articulo", "Fecha de inicio", "Numero de dias" };

		/*
		 * Object[][] filas = { { "123", "12", "27", "20" }, { "123", "12",
		 * "27", "20" } };
		 */
		ArrayList<Object[]> data = new ArrayList<>();
		for (Venta v : app.getVentas()) {
			data.add(v.filaGanancia());
		}
		for (Subasta s : app.getSubastas()) {
			if (s.getEstado().equals(Estado.cerrado))
				data.add(s.filaGanancia());
		}

		Object[][] filas = data.stream().toArray(Object[][]::new);
		modeloDatos = new DefaultTableModel(filas, titulos);
		table = new JTable(modeloDatos);
		JScrollPane tablaArticulos = new JScrollPane(table);
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
		buscar = new JButton("buscar");
		JLabel label1 = new JLabel("total: ");
		total = new JLabel("");

		c.alignLeft();
		c.setWeight(1, 1);
		c.setPos(0, 0);
		c.setInsets(5, 5, 5, 5);
		this.add(inicio, c);
		c.setPos(1, 0);
		this.add(fin, c);
		c.setInsets(0, 0, 0, 0);
		c.alignCenter();
		c.setPos(2, 0);
		this.add(buscar, c);
		c.setPos(0, 1);
		c.setWidth(7);
		c.alignLeft();
		c.allFill();
		this.add(tablaArticulos, c);
		c.noFill();
		c.setWidth(1);
		c.setIpad(3, 20);
		c.setPos(0, 2);
		this.add(label1, c);
		c.setPos(1, 2);
		this.add(total, c);
		c.setPos(0, 3);
		c.setIpad(0, 0);
		this.add(retButton, c);

	}

	public void setControlador(ActionListener buscar) {
		this.buscar.addActionListener(buscar);
	}

	/**
	 * @return the modeloDatos
	 */
	public DefaultTableModel getModeloDatos() {
		return modeloDatos;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
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

	public ReturnButton getRetButton() {
		return retButton;
	}

	public JDatePanelImpl getInicio() {
		return inicio;
	}

	public JDatePanelImpl getFin() {
		return fin;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public JLabel getTotal() {
		return total;
	}

	public void updateTables() {
		for (int i = this.getModeloDatos().getRowCount() - 1; i > -1; i--) {
			this.getModeloDatos().removeRow(i);
		}
	}

}
