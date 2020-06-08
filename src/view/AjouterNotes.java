package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import connection.DAOAcces;
import controller.ControleurAjtNotes;
import controller.ControleurDirection;
import model.Classe;
import model.Examen;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AjouterNotes extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AjouterNotes(Classe cla,Examen exam,JFrame frame) {


						this.setBackground(SystemColor.activeCaption);
						this.setBounds(0, 0, 975, 605);
						setLayout(null);
						
						JLabel lblAjoutDeStagiaire = new JLabel("Ajout de notes");
						lblAjoutDeStagiaire.setFont(new Font("Times New Roman", Font.PLAIN, 55));
						lblAjoutDeStagiaire.setHorizontalAlignment(SwingConstants.CENTER);
						lblAjoutDeStagiaire.setBounds(284, 11, 406, 71);
						add(lblAjoutDeStagiaire);
						
						String Matiere = "";
						
						// Requete SQL
						DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
						String sql = "select * from matiere where idMatiere = "+exam.getMatiere()+";";
							 // ResultSet recuperant un statement initialiser par la connexion dao 
							 //	executeQuery effectue une requete Sql sur ce statement
							 // Recupere la matiere de l'examen
						try {
						ResultSet rs = dao.getStatement().executeQuery(sql);

						while(rs.next()) {

							Matiere = rs.getString("nomMatiere");
							
						}
						
						
						JLabel lblMatiere = new JLabel("Matiere : "+Matiere);
						lblMatiere.setFont(new Font("Tahoma", Font.PLAIN, 20));
						lblMatiere.setBounds(505, 112, 438, 47);
						add(lblMatiere);
						
						JLabel lblDate = new JLabel("Date : "+exam.getDate());
						lblDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
						lblDate.setBounds(297, 112, 185, 47);
						add(lblDate);
						
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(297, 170, 381, 202);
						add(scrollPane);
						
						table = new JTable();
						scrollPane.setViewportView(table);

						
						//Remplissage du tableau
						String[] entete = {"Stagiaire","Note"};
							
						String[][] sta = new String[cla.getStagiaires().size()][2];
						for(int i=0;i<cla.getStagiaires().size();i++) {
							for(int j=0;j<1;j++) {
								sta[i][j] = cla.getStagiaires().get(i).getPrenom() + " " + cla.getStagiaires().get(i).getNom();
								sta[i][j+1] = "";
							}
						}
						
						// Ajout du tableau a la JTable
						table = new JTable(sta, entete);
						scrollPane.setViewportView(table);
						
						
						
						JButton btnNewButton = new JButton("Annuler");
						btnNewButton.setForeground(Color.WHITE);
						btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
						btnNewButton.setBackground(Color.RED);
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								// Redirection
								new ControleurDirection("AjtExamen",frame);
							}
						});
						btnNewButton.setBounds(309, 411, 116, 47);
						add(btnNewButton);
						
						
						JButton btnValider = new JButton("Valider");
						btnValider.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								int test = 0;
								
								// Permet de valider une valeur encore en saisie dans le tableau
								if(table.isEditing())
								{
									table.getCellEditor().stopCellEditing();
								}
								
								
								// Boucle parcourant le tableau sta
								for(int i = 0;i<sta.length;i++) {			

									// Expression régulière verifiant la bonne saisie des notes
									// de 0-20 et sans saisie de texte possible
									if(sta[i][1].matches("^[0-1][0-9]{1}(,.)?[0-9]*|^[2][0]|[0-9]|^[0-9][,.][0-9]*"))
										{
											// Expression reguliere verifiant la présence de ,
											if(sta[i][1].matches(".*,.*"))
												{
													// Remplace la , par un . pour qu'il soit caster ne double sans soucis
													sta[i][1] = sta[i][1].replace(',', '.');
												}
											
										}
										else 
										{
											// Renvoie une erreur
											new ControleurAjtNotes(frame);
											test = 1;
										}
									
									}
								
								// Si le programme ne rentre pas dans la condition d'erreur
								if (test != 1 ) {
									// Appel du controleur AjtNotes
									new ControleurAjtNotes(sta,cla,exam,frame);
								}
								

							}
						});
						btnValider.setForeground(Color.WHITE);
						btnValider.setFont(new Font("Times New Roman", Font.PLAIN, 18));
						btnValider.setBackground(Color.GREEN);
						btnValider.setBounds(543, 409, 116, 50);
						add(btnValider);
						
						JLabel lblDoublecliquezPourSaisir = new JLabel("Double-cliquez pour saisir les notes");
						lblDoublecliquezPourSaisir.setFont(new Font("Tahoma", Font.PLAIN, 13));
						lblDoublecliquezPourSaisir.setHorizontalAlignment(SwingConstants.CENTER);
						lblDoublecliquezPourSaisir.setBounds(688, 223, 208, 47);
						add(lblDoublecliquezPourSaisir);
						
						JLabel lblPuisCliquerSur = new JLabel("puis cliquer sur valider");
						lblPuisCliquerSur.setHorizontalAlignment(SwingConstants.CENTER);
						lblPuisCliquerSur.setFont(new Font("Tahoma", Font.PLAIN, 13));
						lblPuisCliquerSur.setBounds(688, 248, 208, 47);
						add(lblPuisCliquerSur);
						
						
						}
					    catch(SQLException e) {
							System.out.println("Probleme SQL b!!");
							e.printStackTrace();
						}
						
					}
	}

