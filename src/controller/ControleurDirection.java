package controller;

import javax.swing.JFrame;

import view.AjouterClasse;
import view.AjouterExamen;
import view.AjouterMatiere;
import view.AjouterStagiaire;
import view.ChoixClasse;
import view.JAccueil;
import view.SupprClasse;

public class ControleurDirection {
	
	/** Controleur permettant de rediriger vers d'autres pages
	 * <br> <hr>
	 * Parametres :
	 * <li> String  - redirection souhaitée </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	public ControleurDirection(String direction, JFrame frmTableurnotes) {
		
		switch (direction) {
		  case "Accueil":
			  	frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(new JAccueil(frmTableurnotes));
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
		    break;
		    
		  case "AjtClasse":
			  	frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(new AjouterClasse(frmTableurnotes));
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
		    break;
		    
		  case "AjtMatiere":
			  	frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(new AjouterMatiere(frmTableurnotes));
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
		    break;
		    
		  case "AjtStagiaire":
				frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(new AjouterStagiaire(frmTableurnotes));
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
		    break;
		    
		  case "AjtExamen":
				frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(new AjouterExamen(frmTableurnotes));
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
		    break;
		    
		  case "CsltClasse":
			  	frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(new ChoixClasse(frmTableurnotes));
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
		    break;
		    
		  case "DelClasse":
			  	frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(new SupprClasse(frmTableurnotes));
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
		    break;
		    
		}
		
	}
	
}
