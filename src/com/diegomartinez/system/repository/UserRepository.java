package com.diegomartinez.system.repository;

import com.diegomartinez.system.model.Users;
import java.sql.CallableStatement;
import com.diegomartinez.system.config.ConexionDB;

public class UserRepository implements UserInterface {

    private CallableStatement callSP;
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    @Override
    public void create(Users user) {
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_create_users(?,?,?,?,?)}");
        } catch (Exception e) {
        }
    }
}
