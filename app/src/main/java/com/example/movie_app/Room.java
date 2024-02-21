package com.example.movie_app;

import java.util.Random;

public class Room {
    private String[][] seatingLayout;
    private boolean[][] seatAvailability;
    boolean[][] seatUserReserved;

    public Room(String[][] seatingLayout) {
        this.seatingLayout = seatingLayout;

        Random random = new Random();
        seatAvailability = new boolean[seatingLayout.length][seatingLayout[0].length];
        seatUserReserved = new boolean[seatingLayout.length][seatingLayout[0].length];  // Khởi tạo trường này

        for (int i = 0; i < seatingLayout.length; i++) {
            for (int j = 0; j < seatingLayout[i].length; j++) {
                seatAvailability[i][j] = random.nextBoolean();
                seatUserReserved[i][j] = false;
            }
        }

    }

    public boolean getSeatStatus(int row, int column) {
        return seatAvailability[row][column];
    }

    public boolean getReservedStatus(int row, int column) {
        return seatUserReserved[row][column];
    }
    public void reserveSeat(int row, int column) {
            if(getSeatStatus(row,column)) {
                seatAvailability[row][column] = false;
                seatUserReserved[row][column] = true;
            }
    }

    public void unreserveSeat(int row, int column) {
            seatAvailability[row][column] = true;
            seatUserReserved[row][column] = false;
    }

    public int getRows() {
        return seatingLayout.length;
    }

    public int getColumns() {
        return seatingLayout[0].length;
    }

}

