package mateuszteam.final_project.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Genre {

    ACTION("Action"),
    ANIMATION("Animation"),
    COMEDY("Comedy") ,
    CRIME("Crime"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    ROMANCE("Romance"),
    SCI_FI("Science-fiction"),
    SLICE_OF_LIFE("Slice of life"),
    SPORTS("Sports"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private final String displayGenre;


}
