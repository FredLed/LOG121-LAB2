
public class TrieForme {
	public static ClListe<AbstractForme> trier(ClListe<AbstractForme> listeOriginale,
										TypeTrie typeTrie) {
		ClListe<AbstractForme> nouvelleListe;
		
		switch (typeTrie) {
			case NUM_SEQ_CROISSANT : nouvelleListe = ; break;
			case NUM_SEQ_DECROISSANT : nouvelleListe = ; break;
			case AIRE_FORME_CROISSANT : nouvelleListe = ; break;
			case AIRE_FORME_DECROISSANT : nouvelleListe = ; break;
			case TYPE_FORME_ALPHABETIQUE : nouvelleListe = ; break;
			case TYPE_FORME_ALPHABETIQUE_INVERSE : nouvelleListe = ; break;
		}
		
		return nouvelleListe;
	}
	
	private ClListe<AbstractForme> triNumérique(ClListe<AbstractForme> listeOriginale,
													boolean croissant) {
		ClListe<AbstractFe>orm nouvelleListe;
		
		if (croissant) {
			
		}
		else {
			
		}
		
		return nouvelleListe;
	}
	
	// Méthode de tri par insertion du site : http://openclassrooms.com/courses/le-tri-par-insertion
	
	void triInsertion(ClListe<AbstractForme> listeOriginale, int taille)
	{
	   int i, j;
	   for (i = 1; i < listeOriginale.getNbElements(); ++i) {
		   
	       int elem = tab[i];
	       for (j = i; j > 0 && tab[j-1] > elem; j--)
	           tab[j] = tab[j-1];
	       tab[j] = elem;
	   }
	}

}
