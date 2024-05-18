package videoteka1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Glavni {

	private JFrame frmVideoteka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Glavni window = new Glavni();
					window.frmVideoteka.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Glavni() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVideoteka = new JFrame();
		frmVideoteka.setTitle("Videoteka");
		frmVideoteka.setBounds(100, 100, 768, 464);
		frmVideoteka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmVideoteka.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filmovi");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmUnosFilmova = new JMenuItem("Unos novih filmova");
		mntmUnosFilmova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgUnosFilmova dlg = new DlgUnosFilmova();
				dlg.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmUnosFilmova);
		
		JMenuItem mntmPregledFilmova = new JMenuItem("Pregled filmova");
		mnNewMenu.add(mntmPregledFilmova);
		
		JMenu mnNewMenu_1 = new JMenu("Korisnici");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmUnosKorisnika = new JMenuItem("Unos novog korisnika");
		mnNewMenu_1.add(mntmUnosKorisnika);
		
		JMenuItem mntmPregledKorisnika = new JMenuItem("Pregled postoje\u0107ih korisnika");
		mnNewMenu_1.add(mntmPregledKorisnika);
		
		JMenu mnNewMenu_2 = new JMenu("Posudba");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nova posudba");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);
	}

}
