package com.jbdl63.DigitalLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jbdl63.DigitalLibrary.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>
{

    List<Book> findByAuthorAuthorName(String authorName);
}
