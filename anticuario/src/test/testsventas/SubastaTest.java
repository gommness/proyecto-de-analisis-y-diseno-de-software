/**
 * 
 */
package test.testsventas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import articulos.subastable.ObraArte;
import articulos.subastable.TipoObra;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import excepciones.ExistenceException;
import excepciones.anticuarioExcepciones.ContratoException;
import excepciones.anticuarioExcepciones.PujaException;
import excepciones.anticuarioExcepciones.SubastaException;
import persona.cliente.Cliente;
import subasta.Puja;
import subasta.Subasta;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Pruebas unitarias de la subasta
 */
public class SubastaTest {

	private ObraArte obra;
	private Subasta subasta1;
	private Subasta subasta2;
	private Subasta subasta3;
	private Cliente carlos;
	private Cliente javi;
	private Cliente miguel;
	private Subasta subasta4;
	private Subasta subasta5;
	private Subasta subasta6;
	private Subasta subasta7;

	/*
	 * falta ver, en el constructor de subasta, si la obra a subastar puede ser
	 * subastable... oooooo, podr�amos hacer obra.setSubastable(true) en el
	 * constructor de subasta
	 */
	@Before
	public void setup() throws Exception {
		obra = new ObraArte("Es una piedra", "201X", LocalDate.now(), 0.25, 0.5, false, true, "yo", false,
				TipoObra.Especial, true);
		subasta1 = new Subasta(3, 20.99, 40, LocalDate.now(), obra);
		subasta2 = new Subasta(3, 20.99, 40, LocalDate.now(), obra);
		subasta3 = new Subasta(1, 20.99, 40, LocalDate.now().minusDays(4), obra);
		subasta4 = new Subasta(3, 20.99, 40, LocalDate.now(), obra);
		subasta5 = new Subasta(3, 20.99, 40, LocalDate.now(), obra);
		subasta6 = new Subasta(3, 20.99, 40, LocalDate.now(), obra);
		subasta7 = new Subasta(3, 20.99, 40, LocalDate.now(), obra);
		carlos = new Cliente("45465446G", "Carlos", "Madrid, Mexico", 25048, "666666666",
				"CarlosTheBestFull_xXD4rkL0rdXx@yahoo.es");
		carlos.crearContratoEstandar();
		javi = new Cliente("7639812645", "Javi", "mi casa", 834284, "734764329", "javiNoEsDeLaUCM@uam.es");
		javi.crearContratoPreferente();
		miguel = new Cliente("1234124312", "Miguel", "alcala", 894327432, "83485293", "brgvur");
	}

	/**
	 * Test method for {@link subasta.Subasta#cancelarSubasta()}.
	 * 
	 * @throws FailedInternetConnectionException
	 * @throws InvalidEmailAddressException
	 * @throws ExistenceException
	 * @throws ContratoException
	 */

	@Test
	public void testCancelarSubasta() throws InvalidEmailAddressException, FailedInternetConnectionException,
			ExistenceException, ContratoException {
		try {
			subasta1.cancelarSubasta();
		} catch (SubastaException e) {
			fail(e.getMessage());
		}
		try {
			subasta1.cancelarSubasta();
		} catch (SubastaException e) {
			assertTrue(true);
		}
		try {
			subasta2.registrarPuja(carlos, new Puja(100, carlos));
		} catch (PujaException e) {
			fail(e.getMessage());
		}
		try {
			subasta2.cancelarSubasta();
		} catch (SubastaException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link subasta.Subasta#registrarPuja(persona.cliente.Cliente, subasta.Puja)}
	 * .
	 * 
	 * @throws FailedInternetConnectionException
	 * @throws InvalidEmailAddressException
	 * @throws SubastaException
	 * @throws ExistenceException
	 * @throws PujaException
	 * @throws ContratoException
	 */
	@Test
	public void testRegistrarPuja() throws InvalidEmailAddressException, FailedInternetConnectionException,
			SubastaException, ExistenceException, PujaException, ContratoException {
		Puja puja1 = new Puja(1000, javi);
		Puja puja2;
		ArrayList<Cliente> interesados = new ArrayList<>();
		ArrayList<Cliente> participantes = new ArrayList<>();

		// pujamos una cantidad invalida
		subasta1.registrarPuja(carlos,
				new Puja(50, carlos));/* Intentar registrar una puja valida */
		assertTrue(subasta1.getParticipantes().contains(carlos));

		puja2 = subasta1.getUltimaPuja();
		interesados.addAll(subasta1.getClientesInteresados());
		participantes.addAll(subasta1.getParticipantes());

		// pujamos una cantidad casi valida
		try {
			subasta1.registrarPuja(carlos, new Puja(50, carlos));
			subasta1.registrarPuja(carlos, new Puja(50.04, carlos));
		} catch (PujaException e) {
			assertTrue(true);
		}
		// pujamos con un cliente sin contrato
		try {
			subasta1.registrarPuja(miguel, 100);
		} catch (ExistenceException e) {
			assertTrue(true);
		}

		subasta1.registrarPuja(javi, puja1);
		// comprobamos que la ultima puja, la lista de interesados y la de
		// participantes
		// se haya actualizado
		assertTrue(participantes.size() < subasta1.getParticipantes().size());
		assertTrue(interesados.size() < subasta1.getClientesInteresados().size());
		assertFalse(subasta1.getUltimaPuja() == puja2);
		// comprobamos que el valor que han de superar las siguientes pujas se
		// ha actualizado
		try {
			subasta1.registrarPuja(javi, 1001);
		} catch (PujaException e) {
			assertTrue(true);
		}

		interesados = new ArrayList<>();
		interesados.addAll(subasta1.getClientesInteresados());
		participantes = new ArrayList<>();
		participantes.addAll(subasta1.getParticipantes());
		subasta1.registrarPuja(javi, 2000);
		// comprobamos ahora que no se han actualizado las listas, pues es el
		// mismo cliente
		assertTrue(participantes.size() == subasta1.getParticipantes().size());
		assertTrue(interesados.size() == subasta1.getClientesInteresados().size());

		// intentamos pujar en una cuya fecha ya ha expirado
		try {
			subasta3.registrarPuja(carlos, new Puja(100, carlos));
		} catch (PujaException e) {
			assertTrue(true);
		}
		// intentamos pujar en una cancelada
		subasta3.cancelarSubasta();
		try {
			subasta3.registrarPuja(carlos, new Puja(110, carlos));
		} catch (PujaException e) {
			assertTrue(true);
		}
		// intentamos pujar en una cerrada
		subasta1.setFechaInicio(subasta1.getFechaInicio().minusDays(100));
		subasta1.cerrarSubasta();
		try {
			subasta1.registrarPuja(carlos, new Puja(110, carlos));
		} catch (PujaException e) {
			assertTrue(true);
		}

	}

	@Test
	public void testPujaContratoEstandar() throws InvalidEmailAddressException, FailedInternetConnectionException,
			PujaException, ExistenceException, ContratoException {
		subasta1.registrarPuja(carlos, 1000);
		subasta2.registrarPuja(carlos, 1000);
		subasta4.registrarPuja(carlos, 1000);
		subasta5.registrarPuja(carlos, 1000);
		subasta6.registrarPuja(carlos, 1000);
		// Carlos, que tiene contrato estandar, ya habra gastado sus 5
		// inscripciones gratis
		subasta7.registrarPuja(carlos, 1000);
		assertFalse(subasta7.getGanancias() == subasta1.getGanancias());
		// esto sera falso porque en las ganancias de la sub6, estara guardado
		// el suplemento de la cuota que carlos tuvo que pagar

	}

	@Test
	public void testPujaContratoPreferente() throws InvalidEmailAddressException, FailedInternetConnectionException,
			PujaException, ExistenceException, ContratoException {
		subasta1.registrarPuja(javi, 1000);
		subasta2.registrarPuja(javi, 1000);
		subasta4.registrarPuja(javi, 1000);
		subasta5.registrarPuja(javi, 1000);
		subasta6.registrarPuja(javi, 1000);
		// Javi en cambio tiene tantas inscripciones gratis como quiera
		subasta7.registrarPuja(javi, 1000);
		assertTrue(subasta7.getGanancias() == subasta1.getGanancias());
		// esto sera verdad porque javi no paga inscripciones

	}

}
