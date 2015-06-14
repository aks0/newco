package com.example.NewCo.search;

import android.util.Log;
import com.example.NewCo.model.NewCoConstants;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.List;

public class Search {

  private static final String TAG = Search.class.getSimpleName();
  private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

  private final HttpTransport mHttpTransport = new NetHttpTransport();
  private final JsonFactory mJsonFactory = new JacksonFactory();

  private YouTube mYoutube;
  private YouTube.Search.List mSearch;

  public Search() {
  }

  public void setupSearch() {
    try {
      mYoutube = new YouTube.Builder(
        mHttpTransport,
        mJsonFactory,
        new HttpRequestInitializer() {
          @Override
          public void initialize(HttpRequest httpRequest) throws IOException {
          }
        }).setApplicationName("newco-youtube-cmdline-search-sample")
        .build();

      mSearch = mYoutube.search()
        .list("id,snippet")
        .setKey(NewCoConstants.BROWSER_DEVELOPER_KEY)
        .setType("video")
        .setFields(
            "items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)")
        .setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
    } catch (GoogleJsonResponseException e) {
      Log.e(TAG, "There was a service error: " + e.getDetails().getCode() + " : "
          + e.getDetails().getMessage());
    } catch (IOException e) {
      Log.e(TAG, "There was an IO error: " + e.getCause() + " : " + e.getMessage());
    }
  }

  public void searchForQuery(String queryTerm) {
    mSearch.setQ(queryTerm);
    try {
      SearchListResponse searchResponse = mSearch.execute();
      List<SearchResult> searchResultList = searchResponse.getItems();
      if (searchResultList != null) {
        Log.d(TAG, "Results = " + searchResultList);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
