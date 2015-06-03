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
	 * Classe abstraite permettant le dessin de la forme
	 *
	 * @param g the g
	 */
	abstract void draw(Graphics g);
}
