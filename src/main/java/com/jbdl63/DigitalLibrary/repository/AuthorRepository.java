package com.jbdl63.DigitalLibrary.repository;

import com.jbdl63.DigitalLibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer>
{

    Author findByAuthorName(String authorName);
}
