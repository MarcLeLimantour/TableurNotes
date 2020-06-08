package controller;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import connection.DAOAcces;
import view.AjouterClasse;

public class ControleurAjtClasse {


	/** Controleur permettant d'ajouter une classe
	 * <br> <hr>
	 * Parametres :
	 * <li> String  - nom de la classe du stagiaire </li>
	 * <li> String  - nom du stagiaire </li>
	 * <li> String  - prenom du stagiaire </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	    public ControleurAjtClasse(String nom,JFrame frame) {		
	        
	        DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
			try {
				
				Statement creationDeClasse = dao.getConn().createStatement();		
				String nomClasse =  nom;
				// requête permettant d'inserer la classe
				String createClasse= "INSERT INTO classe(`nomClasse`) VALUES ('"+nomClasse+"');";
				//Si le champs du formulaire de création de classe n'est pas vide, ajoute une matière dans la BDD

	            // Expression régulière permettant de vérifier le nom de la classe
	            if(nomClasse.matches("^[a-zA-Z0-9]+[éèêËÊâäÄÂça-zA-Z0-9-\\s]*(?<!\\s)$"))
	            {
	            	
	            	// Validation d'ajout et redirection vers AjouterClasse
	        		JLabel validation = new JLabel("Vous avez bien ajouter la classe : "+nom);
	        		validation.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	        		validation.setForeground(Color.RED);
	        		validation.setBounds(270, 235, 600, 200);
	        		
	        		JPanel panel = new AjouterClasse(frame);
	        		panel.add(validation);
	            	
	               creationDeClasse.executeUpdate(createClasse);
    	               
					frame.getContentPane().removeAll();
					frame.getContentPane().add(panel);
					frame.getContentPane().repaint();
					frame.getContentPane().revalidate();
	            }
	            else 
	            {
	            	
	            	// Echec d'insertion retour ajouterclasse
	        		JLabel pata = new JLabel("Erreur d'insertion");
	        		pata.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	        		pata.setForeground(Color.RED);
	        		pata.setBounds(395, 150, 175, 40);
	        		
	        		JPanel panel = new AjouterClasse(frame);
	        		panel.add(pata);
	            	
					frame.getContentPane().removeAll();
					frame.getContentPane().add(panel);
					frame.getContentPane().repaint();
					frame.getContentPane().revalidate();
	            }

			 }
		    catch(SQLException e) {
				System.out.println("Probleme SQL !!");
				e.printStackTrace();
			}
			dao.closeConnection();
		}

	}

