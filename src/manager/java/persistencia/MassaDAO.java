package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Massa;

public interface MassaDAO {

public abstract Massa selecionar(int id) throws ManagerException;
	
	public abstract Massa selecionar(String nome) throws ManagerException;

	public abstract Massa getMassa(ArrayList<?> dados, int i) throws ManagerException;
	
	public abstract ArrayList<Massa> listar() throws ManagerException;
	
}
