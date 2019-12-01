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
import java.sql.ResultSetMetaData;

/**
 *
 * @author Paulo
 */
public class CompraDAO {
    private Connection connection;
    
    
    public CompraDAO() throws SQLException{
        connection = ConnectionDAO.getConnection();
    }
    
    public int insert(Compra c) throws SQLException{
        String sql = "insert into compra(idvendedor, cpf, data) values(?, ?, ?) returning Compra_id";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, c.getIdvendedor());
        statement.setInt(2, c.getCpf());
        statement.setDate(3, c.getData());
        
        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt("Compra_id");
    }
    
    public ArrayList<DisplayCompra> getDisplayList() throws SQLException{
        ArrayList<DisplayCompra> lista = new ArrayList<>();
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "select cliente.nome, vendedor.nome, compra.compra_id " +
                "from cliente, vendedor, compra " +
                "where compra.cpf = cliente.cpf and " +
                "compra.idvendedor = vendedor.codigo");                
        
        while(result.next()){            
            String comprador = result.getString(1);
            String vendedor = result.getString(2);
            int compra_id = result.getInt(3);
            
            float valor = 0;
            
            Statement compraStatement = connection.createStatement();
            ResultSet resultc = compraStatement.executeQuery(
                    "select produto.preco, itemcompra.quantidade from produto, itemcompra " + 
                    "where itemcompra.compra_id = " + String.valueOf(compra_id) + 
                    " and produto.codigo_produto = itemcompra.codigo");                                                
                                    
            while(resultc.next()){                
                valor += resultc.getFloat("preco") * resultc.getFloat("quantidade");
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
