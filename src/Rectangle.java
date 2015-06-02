import java.awt.Color;
import java.awt.Graphics;
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
	
	/* (non-Javadoc)
	 * @see AbstractForme#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g){
		
		g.setColor(getCouleur());
		g.fillRect(sommet1.x, sommet1.y, (sommet2.x - sommet1.x), (sommet2.y - sommet1.y));
	}
}