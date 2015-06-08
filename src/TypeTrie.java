
public enum TypeTrie {
	NUM_SEQ_CROISSANT("NUM_SEQ_CROISSANT"),
	NUM_SEQ_DECROISSANT("NUM_SEQ_DECROISSANT"),
	AIRE_FORME_CROISSANT("AIRE_FORME_CROISSANT"),
	AIRE_FORME_DECROISSANT("AIRE_FORME_DECROISSANT"),
	TYPE_FORME_ALPHABETIQUE("TYPE_FORME_ALPHABETIQUE"),
	TYPE_FORME_ALPHABETIQUE_INVERSE("TYPE_FORME_ALPHABETIQUE_INVERSE");
	
	private final String nomTrie;
	
	private TypeTrie(String nomTrie) {
		this.nomTrie = nomTrie;
	}
	
	@Override
    public String toString() {
        return nomTrie;
    }
}
