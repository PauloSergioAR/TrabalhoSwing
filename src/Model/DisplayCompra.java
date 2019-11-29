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
public class DisplayCompra {
    private String comprador;
    private String vendedor;
    private float valor;
        
    public DisplayCompra(String comprador, String vendedor, float valor){
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.valor = valor;
    }
    
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }                   
}
