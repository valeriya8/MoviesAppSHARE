package com.example.moviesapp.networkservice;

import java.util.List;

public class ResultWithId {
    private final List<ResponseWithId> results;


    public ResultWithId(List<ResponseWithId> results) {
        this.results = results;
    }

    public List<ResponseWithId> getResultsWithId(){
        return results;
    }
}
