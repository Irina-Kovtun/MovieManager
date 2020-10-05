package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.MovieItem;

import static org.junit.jupiter.api.Assertions.*;

class MovieManagerTestAnotherDefault {
    private MovieManager manager = new MovieManager(5);
    MovieItem first = new MovieItem(1, 10, "Tears in the night", "drama", 20200820);
    MovieItem second = new MovieItem(2, 20, "Snickering killer", "comedy", 20200828);
    MovieItem third = new MovieItem(3, 30, "Notsnickering keller", "horror", 20200828);
    MovieItem forth = new MovieItem(4, 40, "Never say goodbay", "drama", 20200910);
    MovieItem fifth = new MovieItem(5, 50, "Space chase", "fantastic", 20200910);
    MovieItem sixth = new MovieItem(6, 60, "Axe and wax", "horror", 20200910);


    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
    }

    @Test
    public void shouldGetLessThanDefault() {
        manager.setMoviesNumberAtDefault(manager.getMoviesNumberAtDefault());
        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{forth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetDefault() {
        manager.add(fifth);
        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{ fifth, forth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetMoreThanDefault() {
        manager.add(fifth);
        manager.add(sixth);
        manager.setMoviesNumberAtDefault(manager.getMoviesNumberAtDefault());
        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{sixth, fifth, forth, third, second };
        assertArrayEquals(expected, actual);
    }

}