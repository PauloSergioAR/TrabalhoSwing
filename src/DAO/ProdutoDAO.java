/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Produto;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class ProdutoDAO {
    private Connection connection;
    
    public ProdutoDAO() throws SQLException{
       connection = ConnectionDAO.getConnection();
    }
    
    private static float round(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();
 
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
    
    public void insert(Produto p) throws SQLException{
        String sql = "insert into produto (estoque, nome, preco) values(?, ?, ?)";
        System.out.println(p.getNome());
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, p.getEstoque());
        statement.setString(2, p.getNome());
        statement.setFloat(3, round(p.getPreco(), 2));
        
        statement.execute();
    }
    
    public ArrayList<Produto> getAll() throws SQLException{
        ArrayList<Produto> produtos = new ArrayList<>();
        Statement statement = connection.createStatement();        
        ResultSet result = statement.executeQuery("select * from produto");
        
        while(result.next()){
            Integer estoque = result.getInt("estoque");
            String nome = result.getString("nome");
            float preco = result.getFloat("preco");
            Produto p = new Produto(nome, preco, estoque);
            p.setCodigo(result.getInt("codigo_produto"));
            produtos.add(p);
        }
        
        return produtos;
    }
}

      
