/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import Model.Produto;
import Model.Produto;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author gabriel
 */
public class graficoBarra {



   
     public CategoryDataset createDataset(ArrayList<Produto> listadeProdutos){
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         
         for(Produto produto : listadeProdutos){
        dataset.addValue(produto.getEstoque(), produto.getNome(), "");
         
         }
         return dataset;
     }
     
     public JFreeChart createBarChart(CategoryDataset dataset){
        JFreeChart graficoBarra = ChartFactory.createBarChart("Produtos mais vendidos", 
                "", 
                "Codigo",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        return graficoBarra;
     }
     
     public  ChartPanel CriarGrafico(ArrayList<Produto> listadeProdutos){
         CategoryDataset dataset = this.createDataset(listadeProdutos);
         
         JFreeChart grafico = this.createBarChart(dataset);
         ChartPanel paineldoGrafico = new ChartPanel(grafico);
         paineldoGrafico.setPreferredSize(new Dimension(200,200));
         
         return paineldoGrafico;
         
     }
     
     
}
