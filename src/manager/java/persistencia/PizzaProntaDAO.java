package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.PizzaPronta;

public interface PizzaProntaDAO {

	public abstract void adicionar(PizzaPronta p) throws ManagerException;

	public abstract PizzaPronta selecionar(int id) throws ManagerException;

	public abstract PizzaPronta selecionar(String nome) throws ManagerException;

	public abstract PizzaPronta getPizza(ArrayList<?> dados, int i) throws ManagerException;

	public abstract ArrayList<PizzaPronta> listar() throws ManagerException;
	
	public abstract void excluir(PizzaPronta p) throws ManagerException;

}
