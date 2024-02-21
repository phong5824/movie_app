package com.example.movie_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

public class FilmSliderAdapter extends RecyclerView.Adapter<FilmSliderAdapter.FilmSliderViewHolder> {
    private ArrayList<Film> films;
    private Context context;

    public FilmSliderAdapter(ArrayList<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @Override
    public FilmSliderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(parent.getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        return new FilmSliderViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(FilmSliderViewHolder viewHolder, final int position) {
        final Film film = films.get(position);
        viewHolder.bind(film);

        // Xử lý sự kiện khi nhấp vào hình ảnh
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến FilmDetailActivity và chuyển thông tin về bộ phim
                Intent intent = new Intent(context, FilmDetailActivity.class);
                intent.putExtra("MyClass", film);
                // Thêm các thông tin khác cần thiết về bộ phim vào intent nếu cần
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class FilmSliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textViewRating;

        public FilmSliderViewHolder(LinearLayout itemView) {
            super(itemView);
            imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
            textViewRating = new TextView(context);

            ((LinearLayout) itemView).addView(imageView);

            ((LinearLayout) itemView).addView(textViewRating);
        }

        public void bind(Film film) {
            Glide.with(context)
                    .load(film.getPosterPath())
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            // Bo tròn 4 góc của ảnh
                            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                            RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
                            roundedDrawable.setCornerRadius(50); // Điều chỉnh giá trị này để thay đổi độ bo tròn
                            imageView.setImageDrawable(roundedDrawable);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            // Xử lý khi hình ảnh bị xóa
                        }
                    });

            textViewRating.setText(String.valueOf(film.getRating()));
        }


    }
}
