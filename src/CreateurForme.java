import java.awt.Color;


// TODO: Auto-generated Javadoc
/**
 * The Class CreateurForme.
 */
public class CreateurForme {
	
	
	
	/**
	 * Creer forme.
	 * D�cortique la chaine de caract�res re�ue du serveur et retourne une forme avec les 
	 * caract�ristiques corespondant. 
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
			
			case "CARRE":	forme = new Carre(numeroSequence, new Color(1,1,0, 0.7f), UtilitaireRegex.getSommets(chaineForme));
			break;
			
			case "RECTANGLE":	forme = new Rectangle(numeroSequence, new Color(0,1,0, 0.7f), UtilitaireRegex.getSommets(chaineForme));
			break;
			
			case "CERCLE":	forme = new Cercle(numeroSequence, new Color(0,0,1, 0.7f), UtilitaireRegex.getCentres(chaineForme), UtilitaireRegex.getRayon(chaineForme));
			break;
			
			case "OVALE":	forme = new Ovale(numeroSequence, new Color(1,0,0, 0.7f), UtilitaireRegex.getCentres(chaineForme), UtilitaireRegex.getRayon(chaineForme));
			break;
			
			case "LIGNE": forme = new Ligne(numeroSequence, Color.BLACK, UtilitaireRegex.getSommets(chaineForme));
			break;
			}
		}
		
		return forme;
	}
	
}
