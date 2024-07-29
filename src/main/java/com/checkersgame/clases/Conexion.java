package com.checkersgame.clases;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    
    public static Connection conectar(){
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_partidas", "root", "");
            return cn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se han presentado problemas para establecer conexión con la base de datos " + e);
        }
        return (null);
    }
    
}
