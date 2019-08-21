package lector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

import articulos.Articulo;
import articulos.menudencia.Menudencia;
import articulos.subastable.ObraArte;
import articulos.subastable.TipoObra;
import articulos.voluminoso.Dimension;
import articulos.voluminoso.Voluminoso;

/*
 * este lector funciona para el archivo que nos pasan. Sin embargo, tal y como explico mas abajo,
 * no podria leer, por ejemplo, de un archivo en el que especificamos que los articulos han sido vendidos etc
 * Ademas, con un lector como este, no podriamos leer lotes con contenido.
 * Es por esto que tal vez nos interese "ampliar" las clases de lectores:
 * 		p.e. un lector para articulos nuevos, otro para articulos vendidos, otro para lotes, otro para subastas etc.
 * 
 * Ademas, hay que crear un lector para empleados, gerente y clientes
 * 
 * Hay que discutir esto.
 */
/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Clase donde se guardan los metodos para leer de ficheros y cargar
 *         datos
 */
public abstract class Lector {

	/**
	 * Este metodo se encarga de parsear un array de Strings que contienen los
	 * datos de una menudencia para crearla
	 * 
	 * @param st
	 *            el array de Strings que contienen los datos
	 * @return la menudencia creada
	 * @throws Exception
	 *             en caso de que el numero de datos para crear la menudencia
	 *             sea incorrecto o haya un error al formatear un double
	 */
	public static Menudencia leerMenudencia(String[] st) throws Exception {
		if (st.length != 7)
			throw new Exception("error en el numero de argumentos");

		String descripcion = st[1];
		String ano = st[2];
		LocalDate fechaAdquisicion = parseFecha(st[3]);
		double coste = Double.parseDouble(st[4]);
		double precio = Double.parseDouble(st[5]);
		double descuento = Double.parseDouble(st[6]);
		return new Menudencia(descripcion, ano, fechaAdquisicion, coste, precio, false, true, descuento);
	}

	/**
	 * Este metodo se encarga de parsear un array de Strings que contienen los
	 * datos de un voluminoso para crearlo
	 * 
	 * @param st
	 *            el array de Strings que contienen los datos
	 * @return el voluminoso creado
	 * @throws Exception
	 *             en caso de que el numero de datos para crear el voluminoso
	 *             sea incorrecto o haya un error al formatear un double
	 */
	public static Voluminoso leerVoluminoso(String[] st) throws Exception {
		if (st.length != 10)
			throw new Exception();

		String descripcion = st[1];
		String ano = st[2];
		LocalDate fechaAdquisicion = parseFecha(st[3]);
		double coste = Double.parseDouble(st[4]);
		double precio = Double.parseDouble(st[5]);
		double peso = Double.parseDouble(st[6]);
		double alto = Double.parseDouble(st[7]);
		double ancho = Double.parseDouble(st[8]);
		double largo = Double.parseDouble(st[9]);
		return new Voluminoso(descripcion, ano, fechaAdquisicion, coste, precio, false, true, peso,
				new Dimension(alto, ancho, largo));
	}

	/**
	 * Este metodo sirve para "convertir a boolean" el ultimo argumento de una
	 * obra de arte, que en el fichero es S (true, representando que tiene
	 * certificado) o N (false, representando que no lo tiene)
	 * 
	 * @param st
	 *            el String que contendra a este ultimo parametro
	 * @return true si st es S, false si no lo es
	 */
	public static boolean parseCertificated(String st) {
		return st.equalsIgnoreCase("S");
	}

	/**
	 * Este metodo se encarga de parsear un array de Strings que contienen los
	 * datos de una obra de arte para crearla
	 * 
	 * @param st
	 *            el array de Strings que contienen los datos
	 * @return la obra de arte creada
	 * @throws Exception
	 *             en caso de que el numero de datos para crear la obra de arte
	 *             sea incorrecto o haya un error al formatear un double
	 */
	public static ObraArte leerArte(String[] st) throws Exception {
		if (st.length != 9)
			throw new Exception();

		String descripcion = st[1];
		String ano = st[2];

		LocalDate fechaAdquisicion = parseFecha(st[3]);

		double coste = Double.parseDouble(st[4]);
		double precio = Double.parseDouble(st[5]);
		String autor = st[6];
		TipoObra tipo = TipoObra.valueOf(st[7]);
		boolean certificado = Lector.parseCertificated(st[8]);
		return new ObraArte(descripcion, ano, fechaAdquisicion, coste, precio, false, true, autor, certificado, tipo,
				false);
	}

	public static LocalDate parseFecha(String st) {
		String[] sFecha = st.split("/");
		int day = Integer.parseInt(sFecha[0]);
		int month = Integer.parseInt(sFecha[1]);
		int year = Integer.parseInt(sFecha[2]);
		LocalDate fecha = LocalDate.of(year, month, day);
		return fecha;
	}
	public static LocalDate parseFecha(String st, String token) {
		String[] sFecha = st.split(token);
		int day = Integer.parseInt(sFecha[2]);
		int month = Integer.parseInt(sFecha[1]);
		int year = Integer.parseInt(sFecha[0]);
		LocalDate fecha = LocalDate.of(year, month, day);
		return fecha;
	}

	/**
	 * Este metodo sirve para, dado el path de un fichero del que leer, crear
	 * una lista de articulos a partir de los datos guardados en dicho fichero
	 * 
	 * @param path
	 *            la localizacion del fichero dentro del sistema
	 * @return la lista con los articulos
	 * @throws IOException
	 */
	public static ArrayList<Articulo> leerArticulosNuevos(String path) throws IOException {
		String linea;
		String[] st;
		Articulo a;
		ArrayList<Articulo> output = new ArrayList<Articulo>();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		while ((linea = buffer.readLine()) != null) {
			st = linea.split(";");
			a = null;
			if (st[0].equalsIgnoreCase("M")) {
				try {
					a = leerMenudencia(st);
				} catch (Exception e) {
					a = null;
				}
			} else if (st[0].equalsIgnoreCase("V")) {
				try {
					a = leerVoluminoso(st);
				} catch (Exception e) {
					a = null;
				}
			} else if (st[0].equalsIgnoreCase("A")) {
				try {
					a = leerArte(st);
				} catch (Exception e) {
					a = null;
				}
			}
			if (a != null)
				output.add(a);
		}
		buffer.close();
		return output;
	}

}
