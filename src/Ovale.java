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
	
	/* (non-Javadoc)
	 * @see AbstractForme#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g, int position){
		
		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.BLACK);
		
		g.setColor(getCouleur());
		
		g.fillOval((position * 40),(position * 40), rayonH, rayonV);
		
		g2.setStroke(dashed);
		g2.drawRect((position * 40), (position * 40), rayonH, rayonV);
		
		g2.dispose();
	}
}
