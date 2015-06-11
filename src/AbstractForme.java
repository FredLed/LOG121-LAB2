import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractForme.
 */
public abstract class AbstractForme {
	
	/** Le numéro de séquence de la forme. */
	protected int nseq;
	
	/** La couleur de la forme */
	protected Color couleur;
	
	protected final static float dash1[] = { 2.0f };
	 
	protected final static BasicStroke dashed = new BasicStroke(1.0f,
		      BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
	/**
	 * Instantiates a new abstract forme.
	 *
	 * @param nseq the nseq
	 * @param couleur the couleur
	 */
	protected AbstractForme(int nseq, Color couleur){
		
		this.setNumeroSequence(nseq);
		this.setCouleur(couleur);
		
	}
	
	/**
	 * Gets the numero sequence.
	 *
	 * @return the numero sequence
	 */
	protected int getNumeroSequence(){
		
		return this.nseq;
	}
	
	/**
	 * Sets the numero sequence.
	 *
	 * @param nseq the new numero sequence
	 */
	protected void setNumeroSequence(int nseq){
		
		this.nseq = nseq;
	}
	
	/**
	 * Gets the couleur.
	 *
	 * @return the couleur
	 */
	protected Color getCouleur(){
		
		return this.couleur;
	}
	
	/**
	 * Sets the couleur.
	 *
	 * @param couleur the new couleur
	 */
	protected void setCouleur(Color couleur){
		
		this.couleur = couleur;
	}
	
	/**
	 * Méthode abstraite permettant le dessin de la forme
	 *
	 * @param g the g
	 */
	abstract void draw(Graphics g, int position);
	
	/**
	 * Méthode abstraite retourne la largeur de la forme
	 *
	 * @return La largeur de la forme
	 */
	abstract int getLargeur();
	
	/**
	 * Méthode abstraite retournant la hauteur de la forme
	 *
	 * @return La hauteur de la forme
	 */
	abstract int getHauteur();
	
	/**
	 * Méthode abstraite retournant l'aire de la forme
	 *
	 * @return L'aire de la forme
	 */
	abstract double getAire();
	
	/**
	 * Méthode abstraite retournant la plus grande distance entre 
	 * 2 point de la forme
	 *
	 * @return La distance maximale entre 2 points de la forme
	 */
	
	//abstract int getDistanceMax();
}
