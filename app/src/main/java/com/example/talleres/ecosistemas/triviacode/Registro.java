package com.example.talleres.ecosistemas.triviacode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import Comunicacion.ControlCliente.ConexionCliente;
import Comunicacion.ControlCliente;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Registro extends AppCompatActivity implements OnClickListener, ConexionCliente {

    private EditText et_ip;
    private EditText et_apodo;
    private Button btn_iniciar;
    private ControlCliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cargarFuentes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.et_ip = findViewById(R.id.et_ip);
        this.et_apodo = findViewById(R.id.et_apodo);
        this.btn_iniciar = findViewById(R.id.btn_iniciar);

        this.btn_iniciar.setOnClickListener(this);

        cliente = ControlCliente.getIntance();
        cliente.setNotificador(this);

    }

    public static void cargarFuentes() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/inconsolata_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_iniciar:
                String ip = et_ip.getText().toString();
                String apodo = et_apodo.getText().toString();
                this.cliente.iniciar(ip);
                Toast.makeText(this, "Intento: " + ip, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void notificacionCliente(boolean exito) {
        if(exito){
            Intent actividad = new Intent(Registro.this, MainActivity.class);
            startActivity(actividad);
        }else{
            Toast.makeText(this, "Lo siento su ip es incorrecta, Por favor revisar", Toast.LENGTH_SHORT).show();
            et_ip.setError("Ip no valida");
            et_ip.setText("");
        }


    }
}
