package mateuszteam.final_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotEnoughFreeCopiesErrorResponse {

    private String msg;
    private List<Long> moviesWithoutFreeCopies;

}
