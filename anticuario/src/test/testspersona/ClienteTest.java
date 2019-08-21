package test.testspersona;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import excepciones.ExistenceException;
import persona.cliente.Cliente;
import persona.cliente.Estandar;
import persona.cliente.Preferente;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Pruebas unitarias de la cliente
 */
public class ClienteTest {
	private Cliente c;

	@Before
	public void setup() throws Exception {
		c = new Cliente("45465446G", "Javi", "Madrid, Mexico", 25048, "666666666", "algo@ucm.es");
		c.setContrato(new Estandar());
	}

	@Test
	public void testCambiarContrato() throws ExistenceException {
		c.crearContratoEstandar();
		assertTrue(c.getContrato() instanceof Estandar);
		c.cambiarContrato();
		assertTrue(c.getContrato() instanceof Preferente);
	}

}
