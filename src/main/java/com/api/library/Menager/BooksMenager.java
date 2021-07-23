package com.api.library.Menager;

import com.api.library.BookDao.BookRepo;
import com.api.library.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksMenager {


    private BookRepo bookRepo;

    @Autowired
    public BooksMenager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    public Optional<Book> getBookById(int id)
    {
        return bookRepo.findById((long) id);
    }
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public Iterable<Book> getAllBooks()
    {
        return bookRepo.findAll();
    }
    public void deleteBookById(Long id)
    {
        bookRepo.deleteById(id);
    }
}
