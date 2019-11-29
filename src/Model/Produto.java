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
public class Produto {
    private Integer codigo;
    private String nome;  
    private float preco;
    private Integer estoque;

    public Produto(String nome, float preco, Integer estoque){
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;        
    }

    public Produto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
     public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

    public Integer getEstoque() {
        return estoque;
    }
    
}
