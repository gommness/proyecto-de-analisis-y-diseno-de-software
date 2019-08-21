package articulos.subastable;

import java.time.LocalDate;
import java.util.ArrayList;

import articulos.Articulo;
import excepciones.anticuarioExcepciones.LoteException;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Esta clase Lote almacena arrays de articulos
 */
public class Lote extends ObjetoSubasta {

	private static final long serialVersionUID = -7702501884654909081L;
	private ArrayList<Articulo> articulos;

	/**
	 * @param articulos
	 *            un array de articulos
	 */
	public Lote(String nombre,Articulo... articulos) {
		super("", "", LocalDate.now(), 0, 0, false, true);
		String descr = nombre;
		String anoOrigen = "";
		double coste = 0;
		double precio = 0;
		this.articulos = new ArrayList<>();
		for (Articulo a : articulos) {
			try {
				this.addArticulo(a);
			} catch (LoteException e) {
				continue;
			}
			descr += a.getDescripcion() + "\n";
			anoOrigen += a.getAnoOrigen() + "\n";
			coste += a.getCosteAdquisicion();
			precio += a.getPrecioObjetivo();
			// this.articulos.add(a);
			a.setDisponible(false);
		}
		this.setDescripcion(descr);
		this.setAnoOrigen(anoOrigen);
		this.setCosteAdquisicion(coste);
		this.setPrecioObjetivo(precio);
	}
	
	public Lote(String nombre) {
		super("", "", LocalDate.now(), 0, 0, false, true);
		String descr = nombre;
		String anoOrigen = "";
		double coste = 0;
		double precio = 0;
		this.articulos = new ArrayList<>();
		this.articulos = new ArrayList<>();
		this.setDescripcion(descr);
		this.setAnoOrigen(anoOrigen);
		this.setCosteAdquisicion(coste);
	}

	/**
	 * Este metodo sirve para anadir un articulo a la lista de articulos que
	 * componen un lote
	 * 
	 * @param a
	 *            el articulo que se desea anadir
	 * @throws LoteException
	 */
	public void addArticulo(Articulo a) throws LoteException {
		if (a instanceof Lote || this.articulos.contains(a) || !a.isDisponible() || a.isVendido())
			throw new LoteException("no es posible a�adir eso");
		this.articulos.add(a);
	}

	/**
	 * @return los articulos del lote
	 */
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	/**
	 * @param articulos
	 *            los articulos a asignar
	 */
	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see articulos.Articulo#toString()
	 */
	public String toString() {
		String output = "";
		for (Articulo a : this.articulos)
			output += a + ", ";
		return output;
	}

}
