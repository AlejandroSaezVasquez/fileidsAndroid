package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText txUser;
    private EditText txPassword;
    private TextView txRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciar botones y controles

        btLogin = findViewById(R.id.btLogin);
        txRegistrarse = findViewById(R.id.txRegistrarse);
        txUser = findViewById(R.id.txUser);
        txPassword = findViewById(R.id.txPassword);


        //Desplegar toast en caso de que no se llenen algunos campos

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAOSQLITE DAOSQLITE = new DAOSQLITE(MainActivity.this);

                String nombreUsuario=txUser.getText().toString().trim();
                String passUsuario=txPassword.getText().toString().trim();

                if (nombreUsuario.isEmpty()) {
                    //No ingresa user
                    errorHandler.Toaster(enumMensajes.sinNombre, MainActivity.this);
                } else if (passUsuario.isEmpty()) {
                    // No ingresa password
                    errorHandler.Toaster(enumMensajes.sinPassword, MainActivity.this);
                } else {

                    if (DAOSQLITE.authLogin(nombreUsuario,passUsuario)){
                        // Login succesful
                        txUser.setText("");
                        txPassword.setText("");
                        Intent aDashboard = new Intent(MainActivity.this, Dashboard.class);
                        // Obtener un objeto con el usuario logeado para transferirlo al dashboard
                        aDashboard.putExtra("user", DAOSQLITE.retrieveUser(nombreUsuario));
                        startActivity(aDashboard);
                    }else{
                        // Usuario no existe.
                        errorHandler.Toaster(enumMensajes.loginError, MainActivity.this);
                    }

                }
            }
        });

        txRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMenuSubs = new Intent(MainActivity.this, menuRegistro.class);
                startActivity(aMenuSubs);
            }
        });


    }
}

