import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


 public class DAO {


 package com.example.proyectoadbj;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


 public class DAO {


    public DAO(){

 package com.example.proyectoadbj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


 public class DAO {


    public DAO(){


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
            "jdbcsqlserver://addrsql.database.windows.net1433;" 
            "database=addrsql;" 
            "user=addr@addrsql;" 

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
            "jdbcsqlserver://addrsql.database.windows.net1433;" 
            "database=addrsql;" 
            "user=addr@addrsql;" 
            "pass=Isleofman2020";
    private Connection connection;

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
            "jdbcsqlserver://addrsql.database.windows.net1433;" 
            "database=addrsql;" 
            "user=addr@addrsql;" 
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
		
		    public boolean checkExistenceOnDb(String id)



 private String idArchivo;
    private String strRuta;
    private String revLevel;
    private String id;
    private String revLetter;
    private String md5;

    public String getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(String idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getStrRuta() {
        return strRuta;
    }

    public void setStrRuta(String strRuta) {
        this.strRuta = strRuta;
    }

    public String getRevLevel() {
        return revLevel;
    }

    public void setRevLevel(String revLevel) {
        this.revLevel = revLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRevLetter() {
        return revLetter;
    }

    public void setRevLetter(String revLetter) {
        this.revLetter = revLetter;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
