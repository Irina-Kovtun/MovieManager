package ru.netology.repository;

import ru.netology.domain.MovieItem;

public class AfishaRepository {

    private MovieItem[] movies = new MovieItem[0];

    public MovieItem[] findAll() {
        return movies;
    }

    public void save(MovieItem item) {
        int length = movies.length + 1;
        MovieItem[] tmp = new MovieItem[length];
        System.arraycopy(movies, 0, tmp, 0, movies.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        movies = tmp;
    }

    public MovieItem findById(int id) {
        int index = 0;
        for (MovieItem movie : movies) {
            if (movie.getId() == id) {
                movies[index] = movie;
                return movie;
            }
            index++;

        }
        return null;
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

    public MovieItem[] removeAll() {
        MovieItem[] zero = new MovieItem[0];
        movies = zero;
        return movies;
    }
}


