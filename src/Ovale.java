import java.awt.Color;
import java.awt.Graphics;
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
	public void draw(Graphics g){
		
		g.setColor(getCouleur());
		g.fillOval(centre.x, centre.y, rayonH, rayonV);
		
	}
}
