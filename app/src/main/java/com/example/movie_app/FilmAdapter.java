package com.example.movie_app;

import android.content.Context;
import android.content.Intent;

import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private ArrayList<Film> films;
    private FilmAvailable filmAvailable;
    private ArrayList<Film> filteredFilms;


    public FilmAdapter(ArrayList<Film> films, FilmAvailable filmAvailable) {
        this.films = films;
        this.filteredFilms = new ArrayList<>(films);
        this.filmAvailable = filmAvailable;

        // Log the number of films
        Log.d("FilmAdapter", "Number of films: " + films.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Film film;
        if (!filteredFilms.isEmpty() && position < filteredFilms.size()) {
            film = filteredFilms.get(position);
        } else {
            film = films.get(position);
        }

        Picasso.get().load(film.getPosterPath()).into(holder.imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                // Bo tròn hình ảnh sau khi tải thành công
                Bitmap bitmap = ((BitmapDrawable) holder.imageView.getDrawable()).getBitmap();
                RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(holder.itemView.getResources(), bitmap);
                roundedDrawable.setCornerRadius(60);
                holder.imageView.setImageDrawable(roundedDrawable);
            }

            @Override
            public void onError(Exception e) {
                // Xử lý lỗi nếu có
            }
        });
        holder.textTitle.setText(film.getTitle());
        holder.textTime.setText(film.getTime());

        holder.itemListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemDetailIntent = new Intent(view.getContext(), FilmDetailActivity.class);
                itemDetailIntent.putExtra("MyClass", film);
                view.getContext().startActivity(itemDetailIntent);
            }
        });
    }

    public void filter(String query) {
        filteredFilms.clear(); // Clear the filtered list before filtering

        if (query.isEmpty()) { // If the query is empty
            filteredFilms.addAll(films); // Add all films to the filtered list
        } else {
            String lowerCaseQuery = query.toLowerCase(); // Convert the query to lowercase

            for (Film film : films) { // Iterate through all films
                String lowerCaseTitle = film.getTitle().toLowerCase(); // Convert the film's title to lowercase

                if (lowerCaseTitle.contains(lowerCaseQuery)) { // Check if the title contains the query substring
                    filteredFilms.add(film); // Add the film to the filtered list
                }
            }
        }
        Log.d("FilmAdapter", "Number of filtered films: " + filteredFilms.size()); // Log the number of filtered films

        this.notifyDataSetChanged(); // Notify the adapter about the updated filtered list
    }

    @Override
    public int getItemCount() {
        return filteredFilms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout itemListView;
        private TextView textTitle;
        private TextView textTime;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemListView = itemView.findViewById(R.id.listItem);
            textTitle = itemView.findViewById(R.id.titleText);
            textTime = itemView.findViewById(R.id.timeText);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}
