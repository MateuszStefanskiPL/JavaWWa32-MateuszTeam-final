package mateuszteam.final_project.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class MovieCopyDto {

    @Setter(AccessLevel.NONE)
    @NotNull
    private long copyId;

    @NotNull
    private long movieId;

    @NotNull
    private long orderId;
}
