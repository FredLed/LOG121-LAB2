import java.awt.Color;


// TODO: Auto-generated Javadoc
/**
 * The Class CreateurForme.
 */
public class CreateurForme {
	
	
	
	/**
	 * Creer forme.
	 * Décortique la chaine de caractères reçue du serveur et retourne une forme avec les 
	 * caractéristiques corespondant. 
	 *
	 * @param chaineForme the chaine forme
	 * @return the abstract forme
	 */
	public AbstractForme creerForme(String chaineForme){
		
		String typeForme = UtilitaireRegex.getTypeForme(chaineForme);
		AbstractForme forme = null;
		
		if(typeForme != null){
		
			int numeroSequence = UtilitaireRegex.getNumeroSequence(chaineForme);
			
			switch(typeForme){
			
			case "CARRE":	forme = new Carre(numeroSequence, Color.BLUE, UtilitaireRegex.getSommets(chaineForme));
			break;
			
			case "RECTANGLE":	forme = new Rectangle(numeroSequence, Color.DARK_GRAY, UtilitaireRegex.getSommets(chaineForme));
			break;
			
			case "CERCLE":	forme = new Cercle(numeroSequence, Color.CYAN, UtilitaireRegex.getCentres(chaineForme), UtilitaireRegex.getRayon(chaineForme));
			break;
			
			case "OVALE":	forme = new Ovale(numeroSequence, Color.GREEN, UtilitaireRegex.getCentres(chaineForme), UtilitaireRegex.getRayon(chaineForme));
			break;
			
			case "LIGNE": forme = new Ligne(numeroSequence, Color.BLACK, UtilitaireRegex.getSommets(chaineForme));
			break;
			}
		}
		
		return forme;
	}
	
}
