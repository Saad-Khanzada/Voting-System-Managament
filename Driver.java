import javax.swing.UIManager;

public class Driver {

	public static void main(String[] args) {
		// Koded by 19sw10

		try {
			// select Look and Feel
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			// start application
			new HomePage();

			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

	

	}

}
