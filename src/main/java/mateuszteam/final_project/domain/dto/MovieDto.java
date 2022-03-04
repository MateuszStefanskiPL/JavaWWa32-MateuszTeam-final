package mateuszteam.final_project.domain.dto;

import lombok.*;
import mateuszteam.final_project.domain.entities.Genre;
import mateuszteam.final_project.domain.entities.MovieStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovieDto {

    @Setter(AccessLevel.NONE)
    @NotNull
    private long movieId;

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 250)
    private String title;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private Genre genre;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100)
    private String director;

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 500)
    private String starring;

    @NotNull
    private int numberOfCopies;

    @NotNull
    private double movieAverageRating;

    @NotNull
    private MovieStatus movieStatus;
}
