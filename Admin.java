import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Admin implements MouseListener, WindowListener {
	Admin(){}
	private JFrame frame = new JFrame();
	private JPanel Cpanel = new JPanel();
	private JPanel Pprofile = new JPanel();
	private JLabel[] Panels = new JLabel[20];
	private JLabel[] Profile_Labels = new JLabel[15];
	private JLabel[] Profile_Labels1 = new JLabel[15];
	private String Cnames[] = new String[15];
	private LineBorder border = new LineBorder(Color.black);
//	private LineBorder border1 = new LineBorder(Color.PINK);
	private TitledBorder Tb = BorderFactory.createTitledBorder(border, "Candidates", TitledBorder.LEADING, 0,
			new Font("TimesRoman", Font.BOLD, 30), Color.RED);
	private TitledBorder Tb1 = BorderFactory.createTitledBorder(border, "Profile", TitledBorder.LEADING, 0,
			new Font("TimesRoman", Font.BOLD, 30), Color.RED);
	private int id = 0;
	private int num = 0;
	FileOutputStream fout;
    private static Person VotingList=new Person();
    private Voter voter;
	void setPortal(int i,Voter v,boolean k){
		this.id=i;
		voter=v;
		setFrame(k);

	}
	private void setFrame(boolean k) {
		frame.addWindowListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(1000, 1000);
		setProfile();
		setPanels();
		frame.add(Pprofile);
		frame.add(Cpanel);
		frame.add(Pprofile);
		
		
		
		frame.setVisible(k);
		Pprofile.setVisible(true);
		Cpanel.setVisible(true);
	}
	
	
	
	private void setProfile() {
		Pprofile.setLayout(new GridLayout(11, 2, 8, 8));
		Pprofile.setBounds(0, 70, 300, 400);
	//	Pprofile.setSize(100,100);
		Pprofile.setBorder(Tb1);
		for (int i = 0; i < 11; i++) {
			Pprofile.add(Profile_Labels1[i] = new JLabel());
			Pprofile.add(Profile_Labels[i] = new JLabel());
			Profile_Labels[i].setSize(50, 50);
			Profile_Labels1[i].setSize(50, 50);
			//Profile_Labels1[i].setBorder(border);
		}
		int k=1;
		Profile_Labels1[k++].setText("Voter Id");
		Profile_Labels1[k++].setText("Name");
		Profile_Labels1[k++].setText("F Name ");
		Profile_Labels1[k++].setText("D O B");
		Profile_Labels1[k++].setText("Mobile");
		Profile_Labels1[k++].setText("Cnic");
		Profile_Labels1[k++].setText("District");
		Profile_Labels1[k++].setText("Proffession");
		Profile_Labels1[k++].setText("Religion");
		
		int no = 1;
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "saadkz07");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select *  from voter where voterid =" + id);

			while (rs.next()) {
				Profile_Labels[no].setText("" + rs.getInt(no++));
				Profile_Labels[no].setText(rs.getString(no++));
				Profile_Labels[no].setText(rs.getString(no++));
				Profile_Labels[no].setText(rs.getString(no++));
				Profile_Labels[no].setText(rs.getString(no++));
				Profile_Labels[no].setText(rs.getString(no++));
				Profile_Labels[no].setText(rs.getString(no++));
				Profile_Labels[no].setText(rs.getString(no++));
				Profile_Labels[no].setText(rs.getString(no++));
				byte barr[] = rs.getBytes(10);// 1 means first image
				ImageIcon image = new ImageIcon(barr);
				Image img = image.getImage();
				Image dimg = img.getScaledInstance(Profile_Labels[0].getWidth(), Profile_Labels[0].getHeight(),
						Image.SCALE_SMOOTH);
				ImageIcon ic = new ImageIcon(dimg);
				Profile_Labels[0].setIcon(ic);

			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


	Person getPerson() {
		return VotingList;
	}

	private void setPanels() {
		
		Cpanel.setBorder(Tb);
		Cpanel.setLayout(new GridLayout(5, 2, 8, 8));
		for (int i = 0; i < 10; i++) {
			Cpanel.add(Panels[i] = new JLabel());
			Panels[i].setSize(100,100);
			Panels[i].addMouseListener(this);
		}
		Cpanel.setBounds(400, 70, 300, 400);
				try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "saadkz07");

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select name,pic from Cand");
              
			
			
			while (rs.next()) {
				Cnames[num]=rs.getString(1);
				byte barr[] = rs.getBytes(2);// 1 means first image
				ImageIcon image = new ImageIcon(barr);
				Image img = image.getImage();
				Image dimg = img.getScaledInstance(Panels[0].getWidth(), Panels[0].getHeight(), Image.SCALE_SMOOTH);
				ImageIcon ic = new ImageIcon(dimg);
				Panels[num++].setIcon(ic);

			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	void doneVote(int vid) {
		 try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
						"System", "saadkz07");
				PreparedStatement ps = con.prepareStatement("update Voter set canvote =1 where voterid="+vid);
				ps.executeUpdate();

				con.close();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// (String n, String c,int V, String dob,String m,String d,String p, String
			// r,FileInputStream F)

		
	}
	public void addVote(int ids,String name) {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"System", "saadkz07");
			PreparedStatement ps = con.prepareStatement("insert into votinglist values(?,?)");
			ps.setInt(1, ids);
			ps.setString(2, name);
			ps.executeUpdate();

			con.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// (String n, String c,int V, String dob,String m,String d,String p, String
		// r,FileInputStream F)
		
	} 
private boolean CheckVote(int i) {
	int result = JOptionPane.showConfirmDialog(frame,"Sure? You want to vote to :"+Cnames[i], "Confirm Vote",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
         if(result == JOptionPane.YES_OPTION){
        	 VotingList.vote(voter);
        	 JOptionPane.showMessageDialog(null, "Successfully Voted");
        	 doneVote(id);
        	 addVote(id,Cnames[i]);
        	 setVisibleHome();
        	 setName(i);
        	 frame.setVisible(false);
        	 return true;
         }else if (result == JOptionPane.NO_OPTION){
         	JOptionPane.showMessageDialog(null, "Choose Carefully");
         }else {
         }
return false;
	
}
public String  setName(int i) {
	// Koded by 19sw10
	return Cnames[i];
}
void cantvote() {
	JOptionPane.showMessageDialog(frame, "You have already voted");
	frame.dispose();
}
	private void setVisibleHome() {
	// Koded by 19sw10
	
}
	@Override
	public void mouseClicked(MouseEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Koded by 19sw10
		for(int i=0;i<11;i++)		
		if (Panels[i] == e.getSource()) {
          int cv= voter.can1();
			if(cv==0) {
			if( CheckVote(i)) {
		    	
		    HomePage hm=new HomePage();
		    hm.setvisibleHome(true);
		    	
		    }
			}else {cantvote();}
			}
			
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Koded by 19sw10
		if (Panels[0] == e.getSource()) {
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// Koded by 19sw10

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// Koded by 19sw10
		JOptionPane.showMessageDialog(null, "o my god");

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// Koded by 19sw10

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

}
