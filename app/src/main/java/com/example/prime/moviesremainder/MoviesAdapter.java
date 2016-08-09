package com.example.prime.moviesremainder;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by prime on 07-08-2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private ArrayList<Movie> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        //public TextView mTextView;
        public TextView title, year, genre;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public MoviesAdapter(ArrayList<Movie> myDataset) {
       this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
       // LayoutInflater inflater = (LayoutInflater).getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_row, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder viewHolder, int i) {
        Movie movie = mDataset.get(i);
        viewHolder.title.setText(movie.title);
        viewHolder.genre.setText(movie.language);
        viewHolder.year.setText(movie.releaseDate);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
