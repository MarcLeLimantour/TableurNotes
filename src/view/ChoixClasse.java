package view;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import connection.DAOAcces;
import controller.ControleurDirection;
import controller.ControleurCsltClasse;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ChoixClasse extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChoixClasse(JFrame frame) {

		
		this.setBackground(SystemColor.activeCaption);
		this.setBounds(0, 0, 975, 605);
		setLayout(null);
		
		JLabel lblAjoutDeClasse = new JLabel("Affichage de classe");
		lblAjoutDeClasse.setBounds(270, 5, 434, 65);
		lblAjoutDeClasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutDeClasse.setFont(new Font("Times New Roman", Font.PLAIN, 55));
		add(lblAjoutDeClasse);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(505, 224, 127, 20);
		add(comboBox);
		
		// Remplit la comboBox avec les noms de classe
		
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
		
		
		JLabel lblChoisirLaClasse = new JLabel("Choisir la classe :");
		lblChoisirLaClasse.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblChoisirLaClasse.setBounds(307, 209, 188, 40);
		add(lblChoisirLaClasse);
		
		JButton button = new JButton("Retour accueil");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Redirection
				new ControleurDirection("Accueil",frame);
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		button.setBackground(Color.RED);
		button.setBounds(270, 374, 141, 47);
		add(button);
		
		JButton button_1 = new JButton("Valider");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Appel controleur CsltClasse
				new ControleurCsltClasse(comboBox.getSelectedItem().toString(),frame);
				
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		button_1.setBackground(Color.GREEN);
		button_1.setBounds(588, 372, 116, 50);
		add(button_1);
		
		
	}
}
