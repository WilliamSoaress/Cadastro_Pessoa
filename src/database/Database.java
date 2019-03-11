package database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Connection conexao;
    private static String user;

    public static void main(String args[]) {
        getConexao();
    }
    //Nunca colocque um try com um catch vazio

    public static Connection getConexao() {

        try {
            existeConexao(conexao);
            if (conexao == null) {
                Class.forName("org.firebirdsql.jdbc.FBDriver");
                conexao = DriverManager.getConnection("jdbc:firebirdsql://localhost/C:/Users/Halef Dorigan/IdeaProjects/Cadastro_Pessoa/src/database/DBPROD.FDB", "SYSDBA","masterkey");
                existeConexao(conexao);
            }
            return conexao;
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Problema com o Driver");
            cnfe.printStackTrace();
            return null;

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Problema de conexão com Banco de Dados");
            se.printStackTrace();
            return null;
        }
    }

    public static List<String[]> consultaBanco(String sql) {
        List<String[]> retorno = new ArrayList();

        try {
            Statement st = getConexao().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String[] linha = new String[rs.getMetaData().getColumnCount()];
                for (int coluna = 1; coluna <= rs.getMetaData().getColumnCount(); coluna++) {
                    linha[coluna - 1] = rs.getString(coluna);
                }
                retorno.add(linha);
            }
            return retorno;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar o Banco de Dados");
            e.printStackTrace();
            return null;
        }
    }

    /**Tem a função de verificar a existência de uma conexão ativa.
     *
     * @param con   Recebe uma objeto Connection
     * @return true Se tiver uma conexão ativa
     */
    public static boolean existeConexao(Connection con) {
        if(con != null){
            System.out.println("Existe uma conexão ativa!");
            return true;
        } else {
            System.out.println("Não existe conexão criada!");
            return false;
        }
    }

    public static void fechaConexao(Connection con){
        con = null;
        System.out.println("Conexão com" + con + " encerrada");
    }
}