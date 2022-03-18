package mateuszteam.final_project.controller;

import mateuszteam.final_project.domain.entities.Genre;
import mateuszteam.final_project.domain.entities.MovieStatus;
import net.minidev.json.JSONObject;


import java.time.LocalDate;

public class MovieToTest {

    public static JSONObject buildMovieJson(final long movieId,
                                            final String title,
                                            final JSONObject genre,
                                            final LocalDate releaseDate,
                                            final String director,
                                            final String starring,
                                            final int numberOfCopies,
                                            final double averageScore,
                                            final JSONObject movieStatus) {
        JSONObject bookingJson = new JSONObject();
        bookingJson.put("movieId", movieId);
        bookingJson.put("title", title);
        bookingJson.put("genre", genre);
        bookingJson.put("releaseDate", releaseDate);
        bookingJson.put("director", director);
        bookingJson.put("numberOfCopies", numberOfCopies);
        bookingJson.put("averageScore", averageScore);
        bookingJson.put("starring", starring);
        bookingJson.put("movieStatus", movieStatus);

        return bookingJson;
    }

    public static JSONObject buildMovieStatusJson(MovieStatus movieStatus) {

        JSONObject movieStatusJson = new JSONObject();
        movieStatusJson.put("movieStatus", movieStatus);


        return movieStatusJson;
    }

    public static JSONObject buildGenreJson(Genre genre) {

        JSONObject genreJson = new JSONObject();
        genreJson.put("genre", genre);


        return genreJson;
    }


}
