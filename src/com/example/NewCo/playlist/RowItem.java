package com.example.NewCo.playlist;

import javax.annotation.concurrent.Immutable;

@Immutable
public class RowItem {

  private final SongType mSongType;

  private final String mUrl;

  public RowItem(SongType songType, String url) {
    mSongType = songType;
    mUrl = url;
  }

  public String getUrl() {
    return mUrl;
  }

  public SongType getSongType() {
    return mSongType;
  }
}
