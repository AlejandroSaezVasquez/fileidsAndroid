package com.example.proyectoadbj;

public class queryDump {

    public queryDump() {
    }

    public String viewPropiedadesArchivos="select * from viewPropiedadesArchivos order by ID desc";
    public String viewnNombresArchivos="select * from nombres_archivos order by 'nombre archivo'  desc";
    public String viewNombresExtensiones="select * from viewNombresExtensiones";
    public String viewNombresTiposEntregables="select * from viewNombresTiposEntregables";


    //Esto no es un view pero puede serlo.
    public String nombresProyectos="select upper(descriptores) from proyectos";
    public String viewNombresArchivosEntregables="select*from archivos";
    
    public String execProyectoDesdeId(String id)
    {
         return "exec getNombreProyecto '" + id + "'";
    }
    public String execNombreArchivoDesdeId(String id)
    {
         return "exec getNombreArchivo '" + id + "'";
    }
    public String execNombreEntregableDesdeId(String id)
    {
         return "exec getNombreEntregable '" + id + "'";
    }
    public String execGetBundleDesdeId(String id)
    {
         return "exec getbundle '" + id + "'";
    }

    public String execGetFilePropertiesFromId(String id)
    {
         return "exec getFilePropertiesFromId '" + id + "'";
    }

    public String execGetFileProjectAssociationFromId(String id)
    {
         return "exec getFileProjectAssociationFromId '" + id + "'";
    }

    public String execInsertFileProperties(propertyBundle pb)
    {
         return "exec InsertFileProperties '" +
            pb.getDescriptorEs() + "','" +
            pb.getDescriptorEn() + "','" +
            pb.getOemsku() + "'," +
            pb.getIdExtension() + ",'" +
            pb.getDescriptorExtra() + "'";
    }

    public String execInsertFileProjectAssociations(propertyBundle pb)
    {
         return "exec insertFileProjectAssociations '" + pb.getId() + "','" + pb.getIdProyecto() + "','" + pb.getIdTipoEntregable() + "'";
    }

    public String execUpdateArchivos(propertyBundle pb)
    {
         return "exec updatebundle '" + pb.getId() +
            "','" + pb.getDescriptorEs() +
            "','" + pb.getDescriptorEn() +
            "','" + pb.getOemsku() +
            "','" + pb.getDescriptorExtra() +
            "','" + pb.getIdExtension() +
            "','" + pb.getIdTipoEntregable() +
            "','" + pb.getIdProyecto() + "'";
    }

    public String execGetNombreEntregableDesdeId(String id)
    {
         return "exec getNombreEntregable '" + id + "'";
    }

    public String execCargarRuta(Ruta ruta)
    {
         return "exec cargarRuta '" + ruta.getIdArchivo() + "','" + ruta.getStrRuta() + "','" + ruta.getRevLevel() + "','" + ruta.getMd5() + "'";
    }

    public String execGetRevisionLevelFromId(String id)
    {
         return "exec GetRevisionLevelFromId '" + id + "'";
    }

    public String execBorrarRuta(Ruta ruta)
    {
         return "exec borrarRuta '" + ruta.getIdArchivo() + "','" + ruta.getRevLevel() + "'";
    }

    public String execGetRutaDesdeId(Ruta ruta)
    {
         return "exec obtenerRutaDesdeId '" + ruta.getIdArchivo()+ "','" + ruta.getRevLevel() + "'";
    }

    public String execGetArchivosLocalesLike(String keywords)
    {
         return "exec buscarArchivosLocalesLike '" + keywords + "'";
    }

    //Convertir a procedimiento almacenado execIdFromExtension
    public String consultaIdFromExtension(String extension)
    {
         return "select id from extensiones where extension like '%" + extension + "'";
    }

    //Convertir a procedimiento almacenado execExtensionFromId
    public String consultaExtensionFromId(String id)
    {
         return "select extension from extensiones where id='" + id + "'";
    }

    //Convertir a procedimiento almacenado execConsultaBusqueda
    public String consultaPropiedadesLike(String keywords)
    {
         return "exec buscarPropiedadesLike '" + keywords + "'";
    }

    //Convertir a procedimiento almacenado execCheckExistanceOnDb
    public String consultaCheckExistanceOnDb(String id)
    {
         return "select count(*) id from archivos where id ='" + id + "'";
    }




}