package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mvc.model.Loja;
/**
 *
 * @author bzsantos
 */
public class LojaDAO extends GenericDAO  {
    
     private static LojaDAO instance;

    static {
        instance = new LojaDAO();
    }

    public LojaDAO() {
    }

    public static LojaDAO getInstance() {
        return instance;
    }

        public boolean insere(Loja dados) {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO produto (idproduto, nomeproduto, quantidade, peso, preco) VALUES (?,?,?,?,?)");

            pstmt.setInt(1, dados.getIdproduto());
            pstmt.setString(2, dados.getNomeproduto());
            pstmt.setInt(3, dados.getQuantidade());
            pstmt.setDouble(4, dados.getPeso());
            pstmt.setDouble(5, dados.getPreco());
                

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }
    

    public boolean delete(Loja dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM produto WHERE idproduto = " + dados.getIdproduto());
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }



    public boolean update(Loja dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE produto SET "
                    + "idproduto = ?, nomeproduto = ?,quantidade = ?, peso = ?, preco = ?"
                    + "WHERE idgrupo = " + dados.getIdproduto());

            pstmt.setInt(1, dados.getIdproduto());
            pstmt.setString(2, dados.getNomeproduto());        
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }
	
	public Loja read(Loja dados) {

        PreparedStatement pstmt = null;

        int updateQuery = 0;

        Loja dadosAux = new Loja();
        Connection conn = Conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM produto WHERE idloja ='" + dados.getIdproduto()+ "'");
           
            if (rs.next()) {
                dadosAux = carregaLoja(dados, rs);

            } else {
                dados = null;
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return dados;

    }  
    
    
    private Loja carregaLoja(Loja dadosAux, ResultSet rs) throws SQLException {
        
        dadosAux.setIdproduto(rs.getInt("idproduto"));
        dadosAux.setNomeproduto(rs.getString("nomeproduto"));
        dadosAux.setQuantidade(rs.getInt("quantidade"));
        dadosAux.setPeso(rs.getDouble("peso"));
        dadosAux.setPreco(rs.getDouble("preco"));
    

        return dadosAux;

    }


    
}
