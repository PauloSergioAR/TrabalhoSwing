/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import DAO.ClienteDAO;
import DAO.CompraDAO;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import Model.Cliente;
import Model.Compra;
import Model.DisplayCompra;
import Model.Produto;
import Model.Vendedor;
import Util.TableMouseListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paulo
 */
public class Listagem extends javax.swing.JFrame {       
    private JMenuItem vendedorUpdate;
    private JMenuItem vendedorRemove;
    private JPopupMenu vendedorMenu;
    
    private JMenuItem compradorUpdate;
    private JMenuItem compradorRemove;
    private JPopupMenu compradorMenu;
    
    private JMenuItem produtoUpdate;
    private JMenuItem produtoRemove;
    private JPopupMenu produtoMenu;
    
    ArrayList<Cliente> clientes;
    ArrayList<Produto> produtos;
    ArrayList<Vendedor> vendedores;
    
    int xx,xy; 
    
    /**
     * Creates new form Listagem
     */
    public Listagem() {    	        
        initComponents();
        initTables();
        
        setColor(btn_4);
        ind_4.setOpaque(true);
        resetColor(new JPanel[]{btn_3,btn_1}, new JPanel[]{ind_3, ind_1});
    }

    private void initTables(){
        updateVendasTable();
        updateClienteTable();
        updateProdutosTable();
        updateVendedoresTable();
        initVendedorPopup();
        initCompradorPopup();
        initProdutoPopup();
    }
    
    private void initCompradorPopup(){        
        compradorMenu = new JPopupMenu();
        compradorRemove = new JMenuItem("Deletar");
        compradorUpdate = new JMenuItem("Editar");
        
        compradorRemove.addActionListener((ActionEvent e) -> {
        	int index = clienteTable.getSelectedRow();
            try {
            	ClienteDAO dao = new ClienteDAO();
            	
            	dao.excluir(clientes.get(index));
            	JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
            	initTables();
            	
            }catch (SQLException ex) {
            	ex.printStackTrace();
            	JOptionPane.showMessageDialog(null, "Erro ao excluir cliente");
			}
        });
        
        compradorUpdate.addActionListener((ActionEvent e) -> {
            JTextField nome = new JTextField("", 15);            
            
            JLabel labelNome =  new JLabel("Nome: ");            
            JPanel dialogPanel = new JPanel(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
            
            gbc.gridx = 0;
            gbc.gridy = 0;
            
            dialogPanel.add(labelNome, gbc);
            gbc.gridx++;
            dialogPanel.add(nome, gbc);
                     
            
            int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Editar Cliente", JOptionPane.OK_CANCEL_OPTION);
            
            if(result == JOptionPane.OK_OPTION){
                try{
                    ClienteDAO dao = new ClienteDAO();
                    int index = clienteTable.getSelectedRow();
                    Cliente old = clientes.get(index);
                    Cliente c = new Cliente(nome.getText(), old.getCPF());
                    dao.update(c);
                    initTables();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao editar cliente");
                    Logger.getLogger(Listagem.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        });
        
        compradorMenu.add(compradorRemove);
        compradorMenu.add(compradorUpdate);
        
        clienteTable.addMouseListener(new TableMouseListener(clienteTable));
        clienteTable.setComponentPopupMenu(compradorMenu);
    }
    
    private void initProdutoPopup(){
        produtoMenu = new JPopupMenu();
        produtoRemove = new JMenuItem("Deletar");
        produtoUpdate = new JMenuItem("Editar");
        
        produtoRemove.addActionListener((ActionEvent e) -> {
        	int index = produtoTable.getSelectedRow();
            try {
            	ProdutoDAO dao = new ProdutoDAO();
            	
            	dao.excluir(produtos.get(index));
            	JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
            	initTables();
            	
            }catch (SQLException ex) {
            	ex.printStackTrace();
            	JOptionPane.showMessageDialog(null, "Erro ao excluir produto");
			}
        });
        
        produtoUpdate.addActionListener((ActionEvent e) -> {
            JTextField nome = new JTextField("", 15);
            JTextField valor = new JTextField("", 15);
            JTextField estoque = new JTextField("", 15);
            
            JLabel labelNome =  new JLabel("Nome: ");
            JLabel labelValor =  new JLabel("Valor: ");
            JLabel labelEstoque =  new JLabel("Estoque:");
            JPanel dialogPanel = new JPanel(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
            
            gbc.gridx = 0;
            gbc.gridy = 0;
            
            dialogPanel.add(labelNome, gbc);
            gbc.gridx++;
            dialogPanel.add(nome, gbc);
            
            gbc.gridy++;
            gbc.gridx--;
            
            dialogPanel.add(labelValor, gbc);
            gbc.gridx++;
            dialogPanel.add(valor, gbc);
            
            gbc.gridy++;
            gbc.gridx--;
            
            dialogPanel.add(labelEstoque, gbc);
            gbc.gridx++;
            dialogPanel.add(estoque, gbc);
            
            int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Editar Produto", JOptionPane.OK_CANCEL_OPTION);
            
            if(result == JOptionPane.OK_OPTION){
                 try{
                    ProdutoDAO dao = new ProdutoDAO();
                    int index = produtoTable.getSelectedRow();
                    Produto old = produtos.get(index);
                    Produto c = new Produto(nome.getText(), Float.parseFloat(valor.getText()), Integer.parseInt(estoque.getText()));
                    
                    c.setCodigo(old.getCodigo());                    
                    
                    dao.update(c);
                    initTables();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao editar produto");
                    Logger.getLogger(Listagem.class.getName()).log(Level.SEVERE, null, ex);
                }                            
            }
        });
        
        produtoMenu.add(produtoRemove);
        produtoMenu.add(produtoUpdate);
        
        produtoTable.addMouseListener(new TableMouseListener(produtoTable));
        produtoTable.setComponentPopupMenu(produtoMenu);
    }
    
    private void initVendedorPopup(){        
        vendedorMenu = new JPopupMenu();
        vendedorRemove = new JMenuItem("Deletar");
        vendedorUpdate = new JMenuItem("Editar");
        
        vendedorRemove.addActionListener((ActionEvent e) -> {
            int index = vendedorTable.getSelectedRow();
            try {
            	VendedorDAO dao = new VendedorDAO();
            	
            	dao.excluir(vendedores.get(index));
            	JOptionPane.showMessageDialog(null, "Vendedor excluido com sucesso");
            	initTables();
            	
            }catch (SQLException ex) {
            	ex.printStackTrace();
            	JOptionPane.showMessageDialog(null, "Erro ao excluir vendedor");
			}
        });
        
        vendedorUpdate.addActionListener((ActionEvent e) -> {
            JTextField nome = new JTextField("", 30);
            
            JLabel labelNome =  new JLabel("Nome:");
            JPanel dialogPanel = new JPanel();
            
            dialogPanel.add(labelNome);
            dialogPanel.add(nome);
            
            int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Editar Vendedor", JOptionPane.OK_CANCEL_OPTION);
            
            if(result == JOptionPane.OK_OPTION){
                try{
                    VendedorDAO dao = new VendedorDAO();
                    int index = vendedorTable.getSelectedRow();
                    
                    Vendedor old = vendedores.get(index);
                    Vendedor v = new Vendedor(nome.getText());
                    v.setCodigo(old.getCodigo());
                    
                    dao.update(v);
                    initTables();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao editar vendedor");
                    ex.printStackTrace();
                }                
            }
        });
        
        vendedorMenu.add(vendedorRemove);
        vendedorMenu.add(vendedorUpdate);
        
        vendedorTable.addMouseListener(new TableMouseListener(vendedorTable));
        vendedorTable.setComponentPopupMenu(vendedorMenu);
    }
    
    public void updateVendedoresTable(){
        DefaultTableModel vendedoresModel = (DefaultTableModel) vendedorTable.getModel();
        
        Object[] row = new Object[2];
        vendedoresModel.setRowCount(0);
        
        try{
            VendedorDAO dao = new VendedorDAO();
            vendedores = dao.getAll();
            for(Vendedor v : vendedores){
                row[0] = v.getNome();
                row[1] = v.getCodigo();                
                vendedoresModel.addRow(row);
            }            
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateProdutosTable(){
        DefaultTableModel produtosModel = (DefaultTableModel) produtoTable.getModel();
        
        Object[] row = new Object[3];
        produtosModel.setRowCount(0);
        
        try{
            ProdutoDAO dao = new ProdutoDAO();
            produtos = dao.getAll();
            for(Produto p : produtos){
                row[0] = p.getNome();
                row[1] = p.getPreco();
                row[2] = p.getEstoque();                
                produtosModel.addRow(row);
            }            
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateVendasTable(){
        DefaultTableModel vendasModel = (DefaultTableModel) compraTable.getModel();
        
        Object[] row = new Object[3];
        vendasModel.setRowCount(0);
        try{
            CompraDAO dao = new CompraDAO();
            ArrayList<DisplayCompra> compras = dao.getDisplayList();
            for(DisplayCompra c : compras){
                row[0] = c.getComprador();
                row[1] = c.getVendedor();                
                row[2] = c.getValor();
                vendasModel.addRow(row);
            }           
        } catch(SQLException e){
            e.printStackTrace();
        }        
    }
    
    public void updateClienteTable(){
        DefaultTableModel clientemodel = (DefaultTableModel) clienteTable.getModel();
        Object[] row = new Object[2];
        clientemodel.setRowCount(0);
        try{
            ClienteDAO dao = new ClienteDAO();
            clientes = dao.getAll();
            for(Cliente c : clientes){
                row[0] = c.getNome();
                row[1] = c.getCPF();                
                clientemodel.addRow(row);
            }            
        } catch(SQLException e){
            e.printStackTrace();
        }        
    }
    
    private void btn_1homePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1homePressed
        setColor(btn_1);
        ind_1.setOpaque(true);
        resetColor(new JPanel[]{btn_3,btn_4}, new JPanel[]{ind_3, ind_4});
        Navigator.navigate(Navigator.screens.HOME);
    }//GEN-LAST:event_btn_1homePressed

    private void btn_3registroPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3registroPressed
        setColor(btn_3);
        ind_3.setOpaque(true);
        resetColor(new JPanel[]{btn_1,btn_4}, new JPanel[]{ind_1, ind_4});
        Navigator.navigate(Navigator.screens.REGISTRO);
    }//GEN-LAST:event_btn_3registroPressed

    private void btn_4listagemPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_4listagemPressed    	    	    
        setColor(btn_4);
        ind_4.setOpaque(true);
        resetColor(new JPanel[]{btn_3,btn_1}, new JPanel[]{ind_3, ind_1});
        Navigator.navigate(Navigator.screens.LISTAGEM);
    }//GEN-LAST:event_btn_4listagemPressed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void btn_exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMousePressed
        System.exit(0);
    }//GEN-LAST:event_btn_exitMousePressed

        private void setColor(JPanel pane)
    {
        pane.setBackground(new Color(41,57,80));
    }
    
    private void resetColor(JPanel [] pane, JPanel [] indicators)
    {
        for(int i=0;i<pane.length;i++){
           pane[i].setBackground(new Color(23,35,51));
           
        } for(int i=0;i<indicators.length;i++){
           indicators[i].setOpaque(false);           
        }        
    }
    
    public void setVisible(boolean b){                
        initTables();
        super.setVisible(b);
        setColor(btn_4);
        ind_4.setOpaque(true);
        resetColor(new JPanel[]{btn_3,btn_1}, new JPanel[]{ind_3, ind_1});
    }
    
    
    //Codigo gerado automaticamente pelo netbeans
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        side_pane = new javax.swing.JPanel();
        btn_1 = new javax.swing.JPanel();
        ind_1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_3 = new javax.swing.JPanel();
        ind_3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btn_4 = new javax.swing.JPanel();
        ind_4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_exit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        compraTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        produtoTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        clienteTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        vendedorTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1070, 590));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1070, 590));

        side_pane.setBackground(new java.awt.Color(23, 35, 51));
        side_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_1.setBackground(new java.awt.Color(23, 35, 51));
        btn_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1homePressed(evt);
            }
        });

        ind_1.setOpaque(false);
        ind_1.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_1Layout = new javax.swing.GroupLayout(ind_1);
        ind_1.setLayout(ind_1Layout);
        ind_1Layout.setHorizontalGroup(
            ind_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_1Layout.setVerticalGroup(
            ind_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Home");

        javax.swing.GroupLayout btn_1Layout = new javax.swing.GroupLayout(btn_1);
        btn_1.setLayout(btn_1Layout);
        btn_1Layout.setHorizontalGroup(
            btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_1Layout.createSequentialGroup()
                .addComponent(ind_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btn_1Layout.setVerticalGroup(
            btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_1Layout.createSequentialGroup()
                .addComponent(ind_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 120, -1));

        btn_3.setBackground(new java.awt.Color(23, 35, 51));
        btn_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_3registroPressed(evt);
            }
        });

        ind_3.setOpaque(false);
        ind_3.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_3Layout = new javax.swing.GroupLayout(ind_3);
        ind_3.setLayout(ind_3Layout);
        ind_3Layout.setHorizontalGroup(
            ind_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_3Layout.setVerticalGroup(
            ind_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Registro");

        javax.swing.GroupLayout btn_3Layout = new javax.swing.GroupLayout(btn_3);
        btn_3.setLayout(btn_3Layout);
        btn_3Layout.setHorizontalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_3Layout.createSequentialGroup()
                .addComponent(ind_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btn_3Layout.setVerticalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_3Layout.createSequentialGroup()
                .addComponent(ind_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 120, -1));

        btn_4.setBackground(new java.awt.Color(23, 35, 51));
        btn_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_4listagemPressed(evt);
            }
        });

        ind_4.setOpaque(false);
        ind_4.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_4Layout = new javax.swing.GroupLayout(ind_4);
        ind_4.setLayout(ind_4Layout);
        ind_4Layout.setHorizontalGroup(
            ind_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_4Layout.setVerticalGroup(
            ind_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Listagem");

        javax.swing.GroupLayout btn_4Layout = new javax.swing.GroupLayout(btn_4);
        btn_4.setLayout(btn_4Layout);
        btn_4Layout.setHorizontalGroup(
            btn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_4Layout.createSequentialGroup()
                .addComponent(ind_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel11)
                .addGap(0, 34, Short.MAX_VALUE))
        );
        btn_4Layout.setVerticalGroup(
            btn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_4Layout.createSequentialGroup()
                .addComponent(ind_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 120, -1));

        jPanel2.setBackground(new java.awt.Color(71, 120, 197));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        btn_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_Exit_25px.png"))); // NOI18N
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_exitMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Listagem");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(425, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(390, 390, 390)
                .addComponent(btn_exit)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        compraTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Comprador", "vendedor", "Valor"
            }
        ));
        jScrollPane2.setViewportView(compraTable);

        jTabbedPane1.addTab("Vendas", jScrollPane2);

        produtoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Preço", "Estoque"
            }
        ));
        jScrollPane1.setViewportView(produtoTable);

        jTabbedPane1.addTab("Produtos", jScrollPane1);

        clienteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF"
            }
        ));
        jScrollPane4.setViewportView(clienteTable);

        jTabbedPane1.addTab("Compradores", jScrollPane4);

        vendedorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ));
        jScrollPane3.setViewportView(vendedorTable);

        jTabbedPane1.addTab("Vendedores", jScrollPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(side_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(side_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
                                               
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_1;
    private javax.swing.JPanel btn_3;
    private javax.swing.JPanel btn_4;
    private javax.swing.JLabel btn_exit;
    private javax.swing.JTable clienteTable;
    private javax.swing.JTable compraTable;
    private javax.swing.JPanel ind_1;
    private javax.swing.JPanel ind_3;
    private javax.swing.JPanel ind_4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable produtoTable;
    private javax.swing.JPanel side_pane;
    private javax.swing.JTable vendedorTable;
    // End of variables declaration//GEN-END:variables
}
