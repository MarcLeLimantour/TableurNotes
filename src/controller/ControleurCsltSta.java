package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import connection.DAOAcces;
import model.Classe;
import model.Matiere;
import model.Stagiaire;
import view.ConsulterClasse;

public class ControleurCsltSta {

	/** Controleur permettant d'afficher le tableau personnalisé d'un stagiaire
	 * <br> <hr>
	 * Parametres :
	 * <li> Int  - id de la classe du stagiaire </li>
	 * <li> String  - nom du stagiaire </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	public ControleurCsltSta(int idCla,String nomStagiaire,JFrame frmTableurnotes) {
		

		DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
		/* Création de la classe grâce a son id */
		Classe cl1 = new Classe(idCla,dao);
		
		/* Requête pour recuperer l'id du stagiaire en fonction du nom */
		int numStagiaire = 0;
		String sql = "select idStagiaire from stagiaire where nomStagiaire ='"+nomStagiaire+"'";
		
		try {
			 
			ResultSet rs = dao.getStatement().executeQuery(sql);
			
			while(rs.next()) 
			{
				numStagiaire = rs.getInt(1);	
			}

		 }
	    catch(SQLException e) {
			System.out.println("Probleme SQL b!!");
			e.printStackTrace();
		}
		dao.closeConnection();
		
		/* Recupération dans une String l'affichage en html du tableau de notes 
		 * SEULEMENT POUR UN STAGIAIRE */
		String tab = "<html><h1 align=center>Tableau des notes de la classe de "+cl1.getNom()+"</h1><br><br>"
				   + "<table border=1 align=center><tr><th>"+cl1.getNom()+"</th>"; 
		
						for(Matiere mat: cl1.getMatieres()) { 
							tab+= "<td><table border=1><tr><th align=center colspan="+cl1.nbExamensMatiere(mat.getId())+">"+mat.getNom()+"</th></tr><tr>";
							
								int j = 1; 
								for(int i = 0; i < cl1.nbExamensMatiere(mat.getId());i++) { 
									 tab += "<td align=center>Exam"+j+"</td>"; 
									 j++; 
							} 
							tab += "</tr></table></td>"; 
						} 
						 tab += "<th>Moyennes</th></tr>";  
						
						for(Stagiaire sta: cl1.getStagiaires()) {
							/* Condition permettant d'afficher seulement du html pour le stagiaire selectionné */
							if(sta.getId() == numStagiaire) {
								tab += "<tr><td>"+sta.getNom()+" "+sta.getPrenom()+"</td>";
							
								for(int j = 0; j < cl1.getMatieres().size(); j++) { 
									tab += "<td align=center height=100%><table border=1 width=100% height=100%><tr>"; 
								for(int i = 0; i < cl1.getExamens().size(); i++) { 
									if (j < sta.getExamens().size() && j < cl1.getExamens().size()) { 
										if(sta.getExamens().get(i) == cl1.getExamens().get(i).getId() && cl1.getMatieres().get(j).getId() == cl1.getExamens().get(i).getMatiere()){
											if (sta.getNotes().get(i) == null){ 
												tab += "<td align=center height=100% width="+(100/cl1.nbExamensMatiere(cl1.getMatieres().get(j).getId()))+"%>Abs.</td>"; 
											} 
											else { 
												tab += "<td align=center width="+(100/cl1.nbExamensMatiere(cl1.getMatieres().get(j).getId()))+"%>"+sta.getNotes().get(i)+"</td>"; 
												}
									} 
								}

							} 
							tab += "</tr></table></td>";
							
							}
							tab += "<td align=center>"+sta.getMoyenne()+"</td></tr>";
							}
						}
						 
						tab += "<tr><td>Moyennes</td>"; 
						for(int j = 0; j < cl1.getMatieres().size(); j++) {
							tab += "<td align=center height=100%><table border=1 width=100% height=100%><tr>";
							for(int i = 0; i < cl1.getExamens().size(); i++) { 
								if (j < cl1.getExamens().size()) { 
									if(cl1.getMatieres().get(j).getId() == cl1.getExamens().get(i).getIdMatiere()) { 
									tab += "<td align=center width="+(100/cl1.nbExamensMatiere(cl1.getMatieres().get(j).getId()))+"%>"+cl1.getExamens().get(i).getMoyenne()+"</td>"; 
									}
								}
							}
							tab += "</tr><tr><td align=center colspan='"+cl1.nbExamensMatiere(cl1.getMatieres().get(j).getId())+"'>"+cl1.moyenneMatiere(cl1.getMatieres().get(j).getId())+"</td></tr></table></td></html>"; 
						}
							tab += "<td align=center>"+cl1.moyenneClasse()+"</td></tr></table><br>";
		
							/* Renvoie de la page affichant le tableau */
		frmTableurnotes.getContentPane().removeAll();
		frmTableurnotes.getContentPane().add(new ConsulterClasse(idCla,tab, frmTableurnotes));
		frmTableurnotes.getContentPane().repaint();
		frmTableurnotes.getContentPane().revalidate();
		
	}
	
}
