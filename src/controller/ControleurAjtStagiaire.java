package controller;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Statement;

import connection.DAOAcces;
import model.Classe;
import model.Examen;
import model.Stagiaire;
import view.AjouterStagiaire;
import view.JAccueil;

public class ControleurAjtStagiaire {

	/** Controleur permettant d'ajouter un stagiaire
	 * <br> <hr>
	 * Parametres :
	 * <li> String  - nom de la classe du stagiaire </li>
	 * <li> String  - nom du stagiaire </li>
	 * <li> String  - prenom du stagiaire </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	public ControleurAjtStagiaire(String nomClasse,String nomS,String prenomS,JFrame frmTableurnotes) {
		
		/* Expression régulière permettant de vérifier la bonne saisie du nom / prenom du stagiaire */
		if(nomS.matches("^[^0-9][éèêËÊâäÄÂça-zA-Z0-9-\\\\s]*(?<!\\s)$") && prenomS.matches("^[^0-9][éèêËÊâäÄÂça-zA-Z0-9-\\\\s]*(?<!\\s)$"))
        {
		
		
		DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
		
		/* Requête permettant de recuperer l'id de la classe
		 * puis de creer la classe */ 
		int id = 0;
		String sqlIdCla = "SELECT idClasse FROM `classe` where nomClasse = '"+nomClasse+"';";
		try {
		ResultSet rsCla = dao.getStatement().executeQuery(sqlIdCla);
		while(rsCla.next()) { 
			id = rsCla.getInt(1);
		}
		
		Classe cl1 = new Classe(id,dao);
		
		String nom = nomS;
		String prenom = prenomS;
		
		
		int numSta = 0;
		int cpt = 0;
		
		/* Recuperer l'id stagiaire maximum de stagiaire  */
		String sql = "SELECT MAX(idStagiaire) FROM `stagiaire`;";

			ResultSet rs = dao.getStatement().executeQuery(sql);
			while(rs.next()) { 
				cpt = rs.getInt(1);
			}
			
			/* Requete permettant d'ajouter un stagiaire avec ses parametres */
			String sqlInsert = "INSERT INTO `stagiaire`(`idStagiaire`, `nomStagiaire`, `prenomStagiaire`, `classeStagiaire`)"
					+ "VALUES (" + cpt + ",'" + nom + "','" + prenom + "'," + cl1.getId() + ")";

			Statement addStag = (Statement) dao.getConn().createStatement();
			addStag.executeUpdate(sqlInsert);
			
			/* Ajout d'un stagiaire a la classe (objet) */
			cl1.ajouterStagiaire(new Stagiaire(cpt,nom,prenom,cl1,dao));
			
			numSta = cpt;
			
			/* Requête permettant de creer un compte utilisateur pour le stagiaire */
			String sqlUser = "INSERT INTO `utilisateur`(`nom`, `password`, `fonction`, `numStagiaire`)"
					+ "VALUES ('" + nom + "','" + prenom +"','eleve', "+ numSta +")";
			
			addStag.executeUpdate(sqlUser);
			
			/* Ajoute null aux notes d'examen existant avant création du stagiaire
			 * null sera traiter par la suite en absent dans le tableau  */
			for(Examen exa: cl1.getExamens()) {
						cl1.getStagiaires().get(cl1.getStagiaires().size()-1).getNotes().add(null);
						String sqlAjoutNote = "INSERT INTO `note`(`idStagiaire`, `idExamen`, `valeurNote`)"
								+ "VALUES (" + cpt + "," + exa.getId() + ",null)";
						addStag.executeUpdate(sqlAjoutNote);
			}
		}catch(SQLException e){
			System.out.println("Probl�me SQL Recherche id!!");
			e.printStackTrace();
		}

		/* Validation de l'ajout */
		JLabel lblNewLabel = new JLabel("Vous avez bien ajout� l'el�ve : "+nomS+" "+prenomS);		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setBounds(0, 63, 975, 78);
		
		JPanel panel = new JAccueil(frmTableurnotes);
		panel.add(lblNewLabel);
		
		/* Renvoie de la page Accueil */
		
	  	frmTableurnotes.getContentPane().removeAll();
		frmTableurnotes.getContentPane().add(panel);
		frmTableurnotes.getContentPane().repaint();
		frmTableurnotes.getContentPane().revalidate();
		
		dao.closeConnection();
		
		}
		else
		{
			/* Renvoie une erreur et renvoie a la page ajoutStagiaire */
			
    		JLabel error = new JLabel("Erreur d'insertion");
    		error.setFont(new Font("Times New Roman", Font.PLAIN, 25));
    		error.setForeground(Color.RED);
    		error.setBounds(390, 200, 175, 40);
    		
    		JPanel panel = new AjouterStagiaire(frmTableurnotes);
    		panel.add(error);

    		
    		frmTableurnotes.getContentPane().removeAll();
    		frmTableurnotes.getContentPane().add(panel);
    		frmTableurnotes.getContentPane().repaint();
    		frmTableurnotes.getContentPane().revalidate();
			
		}
		
	}
	
}
