package com.example.moviesapp.networkservice;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceMovie {


    //capa
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String myPersonalKey);

    @GET("movie/top_rated")
    Call<Result> getTopRatedMovies(@Query("api_key") String myPersonalKey);



    @GET("movie/{movie_id}/reviews")
    Call<ResultWithId> getId(@Path("movie_id") String groupId, @Query("api_key") String myPersonalKey);


     @GET("movie/{movie_id}/videos")
     Call<ResultTrailer> getTrailers(@Path("movie_id") String groupId, @Query("api_key") String myPersonalKey);

    @GET("movie/{movie_id}")
    Call<ResponseMovie> getMovieDetails(@Path("movie_id") String id, @Query("api_key") String apiKey);



}


