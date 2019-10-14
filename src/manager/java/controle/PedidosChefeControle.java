package manager.java.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import GUI.PedidosChefe;
import manager.java.exception.ManagerException;
import manager.java.modelo.Bebida;
import manager.java.modelo.Pedido;
import manager.java.modelo.Pizza;
import manager.java.modelo.Produto;
import manager.java.modelo.tabela.PedidosTableModel;
import manager.java.persistencia.PedidoMySQL;

public class PedidosChefeControle {

	private PedidosChefe View;
	private ArrayList<Pedido> ListaPedidos;
	Timer timer;

	public PedidosChefeControle() {
		View = new PedidosChefe();

		View.getBtnCancelarPedido1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btCancelar(e);
			}
		});

		View.getBtnConcluirPedido1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btConcluir(e);
			}
		});
		View.getBtnAtualizar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAtualizar(e);
			}
		});

		listar();
                    PedidosTableModel model = (PedidosTableModel) View.getTbPedidos().getModel();
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				tbChange(e);
			}
		});
		timer();
		View.setVisible(true);
	}

	protected void btnAtualizar(ActionEvent e) {
		atualizar();

	}

	private void atualizar() {
		try {
			PedidosTableModel model = (PedidosTableModel) View.getTbPedidos().getModel();
			ListaPedidos = new PedidoMySQL().listar();
			model.setDados(ListaPedidos);
			int i = model.getSelectedIndex();
			if (i != -1) {
				model.setSelectIndex(-1);
				model.setValueAt(true, i, 0);
			}
			model.fireTableDataChanged();
		} catch (ManagerException e1) {
			JOptionPane.showMessageDialog(View, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void timer() {
		final long TEMPO = 180000;

		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				atualizar();
			}
		};
		timer.scheduleAtFixedRate(task, TEMPO, TEMPO);
	}

	protected void fecharTimer() {
		timer.cancel();
	}

	protected void btConcluir(ActionEvent e) {
		int ret = JOptionPane.showConfirmDialog(View, "Este pedido está concluído?", "Tem certeza mesmo?..",
				JOptionPane.YES_NO_OPTION);
		if (ret == JOptionPane.YES_OPTION) {
			PedidosTableModel model = (PedidosTableModel) View.getTbPedidos().getModel();
			try {
				new PedidoMySQL().excluir(ListaPedidos.get(model.getSelectedIndex()));
				model.deleteRow(model.getSelectedIndex());
				JOptionPane.showMessageDialog(View, "Pedido concluído", "Prontinho!!", JOptionPane.PLAIN_MESSAGE);
			} catch (ManagerException e1) {
				JOptionPane.showMessageDialog(View, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void btCancelar(ActionEvent e) {
		int ret = JOptionPane.showConfirmDialog(View, "Tem certeza que qquer cancelar esse pedido?",
				"Será que é uma boa ideia?!...", JOptionPane.YES_NO_OPTION);
		if (ret == JOptionPane.YES_OPTION) {
			PedidosTableModel model = (PedidosTableModel) View.getTbPedidos().getModel();
			try {
				new PedidoMySQL().excluir(ListaPedidos.get(model.getSelectedIndex()));
				model.deleteRow(model.getSelectedIndex());
				JOptionPane.showMessageDialog(View, "Pedido coancelado :-(", "", JOptionPane.PLAIN_MESSAGE);
			} catch (ManagerException e1) {
				JOptionPane.showMessageDialog(View, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void tbChange(TableModelEvent e) {
		if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
			PedidosTableModel model = (PedidosTableModel) View.getTbPedidos().getModel();
			if (model.getSelectedIndex() != -1) {
			Pedido pedido = ListaPedidos.get(model.getSelectedIndex());	
				ArrayList<Produto> produtos = pedido.getProdutos();
				String s = "";
				ArrayList<Bebida> bebidas = new ArrayList<>();
				for (int i = 0; i < produtos.size(); i++) {
					Produto p = produtos.get(i);
					if (p.getClass() == Pizza.class) {
						Pizza pizza = (Pizza) p;
						s += pedido.getQnt().get(i) + " x ";
						s += "Pizza " + pizza.getMassa().getNome();
						if (pizza.getNome().equals("À Moda do Cliente")) {
							s += " Cliente. Ingredientes : " + pizza.getIngredientesString();
						} else {
							s += " Sabor " + pizza.getNome();
						}
						if (pizza.getBorda().getNome().equals("Sem Borda")) {
							s += " - " + pizza.getBorda().getNome();
						} else {
							s += " - Borda de " + pizza.getBorda().getNome();
						}
						s+= "\n";
					} else {
						bebidas.add((Bebida) p);
					}
				}
				s += "\nBebidas:\n";
				for (int i = 0; i < bebidas.size(); i++) {
					s += pedido.getQnt().get(i) + " x " + bebidas.get(i).getNome() + "\n";
				}
				View.getTaDecricaoPedido().setText(s);
				View.getLbMesaAtual1().setText("Mesa " + pedido.getMesa());
			} else {
				View.getTaDecricaoPedido().setText("");
				View.getLbMesaAtual1().setText("Mesa -");
			}
		}else if(e.getType() == TableModelEvent.UPDATE && e.getColumn() == TableModelEvent.ALL_COLUMNS){
			View.getLbNumPedidos().setText(ListaPedidos.size() + "");
		} else if (e.getType() == TableModelEvent.DELETE) {
			View.getTaDecricaoPedido().setText("");
			View.getLbMesaAtual1().setText("Mesa -");
			View.getLbNumPedidos().setText(ListaPedidos.size() + "");
		}
	}

	private void listar() {
		try {
			ListaPedidos = new PedidoMySQL().listar();
			PedidosTableModel model = new PedidosTableModel(ListaPedidos);
			View.getTbPedidos().setModel(model);
			View.getLbNumPedidos().setText(ListaPedidos.size() + "");
		} catch (ManagerException e) {
			JOptionPane.showMessageDialog(View, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public PedidosChefe getView() {
		return View;
	}
}