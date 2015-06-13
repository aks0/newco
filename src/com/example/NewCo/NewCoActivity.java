package com.example.NewCo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
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

    private void testSearch() {
        new SearchAsyncTask().execute("deepika padukone");
    }
}
