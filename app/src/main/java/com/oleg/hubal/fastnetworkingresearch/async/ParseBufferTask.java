package com.oleg.hubal.fastnetworkingresearch.async;

import android.os.AsyncTask;
import android.util.Log;

import com.oleg.hubal.fastnetworkingresearch.flat.ReposList;
import com.oleg.hubal.flatbuffersparser.FlatBuffersParser;

import java.nio.ByteBuffer;

/**
 * Created by User on 11.04.2017.
 */

public class ParseBufferTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "ParseBufferTask";

    private final String mJson;
    private final String mSchema;
    private final FlatBuffersParser mFlatBuffersParser;

    public ParseBufferTask(String json, String schema) {
        mJson = json;
        mSchema = schema;

        mFlatBuffersParser = new FlatBuffersParser();
    }

    @Override
    protected Void doInBackground(Void... params) {
        long start = System.currentTimeMillis();

        parseJsonWithBuffer(mJson, mSchema);


        long end = System.currentTimeMillis() - start;
        Log.d(TAG, "doInBackground: " + end);
        return null;
    }


    private void parseJsonWithBuffer(String json, String schema) {
//        long startTime = System.currentTimeMillis();
        ByteBuffer byteBuffer = mFlatBuffersParser.parseJson(json, schema);
        ReposList reposList = ReposList.getRootAsReposList(byteBuffer);
        for (int i = 0; i < reposList.reposLength(); i++) {
            com.oleg.hubal.fastnetworkingresearch.flat.Repo repos = reposList.repos(i);
//            Log.d("FlatBuffers", "Repo #" + i + ", id: " + repos.id());
        }
        Log.d(TAG, "parseJsonWithBuffer: " + reposList.reposLength());

//        long endTime = System.currentTimeMillis() - startTime;
//        Log.d(TAG, "parseJsonBuffer: " + endTime);
    }
}
