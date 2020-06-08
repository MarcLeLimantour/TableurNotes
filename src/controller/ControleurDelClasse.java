package controller;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import connection.DAOAcces;
import view.SupprClasse;

public class ControleurDelClasse {


	/** Controleur permettant d'ajouter une classe
	 * <br> <hr>
	 * Parametres :
	 * <li> String  - nom de la classe du stagiaire </li>
	 * <li> String  - nom du stagiaire </li>
	 * <li> String  - prenom du stagiaire </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	    public ControleurDelClasse(String nom,JFrame frame) {		
	        
	        DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
			try {
				
				Statement suppressionDeClasse = dao.getConn().createStatement();		
				String nomClasse = nom;
				// requete permettant de recup l'id de la classe
				String getIdClasse = "SELECT idClasse FROM classe WHERE nomClasse = '"+nom+"';";
				ResultSet idClasse = suppressionDeClasse.executeQuery(getIdClasse);
				
				int classe = 0;
				while(idClasse.next()) {
					classe = idClasse.getInt("idClasse");
				}
				
				// requ�te permettant de supprimer la classe
				String deleteNotes = "DELETE note FROM note INNER JOIN examen ex ON note.idExamen = ex.idExamen INNER JOIN classe cl ON cl.idClasse = ex.classeExamen WHERE cl.idClasse = '"+classe+"';";
				String deleteExam = "DELETE examen FROM examen INNER JOIN classe ON classe.idClasse = examen.classeExamen WHERE classe.idClasse = '"+classe+"';";
				String deleteStag = "DELETE stagiaire FROM stagiaire INNER JOIN classe cl ON cl.idClasse = stagiaire.classeStagiaire WHERE cl.idClasse = '"+classe+"';";
				String deleteClasse= "DELETE classe FROM classe WHERE idClasse = '"+classe+"';";	
				
				String checkDeleteClasse = "SELECT idclasse FROM classe WHERE idClasse = '"+classe+"';";
				
				System.out.println(deleteNotes);
	            // Expression régulière permettant de v�rifier le nom de la classe
	            if(!nomClasse.matches("^[a-zA-Z0-9]+[éèêËÊâäÄÂça-zA-Z0-9-\\s]*(?<!\\s)$"))
	            {
	            	// Echec d'insertion retour ajouterclasse
	        		JLabel pata = new JLabel("Nom de classe non reconnu.");
	        		pata.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	        		pata.setForeground(Color.RED);
	        		pata.setBounds(395, 150, 175, 40);
	        		
	        		JPanel panel = new SupprClasse(frame);
	        		panel.add(pata);
	            	
					frame.getContentPane().removeAll();
					frame.getContentPane().add(panel);
					frame.getContentPane().repaint();
					frame.getContentPane().revalidate();	      	        		
	            }
	            else 
	            {
	            	//Le nom de la classe est valide
	            	suppressionDeClasse.executeUpdate(deleteNotes);
	        		suppressionDeClasse.executeUpdate(deleteExam);
	        		suppressionDeClasse.executeUpdate(deleteStag);
	        		suppressionDeClasse.executeUpdate(deleteClasse);
	        	
	        		// Validation d'ajout et redirection vers AjouterClasse
	        		ResultSet checkDelete = suppressionDeClasse.executeQuery(checkDeleteClasse);
	        		if (checkDelete.next() == false) {
		        		
		        		JLabel validation = new JLabel("Vous avez bien supprim� la classe : "+nom);
		        		validation.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        		validation.setForeground(Color.RED);
		        		validation.setBounds(270, 235, 600, 200);
		        		
		        		JPanel panel = new SupprClasse(frame);
		        		
		        		panel.add(validation);
						frame.getContentPane().removeAll();
						frame.getContentPane().add(panel);
						frame.getContentPane().repaint();
						frame.getContentPane().revalidate();
	        		}
	        		else {
	        			JLabel validation = new JLabel("Erreur lors de la suppression. "+nom);
		        		validation.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        		validation.setForeground(Color.RED);
		        		validation.setBounds(270, 235, 600, 200);
		        		
		        		JPanel panel = new SupprClasse(frame);
		        		
		        		panel.add(validation);
						frame.getContentPane().removeAll();
						frame.getContentPane().add(panel);
						frame.getContentPane().repaint();
						frame.getContentPane().revalidate();	        		
	        		}
	            }
			 }
		    catch(SQLException e) {
				System.out.println("Probleme SQL !!");
				e.printStackTrace();
			}
			dao.closeConnection();
		}

	}

