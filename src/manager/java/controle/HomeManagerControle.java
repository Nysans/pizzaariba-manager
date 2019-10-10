package manager.java.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GUI.HomeManagerGUI;

public class HomeManagerControle {

	private HomeManagerGUI View;
	private JPanel SubView;
	Object Controle;

	public HomeManagerControle() {
		View = new HomeManagerGUI();
		
		View.getBtnPedidos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPedidos(e);
			}
		});
		View.getBtnSair().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSair(e);
			}
		});
		View.setVisible(true);
	}

	protected void btnSair(ActionEvent e) {
		if(Controle.getClass() == PedidosChefeControle.class) {
			((PedidosChefeControle) Controle).fecharTimer();
		}
		View.dispose();
		Inicio.reiniciar();
	}

	protected void btnPedidos(ActionEvent e) {
			Controle = new PedidosChefeControle();
			SubView = ((PedidosChefeControle) Controle).getView();
			setPanelContent(SubView);
	}

	private void setPanelContent(JPanel SubView) {
		View.getPnSubView().add(SubView);
		SubView.setSize(499, 476);
		SubView.setVisible(true);
		View.repaint();
		View.revalidate();
	}
}