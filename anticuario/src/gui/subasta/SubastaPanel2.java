package gui.subasta;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import aplicacion.Aplicacion;
import subasta.Subasta;

public class SubastaPanel2 extends JPanel {
	
	private DefaultTableModel listaSubastas;
	private JTable tabla;
	public SubastaPanel2(Aplicacion app) {
		this.setLayout(new BorderLayout());
		String[] titulos = { "id", "objeto", "inicio", "fin", "cuota", "nombre ganador", "precio base",
				"dni ganador","puja" };
		ArrayList<Subasta> subastas = app.getSubastas();
		ArrayList<Object[]> aux = new ArrayList<>();
		for(Subasta s : subastas){
			aux.add(s.fila());
		}
		Object[][] filas = aux.stream().toArray(Object[][]::new);
		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		listaSubastas = new DefaultTableModel(filas, titulos);
		// Crear la tabla, pasando el modelo como parámetro
		tabla = new JTable(listaSubastas);
		tabla.setSize(200, 200);
		tabla.setPreferredSize(new Dimension(200,200));
		JScrollPane listaEmpleados = new JScrollPane(tabla);


		this.add(tabla.getTableHeader(), BorderLayout.NORTH);
		this.add(tabla,BorderLayout.CENTER);
		this.add(tabla);
	}

	public DefaultTableModel getListaSubastas() {
		return listaSubastas;
	}

	/**
	 * @return the tabla
	 */
	public JTable getTabla() {
		return tabla;
	}
	
}
