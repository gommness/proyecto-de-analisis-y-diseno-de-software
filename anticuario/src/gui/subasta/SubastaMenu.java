/**
 * 
 */
package gui.subasta;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import aplicacion.Aplicacion;
import articulos.subastable.ObjetoSubasta;
import gui.ReturnButton;
import gui.test.App;
import subasta.Estado;
import subasta.Subasta;

/**
 * @author e321786
 *
 */
public class SubastaMenu extends JPanel {

	private ReturnButton retButton = App.getRetButton().copy();

	/**
	 * 
	 */
	private SubastaPanel1 subastaPanel1;
	private SubastaPanel2 subastaPanel2;
	private Aplicacion app;
	private JButton cerrar;
	private JButton cancelar;
	

	public SubastaMenu(Aplicacion app) {
		this.app = app;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		subastaPanel1 = new SubastaPanel1(app);
		subastaPanel2 = new SubastaPanel2(app);
		this.cancelar = new JButton("cancelar");
		this.cerrar = new JButton("cerrar");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridwidth = 8;
		c.ipadx = 30;
		c.gridx = 0;
		c.gridy = 0;
		this.add(subastaPanel1, c);// crear subastas
		c.gridwidth = 8;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 2;
		this.add(subastaPanel2, c);
		this.setSize(1080, 720);
		this.setVisible(true);
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 10;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.WEST;
		c.gridy = 4;
		c.insets.set(10, 0, 10, 0);
		this.add(cerrar, c);
		c.gridy = 5;
		this.add(cancelar, c);
		c.gridy = 6;
		c.gridwidth = 1;
		c.insets.set(0, 0, 0, 0);
		c.ipady = 0;
		this.add(retButton, c);

	}

	public JButton getCerrar() {
		return cerrar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setControlador(ActionListener c, ActionListener d,
			ActionListener cerrar, ActionListener cancelar) {
		this.getCrearSubasta().addActionListener(c);
		this.getCrearLote().addActionListener(d);
		this.getCerrar().addActionListener(cerrar);
		this.getCancelar().addActionListener(cancelar);
	}

	public JComboBox getCombo() {
		return subastaPanel1.getCombo();
	}

	public JTextField getCuota() {
		return subastaPanel1.getCuota();
	}

	public JTextField getDuracion() {
		return subastaPanel1.getDuracion();
	}

	public JButton getCrearSubasta() {
		return subastaPanel1.getCrearSubasta();
	}

	public JButton getCrearLote() {
		return subastaPanel1.getCrearLote();
	}

	public DefaultTableModel getListaSubastas() {
		return subastaPanel2.getListaSubastas();
	}

	public JTable getTabla() {
		return subastaPanel2.getTabla();
	}

	public void updateTables() {
		// actualizamos comboBox
		JComboBox combo = this.getCombo();
		combo.removeAllItems();
		//combo.removeAll();
		ArrayList<ObjetoSubasta> aux = app.getSubastables();
		for (ObjetoSubasta o : aux) {
			if (o.isDisponible() && !o.isVendido())
				combo.addItem(o.getDescripcion());
		}
		// actualizamos tabla de subastas
		for (int i = subastaPanel2.getListaSubastas().getRowCount() - 1; i > -1; i--)
			subastaPanel2.getListaSubastas().removeRow(i);
		ArrayList<Subasta> subastas = app.getSubastas();
		for (Subasta s : subastas) {
			if(s.getEstado().equals(Estado.abierto))
				subastaPanel2.getListaSubastas().addRow(s.fila());
		}
	}
}
