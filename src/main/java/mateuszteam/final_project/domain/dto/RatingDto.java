package mateuszteam.final_project.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @NotNull
    private LocalDateTime dateOfEvaluation;
}
