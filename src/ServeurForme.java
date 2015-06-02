import javax.swing.*;
import java.io.*;
import java.net.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ServeurForme.
 */
public class ServeurForme {
	
	/**
	 * Représente l'index du tableau dans lequel est stocké
	 * les informations Port et nom d'hôte d'un serveur de forme.
	 * Pour la lisibilité.
	 */
	public final int NOM_HOTE = 0;
	
	/** The port. */
	public final int PORT = 1;

	/**
	 * Tableau contenant les informations port et nom d'hôte 
	 * d'un serveur de forme.
	 */
	private Object[] serverInfo;
	
	/**
	 *Constructeur par défault; crée une nouvelle référence à un serveur de 
	 *forme (host:port) depuis les informations entrées par 
	 *l'utilisateur dans InputDialog. Le port et le hostname sont 
	 * validé lors de l'entré et stocké dans un tableau (serverInfo).
	 */
	public ServeurForme(){
		
		while(serverInfo == null){
			
			try{
				serverInfo = this.requestServerInfos();
			}
			catch(SaisieNomHoteException e){
				
				JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Constructeur par copie d'objet; crée un nouvelle référence
	 * à un serveur de forme (host:port) en copiant les attributs
	 * d'un autre serveur.
	 * 
	 * @param serveur Une référence à un serveur de forme
	 */
	public ServeurForme(ServeurForme serveur){
		
		this.setNumeroPort(serveur.getNumeroPort());
		this.setNomHote(serveur.getNomHote());
		
	}
	
	/**
	 * Gets the numero port.
	 *
	 * @return the numero port
	 */
	public int getNumeroPort(){
		
		return Integer.parseInt((String)this.serverInfo[PORT]);
	}
	
	/**
	 * Sets the numero port.
	 *
	 * @param numPort the new numero port
	 */
	public void setNumeroPort(int numPort){
		
		this.serverInfo[PORT] = new Integer(numPort);
	}
	
	/**
	 * Gets the nom hote.
	 *
	 * @return the nom hote
	 */
	public String getNomHote(){
		
		return (String)this.serverInfo[NOM_HOTE];
	}
	
	/**
	 * Sets the nom hote.
	 *
	 * @param nomHote the new nom hote
	 */
	public void setNomHote(String nomHote){
		
		this.serverInfo[NOM_HOTE] = new String(nomHote);
	}
	
	/**
	 * Request server infos.
	 *
	 * @return the object[]
	 * @throws SaisieNomHoteException the saisie nom hote exception
	 */
	public Object[] requestServerInfos() throws SaisieNomHoteException{
		
		Object[] serverInfo = null;
		String informationsServeurEntrees = JOptionPane.showInputDialog(null,
				"Quel est le nom d'hôte et le port du serveur de formes?",
				"Informations sur le serveur distant",
				JOptionPane.QUESTION_MESSAGE);
		
		
		if(informationsServeurEntrees == null){
			System.exit(0);
		}
		else{
			if(texteEntreEstValide(informationsServeurEntrees)){
				
				serverInfo = informationsServeurEntrees.split(":");
				
				if(!nomHoteEstValide((String)serverInfo[NOM_HOTE])){
					throw new SaisieNomHoteException("Nom d'hôte invalide.");
				}
				else if(!numeroPortEstValide(Integer.parseInt((String)serverInfo[PORT]))){
					throw new SaisieNomHoteException("Numéro de port invalide. Le numéro doit être entre 0 et 65536");
				}
			}
			else{
				
				throw new SaisieNomHoteException("Entrez un nom d'hôte sous la forme hôte:port \n"
						+ "Où hôte est un nom DNS valide et port un numéro entre 0 et 65536");
			}
		}
		
		return serverInfo;
	}
	
	
	/**
	 * Texte entre est valide.
	 *
	 * @param texteEntre the texte entre
	 * @return true, if successful
	 */
	public boolean texteEntreEstValide(String texteEntre){
		
		return texteEntre.matches(".+:[0-9]{1,5}");
	}
	
	/**
	 * Nom hote est valide.
	 *
	 * @param nomHote the nom hote
	 * @return true, if successful
	 */
	public boolean nomHoteEstValide(String nomHote){
		
		return nomHote.matches("[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,}(\\.[a-zA-Z]){0,1}){0,}");
	}
	
	/**
	 * Numero port est valide.
	 *
	 * @param numPort the num port
	 * @return true, if successful
	 */
	public boolean numeroPortEstValide(int numPort){
		
		return numPort > 0 && numPort <= 65536;
	}
	
}
