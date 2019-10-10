package manager.java.modelo;

import java.util.ArrayList;

import manager.java.exception.ManagerException;

public class Pedido {

    private int ID;
    private float Valor;
    private int Mesa;
    private ArrayList<Produto> Produtos;
    private ArrayList<Integer> qnt;

    public Pedido() {
        Produtos = new ArrayList<>();
        qnt = new ArrayList<>();
    }

    public int getID() {
        return ID;

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setID(String ID) throws ManagerException {
        try {
            setID(Integer.parseInt(ID));
        } catch (NumberFormatException e) {
            throw new ManagerException("Formato inválido");
        }
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float Valor) {
        this.Valor = Valor;
    }

    public void setValor(String valor) throws ManagerException {
        try {
            setValor(Float.parseFloat(valor));
        } catch (NumberFormatException e) {
            throw new ManagerException("Formato inválido");
        }
    }

    public int getMesa() {
        return Mesa;
    }

    public void setMesa(String Mesa) throws ManagerException {
        try {
            setMesa(Integer.parseInt(Mesa));
        } catch (NumberFormatException e) {
            throw new ManagerException("Mesa inválida");
        }
    }

    public void setMesa(int Mesa) {
        this.Mesa = Mesa;
    }
    
    public ArrayList<Integer> getQnt() {
        return qnt;
    }

    public void setQntPizzas(ArrayList<Integer> qntPizzas) {
        this.qnt = qntPizzas;
    }

    public ArrayList<Produto> getProdutos() {
        return Produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        Produtos = produtos;
    }

}
