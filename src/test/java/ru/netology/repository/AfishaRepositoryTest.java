package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.MovieItem;

import static org.junit.jupiter.api.Assertions.*;

class AfishaRepositoryTest {
    AfishaRepository repository = new AfishaRepository();
    MovieItem first = new MovieItem(1, 10, "Tears in the night", "drama", 20200820);
    MovieItem second = new MovieItem(2, 20, "Snickering killer", "comedy", 20200828);
    MovieItem third = new MovieItem(3, 30, "Notsnickering keller", "horror", 20200828);
    MovieItem forth = new MovieItem(4, 40, "Never say goodbay", "drama", 20200910);
    MovieItem fifth = new MovieItem(5, 50, "Space chase", "fantastic", 20200910);
    MovieItem sixth = new MovieItem(6, 60, "Axe and wax", "horror", 20200910);
    MovieItem seventh = new MovieItem(7, 70, "Soledad", "drama", 20200928);
    MovieItem eighths = new MovieItem(8, 80, "Dogs and cats", "comedy", 20200928);
    MovieItem ninth = new MovieItem(9, 90, "Kings of XX", "documental", 20200928);
    MovieItem tenth = new MovieItem(10, 100, "Untill you love me", "drama", 20201011);
    MovieItem eleventh = new MovieItem(11, 110, "Funny guys", "comedy", 20201011);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);
        repository.save(sixth);
        repository.save(seventh);
        repository.save(eighths);
        repository.save(ninth);
        repository.save(tenth);
        repository.save(eleventh);
    }

    @Test
    void shouldFindAll() {
        MovieItem[] expected = new MovieItem[]{first, second, third, forth, fifth, sixth, seventh, eighths, ninth, tenth, eleventh};
        MovieItem[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        int id = 3;
        repository.findById(id);
        MovieItem expected = third;
        MovieItem actual = repository.findById(id);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById() {
        int id = 12;
        repository.findById(id);
        MovieItem expected = null;
        MovieItem actual = repository.findById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        int idToRemove = 5;
        repository.removeById(idToRemove);
        MovieItem[] expected = new MovieItem[]{first, second, third, forth, sixth, seventh, eighths, ninth, tenth, eleventh};
        MovieItem[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveById() {
        int idToRemove = 12;
        repository.removeById(idToRemove);
        MovieItem[] expected = new MovieItem[]{first, second, third, forth, fifth, sixth, seventh, eighths, ninth, tenth, eleventh};
        MovieItem[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveAll() {
        MovieItem[] expected = new MovieItem[0];
        MovieItem[] actual = repository.removeAll();
        assertArrayEquals(expected, actual);
    }
}