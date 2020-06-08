package view;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import connection.DAOAcces;
import controller.ControleurDirection;
import controller.ControleurCsltSta;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ConsulterClasse extends JPanel {
	/**
	 * Panel ConsulterClasse 
	 * <br><hr>
	 * Parametres : 
	 * <li> Int - id classe </li>
	 * <li> String - code html de l'affichage du tableau </li>
	 */
	public ConsulterClasse(int idCla,String tab,JFrame frame) {
			
				this.setBackground(SystemColor.activeCaption);
				this.setBounds(0, 0, 975, 605);
				setLayout(null);
				
				JLabel lblAjoutDeClasse = new JLabel("Affichage de classe");
				lblAjoutDeClasse.setBounds(270, 5, 434, 65);
				lblAjoutDeClasse.setHorizontalAlignment(SwingConstants.CENTER);
				lblAjoutDeClasse.setFont(new Font("Times New Roman", Font.PLAIN, 55));
				add(lblAjoutDeClasse);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(49, 81, 680, 384);
				add(scrollPane);
				
				JLabel lblNewLabel = new JLabel(tab);
				lblNewLabel.setBackground(Color.WHITE);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				scrollPane.setViewportView(lblNewLabel);
				
				JButton button_1 = new JButton("Retour selection");
				button_1.setBackground(Color.RED);
				button_1.setForeground(Color.WHITE);
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						// Redirection
						new ControleurDirection("CsltClasse",frame);
						
					}
				});
				button_1.setBounds(129, 476, 130, 40);
				add(button_1);
				
				JComboBox<String> comboBox = new JComboBox<String>();
				comboBox.setBounds(756, 132, 155, 20);
				add(comboBox);
				
				// Remplissage de la comboBox avec les noms de stagiaire de la classe
				
				DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
				String sql = "select * from stagiaire where classeStagiaire ="+idCla+"";
				
				try {
					 
					ResultSet rs = dao.getStatement().executeQuery(sql);
					
					while(rs.next()) 
					{
						comboBox.addItem(rs.getString(2));	
					}
					
				 }
			    catch(SQLException e) {
					System.out.println("Probleme SQL b!!");
					e.printStackTrace();
				}
				dao.closeConnection();
				
				JLabel lblChoisirLeStagiaire = new JLabel("Choisir le stagiaire : ");
				lblChoisirLeStagiaire.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblChoisirLeStagiaire.setBounds(756, 81, 155, 40);
				add(lblChoisirLeStagiaire);
				
				JButton btnValider = new JButton("Valider");
				btnValider.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						// Appel du controleur CsltSta
						new ControleurCsltSta(idCla,comboBox.getSelectedItem().toString(),frame);
						
					}
				});
				btnValider.setForeground(Color.WHITE);
				btnValider.setBackground(Color.GREEN);
				btnValider.setBounds(790, 163, 89, 23);
				add(btnValider);
				

			}
	}

