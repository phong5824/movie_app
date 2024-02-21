package com.example.movie_app;

public class ShowtimeItem {

    public static final int TYPE_DATE = 1;
    public static final int TYPE_TIME = 2;
    public static final int TYPE_THEATRE = 3;

    private String name;
    private boolean available;
    private int type;
    private boolean isSelected;


    public ShowtimeItem(String name, boolean available, int type) {
        this.name = name;
        this.available = available;
        this.type = type;
        this.isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getType() {
        return type;
    }
}


