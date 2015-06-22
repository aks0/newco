package com.example.NewCo.playlist;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.NewCo.R;
import com.squareup.picasso.Picasso;

public class RowItemViewForYoutube extends LinearLayout
    implements RowItemView {

  private final Context mContext;
  private final Picasso mPicasso;
  private final ImageView mThumbnailImageView;

  public RowItemViewForYoutube(Context context) {
    this(context, null);
  }

  public RowItemViewForYoutube(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RowItemViewForYoutube(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    mContext = context;
    mPicasso = Picasso.with(mContext);

    LayoutInflater inflater = (LayoutInflater)
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.row_item_view_for_youtube, this, true);

    mThumbnailImageView = (ImageView) findViewById(R.id.thumbnail);
    mThumbnailImageView.setImageResource(R.drawable.loading_thumbnail);
  }

  public void setThumbnailSource(Uri uri) {
    mPicasso.setIndicatorsEnabled(true);
    mPicasso.load(uri).into(mThumbnailImageView);
  }
}
