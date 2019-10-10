package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Pedido;

public interface PedidoDAO {

	public abstract void adicionar(Pedido p) throws ManagerException;
	
	public abstract void excluir(Pedido p) throws ManagerException;
	
	public abstract Pedido selecionar(int id) throws ManagerException;

	public abstract Pedido selecionar(String nome) throws ManagerException;

	public abstract Pedido getPedido(ArrayList<?> dados, int i) throws ManagerException;

	public abstract ArrayList<Pedido> listar() throws ManagerException;

}
