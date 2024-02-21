package com.example.movie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.ArrayList;

public class ThankYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        // Get data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            String filmTitle = intent.getStringExtra("filmTitle");
            String selectedDate = intent.getStringExtra("selectedDate");
            String selectedTime = intent.getStringExtra("selectedTime");
            String selectedTheatre = intent.getStringExtra("selectedTheatre");
            ArrayList<String> selectedSeats = intent.getStringArrayListExtra("selectedSeats");

            // Update UI with the received data
            TextView filmTitleTextView = findViewById(R.id.thankYouFilmTitle);
            filmTitleTextView.setText("Film: " + filmTitle);

            TextView dateTextView = findViewById(R.id.thankYouDate);
            dateTextView.setText("Date: " + selectedDate);

            TextView timeTextView = findViewById(R.id.thankYouTime);
            timeTextView.setText("Time: " + selectedTime);

            TextView theatreTextView = findViewById(R.id.thankYouTheatre);
            theatreTextView.setText("Theatre: " + selectedTheatre);

            TextView seatsTextView = findViewById(R.id.thankYouSeats);
            seatsTextView.setText("Seats: " + TextUtils.join(", ", selectedSeats));

            // Tạo mã QR
            String qrData = generateQRData(filmTitle, selectedDate, selectedTime, selectedTheatre, selectedSeats);
            ImageView qrImageView = findViewById(R.id.qrCodeImageView);
            try {
                Bitmap bitmap = generateQRCode(qrData);
                qrImageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

        Button toHomeButton = findViewById(R.id.toHome);
        toHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ThankYouActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }

    // Phương thức để tạo dữ liệu cho mã QR
    private String generateQRData(String filmTitle, String selectedDate, String selectedTime, String selectedTheatre, ArrayList<String> selectedSeats) {

        return filmTitle + "_" + selectedDate + "_" + selectedTime + "_" + selectedTheatre + "_" + TextUtils.join(",", selectedSeats);
    }

    private Bitmap generateQRCode(String data) throws WriterException {
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, bitMatrix.get(x, y) ? getResources().getColor(R.color.black) : getResources().getColor(R.color.white));
            }
        }
        return bmp;
    }
}
