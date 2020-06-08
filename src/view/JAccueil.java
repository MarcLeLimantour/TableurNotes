package view;

import javax.swing.JPanel;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class JAccueil extends JPanel {

	/**
	 * Panel d'accueil contenant de l'affichage
	 */
	public JAccueil(JFrame frame) {
		setBackground(SystemColor.activeCaption);
		this.setBounds(0, 0, 975, 605);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenue sur Tableur Notes.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(284, 29, 408, 78);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ajout :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(136, 179, 144, 49);
		add(lblNewLabel_1);
		
		JLabel lblConsulter = new JLabel("Consulter :");
		lblConsulter.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulter.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblConsulter.setBounds(397, 179, 176, 48);
		add(lblConsulter);
		
		JLabel lblSupprimer = new JLabel("Supprimer :");
		lblSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupprimer.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSupprimer.setBounds(683, 179, 180, 48);
		add(lblSupprimer);
		
		JLabel lblMatire = new JLabel("> Matière");
		lblMatire.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMatire.setBounds(156, 365, 120, 31);
		add(lblMatire);
		
		JLabel lblExamen = new JLabel("> Examen");
		lblExamen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExamen.setBounds(156, 323, 120, 31);
		add(lblExamen);
		
		JLabel lblClasse = new JLabel("> Classe");
		lblClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClasse.setBounds(156, 281, 120, 31);
		add(lblClasse);
		
		JLabel lblStagiaire = new JLabel("> Stagiaire");
		lblStagiaire.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStagiaire.setBounds(156, 239, 120, 31);
		add(lblStagiaire);
		
		JLabel lblClasse_1 = new JLabel("> Classe");
		lblClasse_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClasse_1.setBounds(417, 238, 120, 31);
		add(lblClasse_1);
		
		JLabel label = new JLabel("> Stagiaire");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(417, 281, 120, 31);
		add(label);
		
		JLabel lblClasse_2 = new JLabel("> Classe");
		lblClasse_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClasse_2.setBounds(703, 238, 120, 31);
		add(lblClasse_2);
		
		
	}
}
