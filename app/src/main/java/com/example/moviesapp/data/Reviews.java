package com.example.moviesapp.data;

public class Reviews {

    private final String authorReview;
    private final String contentReview;

    public Reviews(String authorReview, String contentReview) {
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
