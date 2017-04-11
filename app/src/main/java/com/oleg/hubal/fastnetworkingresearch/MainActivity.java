package com.oleg.hubal.fastnetworkingresearch;

import android.os.Bundle;
import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oleg.hubal.fastnetworkingresearch.async.ParseByteTask;
import com.oleg.hubal.fastnetworkingresearch.async.ParseGsonTask;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button btnParseJson;
    Button btnParseBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParseJson = (Button) findViewById(R.id.btn_parse_gson);
        btnParseBuffer = (Button) findViewById(R.id.btn_parse_buffer);
        ButterKnife.bind(MainActivity.this);

        btnParseJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseGson();
            }
        });
        btnParseBuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseBuffer();
            }
        });
    }

    private void parseGson() {
        try {
            String json = loadJsonStringBlocking(R.raw.repos_json);
            new ParseGsonTask(json).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseBuffer() {
        try {
            new ParseByteTask(loadBytesBlocking(R.raw.repos_flat)).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] loadBytesBlocking(@RawRes int rawId) throws IOException {
        final InputStream inputStream = getResources().openRawResource(rawId);
        final byte[] bytes = convertStreamToByteArray(inputStream);
        inputStream.close();
        return bytes;
    }

    private byte[] convertStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[10240];
        int i = Integer.MAX_VALUE;
        while ((i = is.read(buff, 0, buff.length)) > 0) {
            baos.write(buff, 0, i);
        }

        return baos.toByteArray();
    }

    public String loadJsonStringBlocking(int rawId) throws IOException {
        InputStream is = getResources().openRawResource(rawId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        int n;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
        }
        is.close();

        return writer.toString();
    }
}
