package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.MovieItem;
import ru.netology.repository.AfishaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class AfishaManagerTest {

    @Mock
    private AfishaRepository repository;

    @InjectMocks
    private AfishaManager manager = new AfishaManager();
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
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
        manager.add(eighths);
        manager.add(ninth);
//        manager.add(tenth);
//        manager.add(eleventh);
    }

    @Test
    public void shouldGetLessThanDefault() {

        MovieItem[] returned = new MovieItem[]{first, second, third, forth, fifth, sixth, seventh, eighths, ninth};
        doReturn(returned).when(repository).findAll();

        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{ ninth, eighths, seventh, sixth, fifth, forth, third, second, first};
        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldGetDefault() {
        manager.add(tenth);
               MovieItem[] returned = new MovieItem[]{first, second, third, forth, fifth, sixth, seventh, eighths, ninth, tenth};
        doReturn(returned).when(repository).findAll();

        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{ tenth, ninth, eighths, seventh, sixth, fifth, forth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetMoreThanDefault() {
        manager.add(tenth);
        manager.add(eleventh);
        MovieItem[] returned = new MovieItem[]{first, second, third, forth, fifth, sixth, seventh, eighths, ninth, tenth,eleventh};
        doReturn(returned).when(repository).findAll();

        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{ eleventh, tenth, ninth, eighths, seventh, sixth, fifth, forth, third, second};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveIfExists() {
        manager.add(tenth);
        manager.add(eleventh);
        int idToRemove = 3;
        MovieItem[] returned = new MovieItem[]{first, second, forth, fifth, sixth, seventh, eighths, ninth, tenth, eleventh};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        MovieItem[] expected = new MovieItem[]{eleventh, tenth, ninth, eighths, seventh, sixth, fifth, forth, second, first};
        MovieItem[] actual = manager.getAllReverse();
        assertArrayEquals(expected, actual);

        verify(repository).removeById(idToRemove);
    }

    @Test
    public void shouldNotRemoveIfNotExists() {
        manager.add(tenth);
        manager.add(eleventh);
        int idToRemove = 14;
        MovieItem[] returned = new MovieItem[]{first, second, third, forth, fifth, sixth, seventh, eighths, ninth, tenth, eleventh};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        MovieItem[] expected = new MovieItem[]{eleventh, tenth, ninth, eighths, seventh, sixth, fifth, forth, third, second, first};
        MovieItem[] actual = manager.getAllReverse();
        assertArrayEquals(expected, actual);
        verify(repository).removeById(idToRemove);
    }

}

