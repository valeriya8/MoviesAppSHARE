package com.example.moviesapp;

import android.content.res.Configuration;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.data.Mapper;
import com.example.moviesapp.data.Movie;
import com.example.moviesapp.networkservice.Result;
import com.example.moviesapp.networkservice.Service;
import com.example.moviesapp.sqlite.FavoriteHelper;
import com.example.moviesapp.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMovieFragment extends Fragment implements Adapter.ItemMovieClickListener {


    private RecyclerView rcMovies;
    private Adapter adapterMovies;
    private View rootView;


    FavoriteHelper favHeler;

    public static ListMovieFragment newInstance() {

        Bundle args = new Bundle();

        ListMovieFragment fragment = new ListMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_list_movie, container, false);
        setHasOptionsMenu(true);
        adapterConfig();

        if(positionVar ==1){
            getMovies();
        }

        if(positionVar ==2){
            getRatedMovies();
        }

        if(positionVar==3){
            getFavorites();
        }

        favHeler = new FavoriteHelper(getContext());

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movies_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    int positionVar=1;
    //menu click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular_movies_item:
                positionVar=1;
                getMovies();
                return true;
            case R.id.top_rated_movies:
                positionVar=2;
                getRatedMovies();
                return true;
            case R.id.favorite_movies:
                positionVar=3;
                getFavorites();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void adapterConfig() {
        RecyclerView.LayoutManager gridLayoutManager ;
        //checkOrientation
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager = new GridLayoutManager(getContext(), 4);
            // In landscape
        } else {
            gridLayoutManager = new GridLayoutManager(getContext(), 2);
            // In portrait
        }

        rcMovies = rootView.findViewById(R.id.rc_movies);
        adapterMovies = new Adapter(this);
        //grelha de imagens

        rcMovies.setLayoutManager(gridLayoutManager);
        rcMovies.setAdapter(adapterMovies);
    }


    String key = "bd70264835f367b0c65814adf858c175";

    //mostrar os filmes mais populares
    private void getMovies() {
        rcMovies.setAdapter(adapterMovies);
        Service.getIntance().getPopularMovies(key)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            adapterMovies.setMovies(Mapper
                                    .responseToDomain(response.body().getResults()));
                        } else {
                            showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        showError();
                    }
                });
    }

    //mostrar os filmes top_rated
    private void getRatedMovies() {
        rcMovies.setAdapter(adapterMovies);
        Service.getIntance().getTopRatedMovies(key)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            adapterMovies.setMovies(Mapper
                                    .responseToDomain(response.body().getResults()));
                        } else {
                            showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        showError();
                    }
                });
    }




    //Clickar no poster
    @Override
    public void onItemMovieClicked(Movie movie) {

        DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(movie);
        Utils.setFragment(getFragmentManager(), descriptionFragment);

    }

    private void showError() {
        Toast.makeText(getContext(), "AN ERROR HAS OCCURRED", Toast.LENGTH_SHORT).show();
    }



    private void getFavorites(){
        adapterMovies.setMovies(favHeler.getAllFavorite());
    }


}

