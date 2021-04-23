
import java.awt.Color;

import java.awt.Font;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.io.*;

public class HomePage implements ActionListener, MouseListener, WindowListener {
	private JFrame Fhome = new JFrame("E voting by 19SW10");
	private JPanel Plogin = new JPanel();
	private JPanel Pfront = new JPanel();
	private JPanel Paneladmin = new JPanel();
	private JPanel Xray = new JPanel();
	private JPanel CanPanel = new JPanel();
	private JLabel[] Xlabels = new JLabel[4];
	private JTextField[] XFields = new JTextField[3];
	private JButton[] Xbutton = new JButton[3];
	private Font font = new Font("Consolas", Font.BOLD, 15);
	private JTabbedPane tabpane = new JTabbedPane();
	private File file1;
	private String Path;
	private JButton logout = new JButton("Logout");
	private JTable Ptable = new JTable() {
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};;
	private JScrollPane sp = new JScrollPane(Ptable);
	DefaultTableModel model = new DefaultTableModel(new String[0][0], new String[] { "Candidate  Name ", "Votes" });

	private JLabel Lcnic = new JLabel("Pin");
	private JLabel LVoteid = new JLabel("Voter Id");
	private JLabel Title = new JLabel("SK VOTING MANAGEMENT SYSTEM");
	private JLabel picL = new JLabel();
	private JPasswordField Tcnic = new JPasswordField();
	private JTextField Tvoteid = new JTextField();
	LineBorder pane = new LineBorder(Color.green, 3);
	Color custom = new Color(255, 9, 15);
	Image home = Toolkit.getDefaultToolkit().getImage("HP.jpg");
	Icon home1 = new ImageIcon(getClass().getResource("hp1.jpeg"));

	private TitledBorder Tb = BorderFactory.createTitledBorder(pane, "Authenicaion", TitledBorder.LEADING, 0,
			new Font("TimesRoman", Font.BOLD, 30), custom);
	private TitledBorder Tb1 = BorderFactory.createTitledBorder(pane, "Admin", TitledBorder.LEADING, 0,
			new Font("TimesRoman", Font.BOLD, 30), custom);

	private JButton Bsign = new JButton("Sign In");
	private JButton Bsignup = new JButton("Sign up");
	private JButton Bback = new JButton("Back");

	private JLabel Padmin = new JLabel("");
	private JLabel Pvoter = new JLabel("");

	// admin
	private JLabel Ladmin = new JLabel("Name");
	private JLabel Lpass = new JLabel("Password");
	private JTextField Tadmin = new JTextField();
	private JPasswordField Tpass = new JPasswordField();
	private JButton Bpass = new JButton("Enter");
	private JButton Bb = new JButton("Back");
	// private Person person = new Person();

	private VoterData Hvoterlist = new VoterData();
	private static Person person = new Person();
	private Admin admin = new Admin();

	HomePage() {
		Fhome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Fhome.setSize(900, 550);
		Fhome.setLocationRelativeTo(null);
		Fhome.setLayout(null);
		Hvoterlist = new VoterData();
		System.out.print("Hashing::");
		setHashTable();
		person = admin.getPerson();
		// Voter v=(Voter) Hvoterlist.get(1);

		// frame adding
		Fhome.add(Plogin);
		set_Voter_login();
		Fhome.add(Paneladmin);
		setAdmin();
		Fhome.add(Pfront);
		setTabels();
		tabpane.add(Xray, "Candidate List");
		tabpane.add(CanPanel, "Votes List");
		tabpane.setSize(800, 800);
		Fhome.getContentPane().add(tabpane);
		tabpane.setVisible(false);

		// homepage Pfront
		setList();
		setFrontPage();
		Fhome.setVisible(true);
		Pfront.setVisible(true);
		Plogin.setVisible(false);
		Paneladmin.setVisible(false);

	}

	private void setFrontPage() {

		Pfront.setOpaque(false);
		Pfront.setLayout(null);

		picL.add(Title);
		Fhome.add(picL);
		picL.setIcon(home1);
		Title.setFont(new Font("MV Boli", Font.BOLD, 40));
		picL.setVisible(true);
		Pfront.setOpaque(false);
		picL.setBounds(0, 0, 910, 511);
		Title.setBounds(110, -95, 1000, 300);

		Pfront.add(Padmin);
		Padmin.setIcon(new ImageIcon(getClass().getResource("admin11.png")));
		Padmin.setBounds(200, 100, 200, 200);
		Pfront.add(Pvoter);
		Pvoter.setIcon(new ImageIcon(getClass().getResource("fvoter1.png")));
		Pvoter.setBounds(240, 300, 128, 128);
		Pfront.setBounds(400, 0, 500, 500);

		Pvoter.addMouseListener((MouseListener) this);
		Padmin.addMouseListener((MouseListener) this);

	}

	private void set_Voter_login() {

		Plogin.setBounds(250, 130, 300, 300);

		// Pfront add

		Plogin.setBorder(Tb);
		Plogin.add(Lcnic);
		Plogin.add(LVoteid);
		Plogin.add(Tcnic);
		Plogin.add(Tvoteid);
		Plogin.add(Bsign);
		Plogin.add(Bsignup);
		Plogin.add(Bback);
		Plogin.setOpaque(false);
		Plogin.setLayout(null);

		// bounds
		Lcnic.setBounds(20, 130, 50, 30);
		Tcnic.setBounds(90, 130, 100, 30);
		LVoteid.setBounds(20, 90, 80, 30);
		Tvoteid.setBounds(90, 90, 100, 30);
		Bsign.setBounds(40, 200, 90, 30);
		Bsignup.setBounds(130, 200, 90, 30);
		Bback.setBounds(110, 250, 90, 30);

		// listner
		Bback.addActionListener(this);
		Bsign.addActionListener(this);
		Bsignup.addActionListener(this);

	}

	private void setAdmin() {
		Paneladmin.setBounds(250, 130, 300, 350);
		Paneladmin.setLayout(null);
		Paneladmin.setBorder(Tb1);
		Paneladmin.setOpaque(false);

		Paneladmin.add(Ladmin);
		Paneladmin.add(Lpass);
		Paneladmin.add(Tadmin);
		Paneladmin.add(Tpass);
		Paneladmin.add(Bpass);
		Paneladmin.add(Bb);

		Ladmin.setBounds(40, 90, 40, 30);
		Lpass.setBounds(40, 130, 80, 30);
		Tadmin.setBounds(100, 90, 100, 25);
		Tpass.setBounds(100, 130, 100, 25);
		Bpass.setBounds(40, 200, 90, 30);
		Bb.setBounds(140, 200, 90, 30);
		Bpass.addActionListener(this);
		Bb.addActionListener(this);

	}

	void setList() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "saadkz07");

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name,count(id) from votinglist group by name");
			Object[] data = new Object[100];
			int i = 0;
			while (rs.next()) {
				i = 0;
				data[i++] = rs.getString(1);
				data[i++] = rs.getInt(2);
				model.addRow(data);

			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void setvisibleHome(boolean k) {
		Fhome.setVisible(true);
		person = admin.getPerson();
		person.print_voters();

	}

	private void setTabels() {

		Xray.setLayout(null);
		Xray.setSize(700, 700);

		for (int i = 0; i < 4; i++) {
			Xray.add(Xlabels[i] = new JLabel());
			if (i < 3)
				Xray.add(XFields[i] = new JTextField());

		}

		int i = 0;

		Xlabels[i].setText("Candidate Entry ");
		Xlabels[i].setFont(new Font("MV Boli", Font.BOLD, 40));
		Xlabels[i++].setBounds(100, 50, 600, 50);

		Xlabels[i].setText("Candidate Name");
		Xlabels[i].setFont(font);
		Xlabels[i++].setBounds(10, 150, 150, 50);

		Xlabels[i].setText("Add Picture File");
		Xlabels[i].setFont(font);
		Xlabels[i++].setBounds(10, 190, 150, 50);

		Xlabels[i].setText("File Name");
		Xlabels[i].setFont(font);
		Xlabels[i++].setBounds(10, 230, 150, 50);

		i = 0;
		XFields[i].setBounds(150, 163, 50, 20);
		XFields[i++].setFont(font);

		XFields[i].setBounds(100, 245, 150, 20);
		XFields[i].setEditable(false);
		XFields[i++].setFont(font);

		Xray.add(Xbutton[0] = new JButton("Insert"));
		Xray.add(Xbutton[1] = new JButton("Submit"));
		Xray.add(Xbutton[2] = new JButton("verify"));

		Xbutton[0].setBounds(140, 200, 80, 25);
		Xbutton[1].setBounds(100, 290, 80, 25);
		Xbutton[2].setBounds(240, 160, 80, 25);

		Xbutton[0].addActionListener(this);
		Xbutton[1].addActionListener(this);
		Xbutton[2].addActionListener(this);
		Xbutton[0].setEnabled(false);
		Xbutton[1].setEnabled(false);
		// CanPanel.add(sp1);

		CanPanel.add(sp);
		Fhome.add(logout);
		logout.addActionListener(this);
		setXTable();
		Xray.setVisible(false);
	}

	void setXTable() {

		Ptable.setModel(model);
		sp.setBounds(330, 150, 300, 300);
		sp.setViewportView(Ptable);
		JTableHeader header = Ptable.getTableHeader();
		header.setBackground(Color.black);
		header.setForeground(Color.WHITE);
		Ptable.setCellSelectionEnabled(true);
		if (person != null) {

		}

		// Ptable.getColumnModel().getColumn(1).setPreferredWidth(100);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if (Bback == click.getSource()) {
			Plogin.setVisible(false);
			Pfront.setVisible(true);

		}

		if (Bsign == click.getSource()) {
			// int pin1 = getPin(Integer.parseInt(Tvoteid.getText()));
			int id = Integer.parseInt(Tvoteid.getText());
			Voter voter = (Voter) Hvoterlist.get(id);
			int pin = voter.getPin();
			char[] cpin = Tcnic.getPassword();
			String Spin = new String(cpin);

			int pin1 = Integer.parseInt(Spin);
			if (pin1 == pin) {
				if (Tvoteid.getText().length() > 0) {
					JOptionPane.showMessageDialog(null, "Sucessfull");
					Fhome.setVisible(false);
					admin.setPortal(id, voter, true);

				} else {
					JOptionPane.showMessageDialog(null, "vote id Lenght is Invalid");

				}

			} else {
				JOptionPane.showMessageDialog(null, "Pin is not Matching");
			}

		}
		if (Bsignup == click.getSource()) {

			Fhome.setVisible(false);
			new SForm();

		}
		if (Bback == click.getSource()) {
			Plogin.setVisible(false);
			Pfront.setVisible(true);

		}
		if (Bpass == click.getSource()) {
			char[] pas = Tpass.getPassword();
			String pass = new String(pas);
			if (Tadmin.getText().toLowerCase().equals("admin") && pass.equals("saadkz")) {
				Paneladmin.setVisible(false);
				Xray.setVisible(true);
				logout.setBounds(10, 50, 90, 30);
				tabpane.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid password or name");
			}

		}
		if (Bb == click.getSource()) {
			Paneladmin.setVisible(false);
			Pfront.setVisible(true);

		}
		if (Xbutton[0] == click.getSource()) {

			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("All files", "png", "jpg", "jpeg");
			fc.addChoosableFileFilter(filter);
			fc.setAcceptAllFileFilterUsed(false);

			int i = fc.showOpenDialog(null);

			if (i == JFileChooser.APPROVE_OPTION) {
				file1 = fc.getSelectedFile();
				Path = file1.getAbsolutePath();
				XFields[1].setText(Path);

			}
		}
		if (Xbutton[1] == click.getSource()) {
			if (!Path.isEmpty()) {
				inserCand(XFields[0].getText(), Path);

			} else {
				JOptionPane.showMessageDialog(null, "Please select pic");
			}

		}
		if (Xbutton[2] == click.getSource()) {
			String name = new String(XFields[0].getText().toLowerCase());
			if (!name.isEmpty()) {
				Xbutton[0].setEnabled(true);
				Xbutton[1].setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(null, "Enter name Please");
			}

		}
		if (logout == click.getSource()) {

			tabpane.setVisible(false);
			Pfront.setVisible(true);
			logout.setSize(0, 0);

		}
	}

	private void inserCand(String name, String path2) {
		// Koded by 19sw10

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "saadkz07");
			PreparedStatement ps = con.prepareStatement("insert into Cand  values(?,?)");
			ps.setString(1, name);
			FileInputStream fin = new FileInputStream(path2);
			ps.setBinaryStream(2, fin, fin.available());

			ps.executeUpdate();
			ps.close();

			JOptionPane.showMessageDialog(null, "SuccessFully Inserted");

			Xbutton[0].setEnabled(false);
			Xbutton[1].setEnabled(false);

			String n = "";
			XFields[1].setText(n);

		}

		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Not insertd in db");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Koded by 19sw10
		if (Pvoter == e.getSource()) {
			Pfront.setVisible(false);
			Plogin.setVisible(true);

		}
		if (Padmin == e.getSource()) {

			Pfront.setVisible(false);
			Paneladmin.setVisible(true);

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Koded by 19sw10
		if (Pvoter == e.getSource()) {

			Pvoter.setIcon(new ImageIcon(getClass().getResource("fvoter.png")));

		}
		if (Padmin == e.getSource()) {

			Padmin.setIcon(new ImageIcon(getClass().getResource("admin112.png")));

		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Koded by 19sw10

		if (Pvoter == e.getSource()) {
			// Pvoter.setIcon(null);
			Pvoter.setIcon(new ImageIcon(getClass().getResource("fvoter1.png")));
		}
		if (Padmin == e.getSource()) {
			// Pvoter.setIcon(null);
			Padmin.setIcon(new ImageIcon(getClass().getResource("admin11.png")));
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// Koded by 19sw10
		int result = JOptionPane.showConfirmDialog(Fhome, "Sure? You want to exit", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {

		} else if (result == JOptionPane.NO_OPTION) {
			// JOptionPane.showMessageDialog(null, "Choose Carefully");
		} else {
		}
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// Koded by 19sw10

	}

	boolean canVote(int id) {

		Hvoterlist.get(id);
		return false;

	}

	void setHashTable() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "saadkz07");

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from Voter");

			while (rs.next()) {

				Voter V = new Voter(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(11),
						rs.getInt(12));
				Hvoterlist.put(V.getid(), V);
				System.out.println(Hvoterlist.get(V.getid()));

			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}