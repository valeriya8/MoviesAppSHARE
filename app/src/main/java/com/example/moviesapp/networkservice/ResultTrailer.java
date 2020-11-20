package com.example.moviesapp.networkservice;

import java.util.List;

public class ResultTrailer {

    private final List<ResponseTrailer> results;


    public ResultTrailer(List<ResponseTrailer> results) {
        this.results = results;
    }

    public List<ResponseTrailer> getResultsTrailer(){
        return results;
    }
}
