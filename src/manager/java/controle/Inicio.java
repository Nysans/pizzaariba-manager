package manager.java.controle;

import GUI.LoginManagerGUI;

public class Inicio {

	private static LoginManagerControle controle;
	
	public static void main(String[] args) {
		controle = new LoginManagerControle();
		lookAndFeel();
		
	}

	
	public static void reiniciar() {
		controle.setViewVisible(true);
	}
	

	private static void lookAndFeel() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Metal".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LoginManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LoginManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LoginManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}
}
