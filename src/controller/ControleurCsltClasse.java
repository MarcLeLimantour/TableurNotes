package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import connection.DAOAcces;
import model.Classe;
import model.Matiere;
import model.Stagiaire;
import view.ConsulterClasse;

public class ControleurCsltClasse {

	/** Controleur permettant d'afficher le tableau d'une classe
	 * <br> <hr>
	 * Parametres :
	 * <li> String  - nom de la classe </li>
	 * <li> Frame - frame de l'application</li>
	 * */
	public ControleurCsltClasse(String nomCla, JFrame frmTableurnotes) {
		
		DAOAcces daoCla = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
		
		/* Requête permettant de recuperer l'id de la classe
		 * puis de creer une classe */
		Classe cl1 = null;
		int idCla = 0;
		String sqlIdCla = "SELECT idClasse FROM `classe` where nomClasse = '"+nomCla+"';";
		try {
		ResultSet rsCla = daoCla.getStatement().executeQuery(sqlIdCla);
		while(rsCla.next()) { 
			idCla = rsCla.getInt(1);
		}
		
		cl1 = new Classe(idCla,daoCla);
		
		}
	    catch(SQLException e) {
			System.out.println("Probleme SQL b!!");
			e.printStackTrace();
		}
		daoCla.closeConnection();

		
		/* Recupération dans une String l'affichage en html du tableau de notes */
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
		
							
							/* Renvoie une page pour consulter la classe */
						frmTableurnotes.getContentPane().removeAll();
						frmTableurnotes.getContentPane().add(new ConsulterClasse(idCla,tab, frmTableurnotes));
						frmTableurnotes.getContentPane().repaint();
						frmTableurnotes.getContentPane().revalidate();
						
	}
}
