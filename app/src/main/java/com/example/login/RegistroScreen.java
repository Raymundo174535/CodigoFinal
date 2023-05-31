package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroScreen extends AppCompatActivity {

    EditText txtNombre;
    EditText txtApellidos;
    EditText txtUsuario;
    EditText txtCorreo;
    EditText txtContra;
    EditText txtNumero;
    EditText txtCcorreo;
    EditText txtCcontra;
    Button btnReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtNumero = (EditText) findViewById(R.id.txtNumero);
        txtContra = (EditText) findViewById(R.id.txtContra);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtCcorreo = (EditText) findViewById(R.id.txtCconfirma);
        txtCcontra = (EditText) findViewById(R.id.txtContraConf);
        btnReg = (Button) findViewById(R.id.btnRegistrar);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCorreo.getText().toString().equals(txtCcorreo.getText().toString()) && txtContra.getText().toString().equals(txtCcontra.getText().toString())){
                    ejecutarServicio("https://bdmascota.000webhostapp.com/php/crearUsuario.php");
                }
                else{
                    Toast.makeText(getApplicationContext(), "El correo o la contraseña no coinciden", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistroScreen.this,LoginScreen.class);
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
                parametros.put("usuario",txtUsuario.getText().toString());
                parametros.put("contra",txtContra.getText().toString());
                parametros.put("nombre",txtNombre.getText().toString());
                parametros.put("apellidos",txtApellidos.getText().toString());
                parametros.put("tipo","usuario");
                parametros.put("correo",txtCorreo.getText().toString());
                parametros.put("numCel",txtNumero.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}