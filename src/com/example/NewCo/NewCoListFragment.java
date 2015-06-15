package com.example.NewCo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.NewCo.model.VideoItem;

import java.util.ArrayList;
import java.util.List;

public class NewCoListFragment extends ListFragment {

  private List<VideoItem> mListValues;
  private NewCoListAdapter mNewCoListAdapter;

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.newco_list_fragment, container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    mListValues = new ArrayList<VideoItem>();
    mListValues.add(new VideoItem("Nagada Sang Dhol", "cLIQzxgFeNE"));
    mListValues.add(new VideoItem("Balam Pichkari", "YKDZ4zQCQnI"));
    mListValues.add(new VideoItem("Dum Maaro Dum", "_CMBCfxN1lU"));
    mListValues.add(new VideoItem("Titli", "Jet3KGOIh8w"));
    mListValues.add(new VideoItem("Love Mera Hit Hit", "zG1CLCBNhpo"));
    mListValues.add(new VideoItem("Kabira", "jHNNMj5bNQw"));
    mListValues.add(new VideoItem("Ajab Lehar", "G-pRQFsHUo0"));

    mNewCoListAdapter = new NewCoListAdapter(getActivity());
    mNewCoListAdapter.setItems(mListValues);

    setListAdapter(mNewCoListAdapter);
  }

  // when an item of the list is clicked
  @Override
  public void onListItemClick(ListView list, View view, int position, long id) {
    super.onListItemClick(list, view, position, id);

    // String selectedItem = (String) getListView().getItemAtPosition(position);
    VideoItem selectedItem = (VideoItem) getListAdapter().getItem(position);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    mNewCoListAdapter.releaseLoaders();
  }
}
