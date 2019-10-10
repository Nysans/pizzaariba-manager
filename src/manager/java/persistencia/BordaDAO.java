package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Borda;

public interface BordaDAO {

	public abstract Borda selecionar(int id) throws ManagerException;
	
	public abstract Borda selecionar(String nome) throws ManagerException;

	public abstract Borda getBorda(ArrayList<?> dados, int i) throws ManagerException;
	
	public abstract ArrayList<Borda>listar() throws ManagerException;
	
}
