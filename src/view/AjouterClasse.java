package view;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import controller.ControleurAjtClasse;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AjouterClasse extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public AjouterClasse(JFrame frame) {
		this.setBackground(SystemColor.activeCaption);
		this.setBounds(0, 0, 975, 605);
		setLayout(null);
		
		JLabel lblAjoutDeClasse = new JLabel("Ajout de classe");
		lblAjoutDeClasse.setBounds(314, 5, 347, 65);
		lblAjoutDeClasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutDeClasse.setFont(new Font("Times New Roman", Font.PLAIN, 55));
		add(lblAjoutDeClasse);
		
		JLabel lblNomDeLa = new JLabel("Nom de la classe");
		lblNomDeLa.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNomDeLa.setBounds(399, 194, 175, 40);
		add(lblNomDeLa);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(399, 245, 167, 32);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Remet a "" le champ de text pour le nom de classe
				textField.setText("");
			}
		});
		btnNewButton.setBounds(260, 411, 116, 47);
		add(btnNewButton);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Appel du controleur AjtClasse(nomdeclasse,frame)
				new ControleurAjtClasse(textField.getText(),frame);
			}
		});
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnValider.setBackground(Color.GREEN);
		btnValider.setBounds(564, 409, 116, 50);
		add(btnValider);
	}
}
