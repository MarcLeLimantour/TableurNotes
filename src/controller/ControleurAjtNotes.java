package controller;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import connection.DAOAcces;
import model.Classe;
import model.Examen;
import view.AjouterExamen;
import view.JAccueil;

public class ControleurAjtNotes {

	
	/** Controleur permettant d'ajouter les notes a un examen
	 * <br> <hr>
	 * Parametres :
	 * <li> Tableau de String  - [nom prenom][notes] </li>
	 * <li> Classe  - objet classe </li>
	 * <li> Examen  - objet examen </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	public  ControleurAjtNotes(String[][] NoteSta, Classe cla,Examen exa,JFrame frmTableurnotes) {
		
		Examen exa1 = exa;
		Classe cla1 = cla;
		
		DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
		
		/* Requête permettant de selectionner les examens
		 * de les parcourir et d'ajouter l'examen */
		String sqlExam = "Select * from examen;";
		ResultSet rsExam;
		try {
			rsExam = dao.getStatement().executeQuery(sqlExam);
			
			/* Créer une ligne vide */
			rsExam.moveToInsertRow();
			
			/* Créer l'examen dans cette ligne */
			rsExam.updateInt("idExamen", exa1.getId());
			rsExam.updateString("dateExamen", exa1.getDate());
			rsExam.updateInt("classeExamen", cla1.getId());
			rsExam.updateInt("matiereExamen", exa1.getIdMatiere());
			rsExam.insertRow();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Double note;

		for(int i = 0;i<NoteSta.length;i++) {			

			// Requete permettant de selectionner toutes les notes
			String sqlNote = "Select * from note;";

			try {
				ResultSet rsNote = dao.getStatement().executeQuery(sqlNote);
				
				/* Créer une ligne vide et prepare l'ajout */
			rsNote.moveToInsertRow();
			rsNote.updateInt("idStagiaire", cla1.getStagiaires().get(i).getId());
			rsNote.updateInt("idExamen", exa1.getId());

			/* Condition verifiant que les notes
			 * Si la note est vide alors on la détermine null
			 * et elle sera interpreter comme "absent" */
		if (NoteSta[i][1].equals("")) {
				
			note = null;
			exa1.ajouterNote(note);
			rsNote.insertRow();
			
		} 
		/* sinon ajoute la valeur de la note */
		else 
		{
			
			note = Double.parseDouble(NoteSta[i][1]);
			exa1.ajouterNote(note);
			rsNote.updateDouble("valeurNote", note);
			rsNote.insertRow();
			
		}

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					}
			
			dao.closeConnection();
			
			
			/* Affichage page validation */
    		JLabel validation = new JLabel("Vous avez bien ajouter l'examen du "+exa.getDate()+".");
    		validation.setFont(new Font("Times New Roman", Font.PLAIN, 25));
    		validation.setForeground(Color.GREEN);
    		validation.setHorizontalAlignment(SwingConstants.CENTER);
    		validation.setBounds(0, 115, 975, 50);
    		
    		JPanel panel = new JAccueil(frmTableurnotes);
    		panel.add(validation);
			
			frmTableurnotes.getContentPane().removeAll();
			frmTableurnotes.getContentPane().add(panel);
			frmTableurnotes.getContentPane().repaint();
			frmTableurnotes.getContentPane().revalidate();
			
			
			
		}

	/** Constructeur de cas d'erreur  */
	public ControleurAjtNotes(JFrame frmTableurnotes) {
		
		/* Affichage d'erreur et retour page AjoutExamen */
		JLabel lblNewLabel = new JLabel("Erreur lors de la saisie de l'examen.");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(243, 104, 502, 25);
		
		
		JPanel panel = new AjouterExamen(frmTableurnotes);
		panel.add(lblNewLabel);
		
		frmTableurnotes.getContentPane().removeAll();
		frmTableurnotes.getContentPane().add(panel);
		frmTableurnotes.getContentPane().repaint();
		frmTableurnotes.getContentPane().revalidate();
		
	}
		
	}

