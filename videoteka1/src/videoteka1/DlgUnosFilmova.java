package videoteka1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class DlgUnosFilmova extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNaziv;
	private JTextField textFieldGodina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgUnosFilmova dialog = new DlgUnosFilmova();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgUnosFilmova() {
		setTitle("Unos novog filma");
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naziv filma:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(29, 29, 82, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u017Danr:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(29, 70, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Godina:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(29, 114, 82, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Opis:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(29, 156, 46, 14);
		contentPanel.add(lblNewLabel_3);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNaziv.setBounds(121, 26, 201, 20);
		contentPanel.add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		textFieldGodina = new JTextField();
		textFieldGodina.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldGodina.setBounds(121, 113, 201, 20);
		contentPanel.add(textFieldGodina);
		textFieldGodina.setColumns(10);
		
		JComboBox comboBoxZanr = new JComboBox();
		comboBoxZanr.setModel(new DefaultComboBoxModel(new String[] {"Drama", "Horor", "SF", "Triler", "Komedija"}));
		comboBoxZanr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxZanr.setBounds(121, 68, 201, 22);
		contentPanel.add(comboBoxZanr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(121, 156, 210, 164);
		contentPanel.add(scrollPane);
		
		JTextArea textAreaOpis = new JTextArea();
		textAreaOpis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(textAreaOpis);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String naziv = textFieldNaziv.getText();
						String godina = textFieldGodina.getText();
						String zanr = (String) comboBoxZanr.getSelectedItem();
						String opis = textAreaOpis.getText();
						
						if (naziv.equals("") || godina.equals("") || opis.equals("")) {
							JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena","Greška",JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						try {						
						 	  Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
							  Connection conn = DriverManager.getConnection 
									  ("jdbc:mysql://ucka.veleri.hr/rda?" +
											  "user=rda&password=11");
							  //id, naziv, zanr, godina, opis
							  //String sql = "INSERT INTO film VALUES(NULL,"Test 2","Komedija","2009","Testno na nastavi");";
							  String sql = "INSERT INTO film VALUES(NULL,?,?,?,?);";
							  PreparedStatement stmt = conn.prepareStatement(sql);
							  stmt.setString(1,naziv);
							  stmt.setString(2, zanr);
							  stmt.setString(3, godina);
							  stmt.setString(4, opis);
				  			  stmt.execute();
											
							  conn.close();
										
							  textFieldNaziv.setText("");
							  textFieldGodina.setText("");
							  comboBoxZanr.setSelectedIndex(-1);
							  textAreaOpis.setText("");
											
							} catch(Exception ex) {
							  JOptionPane.showMessageDialog(null, 
				   ex.getMessage(),"Greška", JOptionPane.ERROR_MESSAGE);
							}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
