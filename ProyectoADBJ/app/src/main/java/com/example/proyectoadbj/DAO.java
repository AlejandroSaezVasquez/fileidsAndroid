package com.example.proyectoadbj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DAO {


    public DAO(){

    }
    private String conString=
            "jdbc:sqlserver://addrsql.database.windows.net:1433;" +
            "database=addrsql;" +
            "user=addr@addrsql;" +
            "pass=Isleofman2020";
    private Connection connection;
    private ResultSet resultSet;
    private String parametroDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private Statement comando = null;
    private String query;
    private queryDump q = new queryDump();

    private void conectar() {
        try {
            Class.forName(parametroDriver);
            connection = DriverManager.getConnection(conString);
            System.out.println("Conectado a mySql");
        } catch (SQLException e1) {
            System.out.println("Error de conexion SQl");
            System.out.println(e1.getMessage());
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
    } // Cierra el metodo conectar.
    private void desconectar() {
        try {
            connection.close();
            System.out.println("Desconectado de SQL");
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
    }

    public boolean checkExistenceOnDb(String id)
    {

        try {
            conectar();
            comando = connection.createStatement();
            query = q.consultaCheckExistanceOnDb(id);
            resultSet = comando.executeQuery(query);
            if (resultSet.next()) {
                comando.close();
                desconectar();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //errorHandler(EnumMensajes.errorSQL);
        }
        return false;
    }

}
