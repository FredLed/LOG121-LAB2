import java.util.Observable;
import java.lang.reflect.*;

public class ClListe<T> extends Observable{
	private class Noeud {
		
		// On a décidé de faire une liste doublement chaînée pour facilité
		// la réalisation de la fonction InsererAvant.
		public Noeud(T valeur, Noeud precedent, Noeud suivant) {
			this.precedent = precedent;
			this.suivant = suivant;
			this.valeur = valeur;
		}
		
		private Noeud suivant;
		private Noeud precedent;
		private T valeur;
	}
	
	public ClListe(int max) {
		nbElements = 0;
		tete = null;
		fin = null;
		positionCourante = null;
		this.maxNbElements = max;
		this.estCroissante = true;
	}
	
	public boolean estVide() {
		return nbElements == 0;
	}
	
	public void faireCopieSauvegarde() {
		
	}
	
	public boolean InsererAvant(T valeur) {
		if (nbElements < maxNbElements) {
			// Dans tous les cas, on met tout le temps la position courante à la 
			// nouvelle donnée entrée.
			if (estVide()) {
				// Si la liste est vide, on met la tete, la position courante et la fin
				// à la donnée qu'on ajoute car c'est la seule de la liste pour le moment.
				fin = positionCourante = tete = new Noeud(valeur, null, null);
			}
			else if (tete == fin) {
				// Si la tete et la fin sont équivalente, cela veut dire qu'il y a
				// pour le moment une seule donnée et qu'on veut en ajouter une deuxième.
				// Donc on laisse la fin à sa position et on recule la tête et la position courante.
				tete = positionCourante = fin.precedent = new Noeud(valeur, null, fin);
			}
			else if (positionCourante == tete) {
				// Si la position courante est à la tête (si on se rend ici, cela veut
				// dire qu'on a minimum 3 données dans la liste grâce au deux tests
				// ci-haut), on ajoute avant la tête et on déplace la tête à la 
				// nouvelle donnée.
				positionCourante.precedent = new Noeud(valeur, null, positionCourante);
				tete= positionCourante.precedent;
				
				// On place la posistion courant à la nouvelle donnée.
				positionCourante = tete;
			}
			else if (positionCourante != null) {
				// Si on se rend ici, on fait juste ajouté la nouvelle donnée avant
				// la position courante.
				Noeud nouvelleEntree= new Noeud(valeur, positionCourante.precedent, positionCourante);
				positionCourante.precedent.suivant = nouvelleEntree;
				positionCourante.precedent = nouvelleEntree;
				
				// On place la position courante à la nouvelle donnée.
				positionCourante = nouvelleEntree;
			}
			
			++nbElements;
			setChanged();
			notifyObservers();
			return true;
		}
		else
			return false;
	}
	
	public boolean InsererApres(T valeur) {
		if (nbElements < maxNbElements) {
			// Dans tous les cas, on met tout le temps la position courante à la 
			// nouvelle donnée entrée. 
			if (estVide()) {
				// Si la liste est vide, on met la tete, la position courante et la fin
				// à la donnée qu'on ajoute car c'est la seule de la liste pour le moment.
				fin = positionCourante = tete = new Noeud(valeur, null, null);
			}
			else if (tete == fin) {
				// Si la tete et la fin sont équivalente, cela veut dire qu'il y a
				// pour le moment une seule donnée et qu'on veut en ajouter une deuxième.
				// Donc on laisse la tête à sa position et on avance la fin et la position courante.
				positionCourante = tete.suivant = fin = new Noeud(valeur, tete, null);
			}
			else if (positionCourante == fin) {
				// Si on veut ajouter après la fin de la liste, on déplace la fin.
				fin = positionCourante.suivant = new Noeud(valeur, positionCourante, null);
				
				// On place la position courante à la nouvelle donnée.
				positionCourante = fin;
			}
			else if (positionCourante != null) {
				// Si on se rend ici, on fait juste ajouté la nouvelle donnée après
				// la position courante.
				Noeud nouvelleEntree = new Noeud(valeur, positionCourante, positionCourante.suivant);
				positionCourante.suivant.precedent = nouvelleEntree;
				positionCourante.suivant = nouvelleEntree;
				
				// On place la position courante à la nouvelle donnée.
				positionCourante = nouvelleEntree;
			}
			
			++nbElements;
			setChanged();
			notifyObservers();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Permet de supprimer la donnée à la position courante. Par la suite,
	 * on déplace la position courante à la donnée suivante celle ciblé par la
	 * suppression.
	 * @return si la suppression à eu lieu ou non
	 */
	public boolean Supprimer() {
		if (estVide() || positionCourante == null) {
			return false;
		}
		else {
			if (positionCourante == tete)
			{
				tete = positionCourante.suivant;
				tete.precedent = null;
				positionCourante = tete;
			}
			else if (positionCourante == fin) {
				fin = positionCourante.precedent;
				fin.suivant = null;
				positionCourante = fin;
			}
			else {
				positionCourante.precedent.suivant = positionCourante.suivant;
				positionCourante.suivant.precedent = positionCourante.precedent;
				positionCourante = positionCourante.suivant;
			}
			
			--nbElements;
			setChanged();
			notifyObservers();
			return true;
		}
	}
	
	public int getNbElements() {
		return nbElements;
	}
	
	public T getElement() {
		return positionCourante.valeur;
	}
	
	public Noeud getTete(){
		
		return this.tete;
	}
	
	public Noeud getFin(){
		
		return this.fin;
	}
	
	public void avancer() throws Exception {
		if (estVide()) 
			throw new Exception("Impossible d'avancer car la liste est vide.");
		else if (positionCourante == fin) 
			throw new Exception("Impossible d'avancer plus loin que la fin.");
		else if (positionCourante != null)
			positionCourante = positionCourante.suivant;
		else
			throw new Exception("La position courante est nulle.");
	}
	
	public void reculer() throws Exception {
		if (estVide())
			throw new Exception("Impossible de reculer car la liste est vide.");
		else if (positionCourante == tete)
			throw new Exception("Impossible de reculer plus loin que le début.");
		else if (positionCourante != null)
			positionCourante = positionCourante.precedent;
		else
			throw new Exception("La position courante est nulle.");
	}
	
	public void setPositionCouranteDebut() throws Exception {
		if (!estVide()) 
			positionCourante = tete;
		else
			throw new Exception("La liste est vide.");
	}
	
	public void setPositionCouranteFin() throws Exception {
		if (!estVide())
			positionCourante = fin;
		else 
			throw new Exception("La liste est vide.");
	}
	
	public int getMax() {
		return this.maxNbElements;
	}
	
	public boolean estPleine() {
		return this.maxNbElements == this.getNbElements();
	}
	
	public boolean estCroissante(){
		
		return this.estCroissante;
	}
	
	public void reinitialiser() {
		nbElements = 0;
		tete = null;
		fin = null;
		positionCourante = null;
	}
	
	/**
	 * Permet de trier la liste selon un algoryhtme de tri bulle.
	 * Parcours de la liste, si l'élément suivant est plus petit que
	 * l'élément courant, les valeurs sont échangées à l'aide d'un
	 * noeud intermédiaire(noeudTemporaire). Tant qu'il y a des inversions de
	 * valeur la liste n'est pas complétement triés. La comparaison de valeur
	 * se fait selon la méthode de tri passée en paramètre
	 * 
	 * @param methode la méthode de trie à utiliser pour comparer les valeurs
	 * @return 
	 */
	public void trier(String methode){
		
		//Pour faire l'échange de valeur
		Noeud noeudTemporaire = new Noeud(null,null,null); 
		//True s'il y a eu une modification dans la liste
		boolean changementFait = true;
		
		//Tant que la liste n'est pas en ordre
		while(changementFait){
			//Réinitialisation du flag
			changementFait = false;
			//Parcours à partir de la tête de la liste
			Noeud noeudCourant = this.getTete();
			//Tant qu'on est pas au dernier noeud de la liste
			while(noeudCourant.suivant != null){
				//Compare la valeur selon la méthode de tri choisie
				if(this.compareData((AbstractForme)noeudCourant.valeur,
						(AbstractForme)noeudCourant.suivant.valeur, methode)){
					//Si la valeur suivante est plus grande, on echange les valeurs
					noeudTemporaire.valeur = noeudCourant.valeur;
					noeudCourant.valeur = noeudCourant.suivant.valeur;
					noeudCourant.suivant.valeur = noeudTemporaire.valeur;
					//Un changement a été fait
					changementFait = true;
				}
				//Passe au noeud suivant
				noeudCourant = noeudCourant.suivant;
			}
		}
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Méthode qui permet de comparer la valeur de 2 noeuds selon une méthode
	 * de tri passé en paramètre. Exemple: vérifier que la forme stocker dans le
	 * noeud 1 est plus large que la forme stockée dans le noeud 2.
	 * 
	 * @param forme1 une forme à comparer
	 * @param forme2 une forme à comparer
	 * @param methode la méthode de trie à utiliser pour comparer les valeurs
	 * @return isBigger true si la condition de comparaison est vrai, sinon retourne false
	 */
	public  boolean compareData(AbstractForme forme1, AbstractForme forme2,String methode){
		
		boolean isBigger = false;
		String tForme1;
		String tForme2;
		
		//Comparaison de valeur selon la méthode de tri passé en paramètre
		switch (methode) {
		case "NUM_SEQ_CROISSANT":
			this.estCroissante = true;
			isBigger = (forme1.getNumeroSequence() > forme2.getNumeroSequence()) ? true : false;
				break;
		case "NUM_SEQ_DECROISSANT":
			this.estCroissante = false;
			isBigger = (forme1.getNumeroSequence() > forme2.getNumeroSequence()) ? true : false;
				break;
		case "AIRE_FORME_CROISSANT":
			this.estCroissante = true;
			isBigger = (forme1.getAire() > forme2.getAire()) ? true : false;
				break;
		case "AIRE_FORME_DECROISSANT":
			this.estCroissante = false;
			isBigger = (forme1.getAire() > forme2.getAire()) ? true : false;
				break;
		case "TYPE_FORME_1":
			this.estCroissante = true;
			tForme1 = forme1.getClass().toString().substring(6, forme1.getClass().toString().length()).toUpperCase();
			tForme2 = forme2.getClass().toString().substring(6, forme2.getClass().toString().length()).toUpperCase();
			isBigger = (TypeForme.valueOf(tForme1).getNoForme()
						> TypeForme.valueOf(tForme2).getNoForme());
				break;
		case "TYPE_FORME_2":
			this.estCroissante = false;
			tForme1 = forme1.getClass().toString().substring(6, forme1.getClass().toString().length()).toUpperCase();
			tForme2 = forme2.getClass().toString().substring(6, forme2.getClass().toString().length()).toUpperCase();
			isBigger = (TypeForme.valueOf(tForme1).getNoForme()
						> TypeForme.valueOf(tForme2).getNoForme());
				break;
		case "DISTANCE_MAXIMALE_CROISSANT":
			this.estCroissante = true;
			isBigger = forme1.getDistanceMax() > forme2.getDistanceMax();
				break;
		case "LARGEUR_CROISSANTE":
			this.estCroissante = true;
			isBigger = (forme1.getLargeur() > forme2.getLargeur()) ? true : false;
				break;
		case "LARGEUR_DECROISSANTE":
			this.estCroissante = false;
			isBigger = (forme1.getLargeur() > forme2.getLargeur()) ? true : false;
				break;
		case "HAUTEUR_CROISSANTE":
			this.estCroissante = true;
			isBigger = (forme1.getHauteur() > forme2.getHauteur()) ? true : false;
				break;
		case "HAUTEUR_DECROISSANTE":
			this.estCroissante = false;
			isBigger = (forme1.getHauteur() > forme2.getHauteur()) ? true : false;
				break;
		case "ORDRE_ORIGINAL": ;
				break;
		}
		
		return isBigger;
	}
	
	private Noeud tete;
	private Noeud positionCourante;
	private Noeud fin;
	private int nbElements;
	private int maxNbElements;
	private boolean estCroissante;
	
}
