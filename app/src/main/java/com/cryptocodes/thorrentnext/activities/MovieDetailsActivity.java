package com.cryptocodes.thorrentnext.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptocodes.thorrentnext.R;
import com.cryptocodes.thorrentnext.RetrieveTmdbData;
import com.squareup.picasso.Picasso;
import com.uwetrottmann.tmdb2.entities.BaseMovie;

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
        ImageView poster = findViewById(R.id.moviePosterImage);

        String movieTitle = getIntent().getStringExtra("title");
        title.setText(movieTitle);

        try {
            BaseMovie m = new RetrieveTmdbData().execute(movieTitle).get();

            title.setText(m.original_title);
            Picasso.get().load(tmdbBaseImageUrl + largeUrlSuffix + m.poster_path).into(poster);
            description.setText(m.overview);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
