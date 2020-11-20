package com.example.moviesapp.data;

import java.io.Serializable;

//o meu objeto
public class Movie implements Serializable {

    //capa
    private  String posterPath;

    //descrição
    private  String name;
    private  String rank;
    private  String overviewMovie;
    private  String releaseDate;



    private String idDetail;


    public Movie(){}
    public Movie(String posterPath,
                 String name,
                 String rank,
                 String overviewMovie,
                 String releaseDate,
                 String idDetail){

        this.posterPath = posterPath;
        this.name = name;
        this.rank = rank;
        this.overviewMovie = overviewMovie;
        this.releaseDate = releaseDate;
        this.idDetail = idDetail;
    }

    public String getPosterPath() { return posterPath; }
    public String getName() { return name; }
    public String getRank() {return rank;}
    public String getOverviewMovie() {return overviewMovie;}

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getIdDetail() {
        return idDetail;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setOverviewMovie(String overviewMovie) {
        this.overviewMovie = overviewMovie;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

}

