package br.usuario.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ValidaUsuario {
    private Connection conexao;
    ValidaUsuario(Connection conexao){
        this.conexao = conexao;
    }
    public boolean validaUsuario(String usuario, String senha) {
        String sql = "select * from usuario where usuario like '" + usuario + "' and senha like '" +senha + "';";
        PreparedStatement p;

        try{
            p = this.conexao.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
