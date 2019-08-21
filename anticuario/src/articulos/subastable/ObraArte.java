
package articulos.subastable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Javier Gomez &lt;javier.gomezmartinez@estudiante.uam.es&gt;
 * @author Carlos Li &lt;carlos.li@estudiante.uam.es&gt;
 * 
 *         Es una clase de articulos tipo Obra de Arte
 */
public class ObraArte extends ObjetoSubasta {
	private static final long serialVersionUID = -8776867018123448924L;
	private String autor;
	private boolean certificado;
	private TipoObra tipo;
	private boolean subastable;

	/**
	 * @param descripcion
	 *            la descripcion de la obra
	 * @param anoOrigen
	 *            el ano de origen
	 * @param fechaAquisicion
	 *            la fecha de adquisicion
	 * @param costeAdquisicion
	 *            lo que costo
	 * @param precioObjetivo
	 *            el precio por el que se vende
	 * @param vendido
	 *            si esta o no vendido
	 * @param disponible
	 *            si esta disponible o no
	 * @param autor
	 *            el autor
	 * @param certificado
	 *            si tiene o no certificado
	 * @param tipo
	 *            el tipo de la obra
	 * @param subastable
	 *            si es o no subastable
	 */
	public ObraArte(String descripcion, String anoOrigen, LocalDate fechaAquisicion, double costeAdquisicion,
			double precioObjetivo, boolean vendido, boolean disponible, String autor, boolean certificado,
			TipoObra tipo, boolean subastable) {
		super(descripcion, anoOrigen, fechaAquisicion, costeAdquisicion, precioObjetivo, vendido, disponible);
		this.autor = autor;
		this.certificado = certificado;
		this.tipo = tipo;
		this.subastable = subastable;

	}

	/**
	 * @return el autor de la obra
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor
	 *            el autor a asignar
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return si tiene certificado la obra
	 */
	public boolean getCertificado() {
		return certificado;
	}

	/**
	 * @param certificado
	 *            el certificado a asignar
	 */
	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}

	/**
	 * @return el tipo de la obra
	 */
	public TipoObra getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            el tipo a asignar
	 */
	public void setTipo(TipoObra tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return indica si es subastable
	 */
	public boolean isSubastable() {
		return subastable;
	}

	/**
	 * @param subastable
	 *            el subastable a asignar
	 */
	public void setSubastable(boolean subastable) {
		this.subastable = subastable;
	}

	/**
	 * @return un string para seguir con el formato del certificado del archivo
	 */
	private String parseCerticifado() {
		return this.certificado == true ? "S" : "N";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see articulos.Articulo#toString()
	 */
	public String toString() {
		return super.toString() + " " + this.autor + " " + this.tipo + " " + this.parseCerticifado();
	}

	@Override
	public void subastar() {
		this.setDisponible(false);
		this.subastable = true;
	}
	@Override public Object[] filaArticulo(){
		Object[] o = super.filaArticulo();
		o[12] = this.autor;
		o[13] = this.certificado;
		o[14] = this.tipo.toString();
		o[15] = this.subastable;
		return o;
	}
}
