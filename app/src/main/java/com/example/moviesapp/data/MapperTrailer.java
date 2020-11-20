package com.example.moviesapp.data;

import com.example.moviesapp.networkservice.ResponseTrailer;

import java.util.ArrayList;
import java.util.List;

public class MapperTrailer {
    public static List<Trailer> responseToDomain(List<ResponseTrailer> ResponseList){
        //array de filmes
        List<Trailer> listTrailers = new ArrayList<>();
        for (ResponseTrailer response : ResponseList) {
            final Trailer trailer = new Trailer(response.getIdTrailer(), response.getTypeTrailer(), response.getKeyTrailer());

            listTrailers.add(trailer);
        }
        return listTrailers;
    }
}
