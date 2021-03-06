package com.cryptocodes.thorrentnext.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptocodes.thorrentnext.GetFullMovieDetails;
import com.cryptocodes.thorrentnext.R;
import com.cryptocodes.thorrentnext.RetrieveTmdbData;
import com.squareup.picasso.Picasso;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.Movie;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String tmdbBaseImageUrl = "https://image.tmdb.org/t/p/w";
    private static final String smallUrlSuffix = "185";
    private static final String largeUrlSuffix = "500";
    private static final String imdbPrefix = "https://www.imdb.com/title/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        TextView title = findViewById(R.id.movie_title);
        TextView description = findViewById(R.id.description_text_view);
        TextView rating = findViewById(R.id.rating_text_view);
        TextView voteCount = findViewById(R.id.vote_count_text_view);
        TextView runtime = findViewById(R.id.runtime_text_view);
        TextView releaseDate = findViewById(R.id.release_date_text_view);
        TextView genres = findViewById(R.id.genres_text_view);
        TextView tagline = findViewById(R.id.tagline_text_view);
        TextView openImdb = findViewById(R.id.open_imdb_text_view);
        ImageView poster = findViewById(R.id.moviePosterImage);
        ImageView backdrop = findViewById(R.id.backdrop_image_view);

        String movieTitle = getIntent().getStringExtra("title");
        title.setText(movieTitle);

        try {
            BaseMovie m = new RetrieveTmdbData().execute(movieTitle).get();

            if (m == null) {
                return;
            }

            final Movie fullMovie = new GetFullMovieDetails().execute(m.id).get();

            if (fullMovie != null) {
                title.setText(fullMovie.title);

                Picasso.get().load(tmdbBaseImageUrl + smallUrlSuffix + m.poster_path).into(poster);
                Picasso.get().load(tmdbBaseImageUrl + largeUrlSuffix + m.backdrop_path).into(backdrop);

                if (fullMovie.tagline != null && !fullMovie.tagline.isEmpty()) {
                    tagline.setText(fullMovie.tagline);
                } else {
                    tagline.setText(R.string.not_available);
                }

                if (fullMovie.genres != null && fullMovie.genres.size() > 0) {
                    addGenre(genres, fullMovie);
                } else {
                    genres.setText(R.string.no_genres);
                }

                if (fullMovie.overview != null) {
                    description.setText(fullMovie.overview);
                } else {
                    description.setText(R.string.not_available);
                }

                if (fullMovie.vote_average != 0) {
                    rating.setText(String.valueOf(fullMovie.vote_average));
                } else {
                    rating.setText(R.string.not_available);
                }

                if (fullMovie.runtime != null && fullMovie.runtime > 0) {
                    runtime.setText(String.format("%s mins", String.valueOf(fullMovie.runtime)));
                } else {
                    runtime.setText(R.string.not_available);
                }

                releaseDate.setText(android.text.format.DateFormat.format("MMMM d, yyyy", m.release_date));
                voteCount.setText(String.format(Locale.US, "(%d votes)", m.vote_count));

                handleImdb(openImdb, fullMovie);
            } else {
                if (m.original_title != null) {
                    title.setText(m.original_title);
                } else if (m.title != null) {
                    title.setText(m.title);
                } else {
                    title.setText(R.string.not_available);
                }

                Picasso.get().load(tmdbBaseImageUrl + smallUrlSuffix + m.poster_path).into(poster);
                Picasso.get().load(tmdbBaseImageUrl + largeUrlSuffix + m.backdrop_path).into(backdrop);
                openImdb.setText("");
                description.setText(m.overview);
                rating.setText(String.valueOf(m.vote_average));
                releaseDate.setText(android.text.format.DateFormat.format("MMMM d, yyyy", m.release_date));
                voteCount.setText(String.format(Locale.US, "(%d votes)", m.vote_count));
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void handleImdb(TextView openImdb, final Movie fullMovie) {
        if (fullMovie.imdb_id != null && !fullMovie.imdb_id.isEmpty()) {
            openImdb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(imdbPrefix + fullMovie.imdb_id));
                    startActivity(intent);
                }
            });
        } else {
            openImdb.setText("");
        }
    }

    private void addGenre(TextView genres, Movie fullMovie) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < fullMovie.genres.size() - 1; i++) {
            sb.append(fullMovie.genres.get(i).name).append(", ");
        }

        sb.append(fullMovie.genres.get(fullMovie.genres.size() - 1).name);
        genres.setText(sb.toString());
    }
}
