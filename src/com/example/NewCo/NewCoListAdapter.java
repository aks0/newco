package com.example.NewCo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.NewCo.model.NewCoConstants;
import com.example.NewCo.model.VideoItem;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewCoListAdapter extends BaseAdapter {

    private final Context mContext;
    private final Map<YouTubeThumbnailView, YouTubeThumbnailLoader> mThumbnailViewToLoaderMap;
    private final ThumbnailListener mThumbnailListener;

    private List<VideoItem> mItems = new ArrayList<VideoItem>();

    public NewCoListAdapter(Context context) {
        mContext = context;
        mThumbnailViewToLoaderMap = new HashMap<YouTubeThumbnailView, YouTubeThumbnailLoader>();
        mThumbnailListener = new ThumbnailListener();
    }

    public void releaseLoaders() {
        for (YouTubeThumbnailLoader loader : mThumbnailViewToLoaderMap.values()) {
            loader.release();
        }
    }

    public void setItems(List<VideoItem> items) {
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoItem item = mItems.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.newco_list_item, parent, false);

            TextView textView = (TextView) convertView.findViewById(R.id.firstLine);
            textView.setText(item.getTitle());

            YouTubeThumbnailView thumbnail = (YouTubeThumbnailView)
                    convertView.findViewById(R.id.thumbnail);
            thumbnail.setTag(item.getVideoId());
            thumbnail.initialize(NewCoConstants.ANDROID_DEVELOPER_KEY, mThumbnailListener);
        } else {
            TextView textView = (TextView) convertView.findViewById(R.id.firstLine);
            textView.setText(item.getTitle());

            YouTubeThumbnailView thumbnail = (YouTubeThumbnailView)
                    convertView.findViewById(R.id.thumbnail);
            YouTubeThumbnailLoader loader = mThumbnailViewToLoaderMap.get(thumbnail);
            if (loader == null) {
                thumbnail.setTag(item.getVideoId());
            } else {
                thumbnail.setImageResource(R.drawable.loading_thumbnail);
                loader.setVideo(item.getVideoId());
            }
        }

        return convertView;
    }

    private final class ThumbnailListener implements
            YouTubeThumbnailView.OnInitializedListener,
            YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        @Override
        public void onInitializationSuccess(
                YouTubeThumbnailView view,
                YouTubeThumbnailLoader loader) {
            loader.setOnThumbnailLoadedListener(this);
            mThumbnailViewToLoaderMap.put(view, loader);
            view.setImageResource(R.drawable.loading_thumbnail);
            String videoId = (String) view.getTag();
            loader.setVideo(videoId);
        }

        @Override
        public void onInitializationFailure(
                YouTubeThumbnailView view,
                YouTubeInitializationResult loader) {
            view.setImageResource(R.drawable.no_thumbnail);
        }

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView view, String videoId) {
        }

        @Override
        public void onThumbnailError(
                YouTubeThumbnailView view,
                YouTubeThumbnailLoader.ErrorReason errorReason) {
            view.setImageResource(R.drawable.no_thumbnail);
        }
    }
}
