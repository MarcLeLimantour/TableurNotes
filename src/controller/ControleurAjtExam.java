package controller;

import java.awt.Color;
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
import view.AjouterNotes;

public class ControleurAjtExam {
	
	/** Controleur permettant d'ajouter un examen
	 * <br> <hr>
	 * Parametres :
	 * <li> String  - nom de la classe </li>
	 * <li> String  - nom de la matière </li>
	 * <li> String  - dat de l'examen </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	public ControleurAjtExam(String nomCla,String nomMat,String dateExa,JFrame frmTableurnotes) {
		
		DAOAcces daoCla = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
		
		String date = dateExa;
		int idCla = 0;
		
		// Récupération de l'id de classe en fonction du nom de la classe
		String sqlIdCla = "SELECT idClasse FROM `classe` where nomClasse = '"+nomCla+"';";
		try {
		ResultSet rsCla = daoCla.getStatement().executeQuery(sqlIdCla);
		while(rsCla.next()) { 
			idCla = rsCla.getInt(1);
		}
		
		Classe cla = new Classe(idCla,daoCla);
		
		
		int idMat = 0;
		DAOAcces daoMat = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
		
		
		// Récupération de l'id de matiere en fonction du nom de la matiere
		String sqlIdMat = "SELECT idMatiere FROM `matiere` where nomMatiere = '"+nomMat+"';";
		ResultSet rsMat = daoMat.getStatement().executeQuery(sqlIdMat);
		while(rsMat.next()) { 
			idMat = rsMat.getInt(1);
		}
		
		// Condition vérifiant si la date est renseignée
		if(!date.isEmpty()) {
		DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
		
		String sql = "select MAX(idExamen) as max from examen";
			ResultSet rs = dao.getStatement().executeQuery(sql);
			int lastId = 0;
			while(rs.next()) {
				lastId = rs.getInt("max");
			}
				lastId=lastId+1;
				
				// création de l'objet examen
				Examen exa = new Examen(lastId,cla,idMat,date,dao);
				
				dao.closeConnection();

				// Passe l'examen/classe en parametre de AjouterNotes
				frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(new AjouterNotes(cla,exa,frmTableurnotes));
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
			}
			// Sinon renvoie a la page AjouterExamen
			else 
			{
				JLabel lblNewLabel = new JLabel("Saisir une date !");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBounds(70, 275, 975, 23);
				lblNewLabel.setForeground(Color.RED);

				JPanel panel = new AjouterExamen(frmTableurnotes);
				panel.add(lblNewLabel);
				
				
				frmTableurnotes.getContentPane().removeAll();
				frmTableurnotes.getContentPane().add(panel);
				frmTableurnotes.getContentPane().repaint();
				frmTableurnotes.getContentPane().revalidate();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
		}
		
		
	}
		
}
