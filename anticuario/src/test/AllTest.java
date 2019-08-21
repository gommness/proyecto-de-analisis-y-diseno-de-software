package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.testsaplicacion.AplicacionTest;
import test.testsarticulos.MenudenciaTest;
import test.testsarticulos.VoluminosoTest;
import test.testspersona.ClienteTest;
import test.testspersona.EstandarTest;
import test.testsventas.SubastaTest;
import test.testsventas.VentaDomicilioTest;
import test.testsventas.VentaTest;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Test secuencial de las pruebas unitarias
 */
@RunWith(Suite.class)
@SuiteClasses({ AplicacionTest.class, MenudenciaTest.class, VoluminosoTest.class, ClienteTest.class, EstandarTest.class,
		SubastaTest.class, VentaDomicilioTest.class, VentaTest.class })

public class AllTest {

}
