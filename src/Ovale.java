import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;


// TODO: Auto-generated Javadoc
/**
 * The Class Ovale.
 */
public class Ovale extends AbstractForme{
	
	/** The centre. */
	protected Point centre;
	
	/** The rayon v. */
	protected int rayonH, rayonV;
	
	/**
	 * Instantiates a new ovale.
	 *
	 * @param nseq the nseq
	 * @param couleur the couleur
	 * @param centre the centre
	 * @param rayon the rayon
	 */
	public Ovale(int nseq,Color couleur,Point centre, int[] rayon){
		
		super(nseq,couleur);
		this.centre = centre;
		this.rayonH = rayon[0];
		this.rayonV = rayon[1];
	}
	
	/**
	 * Retourne la largeur de la forme
	 *
	 * @return La largeur de la forme
	 * 
	 */
	public int getLargeur(){
		
		return (2 * this.rayonH);
	}
	
	/**
	 * Retourne la hauteur de la forme
	 *
	 * @return La hauteur de la forme
	 * 
	 */
	public int getHauteur(){
		
		return (2 * this.rayonV);
	}
	
	/**
	 * Retourne l'aire de l'ovale
	 *
	 * @return L'aire
	 * 
	 */
	public double getAire(){
		
		return (Math.PI * rayonH * rayonV);
	}
	
	
	/* (non-Javadoc)
	 * @see AbstractForme#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g, int position){
		
		g.setColor(getCouleur());
		
		g.fillOval((position * 40),(position * 40), 2 * rayonH, 2 * rayonV);
		
		this.drawContour(g, position);

	}
}
