package com.example.proyectoadbj;

public class propertyBundle {

    // Bundle de propiedades personalizadas.
    private DAO dao = new DAO();

    public propertyBundle(){

    }

    // Variables de campos de texto en el formulario
    private String id;
    private String oemsku;
    private String descriptorEn;
    private String descriptorEs;
    private String descriptorExtra;

    //id campos combobox
    private int idExtension;
    private int idTipoEntregable;
    private int idProyecto;


    // Valores campos combobox (desde BD)
    private String extension;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOemsku() {
        return oemsku;
    }

    public void setOemsku(String oemsku) {
        this.oemsku = oemsku;
    }

    public String getDescriptorEn() {
        return descriptorEn;
    }

    public void setDescriptorEn(String descriptorEn) {
        this.descriptorEn = descriptorEn;
    }

    public String getDescriptorEs() {
        return descriptorEs;
    }

    public void setDescriptorEs(String descriptorEs) {
        this.descriptorEs = descriptorEs;
    }

    public String getDescriptorExtra() {
        return descriptorExtra;
    }

    public void setDescriptorExtra(String descriptorExtra) {
        this.descriptorExtra = descriptorExtra;
    }

    public int getIdExtension() {
        return idExtension;
    }

    public void setIdExtension(int idExtension) {
        this.idExtension = idExtension;
    }

    public int getIdTipoEntregable() {
        return idTipoEntregable;
    }

    public void setIdTipoEntregable(int idTipoEntregable) {
        this.idTipoEntregable = idTipoEntregable;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
        
    }

    public String getNombreEntregable()
    {
        return getFormattedID();
    }
    public String getNombreArchivo()
    {
        String nombre = getFormattedID() + " - " + descriptorEs;
        return nombre;
    }
    public String getFormattedID()
    {
        if (id!=null)
        {
            //return id.PadLeft(6, '0').Insert(3, "-");  Reemplazar esta funcion.
        }
        return "";


    }
}
