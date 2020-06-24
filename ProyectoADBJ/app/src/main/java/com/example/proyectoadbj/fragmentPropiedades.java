package com.example.proyectoadbj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class fragmentPropiedades extends AppCompatActivity {

    private EditText txtBusquedaPropiedades;
    private ImageButton btLimpiar, btbus;

    public fragmentPropiedades() {
        // Required empty public constructor
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_propiedades);

        // Instanciar botones y controles
        txtBusquedaPropiedades = findViewById(R.id.txtBusquedaPropiedades);
        btLimpiar = findViewById(R.id.btLimpiar);
        btbus = findViewById(R.id.btbus);
    }

    //Desplegar toast en caso de que no se llenen algunos campos

    public void onClick(View v) {
        DAO dao = new DAO(fragmentPropiedades.this);
    }
}//Cierra la Clase fragmentPropiedades.
