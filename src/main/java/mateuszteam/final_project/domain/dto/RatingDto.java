package mateuszteam.final_project.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class RatingDto {


    @NotNull
    @Setter(AccessLevel.NONE)
    public long ratingId;

    @NotNull
    private long userId;

    @NotNull
    private long movieId;

    @NotNull
    private double ratingScore;
}
