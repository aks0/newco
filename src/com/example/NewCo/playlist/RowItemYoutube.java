package com.example.NewCo.playlist;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.concurrent.Immutable;

@Immutable
public class RowItemYoutube implements RowItem {

  public enum ThumbnailQuality {
    LOW("1"),
    HIGH("0");

    public final String quality;

    ThumbnailQuality(String quality) {
      this.quality = quality;
    }
  }

  private final String mVideoId;
  private final ThumbnailQuality mThumbnailQuality;

  private Uri mThumbnailUri;

  public RowItemYoutube(
      String videoId,
      ThumbnailQuality thumbnailQuality) {
    mVideoId = videoId;
    mThumbnailQuality = thumbnailQuality;
  }

  protected RowItemYoutube(Parcel in) {
    mVideoId = in.readString();
    mThumbnailQuality = (ThumbnailQuality) in.readSerializable();
  }

  @Override
  public SongType getSongType() {
    return SongType.YOUTUBE;
  }

  public String getVideoId() {
    return mVideoId;
  }

  public ThumbnailQuality getThumbnailQuality() {
    return mThumbnailQuality;
  }

  public Uri getThumbnailUri() {
    if (mThumbnailUri == null) {
      initUri();
    }

    return mThumbnailUri;
  }

  private void initUri() {
    Uri.Builder thumbnailUriBuilder = new Uri.Builder()
        .scheme("https")
        .authority("img.youtube.com")
        .appendPath("vi")
        .appendPath(mVideoId)
        .appendPath(mThumbnailQuality.quality + ".jpg");
    mThumbnailUri = thumbnailUriBuilder.build();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mVideoId);
    dest.writeSerializable(mThumbnailQuality);
  }

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    @Override
    public RowItemYoutube createFromParcel(Parcel source) {
      return new RowItemYoutube(source);
    }

    @Override
    public RowItemYoutube[] newArray(int size) {
      return new RowItemYoutube[size];
    }
  };
}
