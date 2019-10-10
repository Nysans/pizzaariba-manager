package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Gerente;

public interface GerenteDAO {

	public abstract Gerente selecionar(String user) throws ManagerException;

	public abstract void adicionar(Gerente g) throws ManagerException;
	
	public abstract ArrayList<String> listarUsers() throws ManagerException;

}
