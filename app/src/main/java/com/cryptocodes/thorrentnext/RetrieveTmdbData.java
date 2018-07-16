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

            if (page != null && page.results != null) {
                return page.results.get(0);
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // This is called each time you call publishProgress()
//    protected void onProgressUpdate(Integer... progress) {
//        setProgressPercent(progress[0]);
//    }
//
//    // This is called when doInBackground() is finished
//    protected void onPostExecute(BaseMovie result) {
//        showNotification("Downloaded " + result + " bytes");
//    }
}
