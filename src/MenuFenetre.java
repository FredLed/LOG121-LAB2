/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

// TODO: Auto-generated Javadoc
/**
 * Crée le menu de la fenêtre de l'applicationé.
 */
public class MenuFenetre extends JMenuBar{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1536336192561843187L;
	
	/** The Constant MENU_DESSIN_ARRETER_TOUCHE_MASK. */
	private static final int  MENU_DESSIN_ARRETER_TOUCHE_MASK  = ActionEvent.CTRL_MASK;
	
	/** The Constant MENU_DESSIN_ARRETER_TOUCHE_RACC. */
	private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC  = KeyEvent.VK_A;
	
	/** The Constant MENU_DESSIN_DEMARRER_TOUCHE_MASK. */
	private static final int  MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	
	/** The Constant MENU_DESSIN_DEMARRER_TOUCHE_RACC. */
	private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;
	
	/** The Constant MENU_FICHIER_QUITTER_TOUCHE_MASK. */
	private static final int  MENU_FICHIER_OBTENIRFORME_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	
	/** The Constant MENU_FICHIER_QUITTER_TOUCHE_RACC. */
	private static final char MENU_FICHIER_OBTENIRFORME_TOUCHE_RACC = KeyEvent.VK_Q;
	
	/** The Constant MENU_AIDE_PROPOS. */
	private static final String
			MENU_FICHIER_TITRE = "app.frame.menus.file.title",
			MENU_ORDRE_TITRE = "app.frame.menus.order.title",
			MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",
			MENU_FICHIER_OBTENIRFORME = "app.frame.menus.file.getShape",
			MENU_DESSIN_TITRE = "app.frame.menus.draw.title",
			MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start",
			MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",
			MENU_AIDE_TITRE = "app.frame.menus.help.title",
			MENU_AIDE_PROPOS = "app.frame.menus.help.about";
	
	/** The Constant MESSAGE_DIALOGUE_A_PROPOS. */
	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";  

	/** The demarrer menu item. */
	private JMenuItem arreterMenuItem, demarrerMenuItem;
	
	/** The Constant DELAI_QUITTER_MSEC. */
	private static final int DELAI_QUITTER_MSEC = 200;
 	   
	/** The comm. */
	CommBase comm; // Pour activer/désactiver la communication avec le serveur
	
	/**
	 * Constructeur.
	 *
	 * @param comm the comm
	 */
	public MenuFenetre(CommBase comm) {
		this.comm = comm;
		addMenuFichier();
		addMenuOrdre();
		addMenuAide();
	}

	/** 
	 * Créer le menu "File". 
	 */
	protected void addMenuFichier() {
		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_OBTENIRFORME });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comm.start();
			   
			}
		});
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_OBTENIRFORME_TOUCHE_RACC,
						MENU_FICHIER_OBTENIRFORME_TOUCHE_MASK));
		add(menu);
	}

	/**
	 *  Créer le menu "Help". 
	 */
	private void addMenuAide() {
		JMenu menu = creerMenu(MENU_AIDE_TITRE, new String[] { MENU_AIDE_PROPOS });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,  LangueConfig.getResource(MESSAGE_DIALOGUE_A_PROPOS), 
						LangueConfig.getResource(MENU_AIDE_PROPOS), JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(menu);
	}

	/** 
	 * Créer le menu "Ordre". 
	 */
	protected void addMenuOrdre() {
		JMenu menu = creerMenu(MENU_ORDRE_TITRE, new String[] {});
		
		JRadioButtonMenuItem sequenceCroissant = new JRadioButtonMenuItem("No. S�quence Croissant");
		JRadioButtonMenuItem sequenceDecroissant = new JRadioButtonMenuItem("No. S�quence D�croissant");
		JRadioButtonMenuItem aireCroissante = new JRadioButtonMenuItem("Aire Croissante");
		JRadioButtonMenuItem aireDecroissante = new JRadioButtonMenuItem("Aire D�croissante");
		JRadioButtonMenuItem typeForme1 = new JRadioButtonMenuItem("Type Forme 1");
		JRadioButtonMenuItem typeForme2 = new JRadioButtonMenuItem("Type Forme 2");
		JRadioButtonMenuItem distanceMax = new JRadioButtonMenuItem("Distance Max");
		JRadioButtonMenuItem largeurCroissant = new JRadioButtonMenuItem("Largeur Croissante");
		JRadioButtonMenuItem largeurDecroissante = new JRadioButtonMenuItem("Largeur D�croissante");
		JRadioButtonMenuItem hauteurCroissante = new JRadioButtonMenuItem("Hauteur Croissante");
		JRadioButtonMenuItem hauteurDecroissante = new JRadioButtonMenuItem("Hauteur D�croissante");
		JRadioButtonMenuItem ordreOriginal = new JRadioButtonMenuItem("Ordre Original");
		
		ButtonGroup group = new ButtonGroup();
		group.add(sequenceCroissant);
		group.add(sequenceDecroissant);
		group.add(aireCroissante);
		group.add(aireDecroissante);
		group.add(typeForme1);
		group.add(typeForme2);
		group.add(distanceMax);
		group.add(largeurCroissant);
		group.add(largeurDecroissante);
		group.add(hauteurCroissante);
		group.add(hauteurDecroissante);
		group.add(ordreOriginal);
		
		menu.add(sequenceCroissant);
		menu.add(sequenceDecroissant);
		menu.add(aireCroissante);
		menu.add(aireDecroissante);
		menu.add(typeForme1);
		menu.add(typeForme2);
		menu.add(distanceMax);
		menu.add(largeurCroissant);
		menu.add(largeurDecroissante);
		menu.add(hauteurCroissante);
		menu.add(hauteurDecroissante);
		menu.add(ordreOriginal);
		
		
		add(menu);
	}
	
	/**
	 *  Activer ou désactiver les items du menu selon la sélection. 
	 */
	private void rafraichirMenus() {
		demarrerMenuItem.setEnabled(!comm.isActif());
		arreterMenuItem.setEnabled(comm.isActif());
	}
	
	/**
	 * Créer un élément de menu à partir d'un champs principal et ses éléments.
	 *
	 * @param titleKey champs principal
	 * @param itemKeys éléments
	 * @return le menu
	 */
	private static JMenu creerMenu(String titleKey,String[] itemKeys) {
        JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
        for(int i=0; i < itemKeys.length; ++i) {
           menu.add(new JMenuItem(LangueConfig.getResource(itemKeys[i])));
        }
        return menu;
   }
}
