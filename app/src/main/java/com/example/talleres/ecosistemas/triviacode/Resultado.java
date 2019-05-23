package com.example.talleres.ecosistemas.triviacode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    private TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        this.tv_resultado = findViewById(R.id.tv_resultado);
        this.tv_resultado.setText(MainActivity.puntuacion);
    }
}
