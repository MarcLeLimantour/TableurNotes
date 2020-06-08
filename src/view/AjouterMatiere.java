package view;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.ControleurAjtMatiere;

@SuppressWarnings("serial")
public class AjouterMatiere extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public AjouterMatiere(JFrame frame) {
		this.setBackground(SystemColor.activeCaption);
		this.setBounds(0, 0, 975, 605);
		setLayout(null);
		
		JLabel lblAjoutDeClasse = new JLabel("Ajout de matiere");
		lblAjoutDeClasse.setBounds(306, 11, 367, 65);
		lblAjoutDeClasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutDeClasse.setFont(new Font("Times New Roman", Font.PLAIN, 55));
		add(lblAjoutDeClasse);
		
		JLabel lblNomDeLa = new JLabel("Nom de la matiere");
		lblNomDeLa.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNomDeLa.setBounds(388, 194, 184, 40);
		add(lblNomDeLa);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(399, 245, 167, 32);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Remet le champ text pour nom matiere a ""
				textField.setText("");
			}
		});
		btnNewButton.setBounds(260, 411, 116, 47);
		add(btnNewButton);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Appel du controleur AjtMatiere
				new ControleurAjtMatiere(textField.getText(),frame);
			}
		});
		btnValider.setForeground(Color.WHITE);
		btnValider.setBackground(Color.GREEN);
		btnValider.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnValider.setBounds(565, 409, 116, 50);
		add(btnValider);
	}
}
