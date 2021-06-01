package com.example.deber;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

  public void onClick(View view)
  {
    Intent miIntent=new Intent(MainActivity2.this,MainActivity.class );
    startActivity(miIntent);
  }
    public void onClick2(View view)
    {
        Intent miIntent=new Intent(MainActivity2.this,ActiVolley.class );
        startActivity(miIntent);
    }
}