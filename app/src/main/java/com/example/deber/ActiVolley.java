package com.example.deber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.deber.modeloC.revista;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ActiVolley extends AppCompatActivity {


    List<String> datos =new ArrayList<String>();
    private TextView textView;
    TextView txtBuscador;
    Button btnBuscarRevista2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_volley);
        textView = findViewById(R.id.textView);
        btnBuscarRevista2=findViewById(R.id.button2);
        txtBuscador=findViewById(R.id.editTextTextPersonName);
        btnBuscarRevista2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBuscador= findViewById(R.id.editTextTextPersonName);
                obtenerDatosConVolley(txtBuscador.getText().toString());
            }
        });
        textView.setText("Por favor ingrese un número para buscar la revista");
        //ListdatosView=(ListView)findViewById(r)

    }
    private void obtenerDatosConVolley(String codigo)
    {
        textView.setText(" ");
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+codigo+"";
        // Request a string response from the provided URL.
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0)
                        {
                            datos.clear();
                            for (int i=0;i<response.length();i++)
                            {
                                try {
                                    JSONObject Obj=response.getJSONObject(i);
                                    datos.add("issue_id: "+Obj.get("issue_id").toString());
                                    datos.add("volume: "+Obj.get("volume").toString());
                                    datos.add("number: "+Obj.get("number").toString());
                                    datos.add("year: "+Obj.get("year").toString());
                                    datos.add("date_published: "+Obj.get("date_published").toString());
                                    datos.add("title: " +Obj.get("title").toString());
                                    datos.add("doi: "+Obj.get("doi").toString());
                                    datos.add("cover: "+Obj.get("cover").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            String contenido=" ";
                            int cont=0;
                           for (int i=0;i< datos.size();i++) {

                               if(cont==8)
                               {
                                   contenido="------------------------------------------------------------------------------------\n";
                                   textView.append(contenido);
                                   cont=0;
                               }
                               cont++;
                                contenido=datos.get(i)+"\n";
                                textView.append(contenido);
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        int x=0;
                    }
                });
                //queue.addToRequestQueue(jsonObjectRequest);
                queue.add(jsonObjectRequest);


       /* JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        int x=0;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        int x=0;
                    }
                });
        //queue.addToRequestQueue(jsonObjectRequest);
        queue.add(jsonObjectRequest);*/
    }
    public  void regresarMenu2(View view)
    {
        Intent miIntent=new Intent(ActiVolley.this,MainActivity2.class );
        startActivity(miIntent);
    }
}