package manager.java.persistencia;

import java.util.ArrayList;

import manager.java.exception.ManagerException;
import manager.java.modelo.Bebida;

public class BebidaMySQL implements BebidaDAO {

	@Override
	public Bebida selecionar(int id) throws ManagerException {
		ConexaoMySQL con = new ConexaoMySQL();

		String sql = "SELECT * FROM Bebida WHERE Bebida_ID = ?";
		con.prepararPst(sql);

		con.setParam(1, id);

		ArrayList<ArrayList<String>> dados = con.selecionar();
		con.close();
		if (dados.size() > 0) {
			return getBebida(dados, 0);
		} else
			throw new ManagerException("Bebida não cadastrada: " + id);
	}

	@Override
	public Bebida selecionar(String nome) throws ManagerException {
		ConexaoMySQL con = new ConexaoMySQL();
		String sql = "SELECT * FROM Bebida WHERE Bebida_nome = ?";
		con.prepararPst(sql);

		con.setParam(1, nome);

		ArrayList<ArrayList<String>> dados = con.selecionar();
		con.close();
		if (dados.size() > 0) {
			return getBebida(dados, 0);
		} else
			throw new ManagerException("Ingrediente não cadastrado: " + nome);
	}

	@Override
	public Bebida getBebida(ArrayList<?> dados, int i) throws ManagerException {
		Bebida b = new Bebida();
		ArrayList<?> li = (ArrayList<?>) dados.get(i);

		b.setID(li.get(0).toString());
		b.setNome(li.get(1).toString());
		b.setValor(li.get(2).toString());
		b.setEstoque(li.get(3).toString());

		return b;
	}

	@Override
	public ArrayList<Bebida> listar() throws ManagerException {
		ConexaoMySQL con = new ConexaoMySQL();
		String sql = "SELECT * FROM BEBIDA ORDER BY BEBIDA_NOME";
		con.prepararPst(sql);
		ArrayList<ArrayList<String>> dados = con.selecionar();
		con.close();
		ArrayList<Bebida> resposta = new ArrayList<Bebida>();
		for (int i = 0; i < dados.size(); i++) {

			Bebida b = getBebida(dados, i);
			resposta.add(b);
		}
		return resposta;
	}
}
