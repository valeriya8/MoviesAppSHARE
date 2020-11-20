package com.example.moviesapp;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.data.Movie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MoviesViewHolder>{

    private List<Movie> movies;



    //clicar no poster
    private static ItemMovieClickListener itemMovieClickListener;

    public Adapter(ItemMovieClickListener itemMovieClickListener) {
        this.itemMovieClickListener = itemMovieClickListener;
        movies = new ArrayList<>();

    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        if(movies != null && movies.size()>0){
            return movies.size();
        } else {
            return 0;
        }
    }



    static class MoviesViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView imagePoster;
        private Movie movie;

        public MoviesViewHolder(View view){
            super(view);

            imagePoster = view.findViewById(R.id.image_poster);

            //click no poster
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(itemMovieClickListener != null){
                        itemMovieClickListener.onItemMovieClicked(movie);
                    }
                }
            });


        }

        public void bind(Movie movie) {
            this.movie = movie;
            //scale w500
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                    .into(imagePoster);
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }


    public interface ItemMovieClickListener {
        void onItemMovieClicked(Movie movie);
    }

}
