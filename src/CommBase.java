/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date créé: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/

import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import java.io.*;
import java.net.*;

// TODO: Auto-generated Javadoc
/**
 * Base d'une communication via un fil d'exécution parallèle.
 */
public class CommBase {

	/** The delai. */
	private final int DELAI = 1000;
	
	/** The thread comm. */
	private SwingWorker threadComm = null;
	
	/** The listener. */
	private PropertyChangeListener listener = null;
	
	/** L'�tat de la communication client-serveur */
	private boolean isActif = false;
	
	/** Le canal de communication client-serveur. */
	private SocketClient connexionServeur = null;
	
	/** Le serveur de forme qui envoie les formes */
	private ServeurForme serveurForme = null;
	
	/** The createur forme. */
	private CreateurForme createurForme = null;
	
	/** La liste contenant les formes re�ue du serveur de forme */
	private ClListe listeForme = null;
	
	/**
	 * Constructeur.
	 */
	public CommBase(){
		
		this.serveurForme = new ServeurForme();
		this.listeForme = new ClListe(10);
		this.createurForme = new CreateurForme();
	}
	
	/**
	 * Gets the liste forme.
	 *
	 * @return the liste forme
	 */
	public ClListe getListeForme(){
		
		return this.listeForme;
	}
	
	/**
	 * M�thode qui cr�e le canal de communication. Si le serveur ne r�pond pas
	 * une exception est lev�.
	 *
	 *
	 * @param nomHote the nom hote
	 * @param numPort the num port
	 * @return true, if successful
	 */
	public boolean setConnexionServeurForme(String nomHote, int numPort) {
		
		boolean estConnecte = false;
		
		try {
			
			this.connexionServeur = new SocketClient(nomHote, numPort);
			estConnecte = true;
			
		} catch (ServeurInactifException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur",
					JOptionPane.ERROR_MESSAGE);
			this.serveurForme = new ServeurForme();
		}
		
		return estConnecte;
	}

	/**
	 * D�finir le r�cepteur de l'information re�ue dans la communication avec le
	 * serveur.
	 *
	 * @param listener            sera alerté lors de l'appel de "firePropertyChanger" par le
	 *            SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * Démarre la communication.
	 */
	public void start() {
		
		boolean estConnecte = false;
		
		do{
			estConnecte = this.setConnexionServeurForme(serveurForme.getNomHote(),
				serveurForme.getNumeroPort());
		}while(!estConnecte);
		
		creerCommunication();
		
	}

	/**
	 * Arrête la communication.
	 * Ferme le canal de communication proprement et vide la liste de forme.
	 */
	public void stop() {
		if (threadComm != null) {
			if (connexionServeur != null) {

				connexionServeur.close();
			}

			threadComm.cancel(true);
		}
		isActif = false;
	}

	/**
	 * Créer le nécessaire pour la communication avec le serveur.
	 * Cette m�thode fait les demandes de forme au serveur de forme en envoyant des get
	 * � l'aide du canal de communication socketClient. Les chaines de caract�res re�ue par
	 * le client sont d�cortiqu� � l'aide de la classe static UtilitaireRegex et les formes 
	 * sont ajout� dans la liste de forme. Si la connexion avec le serveur est coup�e, une exception 
	 * est lev�e.
	 */
	protected void creerCommunication() {
		// Crée un fil d'exécusion parallèle au fil courant,
		threadComm = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("R�ception des formes");
				String chaineForme;
				AbstractForme formeRecue;
				listeForme.reinitialiser();
				
				while (!listeForme.estPleine()) {
					
					chaineForme = null;
					
					try {
						connexionServeur.writeLine("GET");
						chaineForme = connexionServeur.readLine();
						
						if(chaineForme != null){
							formeRecue = createurForme.creerForme(chaineForme);
							
							if(formeRecue != null){
								
								listeForme.InsererApres(formeRecue);
							}
						}
						
					} catch (ConnexionPerduException e) {

						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Erreur", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
						stop();
					}

					// La méthode suivante alerte l'observateur
					if (listener != null)
						firePropertyChange("ENVOIE-TEST", null, (Object) ".");
				}
				 Thread.sleep(DELAI);
				 stop();
				 return null;
			}
		};
		if (listener != null)
			threadComm.addPropertyChangeListener(listener); 
		threadComm.execute(); // Lance le fil d'exécution parallèle.
		isActif = true;
	}

	/**
	 * Checks if is actif.
	 *
	 * @return si le fil d'exécution parallèle est actif
	 */
	public boolean isActif() {
		return isActif;
	}
}
