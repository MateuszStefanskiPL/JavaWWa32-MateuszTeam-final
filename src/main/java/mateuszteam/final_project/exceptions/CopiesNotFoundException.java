package mateuszteam.final_project.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class CopiesNotFoundException extends RuntimeException {

    private List<Long> moviesWithoutFreeCopies;

    public CopiesNotFoundException(List<Long> moviesWithoutFreeCopies) {
        super("Brak wolnych kopii dla tych filmow: " + moviesWithoutFreeCopies);
        this.moviesWithoutFreeCopies = moviesWithoutFreeCopies;
    }


}
