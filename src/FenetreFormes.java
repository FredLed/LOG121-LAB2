/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/  

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

// TODO: Auto-generated Javadoc
/**
 * Cette fenêtre gère l'affichage des formes .
 *
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent implements Observer{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2262235643903749505L;
	
	/** The Constant WIDTH. */
	public static final int WIDTH = 500;
	
	/** The Constant HEIGHT. */
	public static final int HEIGHT = 500;
	
	/** The Constant dimension. */
	public static final Dimension dimension = new Dimension(500,500);
	
	/** The liste forme. */
	private ListeForme listeForme = null;
	
	
	/**
	 * Constructeur.
	 *
	 * @param listeForme the liste forme
	 */
	public FenetreFormes(ListeForme listeForme){
		
		this.listeForme = listeForme;
		listeForme.addObserver(this);
	}
	
	/* 
	 * Rafraichi l'Affichage des formes losrqu'une forme est ajout� dans la liste
	 */
	public void update(Observable obs, Object obj){
		
		super.paintComponents(super.getGraphics());
		this.repaint();
	}
	
	/*
	 * Affiche les forme de la liste
	 */
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override 
	public void paintComponent(Graphics g){
		
		for(int i = 0; i < listeForme.getNombreForme(); i++){
			
			listeForme.getForme(i).draw(g);
		}
	}
	
	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver 
	 * l'espace nécessaire à son affichage
	 */
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override 
	public Dimension getPreferredSize(){
		return dimension;
	}
}