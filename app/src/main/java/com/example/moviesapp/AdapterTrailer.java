package com.example.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.data.Movie;
import com.example.moviesapp.data.Trailer;

import java.util.ArrayList;
import java.util.List;

public class AdapterTrailer extends RecyclerView.Adapter<AdapterTrailer.TrailerViewHolder> {

    public static List<Trailer> trailers;
    private static ItemTrailerClickListener itemTrailerClickListener;

    public AdapterTrailer(ItemTrailerClickListener itemTrailerClickListener) {
        this.itemTrailerClickListener = itemTrailerClickListener;

        trailers = new ArrayList<>();
    }

    //public AdapterTrailer() {
      //  trailers = new ArrayList<>();
    //}

    @NonNull
    @Override
    public AdapterTrailer.TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);

        return new AdapterTrailer.TrailerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTrailer.TrailerViewHolder holder, int position) {
        holder.bind(trailers.get(position));

    }


    @Override
    public int getItemCount() {
        if(trailers != null && trailers.size()>0){
            return trailers.size();
        } else {
            return 0;
        }
    }



    static class TrailerViewHolder extends RecyclerView.ViewHolder
    {

        private Trailer trailer;
        private TextView textTypeTrailer;
        private ImageView iVplayTrailer;


        public TrailerViewHolder(View view){
            super(view);

            textTypeTrailer = view.findViewById(R.id.tv_type);
            iVplayTrailer = view.findViewById(R.id.iv_play_trailer);

            iVplayTrailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemTrailerClickListener != null){
                        itemTrailerClickListener.onItemTrailerClicked(trailer);
                    }
                }
            });

        }


        public void bind(Trailer trailer) {
            this.trailer = trailer;

            textTypeTrailer.setText(trailer.getTypeTrailer());

        }
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    //clicar no icon play
   /* private static ItemTrailerClickListener itemTrailerClickListener;

    public Adapter(ItemTrailerClickListener itemTrailerClickListener) {
        this.itemTrailerClickListener = itemTrailerClickListener;

        trailers = new ArrayList<>();
    }*/



    public interface ItemTrailerClickListener {
        void onItemTrailerClicked(Trailer trailer);
    }

}
