package com.example.NewCo.playlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.NewCo.R;

public class RowItemViewForYoutube extends LinearLayout
    implements RowItemView {

  public RowItemViewForYoutube(Context context) {
    this(context, null);
  }

  public RowItemViewForYoutube(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RowItemViewForYoutube(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    LayoutInflater inflater = (LayoutInflater)
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.row_item_view_for_youtube, this, true);

    ImageView imageView = (ImageView) findViewById(R.id.thumbnail);
    imageView.setImageResource(R.drawable.loading_thumbnail);
  }
}
