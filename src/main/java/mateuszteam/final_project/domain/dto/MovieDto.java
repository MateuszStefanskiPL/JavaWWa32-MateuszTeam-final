package mateuszteam.final_project.domain.dto;

import lombok.*;
import mateuszteam.final_project.domain.dao.Genre;
import mateuszteam.final_project.domain.dao.MovieStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {


    @Setter(AccessLevel.NONE)
    @NotNull
    private long movieId;


    @NotEmpty
    @NotNull
    @Size(min = 1, max = 250)
    private String movieTitle;

    @NotNull
    private int yearOfProd;

    @NotNull
    private Genre genre;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100)
    private String director;

    @NotEmpty
    @NotNull
    private String starring;

    @NotNull
    private int numberOfCopies;

    @NotNull
    private double movieAverageRating;

    @NotNull
    private MovieStatus movieStatus;
}
