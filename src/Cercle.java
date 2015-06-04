import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class Cercle.
 */
public class Cercle extends Ovale{
	

	/**
	 * Instantiates a new cercle.
	 *
	 * @param nseq the nseq
	 * @param couleur the couleur
	 * @param centre the centre
	 * @param rayon the rayon
	 */
	public Cercle(int nseq,Color couleur,Point centre, int[] rayon){
		
		super(nseq, couleur,centre,rayon);
		
	}
	
	/* (non-Javadoc)
	 * @see AbstractForme#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g, int position){
		
		g.setColor(getCouleur());
		final Graphics2D g2 = (Graphics2D) g.create();
		
		g.fillOval((position * 40),(position * 40), rayonH, rayonH);
		
		g2.setStroke(dashed);
		g2.drawRect((position * 40), (position * 40), rayonH, rayonH);
		
		g2.dispose();
	}
}
