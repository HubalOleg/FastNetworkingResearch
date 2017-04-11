package com.oleg.hubal.fastnetworkingresearch.async;

import android.os.AsyncTask;
import android.util.Log;

import com.oleg.hubal.fastnetworkingresearch.flat.Repo;
import com.oleg.hubal.fastnetworkingresearch.flat.ReposList;

import java.nio.ByteBuffer;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 11.04.2017.
 */

public class ParseByteTask extends AsyncTask<Void, Void, Void> {

    private final byte[] mBytes;
    private ReposList reposListFlat;

    public ParseByteTask(byte[] bytes) {
        mBytes = bytes;
    }

    @Override
    protected Void doInBackground(Void... params) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            loadFlatBuffer(mBytes);
        }

        long end = System.currentTimeMillis() - start;
        Log.d(TAG, "doInBackground: " + end);
        return null;
    }

    private void loadFlatBuffer(byte[] bytes) {
//        long startTime = System.currentTimeMillis();
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        reposListFlat = ReposList.getRootAsReposList(bb);
        for (int i = 0; i < reposListFlat.reposLength(); i++) {
            Repo repos = reposListFlat.repos(i);
        }
        Log.d(TAG, "loadFlatBuffer: " + reposListFlat.reposLength());
//        long endTime = System.currentTimeMillis() - startTime;

    }
}
