package gui.subasta;

import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aplicacion.Aplicacion;
import articulos.subastable.ObjetoSubasta;
import gui.Constraint;
import gui.test.App;

public class SubastaPanel1 extends JPanel {

	private JComboBox combo;
	private JTextField cuota;
	private JTextField duracion;
	private JButton crearSubasta;
	private JButton crearLote;
	
	public JComboBox getCombo() {
		return combo;
	}

	public JTextField getCuota() {
		return cuota;
	}

	public JTextField getDuracion() {
		return duracion;
	}

	public JButton getCrearSubasta() {
		return crearSubasta;
	}

	public JButton getCrearLote() {
		return crearLote;
	}

	public SubastaPanel1(Aplicacion app) {
		this.setLayout(new GridBagLayout());
		Constraint c = new Constraint();
		
		c.setInsets(10, 10, 10, 10);
		c.setArea(1, 1);
		c.setPos(0, 0);
		c.hFill();
		JLabel etiqueta1 = new JLabel("Articulo a Subastar");
		this.add(etiqueta1,c);
		
		c.setPos(1, 0);
		ArrayList<String> objetos = new ArrayList<>();
		ArrayList<ObjetoSubasta> aux = app.getSubastables(); 
		for(ObjetoSubasta o : aux){
			objetos.add(o.getDescripcion());
		}
		String[] articulos = objetos.stream().toArray(String[]::new);
		combo = new JComboBox(articulos);
		this.add(combo, c);

		c.alignLeft();
		c.setWeight(1, 1);
		c.setPos(0, 1);
		JLabel etiqueta3 = new JLabel("Cuota de Inscripcion");
		this.add(etiqueta3,c);
		
		c.setPos(0, 2);
		JLabel etiqueta4 = new JLabel("Duracion en dias");
		this.add(etiqueta4,c);
		
		c.setPos(0, 4);
		c.setWidth(2);
		crearSubasta = new JButton("Crear Subasta");
		this.add(crearSubasta, c);
		
		c.setPos(0, 5);
		crearLote = new JButton("Crear Lote");
		this.add(crearLote,c);
		
		c.setWeight(0.1, 0.1);
		c.hFill();
		c.setPos(1, 1);
		cuota = new JTextField(10);
		this.add(cuota, c);
		
		c.setPos(1, 2);
		duracion = new JTextField(10);
		this.add(duracion,c);
		
		//this.add(etiqueta1);
		//this.add(combo);
		//this.add(etiqueta2);
		//this.add(campo1);
		//this.add(etiqueta3);
		//this.add(campo2);
		//this.add(etiqueta4);
//		UtilDateModel model = new UtilDateModel();
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
//		this.add(datePicker);
		//this.add(boton1);
	}

}
