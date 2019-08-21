package test.testsarticulos;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import articulos.menudencia.Menudencia;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Pruebas unitarias de la menudencia
 */
public class MenudenciaTest {

	private Menudencia m;

	@Before
	public void setUp() throws Exception {
		m = new Menudencia("es una cosa", "1927", LocalDate.now(), 40, 60, false, true, 50);
	}

	@Test
	public void testCalcularGastosEnvio() {
		assertEquals(0, m.calcularGastosEnvio(), 0);
	}

	@Test
	public void testCalcularDescuento() {
		assertEquals(m.getPrecioObjetivo() * m.getDescuento() / 100, m.calcularDescuento(), 0);
	}

}
