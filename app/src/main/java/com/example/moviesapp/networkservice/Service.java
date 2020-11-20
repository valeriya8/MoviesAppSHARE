package com.example.moviesapp.networkservice;

import com.example.moviesapp.data.Movie;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Service {

    private static ServiceMovie INSTANCE;

    public static ServiceMovie getIntance() {

        if (INSTANCE == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    //onde vamos buscar os filmes
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            INSTANCE = retrofit.create(ServiceMovie.class);

        }
        return INSTANCE;
    }


}
