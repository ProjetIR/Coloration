package Model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Observable;

/**
 * Classe représentant la notion d'information 
 * Par défaut, la couleur, le poids,etc... d'un objet quelconque
 * @author KIEFFER
 *
 */
public abstract class Info extends Observable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color col;
	
	public Info(Color col)
	{
		this.col = col;
	}
	
	
	public Color getCol() {
		return col;
	}


	public void setCol(Color col) {
		this.col = col;
	}

	public abstract void paint(Graphics2D g2d);
	

}
