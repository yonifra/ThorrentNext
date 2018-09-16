package com.cryptocodes.thorrentnext.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cryptocodes.thorrentnext.R;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        TextView title = findViewById(R.id.movie_title);

        title.setText(getIntent().getStringExtra("title"));
    }
}
