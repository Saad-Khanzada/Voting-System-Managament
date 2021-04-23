import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SForm implements ActionListener {
	private JFrame frame = new JFrame();
	private JPanel Panel = new JPanel();
	private JPanel Panel1 = new JPanel();
	private JPanel PPass = new JPanel();
	private JPanel PPass1 = new JPanel();
	private JPanel PColor = new JPanel();
	LineBorder pane = new LineBorder(Color.green, 3);
	private TitledBorder Tb = BorderFactory.createTitledBorder(pane, "next Form", TitledBorder.LEADING, 0,
			new Font("TimesRoman", Font.BOLD, 30), Color.RED);
	LineBorder pane1 = new LineBorder(Color.BLUE, 3);

	// labels
	private JLabel Lname = new JLabel("Name");
	private JLabel Lfname = new JLabel("Father Name");
	private JLabel Lcnic = new JLabel("Cnic");
	private JLabel Lmob = new JLabel("Mobile No");
	private JLabel Ldist = new JLabel("District");
	private JLabel Ldob = new JLabel("D O  B");
	private JLabel Lvote = new JLabel("Voter Id");
	private JLabel Lrelig = new JLabel("Religion");
	private JLabel Lprofes = new JLabel("Proffession");
	private JLabel LPin = new JLabel("PIN");
	private JLabel LCPin = new JLabel("Confirm PIN");
	private JLabel LPic = new JLabel("");
	private File file1;

	private JFileChooser fc = new JFileChooser();

	// textfields
	private JTextField Tname = new JTextField();
	private JTextField Tfname = new JTextField();
	private JTextField Tcnic = new JTextField();
	private JTextField Tmob = new JTextField();
	private JTextField Tdist = new JTextField();
	private JTextField Tdob = new JTextField();
	private JTextField Tvote = new JTextField();
	private JPasswordField TPin = new JPasswordField();
	private JPasswordField TCPin = new JPasswordField();

	JLabel image;
	// Buttons
	private JButton next = new JButton("Next");
	private JButton Signup = new JButton("Signup");
	private JButton Clear = new JButton("Clear");
	private JButton Back = new JButton("Back");
	private JButton Choose = new JButton("Choose");
	private JButton Home = new JButton("Home");
	DefaultListCellRenderer listRenderer1;
	private String path = null;

	private String religion[] = { "Religion", "Muslim", "Hindu", "Christian", "other"

	};
	private String proffesion[] = { "Proffesion", "Goverment Teacher", "Private Teacher", "Engineer", "Buisness Man",
			"Land Lord", "Private Employe", "Doctor", "Student"

	};
	private String dates[] = { "Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String months[] = { "Month", "Jan", "feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sup", "Oct", "Nov",
			"Dec" };
	private String years[] = { "Year", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004",
			"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
			"2018", "2019" };

	private JComboBox<String> date = new JComboBox<String>(dates);
	private JComboBox<String> month = new JComboBox<String>(months);
	private JComboBox<String> year = new JComboBox<String>(years);
	private JComboBox<String> profes = new JComboBox<String>(proffesion);
	private JComboBox<String> relig = new JComboBox<String>(religion);

	private VoterData voterlist = new VoterData();
	private int voter_id;

	SForm() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		frame.setLayout(null);
		Panel.setLayout(null);
		Panel1.setLayout(null);
		PColor.add(Panel1);
		PColor.setSize(500, 500);
		PColor.setLayout(null);
		// Panel1.setOpaque(false);
		// PColor.setBackground(Color);
		Panel1.add(Panel);
		PPass1.add(PPass);

		setForm();
		setPassPanel();

		frame.add(PColor);
		frame.add(PPass1);
		Panel1.setBounds(40, 40, 400, 400);
		Panel.add(Lname);
		Panel1.setBorder(Tb);
		frame.setBounds(0, 0, 470, 480);
		frame.setLocationRelativeTo(null);
		Panel.setBounds(40, 35, 350, 350);
		// Tvote.setBounds(70,190,90,30);
//	Panel.setBackground(Color.black);
		frame.setVisible(true);
		Panel.setVisible(true);
		Panel1.setVisible(true);
		PPass.setVisible(false);

	}

	private void setForm() {

		Panel1.add(Panel);
		Panel.add(Lfname);
		Panel.add(Lcnic);
		Panel.add(Lmob);
		Panel.add(Ldist);
		Panel.add(Ldob);
		Panel.add(Tname);
		Panel.setOpaque(false);
		Tname.setOpaque(false);
		Panel.add(Tfname);
		Panel.add(Tcnic);
		Panel.add(Tmob);
		Panel.add(Tdist);
		Panel.add(Tdob);
		Panel.add(date);
		Panel.add(month);
		Panel.add(year);
		Panel.add(relig);
		Panel.add(Lrelig);
		Panel.add(Lprofes);
		Panel.add(profes);
		Panel.add(Back);
		Panel.add(next);
		Panel.add(Clear);
		Panel.add(Choose);
		Panel.add(LPic);

		// bounds
		Lname.setBounds(0, 10, 40, 30);
		Tname.setBounds(80, 10, 90, 25);
		Lfname.setBounds(0, 40, 90, 30);
		Tfname.setBounds(80, 40, 90, 25);
		Lcnic.setBounds(0, 70, 40, 30);
		Tcnic.setBounds(80, 70, 90, 25);
		Lmob.setBounds(0, 100, 70, 30);
		Tmob.setBounds(80, 100, 90, 25);
		Ldist.setBounds(0, 130, 50, 30);
		Tdist.setBounds(80, 130, 90, 25);
		Ldob.setBounds(0, 160, 40, 30);
		setList();
		month.setBounds(80, 160, 65, 25);
		year.setBounds(140, 160, 60, 25);
		date.setBounds(195, 160, 60, 25);
		Lrelig.setBounds(0, 190, 50, 30);
		relig.setBounds(80, 190, 80, 25);
		Lprofes.setBounds(0, 220, 80, 30);
		profes.setBounds(80, 220, 150, 25);

		next.setBounds(20, 260, 80, 30);
		Clear.setBounds(120, 260, 80, 30);
		Back.setBounds(210, 260, 80, 30);
		Choose.setBounds(250, 110, 80, 25);
		LPic.setBounds(240, 0, 100, 100);
		LPic.setBorder(pane1);
		// buttons
		next.addActionListener(this);
		Clear.addActionListener(this);
		Back.addActionListener(this);
		Choose.addActionListener(this);
	}

	private void setPassPanel() {
		PPass.setLayout(null);
		PPass1.setLayout(null);
		PPass1.setSize(400, 400);
		PPass.setBounds(40, 70, 300, 300);
		PPass1.setBorder(pane1);

		PPass.add(Lvote);
		PPass.add(Tvote);
		PPass.add(LPin);
		PPass.add(LCPin);
		PPass.add(TPin);
		PPass.add(TCPin);
		PPass.add(Signup);
		PPass.add(Home);

		Tvote.setEditable(false);

		Lvote.setBounds(0, 10, 60, 30);
		Tvote.setBounds(80, 10, 90, 25);
		LPin.setBounds(0, 40, 80, 30);
		TPin.setBounds(80, 40, 90, 25);
		LCPin.setBounds(0, 70, 80, 30);
		TCPin.setBounds(80, 70, 90, 25);
		Signup.setBounds(60, 110, 100, 30);
		Home.setBounds(60, 210, 100, 30);

		Signup.addActionListener(this);
		Home.addActionListener(this);
	}

	void setList() {
		listRenderer1 = new DefaultListCellRenderer();

		listRenderer1.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
		month.setRenderer(listRenderer1);
		date.setRenderer(listRenderer1);
		year.setRenderer(listRenderer1);
		profes.setRenderer(listRenderer1);
		relig.setRenderer(listRenderer1);
	}

	private void setImg(Image i) {
		LPic.setIcon(new ImageIcon(i));
	}

	private int getId() {
		int id = 0;
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "saadkz07");

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select max(voterid) from Voter");

			while (rs.next()) {
				id = rs.getInt(1);

			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id + 1;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Koded by 19sw10
		boolean isInt = false;
		if (next == e.getSource()) {
			if (!Tname.getText().isEmpty() && !Tfname.getText().isEmpty() && !Tcnic.getText().isEmpty()
					&& !Tmob.getText().isEmpty() && !Tdist.getText().isEmpty()) {
				try {
					Double.parseDouble(Tname.getText());

				} catch (Exception e1) {
				}
				try {
					Double.parseDouble(Tfname.getText());

				} catch (Exception e1) {
					isInt = true;
				}
				if (isInt) {
					boolean CisInt = false;
					try {
						Double.parseDouble(Tcnic.getText());

						CisInt = true;

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Invalid cnic character");
					}

					if (Tcnic.getText().length() == 5 && CisInt) {
						boolean MisInt = false;
						try {
							Double.parseDouble(Tmob.getText());

							MisInt = true;

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Invalid Mobile No character");
						}
						if (Tmob.getText().length() == 4 && MisInt) {
							if (LPic.getIcon() != null) {

								JOptionPane.showMessageDialog(null, "Please Remember Your Voter Id");
								Tvote.setText("" + getId());
								Panel1.setVisible(false);
								PColor.setVisible(false);

								PPass.setVisible(true);
								PPass1.setVisible(true);

							} else {
								JOptionPane.showMessageDialog(null, "Please Select Image");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Invalid Mobile No");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Invalid Cnic No");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Invalid Name/father name");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Please Fill Every Field");
			}

		}
		if (Back == e.getSource()) {
			frame.setVisible(false);
			new HomePage();
		}
		if (Clear == e.getSource()) {

		}
		if (Signup == e.getSource()) {
			Integer DPin = null, DPin1 = null;
			char[] cpin = TPin.getPassword();
			char[] cpin2 = TCPin.getPassword();
			String pin = new String(cpin);
			String pin2 = new String(cpin2);
			if (pin.toString().length() == 4 && pin2.toString().length() == 4) {
				boolean isPin = false;
				try {
					DPin = Integer.parseInt(pin.toString());
					DPin1 = Integer.parseInt(pin2.toString());
					isPin = true;
				} catch (Exception e1) {
					// JOptionPane.showMessageDialog(null, "Invalid Mobile No character");
				}

				if (isPin) {
					if (DPin.equals(DPin1)) {
						String DOB = "" + (month.getSelectedItem()) + "-" + date.getSelectedItem() + "-"
								+ year.getSelectedItem();
						voter_id = getId();
						Voter V1 = new Voter(voter_id, Tname.getText(), Tfname.getText(), Tcnic.getText(), DOB,
								Tmob.getText(), Tdist.getText(), "" + profes.getSelectedItem(),
								"" + relig.getSelectedItem(), DPin, 0);
						voterlist.put(voter_id, V1);

						try {
							DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
									"System", "saadkz07");
							PreparedStatement ps = con.prepareStatement(
									"insert into Voter (voterid,name,fname,dob,mobile,cnic,district,proffesion,religion,pic,pin,canvote) values(?,?,?,?,?,?,?,?,?,?,?,?)");

							ps.setInt(1, voter_id++);
							ps.setString(2, V1.getName());
							ps.setString(3, V1.getFName());
							ps.setString(4, V1.getDob());
							ps.setString(5, V1.getMobile());
							ps.setString(6, V1.getCnic());
							ps.setString(7, V1.getDistrict());
							ps.setString(8, V1.getProffession());
							ps.setString(9, V1.getReligion());
							FileInputStream fin = new FileInputStream(path);
							ps.setBinaryStream(10, fin, fin.available());
							ps.setInt(11, V1.getPin());
							ps.setInt(12, 0);
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Succefull Created");
							Home.doClick();

							con.close();

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// (String n, String c,int V, String dob,String m,String d,String p, String
						// r,FileInputStream F)

					} else {
						JOptionPane.showMessageDialog(null, "Pin is not matched");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Invalid Pin");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Pin Length should be 4");
			}

		}

		if (Choose == e.getSource()) {

			JFileChooser fc = new JFileChooser();

			FileNameExtensionFilter filter = new FileNameExtensionFilter("All files", "png", "jpg", "jpeg");
			fc.addChoosableFileFilter(filter);
			fc.setAcceptAllFileFilterUsed(false);

			int i = fc.showOpenDialog(null);

			if (i == JFileChooser.APPROVE_OPTION) {
				file1 = fc.getSelectedFile();
				path = file1.getAbsolutePath();
				System.out.println(path);
				BufferedImage img = null;

				try {
					img = ImageIO.read(file1);
					path = file1.getAbsolutePath();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Image dimg = img.getScaledInstance(LPic.getWidth(), LPic.getHeight(), Image.SCALE_SMOOTH);
				setImg(dimg);

			}

		}
		if (Home == e.getSource()) {
			frame.dispose();
			new HomePage();

		}
	}
	public static void main(String arg[]) {
		new SForm();
	}

}
