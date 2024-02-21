package com.example.movie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SeatSelectionActivity extends AppCompatActivity {

    private Room room; // Assuming Room is a member variable of SeatSelectionActivity
    boolean seatUserReserved[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);


        // Lấy giá trị từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            String filmTitle = intent.getStringExtra("filmTitle");
            String filmImageResId = intent.getStringExtra("filmImageResId");
            String selectedDate = intent.getStringExtra("selectedDate");
            String selectedTime = intent.getStringExtra("selectedTime");
            String selectedTheatre = intent.getStringExtra("selectedTheatre");

            // Sử dụng giá trị theo ý muốn
            TextView titleFilmTextView = findViewById(R.id.titleFilm);
            titleFilmTextView.setText(filmTitle);

//            ImageView filmImageView = findViewById(R.id.imageFilm);
//            // Load image from URL using Picasso
//            Picasso.get().load(filmImageResId).into(filmImageView);

            TextView dateTextView = findViewById(R.id.datepick);
            dateTextView.setText(selectedDate);

            TextView timeTextView = findViewById(R.id.timepick);
            timeTextView.setText(selectedTime);

            TextView theatreTextView = findViewById(R.id.theatrepick);
            theatreTextView.setText(selectedTheatre);
        }

        // Call the createTable method to set up the seating layout
        createTable();


        // Add click listener to the "Reserve" button
        ImageView reserverButton = findViewById(R.id.reserverButton);
        reserverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if at least one seat is selected
                int totalTickets = calculateTotalTickets();
                if (totalTickets > 0) {
                    // Seats are selected, proceed to ThankYouActivity

                        String filmTitle = intent.getStringExtra("filmTitle");
                        String filmImageResId = intent.getStringExtra("filmImageResId");
                        String selectedDate = intent.getStringExtra("selectedDate");
                        String selectedTime = intent.getStringExtra("selectedTime");
                        String selectedTheatre = intent.getStringExtra("selectedTheatre");

                    // Create an Intent to start the ThankYouActivity
                    Intent thankYouIntent = new Intent(SeatSelectionActivity.this, ThankYouActivity.class);

                    // Pass seat information to the ThankYouActivity
                    thankYouIntent.putExtra("selectedSeats", getSelectedSeats());

                    // Add other necessary information to the Intent
                    thankYouIntent.putExtra("filmTitle", filmTitle);
                    thankYouIntent.putExtra("selectedDate", selectedDate);
                    thankYouIntent.putExtra("selectedTime", selectedTime);
                    thankYouIntent.putExtra("selectedTheatre", selectedTheatre);

                    // Start the ThankYouActivity
                    startActivity(thankYouIntent);
                } else {
                    // No seats selected, show a message or handle accordingly
                    Toast.makeText(SeatSelectionActivity.this, "Please select at least one seat", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // Helper method to get the list of selected seats
    private ArrayList<String> getSelectedSeats() {
        ArrayList<String> selectedSeats = new ArrayList<>();

        for (int i = 0; i < seatUserReserved.length; i++) {
            for (int j = 0; j < seatUserReserved[i].length; j++) {
                if (seatUserReserved[i][j]) {
                    // If the seat is reserved, add it to the list
                    selectedSeats.add(getSeatNumber(i, j));
                }
            }
        }

        return selectedSeats;
    }

    // Helper method to convert row and column to seat number
    private String getSeatNumber(int row, int column) {
        // Convert row to letter (A, B, C, ...)
        char rowChar = (char) ('A' + row);
        // Convert column to number (1, 2, 3, ...)
        int columnNumber = column + 1;
        // Combine row letter and column number
        return rowChar + String.valueOf(columnNumber);
    }


    private void createTable() {
        GridLayout gridLayout = findViewById(R.id.TableSeats);

        // Define your fixed seating layout (this is just an example)
        String[][] fixedSeatingLayout = {
                {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8"},
                {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8"},
                {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8"},
                {"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8"},
                {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8"},
        };

        // Create an instance of the Room class with the fixed seating layout
        room = new Room(fixedSeatingLayout);
        seatUserReserved = room.seatUserReserved;

        int spacing = 14;
        // Add seats to the GridLayout dynamically
        for (int i = 0; i < fixedSeatingLayout.length; i++) {
            for (int j = 0; j < fixedSeatingLayout[i].length; j++) {
                ImageView seat = new ImageView(this);
                seat.setTag(fixedSeatingLayout[i][j]); // Set tag to identify the seat
                seat.setOnClickListener(onSeatClickListener);

                GridLayout.Spec rowSpec = GridLayout.spec(i);
                GridLayout.Spec colSpec = GridLayout.spec(j);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
                params.width = 100; // Width of each seat (adjust as needed)
                params.height = 100; // Height of each seat (adjust as needed)


                // Set margin to create spacing between elements
                params.setMargins(spacing, spacing, spacing, spacing);

                boolean seatStatus = room.getSeatStatus(i,j);

                // Set the appropriate image based on the seat status
                if (seatStatus) {
                    seat.setImageResource(R.drawable.ic_seat_available);
                } else {
                    seat.setImageResource(R.drawable.ic_seat_unavailable);
                }

                gridLayout.addView(seat, params);
            }
        }
    }

    private View.OnClickListener onSeatClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onSeatClick(v);
        }
    };


    public void onUndoClick(View view) {
        super.onBackPressed();
    }


    private void onSeatClick(View seat) {
        String seatNumber = seat.getTag().toString();

        // Extract row and column from the seat number
        int row = extractRow(seatNumber);
        int column = extractColumn(seatNumber);

        if (row >= 0 && row < room.getRows() && column >= 0 && column < room.getColumns()) {

            // Kiểm tra trạng thái của ghế
            boolean seatStatus = room.getSeatStatus(row, column);

            // Kiem tra cac ghe nguoi dung da click vao
            boolean seatReservedStatus = room.getReservedStatus(row, column);
            if(seatReservedStatus)
            {
                room.unreserveSeat(row, column);
                ((ImageView) seat).setImageResource(R.drawable.ic_seat_available);
            }
            else if (seatStatus) {
                // Nếu ghế có sẵn, thực hiện logic đặt chỗ
                room.reserveSeat(row, column);

                ((ImageView) seat).setImageResource(R.drawable.ic_seat_reserved);
            } else {
                // Nếu ghế đã được đặt chỗ hoặc đã được đặt trước đó
                Toast.makeText(this, "Seat " + seatNumber + " is not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Xử lý trường hợp khi trích xuất dòng hoặc cột thất bại
            Toast.makeText(this, "Invalid seat number: " + seatNumber, Toast.LENGTH_SHORT).show();
        }

        // Khi vé được chọn, cập nhật thông tin hiển thị
        int totalTickets = calculateTotalTickets(); // Thay thế bằng hàm tính tổng số vé đã chọn
        double totalPrice = totalTickets*2.0; // Thay thế bằng hàm tính tổng giá vé

        TextView txtTotalTickets = findViewById(R.id.txtTotalTickets);
        txtTotalTickets.setText("x " + totalTickets);

        TextView txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtTotalPrice.setText("Total Price $" + String.format("%.2f", totalPrice));

    }

    private int calculateTotalTickets() {
        int totalTickets = 0;

        for (int i = 0; i < seatUserReserved.length; i++) {
            for (int j = 0; j < seatUserReserved[i].length; j++) {
                if (seatUserReserved[i][j]) {
                    // Nếu ghế đã được đặt chỗ, tăng biến đếm
                    totalTickets++;
                }
            }
        }

        return totalTickets;
    }


    // Helper method to extract row from seat number
    private int extractColumn(String seatNumber) {
        // Check if the seatNumber contains a valid row identifier
        if (seatNumber.length() > 1) {
            // Extract the numeric part and convert it to an integer
            return Integer.parseInt(seatNumber.substring(1)) - 1;
        } else {
            // Handle error or return a default value
            return -1; // or throw an exception or handle the case appropriately
        }
    }

    // Helper method to extract column from seat number
    private int extractRow(String seatNumber) {
        // Check if the seatNumber contains a valid column identifier
        if (seatNumber.length() > 0) {
            // Extract the column character and convert it to an integer
            char columnChar = seatNumber.charAt(0);
            return columnChar - 'A';
        } else {
            // Handle error or return a default value
            return -1; // or throw an exception or handle the case appropriately
        }
    }



}
