/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ItemCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Paulo
 */
public class ItemCompraDAO {
    private Connection connection;
    
    public ItemCompraDAO() throws SQLException{
        connection = ConnectionDAO.getConnection();
    }
    
    public void insert(ItemCompra ic) throws SQLException{
        String sql = "insert into ItemCompra (codigo, Compra_id, Quantidade)" + 
                " values(?, ?, ?) returning codigo_item";
        
        PreparedStatement statement = connection.prepareStatement(sql);
               
        statement.setInt(1, ic.getCodigoProduto());
        statement.setInt(2, ic.getCompraId());
        statement.setInt(3, ic.getQuantidade());
        
        ResultSet rs = statement.executeQuery();
        rs.next();
        System.out.println(rs.getInt("codigo_item"));
    }
    
    public ArrayList<ItemCompra> getAll() throws SQLException{
        ArrayList<ItemCompra> itens = new ArrayList<>();
        Statement statement = connection.createStatement();        
        ResultSet result = statement.executeQuery("select * from ItemCompra");
        
        while(result.next()){
            int codigoItem = result.getInt("Codigo_Item");
            int codigoProduto = result.getInt("Codigo_Produto");
            int compraId = result.getInt("Compra_id");
            int quantidade = result.getInt("Quantidade");
            
            ItemCompra item = new ItemCompra();
            item.setCodigoItem(codigoItem);
            item.setCodigoProduto(codigoProduto);
            item.setCompraId(compraId);
            item.setQuantidade(quantidade);
            
            itens.add(item);
        }
        
        return itens;
    }
}