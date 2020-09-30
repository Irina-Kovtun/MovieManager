package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieItem {
    private int id;
    private int movieId;
    private String movieName;
    private String genre;
    //releaseDate should be set in the following format: YYYYMMDD
    private int releaseDate;
}
