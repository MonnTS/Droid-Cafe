package com.example.droidcafe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import java.util.List;

public interface DesertsApi {

    String BASE_URL="http://10.0.2.2:8080";

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("/api/desery")
    Call<List<Desert>> getDeserts();
}
