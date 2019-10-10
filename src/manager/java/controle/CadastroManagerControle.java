package manager.java.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import GUI.CadastroManagerGUI;
import manager.java.exception.ManagerException;
import manager.java.modelo.Gerente;
import manager.java.persistencia.GerenteMySQL;

public class CadastroManagerControle {

	CadastroManagerGUI View;

	public CadastroManagerControle() {
		View = new CadastroManagerGUI();
		View.getBtnConfirmar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btConfirmar(e);
			}
		});
		View.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btCancelar(e);
			}
		});

		View.setVisible(true);
	}

	protected void btCancelar(ActionEvent e) {
		fechar();
	}

	protected void btConfirmar(ActionEvent e) {
		String login = View.getTfLogin().getText();
		char[] senha = View.getTfSenha().getPassword();
		char[] senha2 = View.getTfrepitaSenha().getPassword();
		String rest = View.getTfRestaurante().getText();

		if (!login.isEmpty() && senha.length > 0 && senha2.length > 0 && !rest.isEmpty()) {
			if (Arrays.equals(senha, senha2)) {
				try {
					ArrayList<String> list = new GerenteMySQL().listarUsers();
					Object[] dados = list.toArray();
					Arrays.sort(dados);
					int search = Arrays.binarySearch(dados, login);
					
					if(search<0) {
						if(rest.equals("ideirpassanos")) {
							Gerente g = new Gerente();
							g.setUser(login);
							g.setPassword(new String(senha));
							new GerenteMySQL().adicionar(g);
							JOptionPane.showMessageDialog(View, "Usuário cadastrado", "Faça login e utili-ze!", JOptionPane.PLAIN_MESSAGE);
							fechar();
						}else {
							JOptionPane.showMessageDialog(View, "O código de verificação está errado, se informe com o seu chefe para conseguir entrar no sistema", "Fala com o chefão que tá tudo certo", JOptionPane.WARNING_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(View, "Esse nome de usuário já foi usado", "Mude o nome de usuário", JOptionPane.WARNING_MESSAGE);
					}
					
				} catch (ManagerException e1) {
					JOptionPane.showMessageDialog(View, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(View, "As senhas não correspondem", "Tente novamente",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(View, "Digite em todos os campos", "Acho que você esqueceu de alguma coisa",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void fechar() {
		View.dispose();
		Inicio.reiniciar();
	}
}