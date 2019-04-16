package com.example.tecsup.spotigo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivitySpotiWilly extends AppCompatActivity {
    Button btn_play;
    Button btn_siguiente;
    Button btn_atras;
    Button btn_random;
    Button btn_enviar;
    Musica musicaseleccionda;
    boolean randomActivado=false;
    PlayLista pl;
    int pos = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_spoti_willy);

        //Asignar boton a un atributo
        btn_play = (Button)findViewById(R.id.btn_play);
        btn_siguiente = (Button)findViewById(R.id.btn_adelantito);
        btn_atras = (Button)findViewById(R.id.btn_atrasito);
        btn_random = (Button)findViewById(R.id.btn_random);
        btn_enviar = (Button)findViewById(R.id.btn_enviarr);

        pl = new PlayLista("LAS ROMANTICAS");


        agregarcanciones();

        setEventos();

    }

    private void agregarcanciones() {
        //** CREAMOS LA CANCION 1 MOLOTOV
        MediaPlayer media1 = MediaPlayer.create(this,R.raw.cutito);
        Musica cancion1 = new Musica(R.drawable.fondocuuto,media1,"MOLOTOV - CUTITOTITOTITO"); //
        //AGRREGAMOS A NUESTRO PLAYLIST
        pl.getLista().add(cancion1);

        //** CREAMOS LA CANCION 2 ROXETE
        MediaPlayer media2 = MediaPlayer.create(this,R.raw.contralapared);
        Musica cancion2 = new Musica(R.drawable.fondocontralapared,media2,"Sean Paul, J Balvin - Contra la Pared"); //
        //AGRREGAMOS A NUESTRO PLAYLIST
        pl.getLista().add(cancion2);

        MediaPlayer media3 = MediaPlayer.create(this,R.raw.cuandomeve);
        Musica cancion3 = new Musica(R.drawable.fondocuandomeve,media3,"Ovi FT. Yendddi + Adriel Favela - Cuando me Ve"); //
        //AGRREGAMOS A NUESTRO PLAYLIST
        pl.getLista().add(cancion3);

    }

    private void setEventos() {

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySpotiWilly.this, ListaReproduccionB.class);
                //Intent intent = new Intent(getApplicationContext(), ListaReproduccionB.class);


                intent.putExtra("Listado", pl);
                //intent.putExtra("Posicion", pos);
                //Toast.makeText(MainActivitySpotiWilly.this, "-->"+pl.getLista().size(), Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ESTA ES LA CANCION SELECCIONADA DEL PLAY LIST
                musicaseleccionda = pl.getLista().get(pos);

                if(musicaseleccionda.getAudio().isPlaying()){
                    pausar();
                }else{
                    reproducir();
                }
            }
        });

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicaseleccionda.getAudio().pause();
                musicaseleccionda.getAudio().seekTo(0);
                 if(pos >= pl.getLista().size()-1){
                     pos = 0;
                 }else{
                     pos++;
                 }

                if (randomActivado){
                    pos = (int)(Math.random() * (pl.getLista().size()-1));
                }
                musicaseleccionda = pl.getLista().get(pos); // hemos cambiado de musica
                reproducir();
            }
        });

        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicaseleccionda.getAudio().pause();
                musicaseleccionda.getAudio().seekTo(0);
                if(pos <= 0){
                    pos = pl.getLista().size()-1;
                }else{
                    pos--;
                }

                if (randomActivado){
                    pos = (int)(Math.random() * (pl.getLista().size()-1));
                }
                musicaseleccionda = pl.getLista().get(pos); // hemos cambiado de musica
                reproducir();

            }
        });

        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (randomActivado){
                    randomActivado=false;
                    Toast.makeText(MainActivitySpotiWilly.this, "Se DESACTIVADO el aleatorio compareee", Toast.LENGTH_SHORT).show();
                    btn_random.setBackgroundResource(R.drawable.random);
                    }else{
                    Toast.makeText(MainActivitySpotiWilly.this, "Se ACTIVO el aleatorio compareee", Toast.LENGTH_SHORT).show();
                    btn_random.setBackgroundResource(R.drawable.randomactivado);
                    randomActivado=true;
                }
            }
        });
    }

    private void reproducir() {
        musicaseleccionda.getAudio().start(); //INICIE LA CANCION

        ImageView imagen = findViewById(R.id.imageView);
        imagen.setImageResource(musicaseleccionda.getPortada());

        Toast.makeText(MainActivitySpotiWilly.this, musicaseleccionda.getTitulo(), Toast.LENGTH_SHORT).show();

        TextView titulo = findViewById(R.id.titulito);
        titulo.setText(musicaseleccionda.getTitulo());

        btn_play.setBackgroundResource(R.drawable.pausito);

    }

    private void pausar() {
        musicaseleccionda.getAudio().pause();
        btn_play.setBackgroundResource(R.drawable.playplay);
        Toast.makeText(MainActivitySpotiWilly.this, "PAUSADO..", Toast.LENGTH_SHORT).show();
    }
}
