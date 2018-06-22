package com.cryptocodes.thorrentnext.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cryptocodes.thorrentnext.R;
import com.cryptocodes.thorrentnext.entities.Movie;
import com.cryptocodes.thorrentnext.tools.TorrentParser;

import org.mcsoxford.rss.RSSItem;

import java.util.List;

public class RssListAdapter extends ArrayAdapter<RSSItem> {
    private List<RSSItem> items;
    private final Context context;

    public RssListAdapter(Context context, List<RSSItem> rssItems) {
        super(context, -1, rssItems);
        this.items = rssItems;
        this.context = context;
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
        //ImageView imageView = rowView.findViewById(R.id.icon);

        RSSItem item = items.get(position);

        Movie movie = TorrentParser.parseMovie(item.getTitle());

        year.setText(String.valueOf(movie.getYear()));
        title.setText(movie.getTitle());
        description.setText("From filmmaker Steven Spielberg comes the science fiction action adventure “Ready Player One,” based on Ernest Cline’s bestseller of the same name, which has become a worldwide phenomenon. The film is set in 2045, with the world on the brink of chaos and collapse. But the people have found salvation");

//        if (item.getTitle().startsWith("iPhone")) {
//            imageView.setImageResource(R.drawable.no);
//        } else {
//            imageView.setImageResource(R.drawable.ok);
//        }

        return rowView;
    }
}
