package com.example.moviesapp.networkservice;

import com.squareup.moshi.Json;

public class ResponseWithId {


    @Json(name = "author")
    private final String authorReview;

    @Json(name = "content")
    private final String contentReview;

    public ResponseWithId(String authorReview, String contentReview) {
        this.authorReview = authorReview;
        this.contentReview = contentReview;
    }

    public String getAuthorReview() {
        return authorReview;
    }

    public String getContentReview() {
        return contentReview;
    }
}
