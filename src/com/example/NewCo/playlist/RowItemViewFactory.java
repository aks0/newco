package com.example.NewCo.playlist;

import android.content.Context;
import android.net.Uri;

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
    RowItemViewForYoutube view = new RowItemViewForYoutube(mContext);
    Uri.Builder thumbnailUriBuilder = new Uri.Builder()
        .scheme("https")
        .authority("img.youtube.com")
        .appendPath("vi")
        .appendPath(rowItem.getUrl())
        .appendPath(rowItem.getThumbnailQuality().quality + ".jpg");
    view.setThumbnailSource(thumbnailUriBuilder.build());
    return view;
  }
}
