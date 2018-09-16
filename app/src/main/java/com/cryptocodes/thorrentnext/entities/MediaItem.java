package com.cryptocodes.thorrentnext.entities;

import java.util.Date;

public class MediaItem {
    private String title;
    private int year;
    private String description;
    private Date releaseDate;
    private Date uploadDate;
    private ReleaseType releaseType;
    private int resolution;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseType(ReleaseType releaseType) {
        this.releaseType = releaseType;
    }

    public ReleaseType getReleaseType() {
        return releaseType;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setResolution(int res) {
        resolution = res;
    }

    public int getResolution() {
        return resolution;
    }
}
