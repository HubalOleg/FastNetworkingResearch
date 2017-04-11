package com.oleg.hubal.fastnetworkingresearch.async;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.oleg.hubal.fastnetworkingresearch.json.Repo;
import com.oleg.hubal.fastnetworkingresearch.json.RepoListJson;

/**
 * Created by User on 11.04.2017.
 */

public class ParseGsonTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "ParseGsonTask";

    private final String mJson;
    private final Gson mGson;

    public ParseGsonTask(String json) {
        mJson = json;
        mGson = new Gson();
    }

    @Override
    protected Void doInBackground(Void... params) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            parseJson(mJson);
        }

        long end = System.currentTimeMillis() - start;
        Log.d(TAG, "doInBackground: " + end);
        return null;
    }

    private void parseJson(String json) {
//        long startTime = System.currentTimeMillis();
        RepoListJson repoList = mGson.fromJson(json, RepoListJson.class);
        for (int i = 0; i < repoList.repos.size(); i++) {
            Repo repo = repoList.repos.get(i);
//            Log.d("FlatBuffers", "Repo #" + i + ", id: " + repo.id);
        }
        Log.d(TAG, "parseJson: " + repoList.getRepos().size());
//        long endTime = System.currentTimeMillis() - startTime;

//        Log.d(TAG, "parseJson: " + endTime);
    }
}
