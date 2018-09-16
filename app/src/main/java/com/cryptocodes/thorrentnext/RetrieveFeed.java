package com.cryptocodes.thorrentnext;

import android.os.AsyncTask;
import android.util.Log;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RetrieveFeed extends AsyncTask<String, Void, RSSFeed> {

    protected RSSFeed doInBackground(String... urls) {
        try {
            RSSReader reader = new RSSReader();
            String uri = urls[0];
            try {
                return reader.load(uri);
            } catch (RSSReaderException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.e("RetrieveFeed", e.getLocalizedMessage());

            return null;
        }

        return null;
    }

    protected void onPostExecute(RSSFeed feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
