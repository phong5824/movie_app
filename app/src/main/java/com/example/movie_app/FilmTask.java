package com.example.movie_app;

import android.os.AsyncTask;
import java.util.ArrayList;

public class FilmTask extends AsyncTask<Void, Void, ArrayList<Film>> {

    public interface FilmAvailableListener {
        void filmAvailable(ArrayList<Film> films);
    }

    private FilmAvailableListener filmAvailable;

    public FilmTask(FilmAvailableListener filmAvailable) {
        this.filmAvailable = filmAvailable;
    }

    @Override
    protected ArrayList<Film> doInBackground(Void... voids) {
        return FilmRepository.getHardcodedFilms();
    }

    @Override
    protected void onPostExecute(ArrayList<Film> films) {
        if (filmAvailable != null) {
            filmAvailable.filmAvailable(films);
        }
    }
}