package com.example.movie_app;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class FilmDetailActivity extends AppCompatActivity {

    private RecyclerView dateRecyclerView;
    private RecyclerView timeRecyclerView;
    private RecyclerView theatreRecyclerView;
    private ImageView  nextButton;
    private Film selectedFilm;

    private String selectedDate;
    private String selectedTime;
    private String selectedTheatre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        dateRecyclerView = findViewById(R.id.dateRecyclerView);
        timeRecyclerView = findViewById(R.id.timeRecyclerView);
        theatreRecyclerView = findViewById(R.id.theatreRecyclerView);

        // Thiết lập layout manager cho RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // Trước khi thiết lập LayoutManager, kiểm tra xem đã thiết lập chưa
        if (dateRecyclerView.getLayoutManager() == null) {
            LinearLayoutManager dateLayoutManager = new LinearLayoutManager(this);
            dateRecyclerView.setLayoutManager(dateLayoutManager);
        }

        if (timeRecyclerView.getLayoutManager() == null) {
            LinearLayoutManager timeLayoutManager = new LinearLayoutManager(this);
            timeRecyclerView.setLayoutManager(timeLayoutManager);
        }

        if (theatreRecyclerView.getLayoutManager() == null) {
            LinearLayoutManager theatreLayoutManager = new LinearLayoutManager(this);
            theatreRecyclerView.setLayoutManager(theatreLayoutManager);
        }


        // Tạo Adapter và thiết lập cho RecyclerView
        ShowtimeAdapter dateAdapter = new ShowtimeAdapter(ShowtimeData.getDates());
        ShowtimeAdapter timeAdapter = new ShowtimeAdapter(ShowtimeData.getAllTimes());
        ShowtimeAdapter theatreAdapter = new ShowtimeAdapter(ShowtimeData.getAllTheaTres());

        dateRecyclerView.setAdapter(dateAdapter);
        timeRecyclerView.setAdapter(timeAdapter);
        theatreRecyclerView.setAdapter(theatreAdapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        dateRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        timeRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        theatreRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));


        dateAdapter.setOnItemClickListener(new ShowtimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Reset selected state for all items
                resetSelectedState(dateAdapter);

                // Check if the item is available
                if (ShowtimeData.getDates().get(position).isAvailable()) {
                    // Set selected state for the clicked item
                    dateAdapter.getShowtimeItems().get(position).setSelected(true);

                    // Notify the adapter that data has changed
                    dateAdapter.notifyDataSetChanged();

                    // Update other views as needed
                    selectedDate = ShowtimeData.getDates().get(position).getName();
                    timeAdapter.updateItems(ShowtimeData.getTimes(selectedDate));
                    theatreAdapter.updateItems(new ArrayList<>());
                } else {
                    Toast.makeText(FilmDetailActivity.this, "This date is not available.", Toast.LENGTH_SHORT).show();

                    selectedTime = null;
                    selectedTheatre = null;

                    // Update time and theatre adapters accordingly
                    timeAdapter.updateItems(new ArrayList<>());
                    theatreAdapter.updateItems(new ArrayList<>());
                }
            }
        });

        timeAdapter.setOnItemClickListener(new ShowtimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Reset selected state for all items
                resetSelectedState(timeAdapter);

                // Check if a date is selected
                if (selectedDate != null) {
                    // Check if the item is available
                    if (ShowtimeData.getTimes(selectedDate).get(position).isAvailable()) {
                        // Set selected state for the clicked item
                        timeAdapter.getShowtimeItems().get(position).setSelected(true);

                        // Notify the adapter that data has changed
                        timeAdapter.notifyDataSetChanged();

                        // Update other views as needed
                        selectedTime = ShowtimeData.getTimes(selectedDate).get(position).getName();
                        theatreAdapter.updateItems(ShowtimeData.getTheatres(selectedDate, selectedTime));
                    } else {
                        // Show a message indicating that the item is not available
                        Toast.makeText(FilmDetailActivity.this, "This time is not available.", Toast.LENGTH_SHORT).show();

                        // Clear selected theatre when time is not available
                        selectedTheatre = null;

                        // Update theatre adapter accordingly
                        theatreAdapter.updateItems(new ArrayList<>());
                    }
                } else {
                    // Show a message indicating that a date must be selected first
                    Toast.makeText(FilmDetailActivity.this, "Please select a date first.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        theatreAdapter.setOnItemClickListener(new ShowtimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Reset selected state for all items in theatreAdapter
                resetSelectedState(theatreAdapter);

                // Check if a date and time are selected
                if (selectedDate != null && selectedTime != null) {
                    // Check if the item is available
                    if (ShowtimeData.getTheatres(selectedDate, selectedTime).get(position).isAvailable()) {
                        // Set selected state for the clicked item
                        theatreAdapter.getShowtimeItems().get(position).setSelected(true);

                        // Notify the adapter that data has changed
                        theatreAdapter.notifyDataSetChanged();

                        // Update other views as needed
                        selectedTheatre = ShowtimeData.getTheatres(selectedDate, selectedTime).get(position).getName();
                    } else {
                        // Show a message indicating that the item is not available
                        Toast.makeText(FilmDetailActivity.this, "This theatre is not available.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show a message indicating that a date and time must be selected first
                    Toast.makeText(FilmDetailActivity.this, "Please select a date and time first.", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Get the selected film from the previous activity
        selectedFilm = (Film) getIntent().getSerializableExtra("MyClass");
        if (selectedFilm != null) {
            setFilmInfo(selectedFilm);
        } else {
            Toast.makeText(this, "Film information is not available", Toast.LENGTH_SHORT).show();
            finish();
        }


        nextButton = findViewById(R.id.nextButton);

        // Set up button click listener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedFilm != null && selectedDate != null && selectedTime != null && selectedTheatre != null) {
                    String filmTitle = selectedFilm.getTitle();
                    String filmImageResId = selectedFilm.getPosterPath(); // Giả sử có một phương thức getImageResId() để lấy ID của hình ảnh

                    Intent seatSelectionIntent = new Intent(FilmDetailActivity.this, SeatSelectionActivity.class);

                    seatSelectionIntent.putExtra("filmTitle", filmTitle);
                    seatSelectionIntent.putExtra("filmImageResId", filmImageResId);
                    seatSelectionIntent.putExtra("selectedDate", selectedDate);
                    seatSelectionIntent.putExtra("selectedTime", selectedTime);
                    seatSelectionIntent.putExtra("selectedTheatre", selectedTheatre);

                    startActivity(seatSelectionIntent);
                } else {
                    Toast.makeText(FilmDetailActivity.this, "Please select date, time, and theatre before proceeding.", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    private void resetSelectedState(ShowtimeAdapter adapter) {
        for (ShowtimeItem item : adapter.getShowtimeItems()) {
            item.setSelected(false);
        }
    }

    public void onUndoClick(View view) {
        super.onBackPressed();
    }


    private void setFilmInfo(Film film) {
        if (film != null) {

            TextView overview = findViewById(R.id.textOverview);
            overview.setText(film.getOverview());

            TextView title = findViewById(R.id.titleFilm);
            title.setText(film.getTitle());

            TextView time = findViewById(R.id.timeFilm);
            time.setText(film.getTime());

            // Assuming you have a method to get the list of genres from the Film object
            String genres = film.getGenres();
            TextView genresTextView = findViewById(R.id.textGenres);
            genresTextView.setText(genres);

            String ratingString = String.format("%.1f", film.getRating());
            TextView rating = findViewById(R.id.ratingFilm);
            rating.setText(ratingString);
        } else {
            // Handle the case when film is null, e.g., show an error message
            Toast.makeText(this, "Film information is not available", Toast.LENGTH_SHORT).show();
            // You might also want to finish the activity or take appropriate action
            finish();
        }
    }

}
