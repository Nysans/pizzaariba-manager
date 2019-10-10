package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Ingrediente;

public class IngredienteMySQL implements IngredienteDAO{

	@Override
	public Ingrediente selecionar(int id) throws ManagerException {
		ConexaoMySQL con = new ConexaoMySQL();
		String sql = "SELECT * FROM Ingrediente WHERE Ingrediente_ID = ?";
		con.prepararPst(sql);

		con.setParam(1, id);
		
		ArrayList<ArrayList<String>> dados = con.selecionar();
		con.close();
		if (dados.size() > 0) {
			return getIngrediente(dados, 0);
		} else
			throw new ManagerException("Ingrediente não cadastrado: " + id);
	}

	@Override
	public Ingrediente selecionar(String nome) throws ManagerException {
		ConexaoMySQL con = new ConexaoMySQL();
		String sql = "SELECT * FROM Ingrediente WHERE Ingrediente_nome = ?";
		con.prepararPst(sql);

		con.setParam(1, nome);
		
		ArrayList<ArrayList<String>> dados = con.selecionar();
		con.close();
		if (dados.size() > 0) {
			return getIngrediente(dados, 0);
		} else
			throw new ManagerException("Ingrediente não cadastrado: " + nome);
	}

	@Override
	public Ingrediente getIngrediente(ArrayList<?> dados, int i) throws ManagerException {
		Ingrediente b = new Ingrediente();
		ArrayList<?> li = (ArrayList<?>) dados.get(i);
		
		b.setID(li.get(0).toString());
		b.setNome(li.get(1).toString());
		b.setValor(li.get(2).toString());
		b.setEstoque(li.get(3).toString());
		
		return b;
	}

	@Override
	public ArrayList<Ingrediente> listar() throws ManagerException {
		ConexaoMySQL con = new ConexaoMySQL();
		String sql = "SELECT * FROM Ingrediente ORDER BY Ingrediente_nome";
		con.prepararPst(sql);
		ArrayList<ArrayList<String>> dados = con.selecionar();
		con.close();
		ArrayList<Ingrediente> resposta = new ArrayList<Ingrediente>();
		for (int i = 0; i < dados.size(); i++) {

			Ingrediente b = getIngrediente(dados, i);
			resposta.add(b);
		}
		return resposta;
	}
}
