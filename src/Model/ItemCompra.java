/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Paulo
 */
public class ItemCompra {
    private int Codigo_Item;
    private int Codigo_produto;
    private int Compra_Id;
    private int Quantidade;
    
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }        

    public int getCodigo_Item() {
        return Codigo_Item;
    }

    public void setCodigo_Item(int Codigo_Item) {
        this.Codigo_Item = Codigo_Item;
    }

    public int getCodigo_produto() {
        return Codigo_produto;
    }

    public void setCodigo_produto(int Codigo_produto) {
        this.Codigo_produto = Codigo_produto;
    }

    public int getCompra_Id() {
        return Compra_Id;
    }

    public void setCompra_Id(int Compra_Id) {
        this.Compra_Id = Compra_Id;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }        
}
