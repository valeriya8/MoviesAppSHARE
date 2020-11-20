package com.example.moviesapp.networkservice;

import com.squareup.moshi.Json;


public class ResponseMovie {

//capa
    @Json(name = "poster_path")
    private final String posterPath;
//descrição
    @Json(name = "title")
    private final String name;

    @Json(name = "vote_average")      //rank
    private final String rank;

    @Json(name = "overview")          //descrição
    private final String overviewMovie;

    @Json(name = "release_date")      //data
    private final String releaseDate;

    @Json(name = "id")
    private final String idDetail;



    public ResponseMovie(String posterPath,
                         String name,
                         String rank,
                         String overviewMovie,
                         String releaseDate,
                         String idDetail) {

        this.posterPath = posterPath;
        this.name = name;
        this.rank = rank;
        this.overviewMovie = overviewMovie;
        this.releaseDate = releaseDate;
        this.idDetail = idDetail;

    }

    public String getPosterPath(){

        return posterPath;
    }

    public String getName(){
        return name;
    }
    public String getRank() { return rank; }
    public String getOverviewMovie() {return overviewMovie;}

    public String getReleaseDate() {
        return releaseDate;
    }
    public String getIdDetail(){
        return idDetail;
    }


}

