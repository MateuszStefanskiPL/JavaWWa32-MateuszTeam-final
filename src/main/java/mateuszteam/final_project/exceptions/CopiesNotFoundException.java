package mateuszteam.final_project.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class CopiesNotFoundException extends RuntimeException {

    private List<Long> moviesWithoutFreeCopies;

    public CopiesNotFoundException(List<Long> moviesWithoutFreeCopies) {
        super("No free copies for this movie : " + moviesWithoutFreeCopies);
        this.moviesWithoutFreeCopies = moviesWithoutFreeCopies;
    }

    public CopiesNotFoundException(Long id) {
        super("No free copies for this movie : " + id);
    }


}
