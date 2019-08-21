package demostrador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import aplicacion.Aplicacion;
import articulos.Articulo;
import articulos.subastable.Lote;
import articulos.subastable.ObjetoSubasta;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import excepciones.ExistenceException;
import excepciones.anticuarioExcepciones.ArticuloException;
import excepciones.anticuarioExcepciones.ContratoException;
import excepciones.anticuarioExcepciones.PujaException;
import excepciones.anticuarioExcepciones.VentaException;
import lector.Lector;
import persona.cliente.Cliente;
import persona.usuario.Empleado;
import persona.usuario.Gerente;
import subasta.Subasta;
import ventas.Venta;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Demostrador basico de la aplicacion
 */
public class Demostrador {
	/**
	 * @param args un array de Strings. El primer argumento debe ser la localizacion del archivo con articulos nuevos
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		articulos = Lector.leerArticulosNuevos(args[0]);
		Gerente gerente = new Gerente("11235813F", "Fibonacci", "Mi casa", 27027, "666666666", "fibon112@uam.es",
				666666);
		Aplicacion app = new Aplicacion(new ArrayList<Venta>(), articulos, new ArrayList<Subasta>(),
				new ArrayList<Lote>(), gerente, new ArrayList<Empleado>(), new ArrayList<Cliente>());
		System.out.println("Estos son las descripciones de los articulos cargados");
		for (Articulo a : articulos)
			System.out.println(a.getDescripcion());
		System.out.println("Dando de alta empleados...");
		app.darAltaEmpleado("123435544K", "Homer Simpson", "Evergreen Terrace", 12345, "94845589", "Pepe@ucm.es",
				552755);
		app.darAltaEmpleado("512354568G", "Javi", "Madrid,España", 28028, "602876542", "algo@upm.es", 1213245);
		System.out.println("");
		System.out.println("Estos son los empleados de la tienda:");
		for (Empleado e : app.getEmpleados())
			System.out.println(e);

		System.out.println("Dando de alta clientes...");
		app.darAltaCliente("45465446G", "Carlos", "Madrid, Mexico", 25048, "666666666", "algo@ucm.es");
		app.darAltaCliente("55423314G", "Gommness", "Albacete", 27248, "666444466", "algo@upm.es");

		ArrayList<Cliente> clientes = app.buscarCliente("", "Carlos", "");
		System.out.println("");
		System.out.println("Estos son los clientes con nombre Carlos que he encontrado:");
		for (Cliente a : clientes) {
			a.crearContratoPreferente();
			System.out.println(a);
		}
		try {
			Articulo a = app.getArticulos().get(0);
			System.out.println("");
			System.out.println("Creando venta...");
			Venta v = app.crearVenta(clientes.get(0), a);
			System.out.println("Estas son las ventas que hemos creado:");
			System.out.println(v);
			System.out.println("Vendiendo...");
			app.getVentas().get(0).vender();

			System.out.println("Vemos si está vendido:\n" + app.getVentas().get(0));
		} catch (ArticuloException e1) {
			e1.printStackTrace();
		} catch (VentaException e1) {
			e1.printStackTrace();
		}

		System.out.println("");
		System.out.println("Estos son los lotes que hemos creado:");
		System.out.println(app.crearLote("Lote1",app.getArticulos().get(1), app.getArticulos().get(2)));

		try {
			app.iniciarSubasta(20, 30, 150, LocalDate.now(), app.getLotes().get(0));
		} catch (InvalidEmailAddressException | FailedInternetConnectionException | ArticuloException e1) {
			e1.printStackTrace();
		}
		try {
			app.iniciarSubasta(10, 50, 250, LocalDate.now(), (ObjetoSubasta) app.getArticulos().get(4));
		} catch (InvalidEmailAddressException | FailedInternetConnectionException | ArticuloException e1) {
			e1.printStackTrace();
		}
		System.out.println("Estas son las subastas creadas:");
		System.out.println(app.getSubastas().get(0));
		System.out.println("");
		System.out.println(app.getSubastas().get(1));
		System.out.println("Nos disponemos a registrar una puja");
		try {
			app.getSubastas().get(0).registrarPuja(clientes.get(0), 250);
		} catch (InvalidEmailAddressException | FailedInternetConnectionException | PujaException | ExistenceException
				| ContratoException e1) {
			e1.printStackTrace();
		}
		System.out.println("Vemos si existe esa ultima puja");
		System.out.print(app.getSubastas().get(0).getUltimaPuja() + "\n");
		app.save("ficheros\\app.obj");
		Aplicacion app2 = Aplicacion.load("ficheros\\app.obj");
		articulos = app2.getArticulos();
		System.out.println("\nComprobamos que los articulos siguen cargados");
		for (Articulo a : articulos)
			System.out.println(a.getDescripcion());
	}

}
