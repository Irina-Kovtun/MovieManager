package ru.netology.manager;

import lombok.Data;
import ru.netology.domain.MovieItem;
import ru.netology.repository.AfishaRepository;

@Data
public class AfishaManager {
    private AfishaRepository repository;
    private int moviesNumberAtDefault = 10;

    public AfishaManager(int moviesNumberAtDefault, AfishaRepository repository) {
        this.moviesNumberAtDefault = moviesNumberAtDefault;
        this.repository = repository;
    }

    public AfishaManager() {
    }
//
//    public AfishaManager(AfishaRepository repository) {
//        this.repository = repository;
//    }

    public void add(MovieItem movie) {
        repository.save(movie);
    }

    public MovieItem[] getAllReverse() {
        MovieItem[] movies = repository.findAll();
        MovieItem[] result = new MovieItem[movies.length];
        for (int i = 0; i < result.length; i++) {
            int index = movies.length - i - 1;
            result[i] = movies[index];
        }
        return result;
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public MovieItem[] getLastTenOrLess() {
        MovieItem[] movies = repository.findAll();
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



