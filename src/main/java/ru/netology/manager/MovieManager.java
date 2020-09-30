package ru.netology.manager;

import lombok.Data;
import ru.netology.domain.MovieItem;

public class MovieManager {
    private MovieItem[] movies = new MovieItem[0];
    int moviesNumberAtDefault = 10;

    public int getMoviesNumberAtDefault() {
        return moviesNumberAtDefault;
    }

    public void setMoviesNumberAtDefault(int moviesNumberAtDefault) {
        this.moviesNumberAtDefault = moviesNumberAtDefault;
    }

    public void add(MovieItem movie) {
        int length = movies.length + 1;
        MovieItem[] tmp = new MovieItem[length];
        System.arraycopy(movies, 0, tmp, 0, movies.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = movie;
        movies = tmp;
    }

    public MovieItem[] getAllReverse() {
        MovieItem[] result = new MovieItem[movies.length];
        for (int i = 0; i < result.length; i++) {
            int index = movies.length - i - 1;
            result[i] = movies[index];
        }
        return result;
    }

    public void removeById(int id) {
        int index = 0;
        for (MovieItem item : movies) {
            if (item.getId() == id) {
                int length = movies.length - 1;
                MovieItem[] tmp = new MovieItem[length];
                for (MovieItem movie : movies) {
                    if (movie.getId() != id) {
                        tmp[index] = movie;
                        index++;
                    }
                }
                movies = tmp;
                return;
            }
        }
    }

    public MovieItem[] getLastTenOrLess() {
        MovieItem[] result = getAllReverse();
        if (result.length >= moviesNumberAtDefault) {
            MovieItem[] shortList = new MovieItem[moviesNumberAtDefault];
            for (int i = 0; i < moviesNumberAtDefault; i++) {
                shortList[i] = result[i];
            }
            return shortList;
        }
        return result;
    }
}



