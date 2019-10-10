package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Ingrediente;

public interface IngredienteDAO {

	public abstract Ingrediente selecionar(int id) throws ManagerException;
	
	public abstract Ingrediente selecionar(String nome) throws ManagerException;

	public abstract Ingrediente getIngrediente(ArrayList<?> dados, int i) throws ManagerException;
	
	public abstract ArrayList<Ingrediente> listar() throws ManagerException;
}
