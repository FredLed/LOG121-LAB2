import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class Rectangle.
 */
public class Rectangle extends AbstractForme{
	
	/** The sommet2. */
	protected Point sommet1, sommet2;

	
	/**
	 * Instantiates a new rectangle.
	 *
	 * @param nseq the nseq
	 * @param couleur the couleur
	 * @param sommets the sommets
	 */
	public Rectangle(int nseq,Color couleur,Point[] sommets){
		
		super(nseq, couleur);
		this.setSommet1(sommets[0].x,sommets[0].y);
		this.setSommet2(sommets[1].x,sommets[1].y);
	}
	
	/**
	 * Gets the sommet1.
	 *
	 * @return the sommet1
	 */
	public Point getSommet1(){
		
		return this.sommet1;
	}
	
	/**
	 * Sets the sommet1.
	 *
	 * @param x1 the x1
	 * @param y1 the y1
	 */
	public void setSommet1(int x1, int y1){
		
		this.sommet1 = new Point(x1,y1);
	}
	
	/**
	 * Gets the sommet2.
	 *
	 * @return the sommet2
	 */
	public Point getSommet2(){
		
		return this.sommet2;
	}
	
	/**
	 * Sets the sommet2.
	 *
	 * @param x2 the x2
	 * @param y2 the y2
	 */
	public void setSommet2(int x2, int y2){
		
		this.sommet2 = new Point(x2,y2);
	}
	
	/**
	 * Retourne la largeur de la ligne
	 *
	 * @return La largeur de la ligne
	 * 
	 */
	public int getLargeur(){
		
		return Math.abs(this.getSommet2().x - this.getSommet1().x);
	}
	
	/**
	 * Retourne la hauteur de la ligne
	 *
	 * @return La hauteur de la ligne
	 * 
	 */
	public int getHauteur(){
		
		return Math.abs(this.getSommet2().y - this.getSommet1().y);
	}
	
	/**
	 * Retourne l'aire de la ligne
	 *
	 * @return L'aire
	 * 
	 */
	public double getAire(){
		
		return Math.abs(this.getSommet2().y - this.getSommet1().y);
	}
	
	
	/* (non-Javadoc)
	 * @see AbstractForme#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g, int position){
		
		g.setColor(getCouleur());
		
		g.fillRect((position * 40), (position * 40), (sommet2.x - sommet1.x), (sommet2.y - sommet1.y));
		
		this.drawContour(g, position);

	}
	
	public double getDistanceMax() {
		return Math.sqrt((this.getHauteur() * this.getHauteur()) + (this.getLargeur() * this.getLargeur()));
	}
}
