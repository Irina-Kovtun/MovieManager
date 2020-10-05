package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.MovieItem;
import ru.netology.repository.AfishaRepository;

@ExtendWith(MockitoExtension.class)

public class AfishaManagerTestAnotherDefault {

    @Mock
    private AfishaRepository repository;
    @InjectMocks
    private AfishaManager manager = new AfishaManager(5, repository);
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
//        manager.add(fifth);
//        manager.add(sixth);
    }

    @Test
    public void shouldGetLessThanDefault() {

        MovieItem[] returned = new MovieItem[]{first, second, third, forth};
        doReturn(returned).when(repository).findAll();

        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{forth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetDefault() {
        manager.add(fifth);
        MovieItem[] returned = new MovieItem[]{first, second, third, forth, fifth};
        doReturn(returned).when(repository).findAll();

        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{fifth, forth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetMoreThanDefault() {
        manager.add(fifth);
        manager.add(sixth);
        MovieItem[] returned = new MovieItem[]{first, second, third, forth, fifth, sixth};
        doReturn(returned).when(repository).findAll();

        MovieItem[] actual = manager.getLastTenOrLess();
        MovieItem[] expected = new MovieItem[]{sixth, fifth, forth, third, second};
        assertArrayEquals(expected, actual);
    }

}
