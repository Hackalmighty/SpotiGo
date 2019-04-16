package com.example.tecsup.spotigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ListaReproduccionB extends AppCompatActivity {

    Bundle recibir;
    PlayLista listarecibida;
    int pos_recibida;
    LinearLayout listado;

    TextView titulo;
    PlayLista las_romanticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_lista);
        titulo = findViewById(R.id.txt_titulo);

        las_romanticas = (PlayLista) getIntent().getExtras().getSerializable("Listado");
        titulo.setText(las_romanticas.getNombre());


        //sacar del view  y pasar a java
        //listado = ScrollView
        listado = findViewById(R.id.VerticalLayout);

        agregarCanciones();

        //Intent intent = getIntent();
       // recibir = intent.getExtras();
       // listarecibida = (PlayLista)recibir.getSerializable("Lista");
       // pos_recibida = (int)recibir.get("Posicion");
    }

    private void agregarCanciones() {
        for(int i=0; i<las_romanticas.getLista().size(); i++){
            Button botonPlay = new Button(this);
            botonPlay.setText(las_romanticas.getLista().get(i).titulo);
            botonPlay.setBackgroundResource(0);

            listado.addView(botonPlay);
        }

    }


}
