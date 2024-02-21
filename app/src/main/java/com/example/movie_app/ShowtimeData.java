package com.example.movie_app;

import java.util.ArrayList;
import java.util.List;

public class ShowtimeData {

    public static List<ShowtimeItem> getDates() {
        List<ShowtimeItem> dates = new ArrayList<>();
        dates.add(new ShowtimeItem("FRI 12", true, ShowtimeItem.TYPE_DATE));
        dates.add(new ShowtimeItem("SAT 13", true, ShowtimeItem.TYPE_DATE));
        dates.add(new ShowtimeItem("SUN 14", true, ShowtimeItem.TYPE_DATE));
        dates.add(new ShowtimeItem("MON 15", true, ShowtimeItem.TYPE_DATE));
        dates.add(new ShowtimeItem("TUE 16", true, ShowtimeItem.TYPE_DATE));

        // Thêm ngày khác nếu cần
        return dates;
    }

    public static List<ShowtimeItem> getAllTimes() {
        List<ShowtimeItem> times = new ArrayList<>();
        times.add(new ShowtimeItem("09:30 AM", true, ShowtimeItem.TYPE_TIME));
        times.add(new ShowtimeItem("12:30 AM", true, ShowtimeItem.TYPE_TIME));
        times.add(new ShowtimeItem("03:30 PM", true,ShowtimeItem.TYPE_TIME));
        return times;
    }

    public static List<ShowtimeItem> getAllTheaTres() {
        List<ShowtimeItem> theatres = new ArrayList<>();
        theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
        theatres.add(new ShowtimeItem("Galaxy", true, ShowtimeItem.TYPE_THEATRE));
        theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        return theatres;
    }

        public static List<ShowtimeItem> getTimes(String selectedDate) {
        // Tùy chỉnh dữ liệu thời gian dựa trên ngày đã chọn
        List<ShowtimeItem> times = new ArrayList<>();
        if ("FRI 12".equals(selectedDate)) {
            times.add(new ShowtimeItem("09:30 AM", true, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("12:30 AM", false, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("03:30 PM", true,ShowtimeItem.TYPE_TIME));
        } else if ("SAT 13".equals(selectedDate)) {
            times.add(new ShowtimeItem("09:30 AM", true, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("12:30 AM", true, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("03:30 PM", true,ShowtimeItem.TYPE_TIME));
        } else if ("SUN 14".equals(selectedDate)) {
            times.add(new ShowtimeItem("09:30 AM", true, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("12:30 AM", true, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("03:30 PM", false,ShowtimeItem.TYPE_TIME));
        }
        else if ("MON 15".equals(selectedDate)) {
            times.add(new ShowtimeItem("09:30 AM", true, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("12:30 AM", true, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("03:30 PM", true,ShowtimeItem.TYPE_TIME));
        }
        else if ("TUE 16".equals(selectedDate)) {
            times.add(new ShowtimeItem("09:30 AM", false, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("12:30 AM", false, ShowtimeItem.TYPE_TIME));
            times.add(new ShowtimeItem("03:30 PM", false,ShowtimeItem.TYPE_TIME));
        }
        return times;
    }

    public static List<ShowtimeItem> getTheatres(String selectedDate, String selectedTime) {
        // Tùy chỉnh dữ liệu rạp dựa trên ngày và thời gian đã chọn
        List<ShowtimeItem> theatres = new ArrayList<>();
        if ("FRI 12".equals(selectedDate) && "09:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("FRI 12".equals(selectedDate) && "12:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("FRI 12".equals(selectedDate) && "03:30 PM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        }

        if ("SAT 13".equals(selectedDate) && "09:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("SAT 13".equals(selectedDate) && "12:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", false, ShowtimeItem.TYPE_THEATRE));
        } else if ("SAT 13".equals(selectedDate) && "03:30 PM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        }


        if ("SUN 14".equals(selectedDate) && "09:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("SUN 14".equals(selectedDate) && "12:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("SUN 14".equals(selectedDate) && "03:30 PM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        }

        if ("MON 15".equals(selectedDate) && "09:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("MON 15".equals(selectedDate) && "12:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("MON 15".equals(selectedDate) && "03:30 PM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", false, ShowtimeItem.TYPE_THEATRE));
        }

        if ("TUE 16".equals(selectedDate) && "09:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("TUE 16".equals(selectedDate) && "12:30 AM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", true, ShowtimeItem.TYPE_THEATRE));
        } else if ("TUE 16".equals(selectedDate) && "03:30 PM".equals(selectedTime)) {
            theatres.add(new ShowtimeItem("CGV", true, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Galaxy", false, ShowtimeItem.TYPE_THEATRE));
            theatres.add(new ShowtimeItem("Cinestar", false, ShowtimeItem.TYPE_THEATRE));
        }

        return theatres;
    }
}
