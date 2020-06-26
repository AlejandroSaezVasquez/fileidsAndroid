package com.example.proyectoadbj;

public class FileManager {

    // Directorios principales de sistema de archivos.

    DAO dao = new DAO();
    queryDump q = new queryDump();
    private String oemPath;
    private String productosPath;
    private String proyectosPath;
    private String cachePath;

    public FileManager() {
    }

    public FileManager(DAO dao, queryDump q, String oemPath, String productosPath, String proyectosPath, String cachePath) {
        this.dao = dao;
        this.q = q;
        this.oemPath = oemPath;
        this.productosPath = productosPath;
        this.proyectosPath = proyectosPath;
        this.cachePath = cachePath;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public queryDump getQ() {
        return q;
    }

    public void setQ(queryDump q) {
        this.q = q;
    }

    public String getOemPath() {
        return oemPath;
    }

    public void setOemPath(String oemPath) {
        this.oemPath = oemPath;
    }

    public String getProductosPath() {
        return productosPath;
    }

    public void setProductosPath(String productosPath) {
        this.productosPath = productosPath;
    }

    public String getProyectosPath() {
        return proyectosPath;
    }

    public void setProyectosPath(String proyectosPath) {
        this.proyectosPath = proyectosPath;
    }

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    // Constructor

    public FileManager(String oempath, String productospath, String proyectospath, String cachepath)
    {
        oemPath = oempath;
        productosPath = productospath;
        proyectosPath = proyectospath;
        cachePath = cachepath;

    }

}//Cierra la Clase FileManger.
