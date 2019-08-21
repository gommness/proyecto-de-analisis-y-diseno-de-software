package gui;

import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public abstract class ComunMenu extends JPanel {
	protected JButton nuevo;
	protected JButton modificar;
	protected JButton buscar;
	protected JTextField search;

	protected JRadioButton opcion1;
	protected JRadioButton opcion2;
	
	protected DefaultTableModel modeloDatos;

	public ComunMenu() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nuevo
	 */
	public JButton getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(JButton nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the modificar
	 */
	public JButton getModificar() {
		return modificar;
	}

	/**
	 * @param modificar the modificar to set
	 */
	public void setModificar(JButton modificar) {
		this.modificar = modificar;
	}

	/**
	 * @return the buscar
	 */
	public JButton getBuscar() {
		return buscar;
	}

	/**
	 * @param buscar the buscar to set
	 */
	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	/**
	 * @return the search
	 */
	public JTextField getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(JTextField search) {
		this.search = search;
	}

	/**
	 * @return the opcion1
	 */
	public JRadioButton getOpcion1() {
		return opcion1;
	}

	/**
	 * @param opcion1 the opcion1 to set
	 */
	public void setOpcion1(JRadioButton opcion1) {
		this.opcion1 = opcion1;
	}

	/**
	 * @return the opcion2
	 */
	public JRadioButton getOpcion2() {
		return opcion2;
	}

	/**
	 * @param opcion2 the opcion2 to set
	 */
	public void setOpcion2(JRadioButton opcion2) {
		this.opcion2 = opcion2;
	}

	/**
	 * @return the modeloDatos
	 */
	public DefaultTableModel getModeloDatos() {
		return modeloDatos;
	}

	/**
	 * @param modeloDatos the modeloDatos to set
	 */
	public void setModeloDatos(DefaultTableModel modeloDatos) {
		this.modeloDatos = modeloDatos;
	}

}
