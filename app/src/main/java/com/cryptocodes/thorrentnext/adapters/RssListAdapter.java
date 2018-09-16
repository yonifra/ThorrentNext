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
import com.cryptocodes.thorrentnext.entities.ReleaseType;
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
        if (items.get(i).getTitle() != null)
            return items.get(i).getTitle().hashCode();
        else
            return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_layout, viewGroup, false);
        TextView title = rowView.findViewById(R.id.item_title);
        TextView releaseType = rowView.findViewById(R.id.release_type_text_view);
        TextView resolution = rowView.findViewById(R.id.resolution_text_view);
        TextView year = rowView.findViewById(R.id.year_text_view);
        ImageView poster = rowView.findViewById(R.id.poster);

        Movie movie = items.get(position);

        if (movie != null) {
            year.setText(String.valueOf(movie.getYear()));
            title.setText(movie.getTitle());

            if (movie.getResolution() != 0) {
                resolution.setText(String.format("%sp", String.valueOf(movie.getResolution())));
            }

            if (movie.getReleaseType() != null && movie.getReleaseType() != ReleaseType.Unknown) {
                releaseType.setText(getReleaseTypeText(movie.getReleaseType()));
            }

            BaseMovie m = TmdbInfoRetriever.getInstance().getMovieByName(movie.getTitle());

            if (m != null) {
                Picasso.get().load(posterBasePath + m.poster_path).into(poster);
            } else {
                // put a placeholder in the imageview
                Picasso.get().load(R.drawable.ic_movie_black_24dp).into(poster);
            }
        }

        return rowView;
    }

    private String getReleaseTypeText(ReleaseType releaseType) {
        switch (releaseType) {
            case FourK:
                return "4K";
            case Dvdrip:
                return "DVDRip";
            case BluRay:
                return "BluRay";
            case EightK:
                return "8K";
            case Cam:
                return "Cam";
            case Hdtv:
                return "HDTV";
            case WebRip:
                return "WebRip";
        }

        return "";
    }
}
