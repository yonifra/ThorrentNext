package com.cryptocodes.thorrentnext.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptocodes.thorrentnext.R;
import com.cryptocodes.thorrentnext.entities.Movie;
import com.cryptocodes.thorrentnext.tools.TmdbInfoRetriever;
import com.squareup.picasso.Picasso;
import com.uwetrottmann.tmdb2.entities.BaseMovie;

import java.util.List;

public class RssListAdapter extends ArrayAdapter<Movie> {
    private List<Movie> items;
    private final Context context;
    private boolean isMovies;
    private final String posterBasePath = "https://image.tmdb.org/t/p/w500";

    public RssListAdapter(Context context, List<Movie> movies, boolean isMovies) {
        super(context, -1, movies);
        this.items = movies;
        this.context = context;
        this.isMovies = isMovies;
    }

    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public long getItemId(int i) {
        return items.get(i).getTitle().hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_layout, viewGroup, false);
        TextView title = rowView.findViewById(R.id.item_title);
        TextView description = rowView.findViewById(R.id.item_description);
        TextView year = rowView.findViewById(R.id.year_text_view);
        ImageView poster = rowView.findViewById(R.id.poster);

        Movie movie = items.get(position);

        if (movie != null) {
            year.setText(String.valueOf(movie.getYear()));
            title.setText(movie.getTitle());

            BaseMovie m = TmdbInfoRetriever.getInstance().getMovieByName(movie.getTitle());

            if (m != null) {
                description.setText(m.overview);
                Picasso.get().load(posterBasePath + m.poster_path).into(poster);
            }
        }

        return rowView;
    }
}
