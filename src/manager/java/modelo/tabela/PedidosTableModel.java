package manager.java.modelo.tabela;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manager.java.modelo.Pedido;

@SuppressWarnings("serial")
public class PedidosTableModel extends AbstractTableModel {

	private ArrayList<Pedido> dados;
	private ArrayList<Boolean> colunaCheckbox;
	private int index;
	private String[] colunas = { "", "" };

	public PedidosTableModel(ArrayList<Pedido> dados) {
		index = -1;
		this.dados = dados;
		colunaCheckbox = new ArrayList<>();
		for (int i = 0; i < this.dados.size(); i++) {
			colunaCheckbox.add(Boolean.FALSE);
		}
	}

	@Override
	public int getColumnCount() {
		return this.colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return colunaCheckbox.get(rowIndex);
		case 1:
			return ("Mesa " + dados.get(rowIndex).getMesa());
		}
		return null;
	}

	public void setDados(ArrayList<Pedido> dados) {
		this.dados = dados;
		for (int i = 0; i < dados.size(); i++) {
			colunaCheckbox.add(Boolean.FALSE);
		}

	}

	public ArrayList<Boolean> getColunaCheckbox() {
		return colunaCheckbox;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			if (index != -1) {
				if (rowIndex == index) {
					index = -1;
				} else {
					colunaCheckbox.set(index, false);
					fireTableCellUpdated(index, 0);
					index = rowIndex;
				}
			} else {
				index = rowIndex;
			}
		}
		this.colunaCheckbox.set(rowIndex, (Boolean) aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public int getSelectedIndex() {
		return index;
	}


	public void setSelectIndex(int index) {
		this.index = index;
	}

	public void deleteRow(int rowIndex) {
		dados.remove(rowIndex);
		colunaCheckbox.remove(rowIndex);
		index = -1;
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}
}
