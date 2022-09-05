package com.example.facturasrojov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNF, btnRC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNF=(Button)findViewById(R.id.btnNF);
        btnNF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CapturaFacturas.class);
                startActivity(i);
            }
        });

        btnRC=(Button)findViewById(R.id.btnRC);
        btnRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegistrarCliente.class);
                startActivity(i);
            }
        });
    }
}