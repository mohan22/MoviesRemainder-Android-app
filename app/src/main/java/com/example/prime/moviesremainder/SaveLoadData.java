package com.example.prime.moviesremainder;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by prime on 13-12-2015.
 */
public class SaveLoadData
{
   // public  static Context context;
    public static void SaveData(ArrayList<Movie> movielist,Context context)
    {
       // context = this.getApplicationContext();
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor sedit = spref.edit();

        for (int i=0;i<movielist.size();i++)
        {
            sedit.putString("id"+i,movielist.get(i).id);
            sedit.putString("title"+i,movielist.get(i).title);
            sedit.putString("language"+i,movielist.get(i).language);
            sedit.putString("releaseDate"+i,movielist.get(i).releaseDate);
            sedit.putLong("voteCount" + i, movielist.get(i).voteCount);
            sedit.putFloat("popularity" + i, (float) movielist.get(i).popularity);
            sedit.putFloat("average" + i, (float) movielist.get(i).average);
            //sedit.putString("releaseDate" + i, movielist.get(i).releaseDate);
            sedit.putString("poster"+i,movielist.get(i).poster);
        }

        sedit.putInt("size",movielist.size());
        sedit.commit();
    }

    public static ArrayList<Movie> LoadData(Context context)
    {
        context = context.getApplicationContext();
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(context);
       // SharedPreferences.Editor sedit = spref.edit();

        ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
        int size = spref.getInt("size",0);


        for (int i=0;i<size;i++)
        {
            Movie movie = new Movie();
            movie.id = spref.getString("id"+i,null);
            movie.title =spref.getString("title"+i,null);
            movie.language = spref.getString("language"+i,null);
            movie.releaseDate=spref.getString("releaseDate"+i,null);
            movie.voteCount = spref.getLong("voteCount"+i,0);
            movie.popularity = spref.getFloat("popularity"+i,0);
            movie.average = spref.getFloat("average"+i,0);
            movie.poster = spref.getString("poster"+i,null);

            listOfMovies.add(movie);

        }

        return listOfMovies;



    }
}
