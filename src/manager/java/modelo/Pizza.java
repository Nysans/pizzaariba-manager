package manager.java.modelo;

import manager.java.exception.ManagerException;

public class Pizza extends PizzaPronta{

	private Borda borda;
	private Massa massa;

	public Pizza() {

	}

	public Borda getBorda() {
		return borda;
	}

	public void setBorda(Borda borda) {
		this.borda = borda;
	}

	public Massa getMassa() {
		return massa;
	}

	public void setMassa(Massa massa) {
		this.massa = massa;
	}

	@Override
	public float getValor() {
		return valor;
	}


	@Override
	public void setValor(String valor) throws ManagerException {
		try {
			setValor(Float.parseFloat(valor));
		} catch (NumberFormatException e) {
			throw new ManagerException("Formato inválido");
		}
	}

	public float geValorIngredientes() {
		float valor = 0;
		for (Ingrediente i : ingredientes) {
			valor += i.getValor();
		}
		return valor;
	}
}
