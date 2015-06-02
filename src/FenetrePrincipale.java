/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetrePrincipale.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/  

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
// TODO: Auto-generated Javadoc
/**
 * Cette classe représente la fenêtre principale de l'application .
 *
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1210804336046370508L;

	/**
	 * Constructeur.
	 *
	 * @param comm the comm
	 */
	public FenetrePrincipale(CommBase comm){
		
		MenuFenetre menu = new MenuFenetre(comm);
		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.NORTH); 
		FenetreFormes fenetreFormes = new FenetreFormes(comm.getListeForme());
		this.add(fenetreFormes, BorderLayout.CENTER); // Ajoute la fenêtre de forme à la fenètre principale
		this.pack(); // Ajuste la dimension de la fenêtre principale selon celle de ses composants
		this.setVisible(true); // Rend la fenêtre principale visible.
		this.addWindowListener(new EcouteurFenetre(comm));
	}
	
	// Appelé lorsque le sujet lance "firePropertyChanger"
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		
		if(arg0.getPropertyName().equals("ENVOIE-TEST")){
			System.out.print((String) arg0.getNewValue());
		}
	}
	
	protected class EcouteurFenetre extends WindowAdapter{
		
		private CommBase comm;
		
		public EcouteurFenetre(CommBase comm){
			
			this.comm = comm;
		}
		
		public void windowClosing(WindowEvent e){
			
			if(this.comm.isActif()){this.comm.stop();}
			System.exit(0);
		}
	}
}
