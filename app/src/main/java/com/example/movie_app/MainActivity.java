package com.example.movie_app;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager;
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FilmAvailable {

    private boolean isLoggedIn = false;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Film> films;
    private UserDBHelper userDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDBHelper = new UserDBHelper(this);

        CarouselRecyclerview carouselRecyclerview = findViewById(R.id.imageSlider);

        ArrayList<Film> newestFilms = FilmRepository.getNewestFilms();

        FilmSliderAdapter sliderAdapter = new FilmSliderAdapter(newestFilms, this);

        carouselRecyclerview.setAdapter(sliderAdapter);

        carouselRecyclerview.set3DItem(true);
        carouselRecyclerview.setInfinite(true);
        carouselRecyclerview.setAlpha(true);
        carouselRecyclerview.setIsScrollingEnabled(true);
        carouselRecyclerview.setIntervalRatio(0.5f);

        CarouselLayoutManager carouselLayoutManager = carouselRecyclerview.getCarouselLayoutManager();
        int currentlyCenterPosition = carouselRecyclerview.getSelectedPosition();




        mRecyclerView = findViewById(R.id.recycler_allmovie);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(horizontalLayoutManager);

        // Đặt khoảng cách giữa các mục cho recycler_view (All Movies) (ở đây là 16dp)
        int spacingInPixelsAllMovies = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixelsAllMovies));


        ArrayList<Film> allFilms = FilmRepository.getHardcodedFilms();
        FilmAdapter mAdapter = new FilmAdapter(allFilms, this); // Corrected to use allFilms
        mRecyclerView.setAdapter(mAdapter);





        RecyclerView topRatedHorizontalRecyclerView = findViewById(R.id.topRatedHorizontalRecyclerView);
        LinearLayoutManager topRatedHorizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topRatedHorizontalRecyclerView.setLayoutManager(topRatedHorizontalLayoutManager);

        // Đặt khoảng cách giữa các mục cho topRatedHorizontalRecyclerView (Top Rated) (ở đây là 16dp)
        int spacingInPixelsTopRated = getResources().getDimensionPixelSize(R.dimen.spacing);
        topRatedHorizontalRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixelsTopRated));

        ArrayList<Film> topRatedFilms = FilmRepository.getTopRatedFilms();

        // Create an adapter for the top-rated films displayed horizontally
        FilmAdapter topRatedHorizontalAdapter = new FilmAdapter(topRatedFilms, this);

        // Set the adapter for the horizontal RecyclerView
        topRatedHorizontalRecyclerView.setAdapter(topRatedHorizontalAdapter);



        ImageView userAvatarImageView = findViewById(R.id.userAvatar);
        View logoutButton = findViewById(R.id.logoutButton);

        // Check if the user is logged in when the activity is created
        checkLoginStatus();
        if(checkLoginStatus())
            userAvatarImageView.setImageResource(R.drawable.avatar_boy);


        // Set a click listener for the user avatar
        userAvatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the user is logged in
                if (isLoggedIn()) {
                    // If logged in, show the logout button
                    logoutButton.setVisibility(View.VISIBLE);
                } else {
                    // If not logged in, show a popup menu for login/register options
                    showLoginRegisterPopup(v);
                }
            }
        });

        // Set a click listener for the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform logout actions, clear user session data, etc.
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("authToken");
                editor.apply();

                // After logout, navigate back to the MainActivity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        TextView gotoAllMovies = findViewById(R.id.gotoallMovies);

        gotoAllMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến AllMovieActivity khi người dùng click vào TextView
                Intent intent = new Intent(MainActivity.this, AllMoviesActivity.class);
                startActivity(intent);
            }
        });

    }


    private boolean checkLoginStatus() {
        View logoutButton = findViewById(R.id.logoutButton);

        // Check if the user is logged in
        if (isLoggedIn()) {
            // If logged in, show the logout button
            logoutButton.setVisibility(View.VISIBLE);
            return true;
        } else {
            // If not logged in, hide the logout button
            logoutButton.setVisibility(View.GONE);
            return false;
        }
    }


    // Add a method to show a popup menu with login/register options
    private void showLoginRegisterPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.login_register_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Use if-else instead of switch
                if (item.getItemId() == R.id.menuLogin) {
                    // Navigate to LoginActivity
                    navigateToLoginActivity();
                    return true;
                } else if (item.getItemId() == R.id.menuRegister) {
                    // Navigate to RegisterActivity
                    navigateToRegisterActivity();
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }


    private void navigateToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    // Add a method to navigate to the RegisterActivity
    private void navigateToRegisterActivity() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    // Check if the user is logged in
    private boolean isLoggedIn() {
        // Kiểm tra xem token có tồn tại trong SharedPreferences hay không
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String authToken = sharedPreferences.getString("authToken", null);
        return authToken != null;
    }



    @Override
    public void filmAvailable(Film film) {
        Toast.makeText(this, "Film available: " + film.getTitle(), Toast.LENGTH_SHORT).show();
        // You can add more logic if needed
    }
}
