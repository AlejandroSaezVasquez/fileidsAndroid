package com.example.proyectoadbj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentPropiedades extends Fragment {

    EditText txtBusquedaPropiedades;
    ImageButton btLimpiar,btBuscar;

    public fragmentPropiedades() {
        // Required empty public constructor
    }

    // Parametros
    private String activePropiedades;

    // Constructor para crear instancias de este fragmento
    public static fragmentPropiedades crearFragmentPropiedades(String activePropiedades) {
        fragmentPropiedades frag = new fragmentPropiedades();
        // Crear un bundle de argumentos para inyectar en el fragmento
        Bundle argumentos = new Bundle();
        // Acá se pueden insertar tantos argumentos como sea necesario en pares key-value
        argumentos.putString("activePropiedades", activePropiedades);
        // Registrar como argumentos.
        frag.setArguments(argumentos);
        return frag;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Desempacar bundle y extraer nombre de usuario activo.
        activePropiedades=getArguments().getString("activePropiedades");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar fragmento
        View view = inflater.inflate(R.layout.fragment_tab_propiedades, container, false);
        // Inicializar fragmento
        initPropiedadesFragment(container,view);

        return view;
    }

    private void initPropiedadesFragment(ViewGroup container, View view){
        //Views y controles
        TextView txtBusquedaPropiedades=view.findViewById(R.id.txtBusquedaPropiedades);
        btLimpiar= (ImageButton) view.findViewById(R.id.btLimpiar);
        btBuscar= (ImageButton) view.findViewById(R.id.btBuscar);


    }

}
