package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DescriptionReportPrueba extends AppCompatActivity {

    TextView textViewNombre, textViewEstado, textViewFecha, textViewCP, textViewAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_report_prueba);
        findViewById(R.id.atras).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedScreenV3.class);
                startActivity(intent);
                finish();
            }
        });//Creamos objeto de la imagen del boton lateral junto con su evento onClick el cual nos servira para desplegar el menu

        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");
        textViewNombre = findViewById(R.id.namee);
        textViewEstado = findViewById(R.id.estade);
        textViewFecha = findViewById(R.id.feche);
        textViewCP = findViewById(R.id.cpe);
        textViewAnimal = findViewById(R.id.animale);
        textViewNombre.setText(element.getNombre());
        textViewAnimal.setText(element.getAnimal());
        textViewEstado.setText(element.getEstado());
        textViewFecha.setText(element.getFecha());
        textViewCP.setText(element.getCodigoPostal());
    }
}