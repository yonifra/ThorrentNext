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
                if (word.matches("(1080|720)p*")) {
                    movie.setReleaseType(ReleaseType.Hdtv);
                }

                if (word.compareToIgnoreCase("web") == 0) {
                    movie.setReleaseType(ReleaseType.WebRip);
                }
                else if (word.compareToIgnoreCase("bluray") == 0 || word.compareToIgnoreCase("bdrip") == 0) {
                    movie.setReleaseType(ReleaseType.BluRay);
                }
                else if (word.compareToIgnoreCase("dvdrip") == 0) {
                    movie.setReleaseType(ReleaseType.Dvdrip);
                }
                else if (word.compareToIgnoreCase("4k") == 0 || word.compareToIgnoreCase("uhd") == 0) {
                    movie.setReleaseType(ReleaseType.FourK);
                }


                if (word.contains("720p")) {
                    movie.setResolution(720);
                }
                else if (word.contains("1080p")) {
                    movie.setResolution(1080);
                }
                else if (word.contains("2160p")) {
                    movie.setResolution(2160);
                }
            }
        }

        return movie;
    }
}
