package com.example.prime.moviesremainder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.lang.Object;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//TODO: use opening.json in rottentomatoes.com


public class MainActivity extends AppCompatActivity {

    private String data;
    private URL url = null;
    private StringBuffer buffer;
    private char[] chars;
    private JSONObject json;
    private String title;
   // private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            public void run() {

                BufferedReader reader = null;
                try
                {
                    try {
                        url = new URL("http://private-84e2da-themoviedb.apiary-proxy.com/3/movie/now_playing?api_key=3f3e051781bd957a06fa49b7382263a4");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    try {
                        reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buffer = new StringBuffer();
                    int read;

                    chars = new char[1024];
                    try {
                        while ((read = reader.read(chars)) != -1)
                            buffer.append(chars, 0, read);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    data = buffer.toString();

                }
                finally {
                    if (reader != null)
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }

                try {
                    json = new JSONObject(data);

                    title =  json.getJSONObject("dates").getString("minimum");
                    Log.d("1", title);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        Log.d("12", "testss");

//        BufferedReader reader = null;
//        try
//        {
//            try {
//                url = new URL("http://private-84e2da-themoviedb.apiary-proxy.com/3/movie/now_playing?api_key=3f3e051781bd957a06fa49b7382263a4");
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            buffer = new StringBuffer();
//            int read;
//
//            chars = new char[1024];
//            try {
//                while ((read = reader.read(chars)) != -1)
//                    buffer.append(chars, 0, read);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//         data = buffer.toString();
//
//         }
//        finally {
//            if (reader != null)
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//
//        try {
//            json = new JSONObject(data);
//
//            title = (String) json.get("dates");
//            Log.d("1", title);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
