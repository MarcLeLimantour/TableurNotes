package controller;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import connection.DAOAcces;
import view.AjouterMatiere;
	
public class ControleurAjtMatiere {
	       
	/** Controleur permettant d'ajouter une matière
	 * <br> <hr>
	 * Parametres :
	 * <li> String  - nom de la matière </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	    public ControleurAjtMatiere(String nom, JFrame frame) {
	        super();
	        // TODO Auto-generated constructor stub
	        
			DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
			try {
				
				//Prépare la requête d'insertion de matière
				Statement creationMatiere = dao.getConn().createStatement();
	            String nomMat = nom;
	            String sqlMat = "INSERT INTO matiere (`nomMatiere`) VALUES ('"+nomMat+"')";            
	            
				//Si le champs du formulaire de création de classe n'est pas vide, ajoute une matière dans la BDD
	            
	            // Expression régulière permettant d'empecher une mauvaise saisie du nom de matière
	            if(nomMat.matches("^[a-zA-Z0-9]+[éèêËÊâäÄÂça-zA-Z0-9-\\s]*(?<!\\s)$"))
	            {
	            	
	            	// Validation et redirection AjoutMatiere
	        		JLabel validation = new JLabel("Vous avez bien ajouter la matière : "+nom);
	        		validation.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	        		validation.setForeground(Color.RED);
	        		validation.setBounds(300, 175, 600, 300);
	        		
	        		JPanel panel = new AjouterMatiere(frame);
	        		panel.add(validation);
	            	
	               creationMatiere.executeUpdate(sqlMat);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(panel);
					frame.getContentPane().repaint();
					frame.getContentPane().revalidate();
	            }
	            else 
	            {
	            	
	            	// Erreur et redirection ajoutmatiere
	        		JLabel error = new JLabel("Erreur d'insertion");
	        		error.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	        		error.setForeground(Color.RED);
	        		error.setBounds(390, 150, 175, 40);
	        		
	        		JPanel panel = new AjouterMatiere(frame);
	        		panel.add(error);

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

