package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Pizza;

public interface PizzaDAO {

	public abstract void adicionar(Pizza p) throws ManagerException;
	
	public abstract Pizza getPizza(ArrayList<?> dados, int i) throws ManagerException;
	
	public abstract void excluir(Pizza p) throws ManagerException;
	
	public abstract Pizza selecionar(int id) throws ManagerException;
	
}
