package com.example.prime.moviesremainder;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
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
import java.util.ArrayList;

import static android.view.ViewGroup.*;
//TODO: use opening.json in rottentomatoes.com


public class MainActivity extends AppCompatActivity {

    private String data;
    private URL url = null;
    private StringBuffer buffer;
    private char[] chars;
    private JSONObject json;
    private JSONArray jsArray;
    private String title;


    private String movietitle;
    private Manager manager;
   // private String data;


    public void AddTextViewToList()
    {
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

       // Log.d("143",title);
        final LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout);
        final LayoutParams lp = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        for (int i=0;i<manager.moviesList.size();i++)
        {


            TextView tmp = new TextView(this);
            tmp.setLayoutParams(lp);
            tmp.setText(manager.moviesList.get(i).title);
            tmp.setTextSize(20);
            tmp.setGravity(Gravity.CENTER_HORIZONTAL);
            myLayout.addView(tmp);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new Manager();

        final TextView date=new TextView(this);

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
                    Log.d("123", title);


//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            date.setLayoutParams(lp);
//                            date.setText(title);
//                            myLayout.addView(date);
//
//
//                        }
//                    });

                    jsArray = json.getJSONArray("results");





                    for(int i=0;i<jsArray.length();i++)
                    {
                        json = jsArray.getJSONObject(i);
                        movietitle=json.getString("title");
                        Log.d("1235",movietitle);
                        manager.moviesList.add(new Movie(json.getString("id"),"",json.getString("original_language"),movietitle,json.getString("release_date"),Double.parseDouble(json.getString("popularity")),Long.parseLong(json.getString("vote_count")),Double.parseDouble(json.getString("vote_average"))));
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                AddTextViewToList(movietitle);
//                            }
//                        });

//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AddTextViewToList();
                        }
                    });


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
