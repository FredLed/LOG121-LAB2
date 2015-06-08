/**
 * Sert de liste des formes valides et supportées par le logiciel.
 */
public enum TypeForme {
	CARRE("CARRE", 0),
	RECTANGLE("RECTANGLE", 1),
	CERCLE("CERCLE", 2),
	OVALE("OVALE", 3),
	LIGNE("LIGNE", 4);
	
	private final String nomForme;
	private final int noForme;
	
	private TypeForme(String nomforme, int noForme) {
		this.nomForme = nomforme;
		this.noForme = noForme;
	}
	
	@Override
    public String toString() {
        return nomForme;
    }
	
	public int getNoForme() {
		return this.noForme;
	}
}