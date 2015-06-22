package com.example.NewCo.playlist;

import android.content.Context;

public class RowItemViewFactory {

  private final Context mContext;

  public RowItemViewFactory(Context context) {
    mContext = context;
  }

  public RowItemView createRowItemView(RowItem rowItem) {
    switch (rowItem.getSongType()) {
      case YOUTUBE:
        return createRowItemViewForYouTube((RowItemYoutube) rowItem);
      default:
        throw new IllegalStateException("Unknown SongType " + rowItem.getSongType());
    }
  }

  private RowItemViewForYoutube createRowItemViewForYouTube(RowItemYoutube rowItem) {
    return new RowItemViewForYoutube(mContext);
  }
}
