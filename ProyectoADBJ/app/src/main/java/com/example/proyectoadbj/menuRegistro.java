package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Stack;

public class menuRegistro extends AppCompatActivity {

    private TextView txNombres;
    private TextView txApellidos;
    private TextView txPassword;
    private TextView txRepetirPassword;
    private TextView txUserNameRegistro;
    private Button btAceptarSub;
    private Button btCancelarSub;
    private RadioGroup rgGenero;
    private Spinner spPagoPlanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txNombres = findViewById(R.id.txNombreRegistro);
        txApellidos = findViewById(R.id.txApellidosRegistro);
        txUserNameRegistro = findViewById(R.id.txUserNameRegistro);
        txPassword = findViewById(R.id.txPasswordRegistro);
        txRepetirPassword = findViewById(R.id.txRepetirPassword);
        btAceptarSub = findViewById(R.id.btAceptarSub);
        btCancelarSub = findViewById(R.id.btCancelarSub);
        rgGenero = findViewById(R.id.rgGenero);
        spPagoPlanes = findViewById(R.id.spPagoPlanes);

        //Acceso a BD
        final DAOSQLITE DAOSQLITE = new DAOSQLITE(menuRegistro.this);

        // Llenar spinner de planes
        UIHelpers.fillSpinner(spPagoPlanes, DAOSQLITE.getPlanes(), menuRegistro.this);

        btAceptarSub.setOnClickListener(new View.OnClickListener() {
            Stack<enumMensajes> stackErrores = new Stack<>();

            @Override
            public void onClick(View v) {

                String password = txPassword.getText().toString();
                String rePassword = txRepetirPassword.getText().toString();
                String username = txUserNameRegistro.getText().toString().trim();

                if (txNombres.getText().toString().trim().isEmpty()) {
                    stackErrores.add(enumMensajes.sinNombre);
                } else {
                    stackErrores.remove(enumMensajes.sinNombre);
                }
                if (txApellidos.getText().toString().trim().isEmpty()) {
                    stackErrores.add(enumMensajes.sinApellido);
                } else {
                    stackErrores.remove(enumMensajes.sinApellido);
                }
                if (username.isEmpty()) {
                    stackErrores.add(enumMensajes.sinUserName);
                } else {
                    stackErrores.remove(enumMensajes.sinUserName);
                }
                if (password.isEmpty()) {
                    stackErrores.add(enumMensajes.sinPassword);
                } else {
                    stackErrores.remove(enumMensajes.sinPassword);
                }
                if (password.compareTo(rePassword) != 0) {
                    stackErrores.add(enumMensajes.passwordNoCoincide);
                } else {
                    stackErrores.remove(enumMensajes.passwordNoCoincide);
                }

                if (stackErrores.empty()) {

                    // Verificacion de usuario existente
                    if (DAOSQLITE.authLogin(username, password)) {
                        // User existe.
                        errorHandler.Toaster(enumMensajes.usuarioYaExiste, menuRegistro.this);
                    } else {
                        // Todo ok para registrar al usuario, pues no existe

                        Usuario user = new Usuario();

                        user.setNombres(txNombres.getText().toString().trim());
                        user.setApellidos(txApellidos.getText().toString().trim());
                        user.setGenero(UIHelpers.getSelectedRadioText(rgGenero));
                        user.setEmail(txUserNameRegistro.getText().toString().trim());
                        user.setPassword(txRepetirPassword.getText().toString().trim());
                        user.setUsername(txUserNameRegistro.getText().toString().trim());
                        user.setPathFoto("p0");

                        // Asignar plan al usuario
                        int p=spPagoPlanes.getSelectedItemPosition();
                        System.out.println(p);
                        user.setSubscripcion(DAOSQLITE.getPlanFromId(p));

                        System.out.println("Registrando: "+user.getSubscripcion().getNombrePlan());

                        // Ir al menu de pago para terminar subscripcion
                        Intent aMenuPago = new Intent(menuRegistro.this, menuPago.class);
                        aMenuPago.putExtra("user", user);
                        startActivity(aMenuPago);
                    }
                } else {
                    errorHandler.Toaster(stackErrores.lastElement(), menuRegistro.this);
                }
            }
        });

        btCancelarSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent aMenuLogin = new Intent(menuRegistro.this, MainActivity.class);
                startActivity(aMenuLogin);
            }
        });

    }
}
