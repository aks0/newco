package com.example.NewCo.model;

public class VideoItem {

    private final String mTitle;
    private final String mVideoId;

    public VideoItem(String title, String videoId) {
        mTitle = title;
        mVideoId = videoId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getVideoId() {
        return mVideoId;
    }

    @Override
    public String toString() {
        return mTitle + " " + mVideoId;
    }
}
