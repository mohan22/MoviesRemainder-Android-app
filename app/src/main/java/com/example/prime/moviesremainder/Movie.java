package com.example.prime.moviesremainder;

/**
 * Created by Mohan on 11/10/15.
 */
public class Movie
{
    public String id;
    public String poster;
    public String language;
    public String title;
    public String releaseDate;
    public double popularity;
    public long voteCount;
    public double average;


    public Movie(String Id,String Poster,String Language,String ReleaseDate,double Popularity,long VoteCount,double Average)
    {
        id=Id;
        poster=Poster;
        language=Language;
        releaseDate=ReleaseDate;
        popularity=Popularity;
        voteCount = VoteCount;
        average = Average;
    }

}
