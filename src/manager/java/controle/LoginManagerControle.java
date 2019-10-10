package manager.java.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.LoginManagerGUI;
import java.util.Arrays;
import manager.java.exception.ManagerException;
import manager.java.modelo.Gerente;
import manager.java.persistencia.GerenteMySQL;

public class LoginManagerControle {

	private LoginManagerGUI View;
	
	public LoginManagerControle() {
		View = new LoginManagerGUI();
		
		View.getBtnConfirmar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnConfirmar(e);
			}
		});
		
		View.getBtnCadastrar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnCadastrar();	
			}
		});
		View.setVisible(true);
	}

	protected void btnCadastrar() {
		View.dispose();
		new CadastroManagerControle();
	}

	protected void btnConfirmar(ActionEvent e) {
		if(!View.getTfLogin().getText().isEmpty() && View.getTfSenha().getPassword().length != 0){
			try {
				Gerente g = new GerenteMySQL().selecionar(View.getTfLogin().getText());
				if(Arrays.equals(g.getPassword().toCharArray(), View.getTfSenha().getPassword())) {
					JOptionPane.showMessageDialog(View, "Usuário Logado", "Pode continuar", JOptionPane.PLAIN_MESSAGE);
					View.setVisible(false);
					new HomeManagerControle();
				}else {
					JOptionPane.showMessageDialog(View, "Senha incorreta", "Temos um probleminha...", JOptionPane.WARNING_MESSAGE);
				}
				
			} catch (ManagerException e1) {
				if(e1.getMessage().equals("User Inválido")) {
					JOptionPane.showMessageDialog(View, "Nome de usuário não bate com os registros", "Temos um probleminha...", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(View, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(View, "Prencha todos os campos", "Temos um probleminha...", JOptionPane.WARNING_MESSAGE);
		}
		
	}

	public void setViewVisible(boolean b) {
		View.setVisible(b);
	}	
}
