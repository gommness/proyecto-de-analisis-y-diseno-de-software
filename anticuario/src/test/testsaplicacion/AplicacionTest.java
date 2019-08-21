/**
 * 
 */
package test.testsaplicacion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import aplicacion.Aplicacion;
import articulos.Articulo;
import articulos.menudencia.Menudencia;
import articulos.subastable.Lote;
import articulos.subastable.ObraArte;
import articulos.subastable.TipoObra;
import excepciones.anticuarioExcepciones.ArticuloException;
import persona.cliente.Cliente;
import persona.usuario.Empleado;
import persona.usuario.Gerente;
import subasta.Subasta;
import ventas.Venta;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Pruebas unitarias de la aplicacion
 */
public class AplicacionTest {

	private Aplicacion app;
	private ArrayList<Venta> ventas;
	private ArrayList<Articulo> articulos;
	private ArrayList<Subasta> subastas;
	private ArrayList<Lote> lotes;
	private Gerente gerente;
	private ArrayList<Empleado> empleados;
	private ArrayList<Cliente> clientes;

	private Empleado empleado;
	private Cliente cliente;
	private ObraArte o;
	private Menudencia m;
	private Menudencia m2;
	private Venta venta;
	private Subasta subasta;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ventas = new ArrayList<Venta>();
		articulos = new ArrayList<Articulo>();
		subastas = new ArrayList<Subasta>();
		lotes = new ArrayList<Lote>();
		gerente = new Gerente("34252345D", "Karurosu", "su casa", 27, "666272727", "CarlosTBF_XxD4rkLorDxX@yahoo.es",
				72);
		empleados = new ArrayList<Empleado>();
		clientes = new ArrayList<Cliente>();
		app = new Aplicacion(ventas, articulos, subastas, lotes, gerente, empleados, clientes);

		m = new Menudencia("piedra", "20xx", LocalDate.now(), 1, 1, false, true, 0);
		m2 = new Menudencia("piedra", "20xx", LocalDate.now(), 1, 1, false, true, 0);
		empleado = new Empleado("11111111", "marco", "e", 1, "1", "2", 1);
		app.darAltaEmpleado(empleado);
		cliente = new Cliente("22222222", "polo", "b", 3, "213412", "akfivrwrb");
		app.darAltaCliente(cliente);
		o = new ObraArte("llevo desde las 17 en esto", "2016", LocalDate.now(), 1, 1, false, false, "Javier", true,
				TipoObra.Especial, true);
		subasta = app.iniciarSubasta(0, 4, 5, LocalDate.now(), o);
		venta = app.crearVenta(cliente, m2);
		venta.vender();
	}

	/**
	 * Test method for {@link aplicacion.Aplicacion#load(java.lang.String)}.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testSaveLoad() throws FileNotFoundException, ClassNotFoundException, IOException {
		Aplicacion.load("app.obj");// intentamos cargar un fichero que no
									// existe, de ahi que se espere una
									// excepcion FileNotFoundException
		app.save("app.obj");// guardamos los contenidos de la app en un fichero.
							// NOTA: el path hay que cambiarlo para distintos
							// pc's
		Aplicacion app2 = Aplicacion.load("app.obj");// cargamos los datos de la
														// app en otra app
														// distinta
		app2.save("app2.obj");// guardamos los datos de la nueva app en otro
								// fichero NOTA: lo mismo de la nota anterior
		File file1 = new File("app.obj");
		File file2 = new File("app2.obj");// creamos dos variables de tipo
											// fichero con los dos ficheros
											// antes creados
		assertTrue(FileUtils.contentEquals(file1, file2));// comprobamos que el
															// contenido de
															// ambos son iguales
	}

	/**
	 * Test method for
	 * {@link aplicacion.Aplicacion#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLog() {
		assertTrue(app.login(empleado.getCuenta(), empleado.getContrasena()) != null); // nos
																						// logeamos
		assertFalse(app.login(empleado.getCuenta(), empleado.getContrasena()) != null); // intentamos
																						// logear
																						// cuando
																						// ya
																						// hay
																						// alguien
																						// logeado
		app.logout();// deslogueamos
		assertTrue(app.login(empleado.getCuenta(), empleado.getContrasena()) != null);// comprobamos
																						// que
																						// se
																						// ha
																						// deslogueado
																						// correctamente
		app.logout();// deslogueamos de nuevo para ultima prueba
		assertFalse(app.login("a", "b") != null);// logueamos con cuenta que no
													// existe
	}

	/**
	 * Test method for
	 * {@link aplicacion.Aplicacion#buscarCliente(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testBuscarCliente() {
		assertTrue(app.buscarCliente("22222222", "", "").contains(cliente));
		assertTrue(app.buscarCliente("", "polo", "").contains(cliente));
		assertTrue(app.buscarCliente("", "", "213412").contains(cliente));
		assertTrue(app.buscarCliente("", "polo", "213412").contains(cliente));
		assertTrue(app.buscarCliente("22222222", "", "213412").contains(cliente));
		assertTrue(app.buscarCliente("22222222", "polo", "").contains(cliente));
		assertTrue(app.buscarCliente("22222222", "polo", "213412").contains(cliente));
		assertFalse(app.buscarCliente("22222222", "marco", "22").contains(cliente));
	}

	/**
	 * Test method for
	 * {@link aplicacion.Aplicacion#buscarSubasta(java.time.LocalDate, java.time.LocalDate)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBuscarSubasta() throws Exception {
		assertTrue(app.buscarSubasta(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)).contains(subasta));
		assertFalse(app.buscarSubasta(LocalDate.now().minusDays(4), LocalDate.now().minusDays(3)).contains(subasta));
		assertFalse(app.buscarSubasta(LocalDate.now().plusDays(4), LocalDate.now().plusDays(5)).contains(subasta));
	}

	/**
	 * Test method for
	 * {@link aplicacion.Aplicacion#crearVenta(persona.cliente.Cliente, articulos.Articulo)}
	 * .
	 * 
	 * @throws ArticuloException
	 */
	@Test
	public void testCrearVenta() throws ArticuloException {
		Venta w = app.crearVenta(cliente, m);
		assertTrue(app.getVentas().contains(w));
	}

	/**
	 * Test method for
	 * {@link aplicacion.Aplicacion#buscarVenta(java.time.LocalDate, java.time.LocalDate)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBuscarVenta() throws Exception {
		assertTrue(app.buscarVenta(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)).contains(venta));
		assertFalse(app.buscarVenta(LocalDate.now().minusDays(2), LocalDate.now().minusDays(1)).contains(venta));
		assertFalse(app.buscarVenta(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)).contains(venta));
		assertFalse(app.buscarVenta(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2))
				.contains(new Venta(cliente, new Menudencia("", "", LocalDate.now(), 0, 0, false, true, 0))));
	}

	/**
	 * Test method for
	 * {@link aplicacion.Aplicacion#getTotalGanancias(java.time.LocalDate, java.time.LocalDate)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetTotalGanancias() throws Exception {
		double expect = venta.getGanancia();// en el setup solo hemos vendido
											// esta venta
		assertTrue(app.getTotalGanancias(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)) == expect);
	}

	/**
	 * Test method for
	 * {@link aplicacion.Aplicacion#buscarArticulo(int, double, java.time.LocalDate)}
	 * .
	 */
	@Test
	public void testBuscarArticulo() {
		assertTrue(app.buscarArticulo(0, 0, m2.getFechaAquisicion(), null).contains(m2));
		assertTrue(app.buscarArticulo(0, m2.getPrecioObjetivo(), null, null).contains(m2));
		assertTrue(app.buscarArticulo(m2.getId(), 0, null, null).contains(m2));
		assertTrue(app.buscarArticulo(m2.getId(), m2.getPrecioObjetivo(), null, null).contains(m2));
		assertTrue(app.buscarArticulo(m2.getId(), 0, m2.getFechaAquisicion(), null).contains(m2));
		assertTrue(app.buscarArticulo(m2.getId(), m2.getPrecioObjetivo(), m2.getFechaAquisicion(), null).contains(m2));
		assertTrue(app.buscarArticulo(0, 0, null, null).contains(m2));
		assertFalse(app.buscarArticulo(m2.getId(), m2.getPrecioObjetivo(), m2.getFechaAquisicion(), null).contains(m));
	}

}
