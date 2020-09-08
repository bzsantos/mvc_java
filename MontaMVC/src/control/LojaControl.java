/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import mvc.dao.LojaDAO;
import mvc.model.Loja;

/**
 *
 * @author bzsantos
 */
public class LojaControl {
    
    public void inserir(String nomeproduto, int quantidade, double peso, double preco){
        
        Loja lj = new Loja();
        LojaDAO dao = new LojaDAO();
        
        lj.setNomeproduto(nomeproduto);
        lj.setQuantidade(quantidade);
        lj.setPeso(peso);
        lj.setPreco(preco);
        
        dao.insere(lj);       
    }
    
    public void apagar(int id){
        
        Loja lj = new Loja();
        LojaDAO dao = new LojaDAO();
        
        lj.setIdproduto(id);
        
        dao.delete(lj);    
        
        
    }
    
    
}
