package com.example.movie_app;

import java.io.Serializable;
import java.util.ArrayList;

public class Film implements Serializable {
    private int ID;
    private String title;
    private float rating;
    private String date;
    private String posterPath;
    private String backDropPath;
    private String overview;
    private int runtime;
    private String certification;
    private String genres;
    private String director;
    private String actor;
    private String time;

    public Film() {
    }



    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String _time) {
        this.time = _time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public int getID(){
        return ID;
    }
    public void setID(int _ID) {
        this.ID = _ID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String _title) {
        this.title = _title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        String genreList = "";

        for (int i = 0; i < genres.size(); i++) {
            genreList += genres.get(i);
            if ((genres.size() - i) > 1) {
                genreList += " | ";
            }
            this.genres = genreList;
        }
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        if (certification.equals("")) {
            certification = "--";
        }
        this.certification = certification;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = "Director: " + director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        String actorList = "";

        for (String s : actor) {
            actorList += s + " | ";
            this.actor = actorList;
        }
    }

    @Override
    public String toString() {
        return title + " " + genres + " " + director + " " + actor;
    }
}

