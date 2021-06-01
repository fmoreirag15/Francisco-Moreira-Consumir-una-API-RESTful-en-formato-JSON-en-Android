package com.example.deber.Interfaces;

import com.example.deber.modeloC.revista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Path;
import retrofit2.http.Query;

public interface revistasInterface
{
    @GET("ws/issues.php")
    //public Call<revista> find(@Path("id") String id);
    public Call<List<revista>> find(@Query("j_id") String id);

}
