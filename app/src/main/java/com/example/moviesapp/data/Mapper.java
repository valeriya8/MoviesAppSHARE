package com.example.moviesapp.data;

import com.example.moviesapp.networkservice.ResponseMovie;

import java.util.ArrayList;
import java.util.List;


public class Mapper {
    public static List<Movie> responseToDomain(List<ResponseMovie> responseMovieList){
        //array de filmes
        List<Movie> listMovie = new ArrayList<>();
        for (ResponseMovie responseMovie : responseMovieList) {
            final Movie movie = new Movie(
                    responseMovie.getPosterPath(),
                    responseMovie.getName(),
                    responseMovie.getRank(),
                    responseMovie.getOverviewMovie(),
                    responseMovie.getReleaseDate(),
                    responseMovie.getIdDetail()
            );

            listMovie.add(movie);
        }
        return listMovie;
    }
}
