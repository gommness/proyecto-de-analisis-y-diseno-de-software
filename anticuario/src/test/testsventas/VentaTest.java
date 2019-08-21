package test.testsventas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import articulos.menudencia.Menudencia;
import articulos.voluminoso.Voluminoso;
import excepciones.anticuarioExcepciones.VentaException;
import persona.cliente.Cliente;
import ventas.Venta;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Pruebas unitarias de la venta
 */
public class VentaTest {
	private Venta v, v2;
	private Cliente c;
	private Menudencia m;
	private Voluminoso vol;

	@Before
	public void setup() throws Exception {
		c = new Cliente("45465446G", "Javi", "Madrid, Mexico", 25048, "666666666", "algo@ucm.es");
		c.crearContratoPreferente();
		m = new Menudencia("es una cosa", "1927", LocalDate.now(), 40, 60, false, true, 50);
		vol = new Voluminoso("es una cosa2", "1927", LocalDate.now(), 40, 60, false, true, 15, 1, 1, 1);
		v = new Venta(c, m); /* venta de menudencia */
		v2 = new Venta(c, vol); /* venta de voluminoso */
	}

	@Test
	public void testCalcularPrecio() {

		/* para menudencia le restamos el descuento */
		assertEquals(v.calcularPrecio(), m.getPrecioObjetivo() - m.calcularDescuento(), 0);

		/* para el voluminoso no hay cambio de precio si no es a domicilio */
		assertEquals(v2.calcularPrecio(), vol.getPrecioObjetivo(), 0);
	}

	/*
	 * Aqui de nuevo pasa lo de que el resultado no es el esperado porque el
	 * cliente no tiene un contrato (tiene null)
	 */
	@Test
	public void testVender() {
		try {
			v.vender();
		} catch (VentaException e) {
			fail(e.getMessage());
		}
		/* no se puede vender si está vendido */
		try {
			v.vender();
		} catch (VentaException e) {
			assertTrue(true);
		}
		assertEquals(v.getGanancia(), v.calcularPrecio() - v.getArticulo().getCosteAdquisicion(), 0);
	}

}
