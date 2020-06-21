package com.example.proyectoadbj;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentUser extends Fragment {

    // Parametros
    private String activeUserName;

    // Constructor para crear instancias de este fragmento
    public static fragmentUser crearFragmentUser(String activeUserName){
        fragmentUser frag=new fragmentUser();
        // Crear un bundle de argumentos para inyectar en el fragmento
        Bundle argumentos=new Bundle();
        // Acá se pueden insertar tantos argumentos como sea necesario en pares key-value
        argumentos.putString("activeUserName",activeUserName);

        // Registrar como argumentos.
        frag.setArguments(argumentos);
        return frag;
    }


    // El bundle savedinstancestate sirve para pasar variables de entorno al fragmento en el momento de su creacion
    // En este caso, se le traspasa el usuario activo.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Desempacar bundle y extraer nombre de usuario activo.
        activeUserName=getArguments().getString("activeUserName");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar fragmento
        View view = inflater.inflate(R.layout.fragment_tab_user, container, false);
        // Inicializar fragmento
        initUserFragment(container,view);

        return view;
    }

    private void initUserFragment(ViewGroup container, View view){


        //Views y controles
        TextView lblNombre=view.findViewById(R.id.lblNombre);
        ImageView imgUserPhoto=view.findViewById(R.id.imgUserPhoto);


        // Viñeta superior

        /*
        DAOSQLITE DAOSQLITE = new DAOSQLITE(container.getContext());
        Usuario user = DAOSQLITE.retrieveUser(activeUserName);
        // Nombre y apellido
        lblNombre.setText(user.getNombres() + " " + user.getApellidos());
        // Foto del usuario
        int id = getResources().getIdentifier("com.example.proyectoadbj:drawable/" + user.getPathFoto(), null, null);
        imgUserPhoto.setImageResource(id);
        // Fechas de plan

        String fechasPlan="Desde: "+user.getSubscripcion().getFechaInicio()+" hasta "
                +user.getSubscripcion().getFechaFin();*/


    }

    public fragmentUser() {

    }
}
