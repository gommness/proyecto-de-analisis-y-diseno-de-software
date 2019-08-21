/**
 * 
 */
package test.testsventas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import articulos.voluminoso.Voluminoso;
import excepciones.anticuarioExcepciones.VentaException;
import persona.cliente.Cliente;
import ventas.VentaDomicilio;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Pruebas unitarias de la venta a domicilio
 */
public class VentaDomicilioTest {
	private VentaDomicilio vd;
	private Cliente c;
	private Voluminoso vol;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c = new Cliente("45465446G", "Javi", "Madrid, Mexico", 25048, "666666666", "algo@ucm.es");
		vol = new Voluminoso("es una cosa2", "1927", LocalDate.now(), 40, 60, false, true, 15, 1, 1, 1);
		vd = new VentaDomicilio(c, vol);

	}

	/**
	 * Test method for {@link ventas.VentaDomicilio#calcularPrecio()}.
	 */
	@Test
	public void testCalcularPrecio() {
		assertEquals(vd.calcularPrecio(), vol.getPrecioObjetivo() + vol.calcularGastosEnvio(), 0);
	}

	/**
	 * Test method for {@link ventas.VentaDomicilio#vender()}.
	 */
	@Test
	public void testVender() {
		try {
			vd.vender();
		} catch (VentaException e) {
			fail(e.getMessage());
		}

		/* no se puede vender si está vendido */
		try {
			vd.vender();
		} catch (VentaException e) {
			assertTrue(true);
		}
		assertEquals(vd.getGanancia(), vd.calcularPrecio() - vd.getArticulo().getCosteAdquisicion(), 0);
	}

}
