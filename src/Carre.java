import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;



// TODO: Auto-generated Javadoc
/**
 * The Class Carre.
 */
public class Carre extends Rectangle{
	
	/**
	 * Instantiates a new carre.
	 *
	 * @param nseq the nseq
	 * @param couleur the couleur
	 * @param sommets the sommets
	 */
	public Carre(int nseq, Color couleur,Point[] sommets){
		
		super(nseq,couleur,sommets);

	}
	
	/* (non-Javadoc)
	 * @see Rectangle#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g, int position){
		
		g.setColor(getCouleur());
		g.fillRect((position * 40),(position * 40),
				(sommet2.x - sommet1.x), (sommet2.y - sommet1.y));
		
	}
}
