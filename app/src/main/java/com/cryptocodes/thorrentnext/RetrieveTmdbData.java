package com.cryptocodes.thorrentnext;

import android.os.AsyncTask;

import com.cryptocodes.thorrentnext.tools.TmdbInfoRetriever;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.services.SearchService;

import java.io.IOException;

import retrofit2.Call;

public class RetrieveTmdbData extends AsyncTask<String, Void, BaseMovie> {

    // Do the long-running work in here
    protected BaseMovie doInBackground(String... query) {
        String movieName = query[0];
        SearchService searchService = TmdbInfoRetriever.getInstance().getSearchService();
        Call<MovieResultsPage> results = searchService.movie(movieName, 1, "en-US", false, 0, 0, "");

        try {
            MovieResultsPage page = results.execute().body();

            if (page != null && page.results != null && page.results.size() > 0) {
                return page.results.get(0);
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(BaseMovie result) {
//        Intent i = new Intent(MainActivity.this, MovieDetailsActivity.class);
//        i.putExtra("title", result.title);
//        i.putExtra("resolution", result.release_date);
//        i.putExtra("year", result.release_date.getYear());
//        i.putExtra("releaseType", "DVD");
//
//        startActivity(i);
    }
}
