package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreacionDeReportesPrueba extends AppCompatActivity {
    String aux;
    Spinner animal,raza,color;
    EditText txtNombre,txtTamaño,txtCalle,txtColonia,txtCodigoPostal,txtDescripcion,txtAnimal,txtRaza,txtColor;
    Button btnCrear;
    ArrayList<String> datoUsu = new ArrayList<String>();
    Date fechaActual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String fecha = formato.format(fechaActual);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_de_reportes_prueba);
        txtNombre = (EditText) findViewById(R.id.txtNombree);
        txtTamaño = (EditText) findViewById(R.id.txtTamañe);
        txtCalle = (EditText) findViewById(R.id.txtCallee);
        txtColonia = (EditText) findViewById(R.id.txtColonie);
        txtCodigoPostal = (EditText) findViewById(R.id.txtCP);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        txtAnimal = (EditText) findViewById(R.id.txtAnimal);
        txtRaza = (EditText) findViewById(R.id.txtRaza);
        txtColor= (EditText) findViewById(R.id.txtColor);
        btnCrear = (Button) findViewById(R.id.savebtn);

        findViewById(R.id.atrase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedScreenV3.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.savebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("https://bdmascota.000webhostapp.com/php/crearReporte.php");
            }
        });
    }

    public void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FeedScreenV3.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("animal",txtAnimal.getText().toString());
                parametros.put("color",txtColor.getText().toString());
                parametros.put("tamano",txtTamaño.getText().toString());
                parametros.put("estatus","perdido");
                parametros.put("descripcion",txtDescripcion.getText().toString()+ "Raza: " + txtRaza.getText());
                parametros.put("ubicacion",txtCalle.getText().toString()+" "+txtColonia.getText().toString()+" "+txtCodigoPostal.getText().toString());
                parametros.put("estado","vivo");
                parametros.put("idUsuario","0");
                parametros.put("fechaEncontrado",fecha);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}