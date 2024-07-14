package com.jbdl63.DigitalLibrary.service;

import com.jbdl63.DigitalLibrary.dto.BookDto;
import com.jbdl63.DigitalLibrary.exception.DataNotFoundException;
import com.jbdl63.DigitalLibrary.model.Author;
import com.jbdl63.DigitalLibrary.model.Book;
import com.jbdl63.DigitalLibrary.repository.AuthorRepository;
import com.jbdl63.DigitalLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book addNewBook(Book book)
    {
        Author author1= authorRepository.findById(book.getAuthor().getAuthorId()).get();
        book.setAuthor(author1);
        return bookRepository.save(book);
    }

    public void deleteByBookId(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> fetchBookByAuthorName(String authorName)
    {
        return bookRepository.findByAuthorAuthorName(authorName);
    }

    public Book updateBookName(BookDto dtoBookName) {

            Book book=bookRepository.findById(dtoBookName.getId()).orElseThrow(
                    () -> new DataNotFoundException("Book not exist")
            );
            book.setBookName(dtoBookName.getBookName());
            return bookRepository.save(book);
    }

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }


}
