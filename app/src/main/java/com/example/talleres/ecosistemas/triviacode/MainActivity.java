package com.example.talleres.ecosistemas.triviacode;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import Comunicacion.ControlCliente;
import Comunicacion.Observer;


public class MainActivity extends AppCompatActivity implements OnClickListener, Observer {

    private Button btn_opcionA, btn_opcionB, btn_opcionC, btn_opcionD;
    private ControlCliente server;
    static boolean estado = false;
    static String puntuacion = "0";
    static boolean termino = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btn_opcionA = findViewById(R.id.btn_opcionA);
        this.btn_opcionB = findViewById(R.id.btn_opcionB);
        this.btn_opcionC = findViewById(R.id.btn_opcionC);
        this.btn_opcionD = findViewById(R.id.btn_opcionD);

        this.btn_opcionA.setOnClickListener(this);
        this.btn_opcionB.setOnClickListener(this);
        this.btn_opcionC.setOnClickListener(this);
        this.btn_opcionD.setOnClickListener(this);

        server = ControlCliente.getIntance();
        server.setObservador(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_opcionA:
                this.server.enviar("Respuesta;A");

                break;
            case R.id.btn_opcionB:
                this.server.enviar("Respuesta;B");

                break;
            case R.id.btn_opcionC:
                this.server.enviar("Respuesta;C");

                break;
            case R.id.btn_opcionD:
                this.server.enviar("Respuesta;D");

                break;


        }
    }

    public void irAEspera(){
        Intent espera = new Intent(MainActivity.this, Espera.class);
        startActivity(espera);
    }


    String tempMensaje;
    @Override
    public void mensajeRecibido(String mensaje) {
        this.tempMensaje = mensaje;
        Recepcion r = new Recepcion();
        r.execute();
    }


    class Recepcion extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            recibir(tempMensaje);
        }
    }

    public void recibir(String mensaje){
        Toast.makeText(this, mensaje.equals("espera") + " a " + mensaje, Toast.LENGTH_SHORT).show();
        if(mensaje.contains("espera")){
            String[] separar = mensaje.split(";");
            if(separar[1].equals("true")){
                estado = true;

            }else{
                estado = false;
            }
            if(separar[3].equals("true")){
                termino = true;
            }else{
                termino = false;
            }
            puntuacion = separar[2];
            irAEspera();
        }

        if(mensaje.contains("respuesta")){
            String[] separar = mensaje.split(";");
            if(separar[1].equals("true")){
                estado = true;
            }else{
                estado = false;
            }
            puntuacion = separar[2];
            if(separar[3].equals("true")){
                termino = true;
            }else{
                termino = false;
            }
            IrAfeedback(estado);
        }

    }

    public void IrAfeedback(boolean val){
        Intent respuesta;
        if(val){
            respuesta = new Intent(MainActivity.this, Bien.class);

        }else{
            respuesta = new Intent(MainActivity.this, Mal.class);
        }
        startActivity(respuesta);

    }

}
