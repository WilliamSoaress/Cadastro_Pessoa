package dao;

import database.Database;
import pojo.Person;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPerson implements DAOInterface {

    public static final String SQL_CONSULTAR = "SELECT * FROM PERSON";
    public static final String SQL_CONSULTAR_ID = "SELECT * FROM PERSON WHERE ID_PERSON = ?";
    public static final String SQL_INSERIR = "INSERT INTO PERSON(NAME_PERSON,ADDRESS_PERSON,PHONE_PERSON, CPF_PERSON, EMAIL_PERSON) VALUES (?,?,?,?,?)";
    public static final String SQL_ALTERAR = "UPDATE PERSON SET NAME_PERSON = ?, ADDRESS_PERSON = ?, PHONE_PERSON = ?, CPF_PERSON = ?, EMAIL_PERSON = ? WHERE ID_PERSON = ?";
    public static final String SQL_DELETAR = "DELETE FROM PERSON WHERE ID_PERSON = ?";
    private Person person;
    private String errorMsg;

    public DAOPerson(Person person) {
        this.person = person;
    }

    public boolean inserir() {
        try {
            Connection conexao = Database.getConexao();
            String sql = SQL_INSERIR;
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, person.getName_person());
            ps.setString(2, person.getAddress_person());
            ps.setString(3, person.getPhone_person());
            ps.setString(4, person.getCpf_person());
            ps.setString(5, person.getEmail_person());
            ps.execute();
            return true;
        } catch (SQLException se) {
            this.errorMsg = se.getMessage();
            JOptionPane.showMessageDialog(null, "Não foi possivel incluir a pessoa ao banco\n" + this.errorMsg);
            se.printStackTrace();
            return false;
        }
    }

    public boolean alterar() {
        try {
            Connection conexao = Database.getConexao();
            String sql = SQL_ALTERAR;
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, person.getName_person());
            ps.setString(2, person.getAddress_person());
            ps.setString(3, person.getPhone_person());
            ps.setString(4, person.getCpf_person());
            ps.setString(5, person.getEmail_person());
            ps.setInt(6, person.getId_person());
            ps.execute();
            return true;
        } catch (SQLException se) {
            this.errorMsg = se.getMessage();
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar o cadastro da pessoa informada \n" + this.errorMsg);
            se.printStackTrace();
            return false;
        }
    }

    public boolean excluir() {
        try {
            Connection conexao = Database.getConexao();
            String sql = SQL_DELETAR;
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, person.getId_person());
            ps.execute();
            return true;
        } catch (SQLException se) {
            this.errorMsg = se.getMessage();
            JOptionPane.showMessageDialog(null, "Não foi possivel deletar o cadastro da pessoa \n" + this.errorMsg);
            se.printStackTrace();
            return false;
        }
    }

    public void consultar() {
        try {
            Connection conexao = Database.getConexao();
            PreparedStatement ps = conexao.prepareStatement(SQL_CONSULTAR_ID);
            ps.setInt(1, person.getId_person());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                person.setId_person(rs.getInt("ID_PERSON"));
                person.setName_person(rs.getString("NAME_PERSON"));
                person.setAddress_person(rs.getString("ADDRESS_PERSON"));
                person.setPhone_person(rs.getString("PHONE_PERSON"));
                person.setCpf_person(rs.getString("CPF_PERSON"));
                person.setEmail_person(rs.getString("EMAIL_PERSON"));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Não foi possivel consultar o cadastro selecionado \n" + this.errorMsg);
            se.printStackTrace();
        }
    }
}
