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
	}
	
	public boolean estVide() {
		return nbElements == 0;
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
			positionCourante = positionCourante.suivant;
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
	
	public void reinitialiser() {
		nbElements = 0;
		tete = null;
		fin = null;
		positionCourante = null;
	}
	
	public Noeud trier(Noeud p, int n, boolean croissant, TypeTrie methode) {
		int Q = n/2;
		int P = n - Q;
		Noeud q;
		
		if (P >= 2) {
			q = trier(p, P, croissant, methode);
			if (Q >= 2) p = trier(q, Q, croissant, methode);
		}
		else
		{
			q = p.suivant;
		}
		
		q = fusionner(p, P, q, Q, croissant, methode);
		return q;
	}
	
	private Noeud fusionner(Noeud p, int P, Noeud q, int Q, boolean croissant, TypeTrie methode) {
		for (;;) {
			AbstractForme f1 = (AbstractForme)p.suivant.valeur;
			AbstractForme f2 = (AbstractForme)q.suivant.valeur;
			boolean testValeur = false;
			
			switch (methode) {
				case NUM_SEQ_CROISSANT : 
					testValeur = (f1.getNumeroSequence() > f2.getNumeroSequence()) ? true : false;
					break;
				case NUM_SEQ_DECROISSANT: ;
						break;
				case AIRE_FORME_CROISSANT: 
					testValeur = (f1.getAire() > f2.getAire()) ? true : false;
						break;
				case AIRE_FORME_DECROISSANT: ;
						break;
				case TYPE_FORME_1: ;
						break;
				case TYPE_FORME_2: ;
						break;
				case DISTANCE_MAXIMALE_CROISSANTE:
						break;
				case LARGEUR_CROISSANTE:
					testValeur = (f1.getLargeur() > f2.getLargeur()) ? true : false;
						break;
				case LARGEUR_DECROISSANTE: ;
						break;
				case HAUTEUR_CROISSANTE:;
					testValeur = (f1.getHauteur() > f2.getHauteur()) ? true : false;
						break;
				case HAUTEUR_DECROISSANTE: ;
						break;
				case ORDRE_ORIGINAL: ;
						break;
			}
			
			/***/
			if (testValeur) {
				Noeud t = q.suivant;
				
				q.suivant = t.suivant;
				q.suivant.precedent = q;
				
				t.precedent = p;
				t.suivant = p.suivant;
				p.suivant.precedent = t;
				p.suivant = t;
				
				if (Q == 1) break;
				
				--Q;
			}
			else {
				if (P == 1) {
					while (Q >= 1) {
						q = q.suivant;
						--Q;
					}
					break;
				}
				--P;
			}
			
			p = p.suivant;
		}
		/***/
		
		return q;
	}
	
	private Noeud tete;
	private Noeud positionCourante;
	private Noeud fin;
	private int nbElements;
	private int maxNbElements;
	
}
