package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
        txUser = findViewById(R.id.txUser);
        txPassword = findViewById(R.id.txPassword);


        //Desplegar toast en caso de que no se llenen algunos campos

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(MainActivity.this);
                Usuario user=new Usuario();
                user.setLogin(txUser.getText().toString().trim());
                user.setPassword(txPassword.getText().toString().trim());
                user=dao.setUser(user);

                if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
                    //No ingresa user
                    errorHandler.Toaster(enumMensajes.loginError, MainActivity.this);
                } else {

                    if (dao.authLogin(user.getLogin(),user.getPassword())){
                        // Login succesful, limpiar pantalla.
                        txUser.setText("");
                        txPassword.setText("");
                        Intent aDashboard = new Intent(MainActivity.this, Dashboard.class);

                        aDashboard.putExtra("user", user);
                        startActivity(aDashboard);
                    }else{
                        // Usuario no existe.
                        errorHandler.Toaster(enumMensajes.loginError, MainActivity.this);
                    }

                }
            }
        });

    }
}

