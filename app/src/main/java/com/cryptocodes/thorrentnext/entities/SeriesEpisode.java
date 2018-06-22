package com.cryptocodes.thorrentnext.entities;

public class SeriesEpisode extends MediaItem {
    private int season;
    private int episode;

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
