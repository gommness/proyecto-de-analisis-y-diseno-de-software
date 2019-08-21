package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Constraint extends GridBagConstraints {

	private static final long serialVersionUID = 8562077076564887457L;
	
	public Constraint(){
		super();
	}
	
	/**
	 * prepara el constraint para la posicion indicada
	 * @param x la columna del grid
	 * @param y la fila del grid
	 */
	public void setPos(int x, int y){
		this.gridx = x;
		this.gridy = y;
	}
	/**
	 * asigna un peso al constraint
	 * @param x el peso horizontal
	 * @param y el peso vertical
	 */
	public void setWeight(double x, double y){
		this.weightx = x;
		this.weighty = y;
	}
	/**
	 * asigna un area al constraint
	 * @param x la anchura
	 * @param y la altura
	 */
	public void setArea(int x, int y){
		this.gridwidth = x;
		this.gridheight = y;
	}
	/**
	 * asigna una altura al constraint
	 * @param y la altura
	 */
	public void setHeight(int y){
		this.gridheight = y;
	}
	/**
	 * asigna una anchura al constraint
	 * @param x la anchura
	 */
	public void setWidth(int x){
		this.gridwidth = x;
	}
	/**
	 * asigna unos limites dentro de la celda al constraint
	 * @param top los pixeles hasta el lado de arriba
	 * @param left los pixeles hasta el lado de la izquierda
	 * @param right los pixeles hasta el lado de la derecha
	 * @param bottom los pixeles hasta el lado de abajo
	 */
	public void setInsets(int top, int left, int right, int bottom){
		this.insets = new Insets(top, left, right, bottom);
	}
	
	/**
	 * prepara el constraint para rellenar horizontalmente
	 */
	public void hFill(){
		this.fill = GridBagConstraints.HORIZONTAL;
	}
	/**
	 * prepara el constraint para rellenar verticalmente
	 */
	public void vFill(){
		this.fill = GridBagConstraints.VERTICAL;
	}
	/**
	 * prepara el constraint para rellenar todo
	 */
	public void allFill(){
		this.fill = GridBagConstraints.BOTH;
	}
	/**
	 * prepara el constraint no rellenar mas que lo estrictamente necesario
	 */
	public void noFill(){
		this.fill = GridBagConstraints.NONE;
	}
	
	/**
	 * prepara el constraint para alinear a la izquierda
	 */
	public void alignLeft(){
		this.anchor = GridBagConstraints.WEST;
	}
	/**
	 * prepara el constraint para alinear a la derecha
	 */
	public void alignRight(){
		this.anchor = GridBagConstraints.EAST;
	}
	/**
	 * prepara el constraint para alinear arriba
	 */
	public void alignTop(){
		this.anchor = GridBagConstraints.SOUTH;
	}
	/**
	 * prepara el constraint para alinear abajo
	 */
	public void alignBottom(){
		this.anchor = GridBagConstraints.NORTH;
	}
	/**
	 * prepara el constraint para alinear a la izquierda arriba
	 */
	public void alignTopLeft(){
		this.anchor = GridBagConstraints.NORTHWEST;
	}
	/**
	 * prepara el constraint para alinear a la izquierda abajo
	 */
	public void alignBottomLeft(){
		this.anchor = GridBagConstraints.SOUTHWEST;
	}
	/**
	 * prepara el constraint para alinear a la derecha arriba
	 */
	public void alignTopRight(){
		this.anchor = GridBagConstraints.NORTHEAST;
	}
	/**
	 * prepara el constraint para alinear a la derecha abajo
	 */
	public void alignBottomRight(){
		this.anchor = GridBagConstraints.SOUTHEAST;
	}
	/**
	 * prepara el constraint para alinear al centro
	 */
	public void alignCenter(){
		this.anchor = GridBagConstraints.CENTER;
	}
	
	public void setIpad(int x, int y){
		this.ipadx = x;
		this.ipady = y;
	}

}
