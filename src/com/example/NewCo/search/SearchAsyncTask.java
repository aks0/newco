package com.example.NewCo.search;

import android.os.AsyncTask;

/**
 * Created by akshayk on 6/13/15.
 */
public class SearchAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        String queryTerm = params[0];
        Search search = new Search();
        search.setupSearch();
        search.searchForQuery(queryTerm);
        return null;
    }
}
