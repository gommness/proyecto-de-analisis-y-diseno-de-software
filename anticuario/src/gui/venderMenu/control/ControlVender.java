package gui.venderMenu.control;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import articulos.Articulo;
import excepciones.anticuarioExcepciones.ArticuloException;
import excepciones.anticuarioExcepciones.VentaException;
import gui.articulo.ArticuloMenu;
import gui.cliente.ClienteMenu;
import gui.venderMenu.VenderMenu;
import gui.control.Control;
import lector.Lector;
import persona.cliente.Cliente;
import ventas.Venta;

public class ControlVender extends Control {

	public ControlVender(Aplicacion app, JPanel panel, Container con) {
		super(app, panel, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Cliente cliente;
		Articulo articulo;
		Venta venta;
		VenderMenu menu = (VenderMenu) this.panel;
		int rowArt = menu.getListaArticulos().getSelectedRow();
		int rowCli = menu.getListaClientes().getSelectedRow();
		if(rowArt != -1 && rowCli != -1){
			articulo = this.app.findArticulo(
					(Integer)menu.getListaArticulos().getValueAt(rowArt, 0),
					0,null,null);
			cliente = this.app.findCliente(
					(String)menu.getListaClientes().getValueAt(rowCli, 1),
					(String)menu.getListaClientes().getValueAt(rowCli, 0),
					"");
			if(articulo != null && cliente != null)
			{
				try {
					venta = this.app.crearVenta(cliente, articulo);
					venta.vender();
					menu.getModeloDatosArticulo().removeRow(rowArt);
				} catch (Exception e1) {
				}
			}
		}
		
	}

}
