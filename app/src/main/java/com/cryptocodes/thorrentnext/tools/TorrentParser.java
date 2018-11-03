package com.cryptocodes.thorrentnext.tools;

import com.cryptocodes.thorrentnext.entities.Movie;
import com.cryptocodes.thorrentnext.entities.ReleaseType;

public class TorrentParser {
    public static Movie parseMovie(String title) {
        String[] words = title.split(" ");
        Movie movie = new Movie();
        boolean titleSet = false;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (!word.matches("(19|20)\\d\\d")) {

                if (i > 0) {
                    sb.append(" ");
                }

                sb.append(word);
            } else {
                movie.setYear(Integer.parseInt(word));
                movie.setTitle(sb.toString());
                titleSet = true;
            }

            if (titleSet) {
                parseTitle(movie, word);
            }
        }

        return movie;
    }

    private static void parseTitle(Movie movie, String title) {
        if (title.matches("(1080|720)p*")) {
            movie.setReleaseType(ReleaseType.Hdtv);
        }

        if (title.compareToIgnoreCase("web") == 0) {
            movie.setReleaseType(ReleaseType.WebRip);
        } else if (title.compareToIgnoreCase("bluray") == 0 || title.compareToIgnoreCase("bdrip") == 0) {
            movie.setReleaseType(ReleaseType.BluRay);
        } else if (title.compareToIgnoreCase("dvdrip") == 0) {
            movie.setReleaseType(ReleaseType.Dvdrip);
        } else if (title.compareToIgnoreCase("4k") == 0 || title.compareToIgnoreCase("uhd") == 0) {
            movie.setReleaseType(ReleaseType.FourK);
        }

        if (title.contains("720p")) {
            movie.setResolution(720);
        } else if (title.contains("1080p")) {
            movie.setResolution(1080);
        } else if (title.contains("2160p")) {
            movie.setResolution(2160);
        }
    }
}
