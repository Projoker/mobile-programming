package com.nabakulin.lab5_2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @GET("/vaccines")
    Call<List<Vaccine>> getVaccines();

    @POST("/vaccine")
    Call<Vaccine> addVaccine(@Body Vaccine vaccine);
}
