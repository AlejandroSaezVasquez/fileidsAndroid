package com.example.proyectoadbj;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class fragmentRegistro extends AppCompatActivity {

    private EditText txID,txDescriptores,txDescriptoren,txOemsku,txdescriptorExtra,txnomArchivo,txnomEntregable;
    private Spinner spExtesionWin,spTipoDocumentos,spProyecto;
    private Button btlimpiar,btmoficar,btregistro,btAceptar,btCancelar;

    public fragmentRegistro() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_registro);

        // Instanciar botones y controles
        txID = findViewById(R.id.txID);
        txDescriptores = findViewById(R.id.txDescriptores);
        txDescriptoren = findViewById(R.id.txDescriptoren);
        txOemsku = findViewById(R.id.txOemsku);
        txdescriptorExtra = findViewById(R.id.txdescriptorExtra);
        txnomArchivo = findViewById(R.id.txnomArchivo);
        txnomEntregable = findViewById(R.id.txnomEntregable);
        spExtesionWin = findViewById(R.id.spExtesionWin);
        spTipoDocumentos = findViewById(R.id.spTipoDocumentos);
        spProyecto = findViewById(R.id.spProyecto);
        btlimpiar = findViewById(R.id.btlimpiar);
        btmoficar = findViewById(R.id.btmoficar);
        btregistro = findViewById(R.id.btregistro);
        btAceptar = findViewById(R.id.btAceptar);
        btCancelar = findViewById(R.id.btCancelar);
    }

    //Desplegar toast en caso de que no se llenen algunos campos

    public void onClick(View v) {
            DAO dao = new DAO(fragmentRegistro.this);
    }

}//Cierra la Clase fragmentRegistro.

