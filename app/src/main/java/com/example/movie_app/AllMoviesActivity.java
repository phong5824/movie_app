package com.example.movie_app;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllMoviesActivity extends AppCompatActivity implements FilmAvailable {

    private ArrayList<Film> films;
    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);

        // Retrieve the list of movies
        ArrayList<Film> allMovies = FilmRepository.getHardcodedFilms();

        // Set up RecyclerView with GridLayoutManager
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMovies);
        int numberOfColumns = 3; // Number of columns in each row
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        // Create and set up FilmAdapter
        FilmAdapter filmAdapter = new FilmAdapter(allMovies, this);
        recyclerView.setAdapter(filmAdapter);

        // Set up SearchView
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SearchView", "Query text changed: " + newText);
                filmAdapter.filter(newText); // Gọi phương thức filter của adapter
                return true;
            }
        });
    }

    @Override
    public void filmAvailable(Film film) {
        // Implement the interface method if needed
        // This method is called when a film is available
    }
}
