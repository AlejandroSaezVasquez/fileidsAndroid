package com.example.proyectoadbj;

import android.content.Context;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DAO {
    private Context miContexto;

    public DAO(Context contexto) {
        miContexto = contexto;
    }

    public DAO() {

    }

    //JTDS connection parameters.
    private String dbName = "FILEIDS";
    private String dbUser = "addr";
    private String dbPass = "Isleofman2020";
    private String conUrl = "jdbc:jtds:sqlserver://addrsql.database.windows.net:1433/" + dbName;

    private Connection connection;
    private ResultSet resultSet;
    private ResultSetMetaData rsData;

    private Statement comando = null;
    private String query;
    private queryDump q = new queryDump();


    private void conectar() {
        try {
            //necesario investigar que hacen estas dos lineas
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // end
            connection = DriverManager.getConnection(conUrl, dbUser, dbPass);
            System.out.println("Conectado a SQL SERVER");
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

        String loginResult = singleReturnQuery(q.execLogin(nombreUsuario, passUsuario));
        return loginResult.compareToIgnoreCase("1") == 0;
    }

    // Verificar si el archivo existe en la base de datos.
    public boolean checkExistenceOnDb(String id) {

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
            errorHandler.Toaster(enumMensajes.errorSQL, miContexto);
        }
        return false;
    }

    // Consulta generica "select" que retorna un solo String. 
    public String singleReturnQuery(String query) {

        String result;
        try {
            conectar();

            comando = connection.createStatement();
            resultSet = comando.executeQuery(query);
            if (resultSet.next()) {
                result = resultSet.getString(1);
                comando.close();
                desconectar();
                return result;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorHandler.Toaster(enumMensajes.errorSQL, miContexto);
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

                for (int i = 1; i < rsData.getColumnCount()+1; i++) {
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

    //Consulta generica de select para la primera o unica fila retornada.
    public ArrayList<String> genericOneRowQuery(String query) {

        ArrayList<String> lista = new ArrayList<>();

        try {
            conectar();
            comando = connection.createStatement();
            resultSet = comando.executeQuery(query);
            rsData = resultSet.getMetaData();

            if (resultSet.next()) {
                for (int i = 1; i < rsData.getColumnCount()+1; i++) {
                    lista.add(resultSet.getString(i));
                }
            }
            comando.close();
            desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorHandler.Toaster(enumMensajes.errorSQL, miContexto);
        }
        return lista;

    }


    public Usuario setUser(Usuario user) {

        ArrayList<String> userData = new ArrayList<>();
        userData = genericOneRowQuery(q.execObtenerUsuario(user.getLogin()));

        user.setNombre(userData.get(1));
        user.setApellido(userData.get(2));
        return user;

    }

    public boolean updateRecord(propertyBundle pb) {

        boolean result=false;
        String query=q.execUpdateArchivos(pb);
        try {
            conectar();
            comando = connection.createStatement();
            result=comando.execute(query);
            comando.close();
            desconectar();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorHandler.Toaster(enumMensajes.errorSQL, miContexto);
        }
        return false;
    }

    public propertyBundle addRecord(propertyBundle pb) {

        String query=q.execInsertFileProperties(pb);
        pb.setId(singleReturnQuery(query));
        query=q.execInsertFileProjectAssociations(pb);
        singleReturnQuery(query);
        return pb;
    }

    public propertyBundle getPropertyBundleFromDB(propertyBundle pb) {

        ArrayList<String> fp=new ArrayList<>();
        ArrayList<String> fpa=new ArrayList<>();
        // Propiedades de archivo
        String query = q.execGetFilePropertiesFromId(pb.getId());
        fp=genericOneRowQuery(query);
        pb.setDescriptorEs(fp.get(0));
        pb.setDescriptorEn(fp.get(1));
        pb.setOemsku(fp.get(2));
        pb.setDescriptorExtra(fp.get(3));
        pb.setExtension(fp.get(4));
        pb.setIdExtension(Integer.parseInt(fp.get(5))); //string a integer

        //Asociaciones a proyectos
        query = q.execGetFilePropertiesFromId(pb.getId());
        fpa=genericOneRowQuery(query);

        pb.setIdProyecto(Integer.parseInt(fpa.get(4)));
        pb.setIdTipoEntregable(Integer.parseInt(fpa.get(3)));

        return pb;

    }
}
