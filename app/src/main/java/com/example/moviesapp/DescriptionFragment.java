package com.example.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.data.MapperTrailer;
import com.example.moviesapp.data.MapperWithId;
import com.example.moviesapp.data.Movie;
import com.example.moviesapp.data.Trailer;
import com.example.moviesapp.networkservice.ResponseMovie;
import com.example.moviesapp.networkservice.ResultTrailer;
import com.example.moviesapp.networkservice.ResultWithId;
import com.example.moviesapp.networkservice.Service;
import com.example.moviesapp.sqlite.FavoriteHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionFragment extends Fragment implements AdapterTrailer.ItemTrailerClickListener {

    public static final String ARG_MOVIE = "ARG_MOVIE";
    private Movie movie;

    private FavoriteHelper favoriteHelper;
    private Movie favorite;
    ImageView imagePoster;
    ImageButton favBtn;

    boolean finded = false;
    private View rootView;

    //new instance
    public static DescriptionFragment newInstance(Movie movie) {

        Bundle args = new Bundle();

        args.putSerializable(ARG_MOVIE, movie);
        DescriptionFragment fragment = new DescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_description, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            movie = (Movie) bundle.getSerializable(ARG_MOVIE);
            //favorite = (Movie) bundle.getSerializable(ARG_MOVIE);
        }

        imagePoster = (ImageView) rootView.findViewById(R.id.iv_poster_detail);
        //scale w500
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                .into(imagePoster);

        adapterConfigReviwe();
        adapterConfigTrailers();

        if (movie != null) {
            getIdMovie(movie.getIdDetail());
            getTrailers(movie.getIdDetail());
            getMovieDetails(movie.getIdDetail());
        }

        //Favorits

        favBtn = rootView.findViewById(R.id.button_favorite);
        favoriteHelper = new FavoriteHelper(getActivity());

        List<Movie> favs = favoriteHelper.getAllFavorite();

        for (int i = 0; i < favs.size(); i++) {
            if (movie.getIdDetail().equals(favs.get(i).getIdDetail())) {
                favBtn.setBackgroundResource(R.drawable.ic_baseline_star_24_yellow);
                finded = true;
            }
        }


        favBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(finded == false){
                    saveFavorite();
                    favBtn.setBackgroundResource(R.drawable.ic_baseline_star_24_yellow);
                    finded = true;
                } else {
                    Boolean res = favoriteHelper.deleteFavorite(movie.getIdDetail());
                    favoriteHelper.deleteFavorite(movie.getIdDetail());
                    Log.i("DELETE", res.toString() );
                    favBtn.setBackgroundResource(R.drawable.ic_baseline_star_24);
                    finded = false;
                }
            }
        });


        return rootView;
    }

    private RecyclerView rcReview;
    private RecyclerView rcTrailer;
    private AdapterWithId adapterWithId;
    private AdapterTrailer adapterTrailer;

    public void adapterConfigReviwe() {
        rcReview = rootView.findViewById(R.id.rc_reviews);
        adapterWithId = new AdapterWithId();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcReview.setLayoutManager(linearLayoutManager);
        //if(adapterWithId != null){
            rcReview.setVisibility(View.VISIBLE);
        //}
        rcReview.setAdapter(adapterWithId);
    }

    public void adapterConfigTrailers() {
        rcTrailer = rootView.findViewById(R.id.rc_trailers);
        adapterTrailer = new AdapterTrailer(this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcTrailer.setLayoutManager(linearLayoutManager);
        rcTrailer.setAdapter(adapterTrailer);
    }


    String key = "bd70264835f367b0c65814adf858c175";

    //Movie movie;
    public void getIdMovie(String id) {
        Service.getIntance().getId(id, key)
                .enqueue(new Callback<ResultWithId>() {
                    @Override
                    public void onResponse(Call<ResultWithId> call, Response<ResultWithId> response) {
                        if (response.isSuccessful()) {
                            adapterWithId.setReviews(MapperWithId
                                    .responseToDomain(response.body().getResultsWithId()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultWithId> call, Throwable t) {

                    }
                });
    }


    //details
    public void getMovieDetails(String id) {
        Service.getIntance().getMovieDetails(id, key)
                .enqueue(new Callback<ResponseMovie>() {
                    @Override
                    public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                        if (response.isSuccessful()) {
                            //Log.i("Movie title", response.body().);

                            TextView textMovieName = (TextView) rootView.findViewById(R.id.tv_movie_name);
                            textMovieName.setText(response.body().getName());

                            TextView textRankDetail = (TextView) rootView.findViewById(R.id.tv_rank_detail);
                            textRankDetail.setText(response.body().getRank() + "/10");

                            TextView textOverviewMovie = (TextView) rootView.findViewById(R.id.tv_overview_movie);
                            textOverviewMovie.setText(response.body().getOverviewMovie());

                            TextView textReleaseDate = (TextView) rootView.findViewById(R.id.tv_release_date);
                            String input = textReleaseDate.toString();
                            if (input.length() > 4) {
                            //primeiros 4 carateres
                              textReleaseDate.setText(response.body().getReleaseDate().substring(0, 4));
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMovie> call, Throwable t) {

                    }
                });
    }



    //Trailer trailer
    public void getTrailers(String id) {
        Service.getIntance().getTrailers(id, key)
                .enqueue(new Callback<ResultTrailer>() {
                    @Override
                    public void onResponse(Call<ResultTrailer> call, Response<ResultTrailer> response) {
                        if (response.isSuccessful()) {
                            adapterTrailer.setTrailers(MapperTrailer
                                    .responseToDomain(response.body().getResultsTrailer()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultTrailer> call, Throwable t) {

                    }
                });
    }


    public void onItemTrailerClicked(Trailer trailer) {
        String url = "vnd.youtube://" + trailer.getKeyTrailer();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }


    public void saveFavorite() {
        favoriteHelper = new FavoriteHelper(getContext());
        favorite = new Movie();
        favorite.setIdDetail(movie.getIdDetail());
        favorite.setName(movie.getName());
        favorite.setPosterPath(movie.getPosterPath());
        favorite.setRank(movie.getRank());
        favorite.setOverviewMovie(movie.getOverviewMovie());
        favoriteHelper.addFavorite(favorite);
    }


}
