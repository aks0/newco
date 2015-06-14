package com.example.NewCo;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.example.NewCo.model.VideoItem;
import com.example.NewCo.search.SearchAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class NewCoActivity extends ListActivity {

  private TextView text;
  private List<VideoItem> mListValues;
  private NewCoListAdapter mNewCoListAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    text = (TextView) findViewById(R.id.mainText);

    // Get the intent, verify the action and get the query
    Intent intent = getIntent();
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      doSearch(query);
    }

    mListValues = new ArrayList<VideoItem>();
    mListValues.add(new VideoItem("Nagada Sang Dhol", "cLIQzxgFeNE"));
    mListValues.add(new VideoItem("Balam Pichkari", "YKDZ4zQCQnI"));
    mListValues.add(new VideoItem("Dum Maaro Dum", "_CMBCfxN1lU"));
    mListValues.add(new VideoItem("Titli", "Jet3KGOIh8w"));
    mListValues.add(new VideoItem("Love Mera Hit Hit", "zG1CLCBNhpo"));
    mListValues.add(new VideoItem("Kabira", "jHNNMj5bNQw"));
    mListValues.add(new VideoItem("Ajab Lehar", "G-pRQFsHUo0"));

    mNewCoListAdapter = new NewCoListAdapter(this);
    mNewCoListAdapter.setItems(mListValues);

    // assign the list adapter
    setListAdapter(mNewCoListAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the options menu from XML
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.options_menu, menu);

    // Get the SearchView and set the searchable configuration
    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setIconifiedByDefault(true);

    return super.onCreateOptionsMenu(menu);
  }

  // when an item of the list is clicked
  @Override
  protected void onListItemClick(ListView list, View view, int position, long id) {
    super.onListItemClick(list, view, position, id);

    // String selectedItem = (String) getListView().getItemAtPosition(position);
    VideoItem selectedItem = (VideoItem) getListAdapter().getItem(position);

    text.setText("You clicked " + selectedItem + " at position " + position);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    mNewCoListAdapter.releaseLoaders();
  }

  private void doSearch(String query) {
    new SearchAsyncTask().execute("deepika padukone");
  }
}
