import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class Candidate implements ActionListener{
	private JFrame frame=new JFrame();
	private JPanel panel=new JPanel();
	
	private JLabel[] labels = new JLabel[4];
	private JTextField[] Fields = new JTextField[3];
	private JButton[] button = new JButton[3];
	private Font font = new Font("Consolas", Font.BOLD, 15);
	private File file1;
	private String path=new String();
Candidate(){
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setLayout(null);
	frame.setSize(600,600);
	set();
	frame.add(panel);
	frame.setVisible(true);
	
}
	
	void set() {
		panel.setLayout(null);
		panel.setSize(700, 700);

		for (int i = 0; i < 4; i++) {
			panel.add(labels[i] = new JLabel());
			if (i < 3)
				panel.add(Fields[i] = new JTextField());

		}

		int i = 0;

		labels[i].setText("Candidate Entry");
		labels[i].setFont(new Font("MV Boli", Font.BOLD, 40));
		labels[i++].setBounds(100, 50, 600, 50);

		labels[i].setText("Candidate Name");
		labels[i].setFont(font);
		labels[i++].setBounds(10, 150, 150, 50);

		labels[i].setText("Add  Picture");
		labels[i].setFont(font);
		labels[i++].setBounds(10, 190, 150, 50);

		labels[i].setText("File Name");
		labels[i].setFont(font);
		labels[i++].setBounds(10, 230, 150, 50);

		i = 0;
		Fields[i].setBounds(150, 163, 90, 20);
		Fields[i++].setFont(font);

		Fields[i].setBounds(100, 245, 150, 20);
		Fields[i].setEditable(false);
		Fields[i++].setFont(font);

		panel.add(button[0] = new JButton("Insert"));
		panel.add(button[1] = new JButton("Submit"));

		button[0].setBounds(140, 200, 80, 25);
		button[1].setBounds(100, 290, 80, 25);


		button[0].addActionListener(this);
		button[1].addActionListener(this);
		button[1].setEnabled(false);

		
		panel.setVisible(true);
		
	}

	
	
	
	public boolean CreateCandidate(String n,String path){
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","saadkz07");
		PreparedStatement ps=con.prepareStatement("insert into cand (name,pic)Values(?,?)");
		FileInputStream pic=new FileInputStream(path);
		ps.setString(1, n);
		ps.setBinaryStream(2,pic,pic.available());
		con.close(); 
		
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
		
		
		
	}
	void insert(String name, String path) {
	
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "saadkz07");
			PreparedStatement ps = con.prepareStatement("insert into Cand values(?,?)");
			ps.setString(1, name);
			FileInputStream fin = new FileInputStream(path);
			ps.setBinaryStream(2, fin, fin.available());

			ps.executeUpdate();
			ps.close();
			
			JOptionPane.showMessageDialog(null, "SuccessFully Inserted");
			

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
public static void main(String[] arg) {
	
	new Candidate();
}



	@Override
	public void actionPerformed(ActionEvent e) {
		// Koded by 19sw10
		String ID;
		int k = 0;

		if (button[0] == e.getSource()) {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("All files", "png", "jpg", "jpeg");
			fc.addChoosableFileFilter(filter);
			fc.setAcceptAllFileFilterUsed(false);

			int i = fc.showOpenDialog(null);

			if (i == JFileChooser.APPROVE_OPTION) {
				file1 = fc.getSelectedFile();
				path = file1.getAbsolutePath();
				Fields[1].setText(path);
				button[1].setEnabled(true);


			}

		}
		if (button[1] == e.getSource()) {
			ID = Fields[0].getText();
			if (file1 != null) {
				try {
					System.out.println(k);
					insert(ID, path);
					
				} catch (Exception r) {
					r.printStackTrace();
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Please Select File");
			}
			
		}
		
	}
}
