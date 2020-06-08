package view;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import connection.DAOAcces;
import controller.ControleurAjtStagiaire;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AjouterStagiaire extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public AjouterStagiaire(JFrame frame) {
		this.setBackground(SystemColor.activeCaption);
		this.setBounds(0, 0, 975, 605);
		setLayout(null);
		
		JLabel lblAjoutDeStagiaire = new JLabel("Ajout de stagiaire ");
		lblAjoutDeStagiaire.setFont(new Font("Times New Roman", Font.PLAIN, 55));
		lblAjoutDeStagiaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutDeStagiaire.setBounds(284, 11, 406, 71);
		add(lblAjoutDeStagiaire);
		
		JLabel lblChoisirLaClasse = new JLabel("Choisir la classe :");
		lblChoisirLaClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChoisirLaClasse.setBounds(332, 140, 185, 47);
		add(lblChoisirLaClasse);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNom.setBounds(332, 237, 73, 47);
		add(lblNom);
		
		JLabel lblPrenoom = new JLabel("Prenom :");
		lblPrenoom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrenoom.setBounds(332, 304, 105, 47);
		add(lblPrenoom);
		
		textField = new JTextField();
		textField.setBounds(476, 249, 116, 30);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(476, 316, 116, 30);
		add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setBounds(497, 157, 116, 20);
		add(comboBox);
		
		// Remplit la comboBox de nom de classe
		
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
		
		
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// remet les champ textes a ""
				textField.setText("");
				textField_1.setText("");
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setSelectedIcon(null);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(284, 418, 116, 47);
		add(btnNewButton);
		

		JButton button = new JButton("Valider");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Appel controleur AjtStagiaire
				new ControleurAjtStagiaire(comboBox.getSelectedItem().toString(),textField.getText(),textField_1.getText(),frame);
				
			}
		});
		button.setBackground(Color.GREEN);
		button.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		button.setForeground(Color.WHITE);
		button.setBounds(540, 418, 116, 47);
		add(button);

		
	}
}
