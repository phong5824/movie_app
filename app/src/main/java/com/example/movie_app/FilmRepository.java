package com.example.movie_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FilmRepository {

    public static ArrayList<Film> getHardcodedFilms() {
        ArrayList<Film> films = new ArrayList<>();

        // Tạo và thêm các bộ phim vào danh sách
        films.add(createFilm(1, "Inception","2h30", 8.5f,  "2015-08-16", "https://m.media-amazon.com/images/I/71uKM+LdgFL._AC_UF894,1000_QL80_.jpg", "https://m.media-amazon.com/images/I/71uKM+LdgFL._AC_UF894,1000_QL80_.jpg", "A thief who enters the dreams of others to steal their secrets.", 148, "PG-13", createGenreList("Action", "Adventure", "Sci-Fi"), "Christopher Nolan", createActorList("Leonardo DiCaprio", "Ellen Page")));
        films.add(createFilm(2, "Black Panther", "2h14", 7.3f, "2018-02-16", "https://www.cgv.vn/media/catalog/product/cache/3/image/c5f0a1eff4c394a251036189ccddaacd/b/p/bp2_official_poster_1_.jpg", "https://m.media-amazon.com/images/I/71hOB6MXC4L._AC_SY679_.jpg", "T'Challa, heir to the hidden kingdom of Wakanda, must step forward to lead his people and protect the world as the Black Panther.", 134, "PG-13", createGenreList("Action", "Adventure", "Sci-Fi"), "Ryan Coogler", createActorList("Chadwick Boseman", "Michael B. Jordan")));
        films.add(createFilm(3, "The Dark Knight","1h30",4.8f, "2010-07-20", "https://i.etsystatic.com/26881902/r/il/bb22f2/2821060236/il_570xN.2821060236_17pf.jpg", "https://i.etsystatic.com/26881902/r/il/bb22f2/2821060236/il_570xN.2821060236_17pf.jpg", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.", 152, "PG-13", createGenreList("Action", "Crime", "Drama"), "Christopher Nolan", createActorList("Christian Bale", "Heath Ledger")));
        films.add(createFilm(4, "Guardians of the Galaxy", "2h10", 8.0f, "2020-08-01", "https://m.media-amazon.com/images/M/MV5BNDIzMTk4NDYtMjg5OS00ZGI0LWJhZDYtMzdmZGY1YWU5ZGNkXkEyXkFqcGdeQXVyMTI5NzUyMTIz._V1_.jpg", "https://m.media-amazon.com/images/I/81rhi9drpeL._AC_SY606_.jpg", "A group of intergalactic misfits, including a talking raccoon and a tree-like creature, come together to form the Guardians of the Galaxy and save the universe.", 121, "PG-13", createGenreList("Action", "Adventure", "Comedy"), "James Gunn", createActorList("Chris Pratt", "Zoe Saldana")));
        films.add(createFilm(6, "Venom","2h15", 10.0f, "2021-10-01", "https://vb.1cdn.vn/2021/08/18/thegioidienanh.vn-stores-news_dataimages-thanhtan-082021-17-22-in_article-_3734_vietnam_vnm2_localizedposter_3.jpg", "https://vb.1cdn.vn/2021/08/18/thegioidienanh.vn-stores-news_dataimages-thanhtan-082021-17-22-in_article-_3734_vietnam_vnm2_localizedposter_3.jpg", "A failed reporter is bonded to an alien entity, one of many symbiotes who have invaded Earth. But the being takes a liking to Earth and decides to protect it.", 112, "PG-13", createGenreList("Action", "Sci-Fi", "Thriller"), "Ruben Fleischer", createActorList("Tom Hardy", "Michelle Williams")));
        films.add(createFilm(7, "Avengers: Endgame", "3h20", 9.0f, "2019-04-26", "https://www.elle.vn/wp-content/uploads/2019/05/09/elle-viet-nam-avengers-endgame-24.jpg", "https://m.media-amazon.com/images/I/81V%2BclPZpeL._AC_SL1500_.jpg", "The Avengers embark on a final mission to undo the chaos caused by the villain Thanos and restore balance to the universe.", 182, "PG-13", createGenreList("Action", "Adventure", "Sci-Fi"), "Anthony Russo, Joe Russo", createActorList("Robert Downey Jr.", "Chris Evans")));
        films.add(createFilm(8, "The Lion King","1h58",8.9f, "2023-08-16", "https://m.media-amazon.com/images/M/MV5BMjIwMjE1Nzc4NV5BMl5BanBnXkFtZTgwNDg4OTA1NzM@._V1_.jpg", "https://m.media-amazon.com/images/M/MV5BMjIwMjE1Nzc4NV5BMl5BanBnXkFtZTgwNDg4OTA1NzM@._V1_.jpg",         "A young lion prince flees his kingdom only to learn the true meaning of responsibility and bravery." , 195, "G", createGenreList("Animation", "Adventure", "Drama"), "Roger Allers, Rob Minkoff", createActorList("Matthew Broderick", "Jeremy Irons", "James Earl Jones")));
        films.add(createFilm(9, "The Lord of the Rings: The Fellowship of the Ring","2h45",8.5f, "2010-07-16", "https://m.media-amazon.com/images/I/81EBp0vOZZL.jpg", "https://m.media-amazon.com/images/I/81EBp0vOZZL.jpg", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", 178, "PG-13", createGenreList("Action", "Adventure", "Drama"), "Peter Jackson", createActorList("Elijah Wood", "Ian McKellen")));
        films.add(createFilm(10, "Titanic","1h15",8.8f, "2009-07-16", "https://m.media-amazon.com/images/I/71V3V0FE1gS._AC_UF894,1000_QL80_.jpg", "https://m.media-amazon.com/images/I/71V3V0FE1gS._AC_UF894,1000_QL80_.jpg", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", 195, "PG-13", createGenreList("Drama", "Romance"), "James Cameron", createActorList("Leonardo DiCaprio", "Kate Winslet")));
        films.add(createFilm(11, "Avatar", "2h42", 7.8f, "2023-10-18", "https://m.media-amazon.com/images/M/MV5BYjhiNjBlODctY2ZiOC00YjVlLWFlNzAtNTVhNzM1YjI1NzMxXkEyXkFqcGdeQXVyMjQxNTE1MDA@._V1_.jpg", "https://m.media-amazon.com/images/I/81KA4ZGLPeL._AC_SY679_.jpg", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", 162, "PG-13", createGenreList("Action", "Adventure", "Fantasy"), "James Cameron", createActorList("Sam Worthington", "Zoe Saldana")));

        return films;
    }



    private static Film createFilm(int id, String title, String time, float rating, String date, String posterPath, String backDropPath, String overview, int runtime, String certification, ArrayList<String> genres, String director, ArrayList<String> actors) {
        Film film = new Film();
        film.setTime(time);
        film.setID(id);
        film.setTitle(title);
        film.setRating(rating);
        film.setDate(date);
        film.setPosterPath(posterPath);
        film.setBackDropPath(backDropPath);
        film.setOverview(overview);
        film.setRuntime(runtime);
        film.setCertification(certification);
        film.setGenres(genres);
        film.setDirector(director);
        film.setActor(actors);
        return film;
    }

    private static ArrayList<String> createGenreList(String... genres) {
        ArrayList<String> genreList = new ArrayList<>();
        for (String genre : genres) {
            genreList.add(genre);
        }
        return genreList;
    }

    private static ArrayList<String> createActorList(String... actors) {
        ArrayList<String> actorList = new ArrayList<>();
        for (String actor : actors) {
            actorList.add(actor);
        }
        return actorList;
    }

    public static ArrayList<Film> getNewestFilms() {
        ArrayList<Film> films = getHardcodedFilms();

        // Sắp xếp danh sách phim theo ngày giảm dần
        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film film1, Film film2) {
                return film2.getDate().compareTo(film1.getDate());
            }
        });

        // Trả về 5 bộ phim đầu tiên
        int count = Math.min(5, films.size());
        return new ArrayList<>(films.subList(0, count));
    }

    public static ArrayList<Film> getTopRatedFilms() {
        ArrayList<Film> films = getHardcodedFilms();

        // Sắp xếp danh sách phim theo rating giảm dần
        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film film1, Film film2) {
                return Double.compare(film2.getRating(), film1.getRating());
            }
        });

        // Trả về 5 bộ phim đầu tiên hoặc tất cả nếu danh sách ít hơn 5 phim
        int count = Math.min(5, films.size());
        return new ArrayList<>(films.subList(0, count));
    }

}
