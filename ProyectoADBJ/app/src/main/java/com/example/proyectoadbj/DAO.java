package com.example.proyectoadbj;

import android.content.Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DAO {
    private Context miContexto;

    public DAO(Context contexto){
        miContexto=contexto;
    }
    public DAO(){

    }
    private String conString=
            "jdbc:sqlserver://addrsql.database.windows.net:1433;" +
            "database=addrsql;" +
            "user=addr@addrsql;" +
            "pass=Isleofman2020";
    private Connection connection;
    private ResultSet resultSet;
    private ResultSetMetaData rsData;
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

    public boolean authLogin(String nombreUsuario, String passUsuario) {
        // Metodo de login mediante DB, debe ser implementado.

        String loginResult= singleReturnQuery(q.execLogin(nombreUsuario,passUsuario));
        return loginResult.compareToIgnoreCase("1")==1;
    }

    // Verificar si el archivo existe en la base de datos.
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
            errorHandler.Toaster(enumMensajes.errorSQL,miContexto);
        }
        return false;
    }

    // Consulta generica "select" que retorna un solo String. 
    public String singleReturnQuery(String query)
    {
        try {
            conectar();
            comando = connection.createStatement();
            resultSet = comando.executeQuery(query);
            if (resultSet.next()) {
                comando.close();
                desconectar();
                resultSet.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorHandler.Toaster(enumMensajes.errorSQL,miContexto);
        }
        return "";
    }

    // Consulta generica "select" que retorna un arreglo bidimensional. Esto es mejorable con otra estructura bidimensional mas ad hoc.
    public ArrayList<ArrayList<String>> genericBidimensionalQuery(String query) {

        ArrayList<ArrayList<String>> lista = new ArrayList<>();
        ArrayList<String> fila = new ArrayList<>();

        try {
            conectar();
            comando = connection.createStatement();
            resultSet = comando.executeQuery(query);
            rsData = resultSet.getMetaData();


            while (resultSet.next()) {

                for (int i = 1; i < rsData.getColumnCount(); i++) {
                    fila.add(resultSet.getString(i));
                }
                lista.add(fila);
                fila = null;
            }
            comando.close();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorHandler.Toaster(enumMensajes.errorSQL, miContexto);
        }
        return lista;

    }

    // Consulta genérica "select" que retorna un arraylist de strings.
    public ArrayList<String> genericOneDimensionalQuery(String query) {

        ArrayList<String> lista = new ArrayList<>();

        try {
            conectar();
            comando = connection.createStatement();
            resultSet = comando.executeQuery(query);
            while (resultSet.next()) {
                lista.add(resultSet.getString(1));
            }
            comando.close();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorHandler.Toaster(enumMensajes.errorSQL, miContexto);
        }
        return lista;

    }


}
