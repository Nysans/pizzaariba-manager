
package manager.java.modelo;

import manager.java.exception.ManagerException;

public class Bebida extends Produto {

	private int estoque;

	public Bebida() {
		
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public void setEstoque(String estoque) throws ManagerException {
		try {
			setEstoque(Integer.parseInt(estoque));
		} catch (NumberFormatException e) {
			throw new ManagerException("Formato inválido");
		}
	}

}
