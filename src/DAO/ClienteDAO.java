/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
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
public class ClienteDAO {
    private Connection connection;
    
    public ClienteDAO() throws SQLException{
        this.connection = ConnectionDAO.getConnection();
    }
    
    public void insert(Cliente c) throws SQLException{
        String sql = "insert into cliente (nome, cpf) values (?, ?)";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, c.getNome());
        statement.setInt(2, c.getCPF());
        
        statement.execute();
    }
    
    public ArrayList<Cliente> getAll() throws SQLException{
        ArrayList<Cliente> clientes = new ArrayList<>();
        Statement statement = connection.createStatement();        
        ResultSet result = statement.executeQuery("select * from cliente");
        
        while(result.next()){
            Integer cpf = result.getInt("cpf");
            String nome = result.getString("nome");
            Cliente c = new Cliente(nome, cpf);            
            clientes.add(c);
        }
        return clientes;
    }
    
    public void update(Cliente c) throws SQLException{
        Statement statement = connection.createStatement();
        String sql = "update cliente set nome = '" + c.getNome() +
                "' where cliente.cpf = " + c.getCPF();
        
        statement.execute(sql);
    }
}
