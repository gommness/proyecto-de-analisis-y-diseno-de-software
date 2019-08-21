package test.testsarticulos;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import articulos.voluminoso.Voluminoso;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Pruebas unitarias de voluminoso
 */
public class VoluminosoTest {
	private Voluminoso v;
	private Voluminoso v2;
	private Voluminoso v3;

	@Before
	public void setUp() throws Exception {
		v = new Voluminoso("es una cosa", "1927", LocalDate.now(), 40, 60, false, true, 50, 120, 130, 140);
		v2 = new Voluminoso("es una cosa2", "1927", LocalDate.now(), 40, 60, false, true, 15, 1, 1, 1);
		v3 = new Voluminoso("es una cosa3", "1927", LocalDate.now(), 40, 60, false, true, 21, 1, 1, 1);
	}

	@Test
	public void testCalcularGastosEnvio() {
		assertEquals(50, v.calcularGastosEnvio(), 0);
		assertEquals(20, v2.calcularGastosEnvio(), 0);
		assertEquals(24, v3.calcularGastosEnvio(), 0);
	}

	@Test
	public void testCalcularDescuento() {
		assertEquals(0, v.calcularDescuento(), 0);
		assertEquals(0, v2.calcularDescuento(), 0);
		assertEquals(0, v3.calcularDescuento(), 0);
	}
}
