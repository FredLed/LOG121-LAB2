import java.util.Observable;


// TODO: Auto-generated Javadoc
/**
 * The Class ListeForme.
 */
public class ListeForme extends Observable{
	
	/** The Constant NOMBRE_FORME_MAX. */
	public final static int NOMBRE_FORME_MAX = 10;
	
	/** The index. */
	private int index;
	
	/** :Le nombre de formes contenues dans le tableau */
	private int nombreForme;
	
	/** Tableau qui contient les formes */
	private AbstractForme[] tabForme;
	
	/**
	 * Instantiates a new liste forme.
	 */
	public ListeForme(){
		
		this.setIndex(0);
		this.setNombreForme(0);
		this.tabForme = new AbstractForme[NOMBRE_FORME_MAX];
	}
	
	
	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex(){
		
		return this.index;
	}
	
	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(int index){
	
		this.index = index;
	}
	
	/**
	 * Gets the nombre forme.
	 *
	 * @return the nombre forme
	 */
	public int getNombreForme(){
		
		return this.nombreForme;
	}
	
	/**
	 * Sets the nombre forme.
	 *
	 * @param nombreForme the new nombre forme
	 */
	public void setNombreForme(int nombreForme){
	
		this.nombreForme = nombreForme;
	}
	
	/**
	 * Gets the forme.
	 *
	 * @param position the position
	 * @return the forme
	 */
	public AbstractForme getForme(int position){
		
		return this.tabForme[position];
	}
	
	/**
	 * Liste forme est pleine.
	 *
	 * @return true, if successful
	 */
	public boolean listeFormeEstPleine(){
		
		return this.getIndex() == NOMBRE_FORME_MAX;
	}
	
	/**
	 * Ajoute une forme dans le tableau. S'il y a 10 formes dans le tableau,
	 * la première forme est supprimée. La classe FenetrePrincipale est 
	 * avisé de tout ajout de forme.
	 *
	 * @param forme the forme
	 */
	public void ajouterForme(AbstractForme forme){
		
		if(listeFormeEstPleine()){
			
			this.setIndex(0);
		}
		
		this.tabForme[this.getIndex()] = forme;
		this.setIndex(this.getIndex() + 1);
		
		if(this.getNombreForme() < NOMBRE_FORME_MAX){
			
			this.setNombreForme(this.getNombreForme() + 1);
		}
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 *Vide le tableau de forme
	 *
	 */
	public void viderListe(){
		
		this.setIndex(0);
		this.setNombreForme(0);
		setChanged();
		notifyObservers();
	}
}
