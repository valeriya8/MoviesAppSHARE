package com.example.moviesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.data.Reviews;

import java.util.ArrayList;
import java.util.List;

public class AdapterWithId extends RecyclerView.Adapter<AdapterWithId.ReviewsViewHolder> {

    private List<Reviews> reviews;


    public AdapterWithId() {
       reviews = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterWithId.ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);

        return new AdapterWithId.ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWithId.ReviewsViewHolder holder, int position) {
        holder.bind(reviews.get(position));

    }


    @Override
    public int getItemCount() {
        if(reviews != null && reviews.size()>0){
            return reviews.size();
        } else {
            return 0;
        }
    }



    static class ReviewsViewHolder extends RecyclerView.ViewHolder
    {

        private Reviews review;
        private TextView textAuthorReview;
        private TextView textContentReview;

        public ReviewsViewHolder(View view){
            super(view);

            textAuthorReview = view.findViewById(R.id.tv_review_author);
            textContentReview = view.findViewById(R.id.tv_review_content);

        }

        public void bind(Reviews review) {
            this.review = review;


            textAuthorReview.setText("Author: " + review.getAuthorReview());
            textContentReview.setText("Content: "+review.getContentReview());
        }
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }


}
