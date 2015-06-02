import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class SocketClient.
 */
public class SocketClient extends Socket{
	
	/** The serveur distant. */
	private InetSocketAddress serveurDistant;
	
	/** The flux sortant. */
	private PrintWriter fluxSortant;
	
	/** The flux entrant. */
	private BufferedReader fluxEntrant;
	
	/**
	 * Instantiates a new socket client.
	 * Tente d'établir une connexion avec les information serveur en paramètres. 
	 * @param nomHote the nom hote
	 * @param numPort the num port
	 * @throws ServeurInactifException the serveur inactif exception
	 */
	public SocketClient(String nomHote, int numPort) throws ServeurInactifException{
		
		super();
		
		try{
			serveurDistant = new InetSocketAddress(nomHote, numPort);
			this.connect(serveurDistant, 1000);
			fluxSortant = new PrintWriter(this.getOutputStream(), true);
			fluxEntrant = new BufferedReader(new InputStreamReader(this.getInputStream()));
		}
		catch(IOException e){
			
			e.printStackTrace();
			throw new ServeurInactifException("Impossible d'établir une connection au serveur: " + nomHote + " sur le port: " + numPort);
			
		}
	}
	
	/**
	 * Instantiates a new socket client.
	 *
	 * @param socketClient the socket client
	 */
	public SocketClient(Socket socketClient){
		
		super();
		
		try{
			this.serveurDistant = new InetSocketAddress(socketClient.getInetAddress(),socketClient.getPort());
			fluxSortant = new PrintWriter(this.getOutputStream(), true);
			fluxEntrant = new BufferedReader(new InputStreamReader(this.getInputStream()));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Read line.
	 * Lit les données reçues dans le cannal de communication
	 * @return the string
	 * @throws ConnexionPerduException the connexion perdu exception
	 */
	public String readLine() throws ConnexionPerduException{
		
		String donneeRecue = null;
		
		try{
			donneeRecue = fluxEntrant.readLine();
		}
		catch(Exception e){
			
			throw new ConnexionPerduException("Connexion avec le serveur perdu.");
		}
		
		return donneeRecue;
	}
	
	/**
	 * Write line.
	 * Envoie des données dans le canal de commnunication
	 * @param donnee the donnee
	 * @throws ConnexionPerduException the connexion perdu exception
	 */
	public void writeLine(String donnee) throws ConnexionPerduException{
		
		
		fluxSortant.println(donnee);
		
		if(fluxSortant.checkError()){
		
			throw new ConnexionPerduException("Connexion avec le serveur perdu. Impossible d'envoyer la commande: " + donnee);
		}
	}
	
	/* 
	 * Ferme la communicaiton client-serveur de façon correct: en envoyant la commande END
	 */
	public void close(){
		
		try{
			this.writeLine("END");
			super.close();
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
		catch(ConnexionPerduException e){
			
			//JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
}
