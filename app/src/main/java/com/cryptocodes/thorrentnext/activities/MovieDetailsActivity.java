package com.cryptocodes.thorrentnext.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptocodes.thorrentnext.GetFullMovieDetails;
import com.cryptocodes.thorrentnext.R;
import com.cryptocodes.thorrentnext.RetrieveTmdbData;
import com.squareup.picasso.Picasso;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.entities.Movie;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String tmdbBaseImageUrl = "https://image.tmdb.org/t/p/w";
    private static final String smallUrlSuffix = "185";
    private static final String largeUrlSuffix = "500";

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
        ImageView poster = findViewById(R.id.moviePosterImage);
        ImageView backdrop = findViewById(R.id.backdrop_image_view);

        String movieTitle = getIntent().getStringExtra("title");
        title.setText(movieTitle);

        try {
            BaseMovie m = new RetrieveTmdbData().execute(movieTitle).get();

            if (m == null) {
                return;
            }

            Movie fullMovie = new GetFullMovieDetails().execute(m.id).get();

            if (fullMovie != null) {
                title.setText(fullMovie.title);

                Picasso.get().load(tmdbBaseImageUrl + smallUrlSuffix + m.poster_path).into(poster);
                Picasso.get().load(tmdbBaseImageUrl + largeUrlSuffix + m.backdrop_path).into(backdrop);
                tagline.setText(fullMovie.tagline);

                StringBuilder sb = new StringBuilder();

                for (Genre genre : fullMovie.genres) {
                    sb.append(genre.name).append(", ");
                }

                genres.setText(sb.toString());
                description.setText(fullMovie.overview);
                rating.setText(String.valueOf(fullMovie.vote_average));
                runtime.setText(String.format("%s mins", String.valueOf(fullMovie.runtime)));
                releaseDate.setText(android.text.format.DateFormat.format("MMMM d, yyyy", m.release_date));
                voteCount.setText(String.format(Locale.US, "(%d votes)", m.vote_count));
            } else {
                if (m.original_title != null) {
                    title.setText(m.original_title);
                } else if (m.title != null) {
                    title.setText(m.title);
                }

                Picasso.get().load(tmdbBaseImageUrl + smallUrlSuffix + m.poster_path).into(poster);
                Picasso.get().load(tmdbBaseImageUrl + largeUrlSuffix + m.backdrop_path).into(backdrop);
                description.setText(m.overview);
                rating.setText(String.valueOf(m.vote_average));
                releaseDate.setText(android.text.format.DateFormat.format("MMMM d, yyyy", m.release_date));
                voteCount.setText(String.format(Locale.US, "(%d votes)", m.vote_count));
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
