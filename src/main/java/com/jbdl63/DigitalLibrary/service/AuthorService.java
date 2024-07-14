package com.jbdl63.DigitalLibrary.service;

import com.jbdl63.DigitalLibrary.dto.UpdateAddressDto;
import com.jbdl63.DigitalLibrary.exception.BadRequestException;
import com.jbdl63.DigitalLibrary.exception.DataNotFoundException;
import com.jbdl63.DigitalLibrary.model.Author;
import com.jbdl63.DigitalLibrary.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author addNewAuthor(Author author) throws Exception
    {

        return authorRepository.save(author);
    }


    public Author fetchAuthorDetailsByAuthorName(String authorName)
    {
        return authorRepository.findByAuthorName(authorName);
    }

    public Author fetchAuthorByNameUsingParam(String authorName)
    {
        return authorRepository.findByAuthorName(authorName);
    }

    public List<Author> fetchAllAvailAuthors()
    {
        return authorRepository.findAll();
    }




    public Author updateAuthorAddress(UpdateAddressDto updateAddressDto) throws BadRequestException {
        try{
            Author author=authorRepository.findById(updateAddressDto.getId()).orElseThrow(
                    () -> new DataNotFoundException("Author not exist")
            );
            author.setAuthorAddress(updateAddressDto.getAddress());
            return authorRepository.save(author);
        }
        catch (RuntimeException e)
        {
            log.error("Error is occured while working with update operation with exception ;{}",e.getMessage());
            throw new DataNotFoundException("Update operation is failed due to exception :"+e.getMessage());
        }
    }

    public void deleteAuthorById(Integer authorId)
    {
        authorRepository.deleteById(authorId);
    }
    

    public void uploadAuthorsDataToDatabase(String fileContent) {
        List<String> authorsData = List.of(fileContent.split("\n"));
        List<Author> authors = new ArrayList<>();
        for(int i = 1; i < authorsData.size(); i++) {
            String[] row = authorsData.get(i).split(",");
            authors.add(Author.builder()
                    .authorId(Integer.valueOf(row[0]))
                    .authorName(row[1])
                    .authorAddress(row[2])
                    .build());
        }
        authorRepository.saveAll(authors);
    }
}
