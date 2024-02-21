package com.example.movie_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowtimeAdapter extends RecyclerView.Adapter<ShowtimeAdapter.BaseViewHolder> {

    private List<ShowtimeItem> showtimeItems;
    private OnItemClickListener onItemClickListener;

    public ShowtimeAdapter(List<ShowtimeItem> showtimeItems) {
        this.showtimeItems = showtimeItems;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        // Inflate the appropriate layout based on the view type
        switch (viewType) {
            case ShowtimeItem.TYPE_DATE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_date, parent, false);
                return new DateViewHolder(view, onItemClickListener);
            case ShowtimeItem.TYPE_TIME:
            case ShowtimeItem.TYPE_THEATRE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_time_theatre, parent, false);
                return new TimeTheatreViewHolder(view, onItemClickListener);
            default:
                // Default layout (if needed)
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showtime_item, parent, false);
                return new BaseViewHolder(view, onItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        ShowtimeItem showtimeItem = showtimeItems.get(position);
        holder.bind(showtimeItem);
    }

    @Override
    public int getItemCount() {
        return showtimeItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return showtimeItems.get(position).getType();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // BaseViewHolder to be extended by other ViewHolders
    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public BaseViewHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.showtime_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        public void bind(ShowtimeItem showtimeItem) {
            if (showtimeItem != null) {
                textView.setText(showtimeItem.getName());
                if (showtimeItem.isAvailable()) {
                    textView.setTextColor(Color.BLACK);
                } else {
                    textView.setTextColor(Color.GRAY);
                }

                // Thêm logic để thay đổi giao diện khi mục được chọn
                if (showtimeItem.isSelected()) {
                    // Đặt màu nền hoặc hiệu ứng khác để thể hiện mục đã được chọn
                    itemView.setBackgroundResource(R.drawable.border_selector);
                } else {
                    // Đặt màu nền hoặc hiệu ứng khác để thể hiện mục không được chọn
                    itemView.setBackgroundColor(Color.TRANSPARENT); // Ví dụ: Màu nền trong suốt
                }
            }
        }

    }

    // DateViewHolder
    public static class DateViewHolder extends BaseViewHolder {
        // Additional date-specific views if needed

        public DateViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            // Initialize date-specific views if needed
        }
    }

    // TimeTheatreViewHolder
    public static class TimeTheatreViewHolder extends BaseViewHolder {
        // Additional time or theatre-specific views if needed

        public TimeTheatreViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            // Initialize time or theatre-specific views if needed
        }
    }

    public void updateItems(List<ShowtimeItem> showtimeItems) {
        this.showtimeItems = showtimeItems;
        notifyDataSetChanged();
    }

    public List<ShowtimeItem> getShowtimeItems() {
        return showtimeItems;
    }


    public boolean hasOnItemClickListener() {
        return onItemClickListener != null;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
}
