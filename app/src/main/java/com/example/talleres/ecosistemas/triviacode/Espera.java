package com.example.talleres.ecosistemas.triviacode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Comunicacion.ControlCliente;
import Comunicacion.Observer;
import pl.droidsonroids.gif.GifImageView;

public class Espera extends AppCompatActivity implements Observer {

    GifImageView anim_espera;
    ControlCliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera);
        this.anim_espera = findViewById(R.id.anim_espera);

        this.cliente = ControlCliente.getIntance();
        this.cliente.setObservador(this);

    }

    @Override
    public void mensajeRecibido(String mensaje) {
        if(mensaje.contains("siguiente")){
            IrAfeedback(MainActivity.estado);
        }


    }

    public void IrAfeedback(boolean val){
        Intent respuesta;
        if(val){
            respuesta = new Intent(Espera.this, Bien.class);

        }else{
            respuesta = new Intent(Espera.this, Mal.class);
        }
        startActivity(respuesta);

    }
}
