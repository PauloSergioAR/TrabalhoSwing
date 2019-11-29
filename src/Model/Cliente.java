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
public class Cliente {
    private String nome;
    private Integer CPF;
    
    public Cliente(String nome, Integer CPF){
        this.nome = nome;
        this.CPF = CPF;        
    }
    
    public String getNome(){
        return this.nome;        
    }
    
    public Integer getCPF(){
        return CPF;
    }
}
