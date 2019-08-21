package test.testspersona;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import excepciones.ExistenceException;
import persona.cliente.Estandar;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Pruebas unitarias de contrato de tipo estandar
 */
public class EstandarTest {

	private Estandar e;

	@Before
	public void setUp() throws Exception {
		e = new Estandar();
	}

	@Test
	public void testAplicarDescuento() {
		e.setAcumulados(240);
		assertTrue(e.aplicarDescuento());
		e.setAcumulados(199);
		assertFalse(e.aplicarDescuento());
	}

	@Test
	public void testAplicarInscripcionGratis() throws ExistenceException {
		assertTrue(e.tieneInscripcionGratis());
		for (int i = 0; i < 5; i++)
			e.aplicarInscripcionGratis();
		assertFalse(e.tieneInscripcionGratis());
	}

}
