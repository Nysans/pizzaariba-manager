package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Bebida;

public interface BebidaDAO {

	public abstract Bebida selecionar(int id) throws ManagerException;

	public abstract Bebida selecionar(String nome) throws ManagerException;

	public abstract Bebida getBebida(ArrayList<?> dados, int i) throws ManagerException;

	public abstract ArrayList<Bebida> listar() throws ManagerException;

}
