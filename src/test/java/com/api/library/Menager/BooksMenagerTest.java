package com.api.library.Menager;



import com.api.library.BookDao.BookRepo;
import com.api.library.Entity.Book;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BooksMenagerTest {

    @InjectMocks
    private BooksMenager booksMenager;

    @Mock
    private BookRepo bookRepo;


    @Autowired
    private MockMvc mvc;

    @Test
    void shouldGetAllBooks()
    {
        //when
        when(bookRepo.findAll()).thenReturn(Arrays.asList(
                new Book(1L, "Jan", "Kowalski", "Książka"),
                new Book(2L,"Jan","Kowalski","Książka2"),
                new Book(3L,"Jan","Kowalski","Książka3")
        ));
        Iterable<Book> bookList = booksMenager.getAllBooks();
        //then
        assertThat(bookList).hasSize(3);
    }


    @Test
    void shouldGetBookById()
    {

        when(booksMenager.getBookById(1)).thenReturn(Optional.of(new Book(1L, "Jan", "Kowalski", "Książka")));
        //when
        Optional<Book> bookById = booksMenager.getBookById(1);
        //then
        assertThat(bookById.get().getId().compareTo(1L));

    }

    @Test
    void shouldAddBook()  {
    Book book = new Book(1L,"Janek","Kowalski","Tron");
        bookRepo.save(book);
        assertNotNull(bookRepo.findById(1L));
    }
}