package com.example.deber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deber.Interfaces.revistasInterface;
import com.example.deber.modeloC.revista;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    //TextView issue_id;
    TextView idBusqueda;
    Button btnBuscarRevista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btnBuscarRevista=findViewById(R.id.button2);


        btnBuscarRevista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idBusqueda= findViewById(R.id.editTextTextPersonName);
                find(idBusqueda.getText().toString());
            }
        });
        textView.setText("Ingrese un ID en el campo de texto para busca");

    }
    public  void regresarMenu(View view)
    {
        Intent miIntent=new Intent(MainActivity.this,MainActivity2.class );
        startActivity(miIntent);
    }
    private void find(String codigo) {
        textView.setText("");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        revistasInterface Jsonresvista = retrofit.create(revistasInterface.class);

        Call<List<revista>> call = Jsonresvista.find(codigo);
        call.enqueue(new Callback<List<revista>>() {
            @Override
            public void onResponse(Call<List<revista>> call, Response<List<revista>> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Codigo" + response.code());

                }

                List<revista> RevistaLis = response.body();
                int cont=0;
                for (revista list : RevistaLis) {
                    String contenido="";

                    contenido+="issue_id:"+list.getIssue_id()+"\n";
                    contenido+="volume:"+list.getVolume()+"\n";
                    contenido+="number:"+list.getNumber()+"\n";
                    contenido+="year:"+list.getYear()+"\n";
                    contenido+="date_published:"+list.getDate_published()+"\n";
                    contenido+="title:"+list.getTitle()+"\n";
                    contenido+="doi:"+list.getDoi()+"\n";
                    contenido+="cover:"+list.getCover()+"\n";
                    contenido+="-------------------------------------------------------------------------------------------\n";
                    textView.append(contenido);
                }
            }

            @Override
            public void onFailure(Call<List<revista>> call, Throwable t) {
                textView.setText("Codigo" + t.getMessage());
            }
        });
    }

}




/*
    private void find(String codigo)
    {
        Retrofit retrofit= new  Retrofit.Builder().baseUrl("https://revistas.uteq.edu.ec/")
        .addConverterFactory(GsonConverterFactory.create()).build();
        revistasInterface resvista=retrofit.create(revistasInterface.class);
        Call<revista> call=resvista.find(codigo);
        call.enqueue(new Callback<revista>() {
            @Override
            public void onResponse(Call<revista> call, Response<revista> response) {
                try {
                    if(response.isSuccessful())
                    {
                        revista r= response.body();
                        issue_id.setText(r.getIssue_id());

                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<revista> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/
