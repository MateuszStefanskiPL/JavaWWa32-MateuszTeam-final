package mateuszteam.final_project.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MainPageMovieDto {

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 250)
    private String movieTitle;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100)
    private String director;

    @NotEmpty
    @NotNull
    private String starring;


}
