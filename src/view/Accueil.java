package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.ControleurDirection;

public class Accueil {

	private JFrame frmTableurnotes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil window = new Accueil();
					window.frmTableurnotes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Accueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTableurnotes = new JFrame();
		frmTableurnotes.getContentPane().setBackground(SystemColor.activeCaption);
		frmTableurnotes.setBackground(SystemColor.activeCaption);
		frmTableurnotes.getContentPane().setForeground(SystemColor.activeCaption);
		frmTableurnotes.setResizable(false);
		frmTableurnotes.setTitle("Tableur Notes");
		frmTableurnotes.setBounds(100, 100, 975, 605);
		frmTableurnotes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Ajoute le panel JAccueil directement à la frame
		JAccueil accueil = new JAccueil(frmTableurnotes);
		frmTableurnotes.getContentPane().add(accueil);

		JMenuBar menuBar = new JMenuBar();
		frmTableurnotes.setJMenuBar(menuBar);
		
		// Bouton Accueil avec icone
		JButton btnAccueil = new JButton("");
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirection
				new ControleurDirection("Accueil",frmTableurnotes);
				
			}
		});
		// Récuperation et ajout de l'icone
		Image home = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
		btnAccueil.setIcon(new ImageIcon(home));
		menuBar.add(btnAccueil);
		
		JMenu mnAjouter = new JMenu("Ajouter");
		menuBar.add(mnAjouter);
		
		JMenuItem mntmClasse = new JMenuItem("Classe");
		mntmClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Redirection
				new ControleurDirection("AjtClasse",frmTableurnotes);
;				
			}
		});
		mnAjouter.add(mntmClasse);
		
		JMenuItem mntmMatire = new JMenuItem("Matière");
		mntmMatire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Redirection
				new ControleurDirection("AjtMatiere",frmTableurnotes);
				
			}
		});
		mnAjouter.add(mntmMatire);
		
		JMenuItem mntmStagiaire = new JMenuItem("Stagiaire");
		mntmStagiaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirection
				new ControleurDirection("AjtStagiaire",frmTableurnotes);
				
			}
		});
		mnAjouter.add(mntmStagiaire);
		
		JMenuItem mntmExamen = new JMenuItem("Examen");
		mntmExamen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirection
				new ControleurDirection("AjtExamen",frmTableurnotes);
				
			}
		});
		mnAjouter.add(mntmExamen);
		
		JMenu mnConsulter = new JMenu("Consulter");
		menuBar.add(mnConsulter);
		
		JMenuItem mntmClasse_1 = new JMenuItem("Classe");
		mntmClasse_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirection
				new ControleurDirection("CsltClasse",frmTableurnotes);
				
			}
		});
		mnConsulter.add(mntmClasse_1);
		
		JMenu mnSupprimer = new JMenu("Supprimer");
		menuBar.add(mnSupprimer);
		
		JMenuItem mntmClasse_del = new JMenuItem("Classe");
		mntmClasse_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirection
				new ControleurDirection("DelClasse",frmTableurnotes);
				
			}
		});
		mnSupprimer.add(mntmClasse_del);
		
		frmTableurnotes.getContentPane().setLayout(null);
		
	}
}
