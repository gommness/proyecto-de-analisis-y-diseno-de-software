package gui.subasta;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import subasta.Subasta;

public class DatosSubastaPanel extends JPanel{
	
	public DatosSubastaPanel(Subasta subasta){
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;//se rellenara horizontalmente
		
		//insets : bottom, left, right, top
		c.insets = new Insets(10,10,10,10);//espacio externo entre celdas
		
		//encabezado:
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		JLabel encabezado = new JLabel("datos de la subasta:");
		this.add(encabezado, c);
		
		//articulos de la subasta
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 2;
		JLabel datos = new JLabel("articulo(s): " + subasta.getObjetoSubasta());
		this.add(datos, c);
		
		//datos de la subasta
		c.insets = new Insets(3,10,10,3);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		JLabel fechaLimite = new JLabel("fecha limite: " + subasta.getFechaFin());
		this.add(fechaLimite, c);
		c.gridy = 3;
		JLabel cuota = new JLabel("cuota de inscripcion: " + subasta.getCuotaInscripcion());
		this.add(cuota, c);
		c.gridy = 4;
		JLabel precio = new JLabel("precio base: " + subasta.getPrecioBase());
		this.add(precio, c);
		
		//datos de la ultima puja
		c.insets = new Insets(10,10,10,3);
		c.gridy = 5;
		JLabel puja = new JLabel("puja mas alta: " + subasta.getUltimaPuja());
		this.add(puja, c);
		c.insets = new Insets(3,10,10,3);
		c.gridy = 6;
		JLabel ganador = new JLabel("actual ganador: " + subasta.getUltimaPuja().getCliente().getNombre());
		this.add(ganador, c);
	}

}
