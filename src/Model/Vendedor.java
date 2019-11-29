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
public class Vendedor {
    private String nome;
    private Integer codigo;
    
    public Vendedor(String nome){
        this.nome = nome;            
    }
    
    public String getNome(){
        return nome;
    }
    public Integer getCodigo(){
        return codigo;
    }
    public void setCodigo(Integer codigo){
        this.codigo = codigo;
    }
}
