package com.jbdl63.DigitalLibrary.controller;

import com.jbdl63.DigitalLibrary.dto.BookDto;
import com.jbdl63.DigitalLibrary.dto.UpdateAddressDto;
import com.jbdl63.DigitalLibrary.model.Author;
import com.jbdl63.DigitalLibrary.model.Book;
import com.jbdl63.DigitalLibrary.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(value = "/v1/books",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Slf4j
public class BookController
{
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody Book book)
    {
        return new ResponseEntity<>(bookService.addNewBook(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookId}")
    public void deleteByBookId(@PathVariable("bookId") Integer bookId)
    {
        bookService.deleteByBookId(bookId);
    }

    @GetMapping("/{authorName}")
    public ResponseEntity<List<Book>>  fetchBookByAuthorName(@PathVariable("authorName") String authorName)
    {
        return new ResponseEntity<>(bookService.fetchBookByAuthorName(authorName),HttpStatus.OK);
    }

    @PutMapping
    public Book updateBookName(@RequestBody @Valid BookDto dtoBookName)
    {
        return bookService.updateBookName(dtoBookName);
    }

    @GetMapping
    public List<Book> findAllBooks()
    {
        return bookService.findAllBook();
    }


}
