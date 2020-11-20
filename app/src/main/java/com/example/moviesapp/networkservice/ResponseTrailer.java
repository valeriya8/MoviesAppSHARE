package com.example.moviesapp.networkservice;

import com.squareup.moshi.Json;

public class ResponseTrailer {

    @Json(name="id")
    private final String idTrailer;

    @Json( name = "type" )
    private final String typeTrailer;

    @Json(name = "key")
    private final String keyTrailer;


    public ResponseTrailer(String idTrailer, String typeTrailer, String keyTrailer) {
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
