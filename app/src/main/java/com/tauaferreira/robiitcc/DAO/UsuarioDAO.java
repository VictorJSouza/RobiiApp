package com.tauaferreira.robiitcc.DAO;

import com.tauaferreira.robiitcc.Utils.SQLDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static Usuario getUsuario(String username) {
        Usuario user = new Usuario();
        String query = "Select * from USERS WHERE USERNAME = ?;";
        ResultSet res;
        try {
            Connection conn = SQLDatabaseConnection.conectar();
            if( conn == null) return null;
            PreparedStatement pst =  conn.prepareStatement(query);
            pst.setString(1, username);
            res = pst.executeQuery();


            while(res.next()){

            user.setUsername(res.getString(1));
            user.setEmail(res.getString(2));
            user.setPassword(res.getString(3));
            user.setId(res.getString(4));
            user.setBithdate(res.getString(5));
            user.setName(res.getString(6));
            }
        } catch (SQLException sqlException){
            return null;
        }
        return user;
    }

    public static boolean verificarUsuario(String username, String password) {
        Usuario user;
        user = getUsuario(username);
        if(user == null || user.getPassword() == null) return false;
        return user.getPassword().equals(password);

    }

    public static boolean registrarUsuario(Usuario user) {
        String query = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) values (?, ?, ?);";
        try {
            Connection conn = SQLDatabaseConnection.conectar();
            if( conn == null) return false;
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());

            pst.execute();

        } catch (SQLException sqlException){
            return false;
        }
        return true;
    }

}
