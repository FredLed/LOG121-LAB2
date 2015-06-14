import java.util.Observable;
import java.lang.reflect.*;

public class ClListe<T> extends Observable{
	private class Noeud {
		
		// On a d�cid� de faire une liste doublement cha�n�e pour facilit�
		// la r�alisation de la fonction InsererAvant.
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
			// Dans tous les cas, on met tout le temps la position courante � la 
			// nouvelle donn�e entr�e.
			if (estVide()) {
				// Si la liste est vide, on met la tete, la position courante et la fin
				// � la donn�e qu'on ajoute car c'est la seule de la liste pour le moment.
				fin = positionCourante = tete = new Noeud(valeur, null, null);
			}
			else if (tete == fin) {
				// Si la tete et la fin sont �quivalente, cela veut dire qu'il y a
				// pour le moment une seule donn�e et qu'on veut en ajouter une deuxi�me.
				// Donc on laisse la fin � sa position et on recule la t�te et la position courante.
				tete = positionCourante = fin.precedent = new Noeud(valeur, null, fin);
			}
			else if (positionCourante == tete) {
				// Si la position courante est � la t�te (si on se rend ici, cela veut
				// dire qu'on a minimum 3 donn�es dans la liste gr�ce au deux tests
				// ci-haut), on ajoute avant la t�te et on d�place la t�te � la 
				// nouvelle donn�e.
				positionCourante.precedent = new Noeud(valeur, null, positionCourante);
				tete= positionCourante.precedent;
				
				// On place la posistion courant � la nouvelle donn�e.
				positionCourante = tete;
			}
			else if (positionCourante != null) {
				// Si on se rend ici, on fait juste ajout� la nouvelle donn�e avant
				// la position courante.
				Noeud nouvelleEntree= new Noeud(valeur, positionCourante.precedent, positionCourante);
				positionCourante.precedent.suivant = nouvelleEntree;
				positionCourante.precedent = nouvelleEntree;
				
				// On place la position courante � la nouvelle donn�e.
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
			// Dans tous les cas, on met tout le temps la position courante � la 
			// nouvelle donn�e entr�e. 
			if (estVide()) {
				// Si la liste est vide, on met la tete, la position courante et la fin
				// � la donn�e qu'on ajoute car c'est la seule de la liste pour le moment.
				fin = positionCourante = tete = new Noeud(valeur, null, null);
			}
			else if (tete == fin) {
				// Si la tete et la fin sont �quivalente, cela veut dire qu'il y a
				// pour le moment une seule donn�e et qu'on veut en ajouter une deuxi�me.
				// Donc on laisse la t�te � sa position et on avance la fin et la position courante.
				positionCourante = tete.suivant = fin = new Noeud(valeur, tete, null);
			}
			else if (positionCourante == fin) {
				// Si on veut ajouter apr�s la fin de la liste, on d�place la fin.
				fin = positionCourante.suivant = new Noeud(valeur, positionCourante, null);
				
				// On place la position courante � la nouvelle donn�e.
				positionCourante = fin;
			}
			else if (positionCourante != null) {
				// Si on se rend ici, on fait juste ajout� la nouvelle donn�e apr�s
				// la position courante.
				Noeud nouvelleEntree = new Noeud(valeur, positionCourante, positionCourante.suivant);
				positionCourante.suivant.precedent = nouvelleEntree;
				positionCourante.suivant = nouvelleEntree;
				
				// On place la position courante � la nouvelle donn�e.
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
	 * Permet de supprimer la donn�e � la position courante. Par la suite,
	 * on d�place la position courante � la donn�e suivante celle cibl� par la
	 * suppression.
	 * @return si la suppression � eu lieu ou non
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
			throw new Exception("Impossible de reculer plus loin que le d�but.");
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
	 * Parcours de la liste, si l'�l�ment suivant est plus petit que
	 * l'�l�ment courant, les valeurs sont �chang�es � l'aide d'un
	 * noeud interm�diaire(noeudTemporaire). Tant qu'il y a des inversions de
	 * valeur la liste n'est pas compl�tement tri�s. La comparaison de valeur
	 * se fait selon la m�thode de tri pass�e en param�tre
	 * 
	 * @param methode la m�thode de trie � utiliser pour comparer les valeurs
	 * @return 
	 */
	public void trier(String methode){
		
		//Pour faire l'�change de valeur
		Noeud noeudTemporaire = new Noeud(null,null,null); 
		//True s'il y a eu une modification dans la liste
		boolean changementFait = true;
		
		//Tant que la liste n'est pas en ordre
		while(changementFait){
			//R�initialisation du flag
			changementFait = false;
			//Parcours � partir de la t�te de la liste
			Noeud noeudCourant = this.getTete();
			//Tant qu'on est pas au dernier noeud de la liste
			while(noeudCourant.suivant != null){
				//Compare la valeur selon la m�thode de tri choisie
				if(this.compareData((AbstractForme)noeudCourant.valeur,
						(AbstractForme)noeudCourant.suivant.valeur, methode)){
					//Si la valeur suivante est plus grande, on echange les valeurs
					noeudTemporaire.valeur = noeudCourant.valeur;
					noeudCourant.valeur = noeudCourant.suivant.valeur;
					noeudCourant.suivant.valeur = noeudTemporaire.valeur;
					//Un changement a �t� fait
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
	 * M�thode qui permet de comparer la valeur de 2 noeuds selon une m�thode
	 * de tri pass� en param�tre. Exemple: v�rifier que la forme stocker dans le
	 * noeud 1 est plus large que la forme stock�e dans le noeud 2.
	 * 
	 * @param forme1 une forme � comparer
	 * @param forme2 une forme � comparer
	 * @param methode la m�thode de trie � utiliser pour comparer les valeurs
	 * @return isBigger true si la condition de comparaison est vrai, sinon retourne false
	 */
	public  boolean compareData(AbstractForme forme1, AbstractForme forme2,String methode){
		
		boolean isBigger = false;
		String tForme1;
		String tForme2;
		
		//Comparaison de valeur selon la m�thode de tri pass� en param�tre
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
