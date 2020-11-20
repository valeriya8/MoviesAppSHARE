package com.example.moviesapp.networkservice;

import java.util.List;

public class Result {
    private final List<ResponseMovie> results;


    public Result(List<ResponseMovie> results) {
        this.results = results;
    }

    public List<ResponseMovie> getResults(){
        return results;
    }
}
