package com.example.moviesapp.data;


import com.example.moviesapp.networkservice.ResponseWithId;

import java.util.ArrayList;
import java.util.List;

public class MapperWithId {
    public static List<Reviews> responseToDomain(List<ResponseWithId> ResponseList){
        //array de filmes
        List<Reviews> listReviews = new ArrayList<>();
        for (ResponseWithId response : ResponseList) {
            final Reviews reviews = new Reviews(response.getAuthorReview(), response.getContentReview());

            listReviews.add(reviews);
        }
        return listReviews;
    }
}
