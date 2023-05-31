package com.example.login;

import androidx.annotation.Nullable;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    //Declaracion de variables
    EditText edtUsuario, edtContra;
    Button btnLogin, btnRegister;
    RequestQueue requestQueue;
    ArrayList<String> datoUsu = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //creacion de variables
        edtUsuario = findViewById(R.id.username);
        edtContra = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginbtn);
        btnRegister = findViewById(R.id.registerbtn);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario("https://bdmascota.000webhostapp.com/php/validaUsuario.php");
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this,RegistroScreen.class);
                startActivity(intent);
            }
        });

    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.isEmpty()){
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                datoUsu.add(jsonObject.getString("id"));
                                datoUsu.add(jsonObject.getString("usuario"));
                                datoUsu.add(jsonObject.getString("contra"));
                                datoUsu.add(jsonObject.getString("nombre"));
                                datoUsu.add(jsonObject.getString("apellidos"));
                                datoUsu.add(jsonObject.getString("tipo"));
                                datoUsu.add(jsonObject.getString("correo"));
                                datoUsu.add(jsonObject.getString("numCel"));
                                System.out.println(datoUsu.get(1).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            Intent intent = new Intent(getApplicationContext(), FeedScreenV3.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginScreen.this,"Usuario o contra incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("usuario", edtUsuario.getText().toString());
                params.put("contra", edtContra.getText().toString());
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public ArrayList<String> getDatoUsu() {
        return datoUsu;
    }
}