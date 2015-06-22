package com.example.NewCo.playlist;

import javax.annotation.concurrent.Immutable;

@Immutable
public class RowItemYoutube extends RowItem {

  public enum ThumbnailQuality {
    LOW("1"),
    HIGH("0");

    public final String quality;

    ThumbnailQuality(String quality) {
      this.quality = quality;
    }
  }

  private ThumbnailQuality mThumbnailQuality;

  public RowItemYoutube(String url, ThumbnailQuality thumbnailQuality) {
    super(SongType.YOUTUBE, url);

    mThumbnailQuality = thumbnailQuality;
  }

  public ThumbnailQuality getThumbnailQuality() {
    return mThumbnailQuality;
  }
}
