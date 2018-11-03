package com.cryptocodes.thorrentnext.tools;

import com.cryptocodes.thorrentnext.Keys;
import com.cryptocodes.thorrentnext.RetrieveTmdbData;
import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.services.MoviesService;
import com.uwetrottmann.tmdb2.services.SearchService;
import com.uwetrottmann.tmdb2.services.TvService;

public class TmdbInfoRetriever {
    private MoviesService moviesService;
    private TvService tvService;
    private SearchService searchService;
    private static TmdbInfoRetriever instance;

    public static TmdbInfoRetriever getInstance() {
        if (instance == null) {
            instance = new TmdbInfoRetriever();
        }

        return instance;
    }

    private TmdbInfoRetriever() {
        // Create an instance of the service you wish to use
        // you can keep this around
        Tmdb tmdb = new Tmdb(Keys.TMDB_API_KEY);
        moviesService = tmdb.moviesService();
        tvService = tmdb.tvService();
        searchService = tmdb.searchService();
    }

    public BaseMovie getMovieByName(String movieName) {
        new RetrieveTmdbData().execute(movieName);

        return null;
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public MoviesService getMoviesService() {
        return moviesService;
    }
}
