package com.cryptocodes.thorrentnext;

import android.os.AsyncTask;

import com.cryptocodes.thorrentnext.tools.TmdbInfoRetriever;
import com.uwetrottmann.tmdb2.entities.Movie;

import java.io.IOException;

import retrofit2.Call;

public class GetFullMovieDetails extends AsyncTask<Integer, Void, Movie> {

    // Do the long-running work in here
    protected Movie doInBackground(Integer... query) {
        Integer movieId = query[0];
        Call<Movie> call = TmdbInfoRetriever.getInstance().getMoviesService().summary(
                movieId,
                "en"
        );

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
