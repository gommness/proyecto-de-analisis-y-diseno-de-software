package articulos.subastable;

import java.time.LocalDate;

import articulos.Articulo;
/** 
* @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
* @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
* 
* Es una clase de articulos subastables
*/
public abstract class ObjetoSubasta extends Articulo {


	private static final long serialVersionUID = 4280218507263760414L;

	/**
	 * @param descripcion
	 *            descripcion del articulo
	 * @param anoOrigen
	 *            año de origen del articulo
	 * @param fechaAquisicion
	 *            fecha de compra del articulo
	 * @param costeAdquisicion
	 *            coste de compra del articulo
	 * @param precioObjetivo
	 *            precio de venta del articulo
	 * @param vendido
	 *            indica si el articulo ha sido vendido
	 * @param disponible
	 *            indica si el articulo esta disponible o en un lote
	 */
	public ObjetoSubasta(String descripcion, String anoOrigen, LocalDate fechaAquisicion, double costeAdquisicion,
			double precioObjetivo, boolean vendido, boolean disponible) {
		super(descripcion, anoOrigen, fechaAquisicion, costeAdquisicion, precioObjetivo, vendido, disponible);
	}
	
	@Override public void subastar(){
		this.setDisponible(false);
	}

}
