package com.example.proyectoadbj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Importacion del objeto user desde el login
        Intent desdeLogin = getIntent();
        final Usuario user = desdeLogin.getParcelableExtra("user");

        //Inicialización de elementos de la interfaz.
        TextView lblUserDash = findViewById(R.id.lblUserDash);
        TextView lblNombreDash=findViewById(R.id.lblNombreDash);
        ImageView imgUserDash=findViewById(R.id.imgUserDash);
        lblUserDash.setText(user.getNombre()+" "+user.getApellido());
        lblNombreDash.setText(user.getLogin());
        // Foto del usuario
        //int id = getResources().getIdentifier("com.example.proyectoadbj:drawable/" + user.getPathFoto(), null, null);
        //imgUserDash.setImageResource(id);

        //TabLayout
        TabLayout dashTabLayout = findViewById(R.id.dashTabLayout);
        // dashViewPager es el ViewPager definido en el XML
        final ViewPager dashViewPager = findViewById(R.id.dashViewPager);


        // Obtener el ViewPager y setear su PagerAdapter para que pueda mostrar elementos
        PagerAdapter pagerAdapter = new fragmentPagerAdapter(getSupportFragmentManager(), dashTabLayout.getTabCount(), user.getNombre());
        dashViewPager.setAdapter(pagerAdapter);

        // Asigna el viewpager al viewlayout
        dashTabLayout.setupWithViewPager(dashViewPager);

    }

}
