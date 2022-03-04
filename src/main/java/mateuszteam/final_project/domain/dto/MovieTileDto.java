package mateuszteam.final_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovieTileDto {

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 250)
    private String title;

    @NotNull
    private LocalDate releaseDate;
}
