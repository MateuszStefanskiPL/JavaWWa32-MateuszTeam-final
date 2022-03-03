package mateuszteam.final_project.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovieCopyDto {

    @Setter(AccessLevel.NONE)
    @NotNull
    private long copyId;

    @NotNull
    private long movieId;

    private long orderId;
}
