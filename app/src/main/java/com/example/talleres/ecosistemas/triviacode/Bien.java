package com.example.talleres.ecosistemas.triviacode;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Bien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bien);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MainActivity.termino){
                    Intent resultado = new Intent(Bien.this, Resultado.class);
                    startActivity(resultado);
                }else{
                    Intent registro = new Intent(Bien.this, MainActivity.class);
                    startActivity(registro);
                }

            }
        }, 3000);
    }
}
