/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Paulo
 */
public class VendedorDAO {
    private Connection connection;
    
    public VendedorDAO() throws SQLException{
        this.connection = ConnectionDAO.getConnection();
    }
    
    public void insert(Vendedor v) throws SQLException{
        String sql = "insert into vendedor (nome) values(?)";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, v.getNome());
        
        statement.execute();
    }
}
