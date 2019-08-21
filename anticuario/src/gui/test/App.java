package gui.test;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

import javax.swing.*;

import aplicacion.*;
import articulos.*;
import articulos.subastable.*;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import excepciones.ExistenceException;
import excepciones.anticuarioExcepciones.ArticuloException;
import excepciones.anticuarioExcepciones.ContratoException;
import excepciones.anticuarioExcepciones.PujaException;
import excepciones.anticuarioExcepciones.VentaException;
import gui.ReturnButton;
import gui.articulo.*;
import gui.articulo.control.ControlBuscarArticuloMenu;
import gui.cliente.ClienteMenu;
import gui.cliente.ClienteModificar;
import gui.empleado.*;
import gui.ganancias.GananciasMenu;
import gui.login.*;
import gui.login.control.ControlLogin;
import gui.menu.*;
import gui.menu.control.*;
import gui.subasta.*;
import gui.subasta.lote.CrearLotePanel;
import gui.subasta.puja.PujaMenu;
import gui.venderMenu.VenderMenu;
import lector.*;
import persona.cliente.*;
import persona.usuario.*;
import subasta.*;
import ventas.*;

// Este es nuestro frame, donde tendremos todos los paneles cargados

public class App extends JFrame{

	
	private static ReturnButton retButton;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6434601693439266337L;

	private static Aplicacion app = null;
	private static LoginPanel login;
	private static MainMenuGerente mainGerente = null;
	private static MainMenuEmpleado mainEmpleado = null;
	private static SubastaMenu subastaMenu = null;
	private static ArticuloMenu articuloMenu = null;
	private static EmpleadoMenu empleadoMenu = null;
	private static EmpleadoModificar empleadoModificar = null;
	private static ClienteMenu clienteMenu = null;
	private static ClienteModificar clienteModificar = null;
	private static CrearLotePanel crearLote = null;
	private static GananciasMenu gananciasMenu = null;
	private static PujaMenu pujaMenu = null;
	private static VenderMenu ventaMenu = null;

	public App() throws IOException{
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		articulos = Lector.leerArticulosNuevos("ficheros/EjemploDeArticulosNuevos.txt");
		PrintWriter writer = new PrintWriter("ficheros/EjemploDeArticulosNuevos.txt");
		writer.print("");
		writer.close();
		try {
			app = Aplicacion.load("ficheros/anticuario.obj");
			for(Articulo e : articulos)
				app.getArticulos().add(e);
			
		} catch (Exception e2) {
			Gerente gerente = new Gerente("1", "Fibonacci", "Mi casa", 27027, "666666666", "fibon112@uam.es",
					666666);
			app = new Aplicacion(new ArrayList<Venta>(), articulos, new ArrayList<Subasta>(),
					new ArrayList<Lote>(), gerente, new ArrayList<Empleado>(), new ArrayList<Cliente>());
		}
		
		App.retButton = new ReturnButton(app, this.getContentPane());
		
		this.getContentPane().setLayout(new CardLayout());
		
		login=new LoginPanel();
		mainGerente= new MainMenuGerente();
		mainEmpleado = new MainMenuEmpleado();
		subastaMenu = new SubastaMenu(app);
		articuloMenu = new ArticuloMenu(app);
		empleadoMenu= new EmpleadoMenu(app);
		empleadoModificar = new EmpleadoModificar();
		clienteMenu = new ClienteMenu(app);
		clienteModificar = new ClienteModificar();
		crearLote = new  CrearLotePanel(app);
		gananciasMenu = new GananciasMenu(app);
		pujaMenu = new PujaMenu(app);
		ventaMenu = new VenderMenu(app);
		
		this.getContentPane().add(mainGerente, "MainMenuGerente");
		this.getContentPane().add(mainEmpleado, "MainMenuEmpleado");
		this.getContentPane().add(login,"Login");
		this.getContentPane().add(subastaMenu,"SubastaMenu");
		this.getContentPane().add(crearLote,"CrearLote");
		this.getContentPane().add(articuloMenu,"ArticuloMenu");
		this.getContentPane().add(empleadoMenu,"EmpleadoMenu");
		this.getContentPane().add(empleadoModificar,"EmpleadoModificar");
		this.getContentPane().add(clienteMenu,"ClienteMenu");
		this.getContentPane().add(clienteModificar, "ClienteModificar");
		this.getContentPane().add(gananciasMenu, "GananciasMenu");
		this.getContentPane().add(pujaMenu, "PujaMenu");
		this.getContentPane().add(ventaMenu, "VenderMenu");
		
		/*Esto setea el panel que se muestre al principio*/
		((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(),"Login");
		
		login.setControlador(new ControlLogin(app, login, this.getContentPane()));
		mainEmpleado.setControlador(new ControlLogout(app,null,this.getContentPane()),
				new ControlBuscarCliente(app,null,this.getContentPane()),
				new ControlBuscarArticulo(app,null,this.getContentPane()),
				new ControlRegistrarPuja(app,null,this.getContentPane()), 
				new gui.cliente.control.ControlNuevoClienteMenu(app, clienteMenu, this.getContentPane()), 
				new ControlVender(app,null,this.getContentPane()));
		mainGerente.setControlador(new ControlLogout(app,null,this.getContentPane()),
				new ControlBuscarCliente(app,null,this.getContentPane()),
				new ControlBuscarArticulo(app,null,this.getContentPane()),
				new ControlGestionarSubasta(app,null,this.getContentPane()), 
				new ControlGestionarEmpleados(app,null,this.getContentPane()), 
				new ControlConsultarGanancias(app,null,this.getContentPane()));
		articuloMenu.setControlador(new ControlBuscarArticuloMenu(app,articuloMenu,this.getContentPane()));
		clienteMenu.setControlador(new gui.cliente.control.ControlNuevoClienteMenu(app, clienteMenu, this.getContentPane()),
				new gui.cliente.control.ControlModificarClienteMenu(app, clienteModificar, this.getContentPane()),
				new gui.cliente.control.ControlBuscarCliente(app,clienteMenu,this.getContentPane()));
		clienteModificar.setControlador(new gui.cliente.control.ControlActualizar(app,clienteModificar,this.getContentPane()));
		empleadoMenu.setControlador(new gui.empleado.control.ControlNuevoEmpleadoMenu(app, empleadoMenu, this.getContentPane()),
				new gui.empleado.control.ControlModificarEmpleadoMenu(app, empleadoModificar, this.getContentPane()),
				new gui.empleado.control.ControlBuscarEmpleado(app, empleadoMenu, this.getContentPane()));
		empleadoModificar.setControlador(new gui.empleado.control.ControlActualizar(app,empleadoModificar,this.getContentPane()));
		ventaMenu.setControlador(new gui.venderMenu.control.ControlBuscarCliente(app, ventaMenu,this.getContentPane()),
				new gui.venderMenu.control.ControlBuscarArticuloMenu(app, ventaMenu, this.getContentPane()),
				new gui.venderMenu.control.ControlVender(app, ventaMenu, this.getContentPane()));
		gananciasMenu.setControlador(new gui.ganancias.control.ControlBuscarGanancias(app,gananciasMenu,this.getContentPane()));
		subastaMenu.setControlador(new gui.subasta.control.ControlCrearSubasta(app, subastaMenu, this.getContentPane()),
				new gui.subasta.control.ControlCrearLote(app, subastaMenu, this.getContentPane()),
				new gui.subasta.control.ControlCerrarSubasta(app, subastaMenu, this.getContentPane()),
				new gui.subasta.control.ControlCancelarSubasta(app, subastaMenu, this.getContentPane()));
		pujaMenu.setControlador(new gui.subasta.puja.control.ControlBuscarSubasta_Puja(app, pujaMenu, this.getContentPane()),
				new gui.subasta.puja.control.ControlRegistrar(app, pujaMenu, this.getContentPane()),
				new gui.subasta.puja.control.ControlBuscarCliente_Puja(app, pujaMenu, this.getContentPane()));
		crearLote.setControlador(new gui.subasta.lote.control.ControlBuscarArticuloLote(app, crearLote, this.getContentPane()),
				new gui.subasta.lote.control.ControlCrearLote(app, crearLote, this.getContentPane()),
				new gui.subasta.lote.control.ControlDescartar(app, crearLote, this.getContentPane()),
				new gui.subasta.lote.control.ControlAnadir(app, crearLote, this.getContentPane()));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	try {
            		App.app.setUser(null);
        			App.app.save("ficheros/anticuario.obj");
        		} catch (IOException ex) {
        			ex.printStackTrace();
        		}
                e.getWindow().dispose();
            }
        });
		App.updateTables();
	}
	
	public static void updateTables(){
		subastaMenu.updateTables();
		empleadoMenu.updateTables();
		clienteMenu.updateTables();
		crearLote.updateTables();
		gananciasMenu.updateTables();
		pujaMenu.updateTables();
		ventaMenu.updateTables();
	}
	
	public static Aplicacion getApp(){
		return App.app;
	}
	
	public static ReturnButton getRetButton(){
		return App.retButton;
	}

	public static LoginPanel getLogin() {
		return login;
	}

	public static MainMenuGerente getMainGerente() {
		return mainGerente;
	}

	public static MainMenuEmpleado getMainEmpleado() {
		return mainEmpleado;
	}

	public static SubastaMenu getSubastaMenu() {
		return subastaMenu;
	}

	public static ArticuloMenu getArticuloMenu() {
		return articuloMenu;
	}

	public static EmpleadoMenu getEmpleadoMenu() {
		return empleadoMenu;
	}

	public static EmpleadoModificar getEmpleadoModificar() {
		return empleadoModificar;
	}

	public static ClienteMenu getClienteMenu() {
		return clienteMenu;
	}

	public static ClienteModificar getClienteModificar() {
		return clienteModificar;
	}

	public static CrearLotePanel getCrearLote() {
		return crearLote;
	}

	public static GananciasMenu getGananciasMenu() {
		return gananciasMenu;
	}

	public static PujaMenu getPujaMenu() {
		return pujaMenu;
	}

	public static VenderMenu getVentaMenu() {
		return ventaMenu;
	}
	
}
