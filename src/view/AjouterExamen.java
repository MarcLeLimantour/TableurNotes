package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import connection.DAOAcces;
import controller.ControleurAjtExam;
import controller.ControleurDirection;
import model.DateLabelFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AjouterExamen extends JPanel {
	/**
	 * Create the panel.
	 */
	public AjouterExamen(JFrame frame) {


				this.setBackground(SystemColor.activeCaption);
				this.setBounds(0, 0, 975, 605);
				setLayout(null);
				
				JLabel lblAjoutDeStagiaire = new JLabel("Ajout d'examen");
				lblAjoutDeStagiaire.setFont(new Font("Times New Roman", Font.PLAIN, 55));
				lblAjoutDeStagiaire.setHorizontalAlignment(SwingConstants.CENTER);
				lblAjoutDeStagiaire.setBounds(284, 11, 406, 71);
				add(lblAjoutDeStagiaire);
				
				JLabel lblChoisirLaClasse = new JLabel("Choisir la classe :");
				lblChoisirLaClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblChoisirLaClasse.setBounds(311, 140, 185, 47);
				add(lblChoisirLaClasse);
				
				JLabel lblPrenoom = new JLabel("Choisir la date :");
				lblPrenoom.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblPrenoom.setBounds(311, 298, 146, 47);
				add(lblPrenoom);
				
				JComboBox<String> comboBox = new JComboBox<String>();
				comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 11));
				comboBox.setBounds(478, 157, 153, 20);
				add(comboBox);
				
				// Remplit la comboBox avec les nom de classe
				DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
				String sql = "select * from classe";
				
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
				
				
				JComboBox<String> comboBox_1 = new JComboBox<String>();
				comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
				comboBox_1.setBounds(490, 234, 141, 20);
				add(comboBox_1);
				
				// Remplit la comboBox avec les nom de matiere
				DAOAcces dao1 = new DAOAcces("com.mysql.jdbc.Driver", "tableurnotes", "root", "");
				String sql1 = "select * from matiere";
				
				
				try {
					 
					ResultSet rs = dao1.getStatement().executeQuery(sql1);
					
					while(rs.next()) 
					{
						comboBox_1.addItem(rs.getString(2));	
					}
					
				}
			    catch(SQLException e) {
					System.out.println("Probleme SQL b!!");
					e.printStackTrace();
				}
				dao1.closeConnection();
				
				// Initialisae le champ datePicker
				UtilDateModel model = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				
				
				JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
				JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
				datePicker.setBounds(501, 310, 116, 23);
				add(datePicker);
				
				
				JButton btnNewButton = new JButton("Annuler");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						// Redirection
						new ControleurDirection("AjtExamen",frame);
						
					}
				});
				btnNewButton.setBackground(Color.RED);
				btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				btnNewButton.setBounds(284, 418, 116, 47);
				btnNewButton.setForeground(Color.WHITE);
				add(btnNewButton);
				

				JButton button = new JButton("Valider");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
							// Appel au controleur AjtExam
							new ControleurAjtExam(comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),datePicker.getJFormattedTextField().getText(),frame);

					}
				});
				button.setBackground(Color.GREEN);
				button.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				button.setForeground(Color.WHITE);
				button.setBounds(540, 418, 116, 47);
				add(button);
				
				JLabel lblChoisirLaMatiere = new JLabel("Choisir la matiere :");
				lblChoisirLaMatiere.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblChoisirLaMatiere.setBounds(311, 217, 185, 47);
				add(lblChoisirLaMatiere);
				

				
			}
	}
