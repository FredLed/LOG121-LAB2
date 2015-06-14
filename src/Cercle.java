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
		this.rayonV = this.rayonH;
	}
	
}
