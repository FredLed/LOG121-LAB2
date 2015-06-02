import java.awt.Point;
import java.util.regex.*;
import ca.etsmtl.log.util.IDLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilitaireRegex.
 */
public class UtilitaireRegex {
	
	/** The pattern. */
	private static Pattern pattern;
	
	/** The matcher. */
	private static Matcher matcher;
	
	private static IDLogger logger = IDLogger.getInstance();
	/**
	 * Gets the numero sequence.
	 *
	 * @param chaineForme the chaine forme
	 * @return the numero sequence
	 */
	public static int getNumeroSequence(String chaineForme){
		
		int numeroSequence = 0;
		
		pattern = Pattern.compile("^[0-9]+");
		matcher = pattern.matcher(chaineForme);
		
		if(matcher.find()){
			
			numeroSequence = Integer.parseInt(matcher.group());
		}
		
		logger.logID(numeroSequence);
		
		return numeroSequence;
		
	}
	
	/**
	 * Gets the type forme.
	 *
	 * @param chaineForme the chaine forme
	 * @return the type forme
	 */
	public static String getTypeForme(String chaineForme){
		
		String type = null;
		
		pattern = Pattern.compile("<([^/]+)>");
		matcher = pattern.matcher(chaineForme);
		
		if(matcher.find()){
			
			type = matcher.group(1);
		}
		
		return type;
	}
	
	/**
	 * Gets the sommets.
	 *
	 * @param chaineForme the chaine forme
	 * @return the sommets
	 */
	public static Point[] getSommets(String chaineForme){
		
		Point[] pointsSommets = new Point[2];
		int[] coordonnees = new int[4];
		pattern = Pattern.compile("(?!([0-9]+ <[a-zA-Z]+>))[0-9]+");
		matcher = pattern.matcher(chaineForme);
		int index = 0;
		
		while(matcher.find()){
			
			coordonnees[index++] = Integer.parseInt(matcher.group(0));
			
		}
		
		pointsSommets[0] = new Point(coordonnees[0], coordonnees[1]);
		pointsSommets[1] = new Point(coordonnees[2], coordonnees[3]);
		
		return pointsSommets;
	}
	
	/**
	 * Gets the centres.
	 *
	 * @param chaineForme the chaine forme
	 * @return the centres
	 */
	public static Point getCentres(String chaineForme){
		
		Point pointCentre;
		int[] coordonnees = new int[2];
		
		pattern = Pattern.compile("(?!([0-9]+ <[a-zA-Z]+>))[0-9]+");
		matcher = pattern.matcher(chaineForme);
		int flagCentre = 0;
		int index = 0;
		
		while(matcher.find() && flagCentre < 2){
			
			coordonnees[index++] = Integer.parseInt(matcher.group(0));
			flagCentre++;
		}
		
		pointCentre = new Point(coordonnees[0], coordonnees[1]);
		
		return pointCentre;
	}
	
	/**
	 * Gets the rayon.
	 *
	 * @param chaineForme the chaine forme
	 * @return the rayon
	 */
	public static int[] getRayon(String chaineForme){
		
		int[] rayon;
		pattern = Pattern.compile("(?!([0-9]+ <[a-zA-Z]+>))[0-9]+");
		matcher = pattern.matcher(chaineForme);
		int flagRayon = 0;
		int index = 0;
		
			
		rayon = new int[2];
		
		while(matcher.find()){
			
			if(flagRayon >= 2){
				
				rayon[index++] = Integer.parseInt(matcher.group(0));
			}
			
			flagRayon++;
		}
		
		return rayon;
	}
}
