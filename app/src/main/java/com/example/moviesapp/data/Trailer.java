package com.example.moviesapp.data;

public class Trailer {
    //https://www.youtube.com/results?search_query=
    //Uri.parse

    private final String idTrailer;
    private final String typeTrailer;
    private final String keyTrailer;


    public Trailer(String idTrailer, String typeTrailer, String keyTrailer) {
        this.idTrailer = idTrailer;
        this.typeTrailer = typeTrailer;
        this.keyTrailer = keyTrailer;
    }

    public String getIdTrailer() {
        return idTrailer;
    }

    public String getTypeTrailer() {
        return typeTrailer;
    }

    public String getKeyTrailer() {
        return keyTrailer;
    }

}
