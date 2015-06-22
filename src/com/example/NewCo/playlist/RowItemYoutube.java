package com.example.NewCo.playlist;

import javax.annotation.concurrent.Immutable;

@Immutable
public class RowItemYoutube extends RowItem {

  public RowItemYoutube(SongType songType, String url) {
    super(songType, url);
  }
}
