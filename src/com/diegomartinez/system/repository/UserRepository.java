package com.diegomartinez.system.repository;
 
import com.diegomartinez.system.model.User;
import java.sql.CallableStatement;
import com.diegomartinez.system.config.ConexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class UserRepository implements UserInterface {
 
    private CallableStatement callSP;
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();
 
    @Override
    public void create(User user) {
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_create_users(?,?,?,?,?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            callSP.execute();
            callSP.close();
        } catch (SQLException e) {
            System.out.println("Error al crear usuario repository");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public User findByUserAndPassword(String user, String password) {
        User userEncontrado = null;
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_login_user(?,?)}");
            callSP.setString(1, user);
            callSP.setString(2, password);
            ResultSet resultado = callSP.executeQuery();
            if (resultado.next()) {
                userEncontrado = new User();
                userEncontrado.setName(resultado.getString("name"));
                userEncontrado.setLastname(resultado.getString("lastname"));
                userEncontrado.setEmail(resultado.getString("email"));
                userEncontrado.setUser(resultado.getString("user"));
                userEncontrado.setPassword(resultado.getString("password"));
                userEncontrado.setIdUser(resultado.getString("id_user"));
            }
            callSP.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario repository");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return userEncontrado;
    }
}