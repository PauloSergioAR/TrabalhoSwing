/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Compra;
import Model.DisplayCompra;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author Paulo
 */
public class CompraDAO {
    private Connection connection;
    
    
    public CompraDAO() throws SQLException{
        connection = ConnectionDAO.getConnection();
    }
    
    public void insert(Compra c) throws SQLException{
        String sql = "insert into compra(idvendedor, cpf, data) values(?, ?, ?)";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, c.getIdvendedor());
        statement.setInt(2, c.getCpf());
        statement.setDate(3, c.getData());
        
        statement.execute();
    }
    
    public ArrayList<DisplayCompra> getDisplayList() throws SQLException{
        ArrayList<DisplayCompra> lista = new ArrayList<>();
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from compra");
        
        while(result.next()){
            String getComprador = "select nome from cliente where cliente.cpf = " + result.getInt("cpf");            
            ResultSet compradorRS = statement.executeQuery(getComprador);
            String comprador = compradorRS.getString("nome");
            
            String getVendedor = "select nome from vendedor where vendedor.codigo = " + result.getInt("idVendedor");            
            ResultSet vendedorRs = statement.executeQuery(getComprador);
            String vendedor = vendedorRs.getString("nome");
                                    
            String getCompras = "select * from itemCompra where Compra_id = " + result.getInt("Compra_id");            
            ResultSet compraRs = statement.executeQuery(getCompras);
            
            float valor = 0;
            
            while(compraRs.next()){
                float preco;
                
                String precoQuery = "select Preco from Produto where Codigo_Produto = " + compraRs.getInt("CodigoProduto");
                ResultSet produtoRS = statement.executeQuery(precoQuery);
                preco = produtoRS.getFloat("Preco");
                
                valor += preco;
            }
            
            DisplayCompra c = new DisplayCompra(comprador, vendedor, valor);
            lista.add(c);
        }                                        
        return lista;
    }
    
    public ArrayList<Compra> getAll() throws SQLException{
        ArrayList<Compra> compras = new ArrayList<>();
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from compra");
        
        while(result.next()){
            Integer idVendedor = result.getInt("idVendedor");
            Integer cpf = result.getInt("CPF");
            Date data = result.getDate("Data");
            Compra c = new Compra(idVendedor, cpf, data);
            compras.add(c);
        }
        return compras;
    }
}
