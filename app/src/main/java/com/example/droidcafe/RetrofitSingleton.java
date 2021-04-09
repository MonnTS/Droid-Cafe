package com.example.droidcafe;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    public static Retrofit sDesertsRetrofit = null;

    public static Retrofit getDesertsRetrofit(){
        if(sDesertsRetrofit == null){
            sDesertsRetrofit = new Retrofit.Builder()
                    .baseUrl(DesertsApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sDesertsRetrofit;
    }
}
