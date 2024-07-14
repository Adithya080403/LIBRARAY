package com.jbdl63.DigitalLibrary.controller;

import com.jbdl63.DigitalLibrary.dto.UpdateAddressDto;
import com.jbdl63.DigitalLibrary.exception.BadRequestException;
import com.jbdl63.DigitalLibrary.model.Author;
import com.jbdl63.DigitalLibrary.service.AuthorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import java.awt.*;
import java.util.*;

@RestController
@RequestMapping(value = "/v1/authors",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Slf4j
@EnableCaching
public class AuthorController
{
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> addNewAuthor(@RequestBody @Valid Author author)
    {
        try{
            if(author!=null)
            {
                return new ResponseEntity<>( authorService.addNewAuthor(author), HttpStatus.CREATED);
            }
        }
        catch(Exception e)
        {
              throw new BadRequestException(e.getMessage());
        }
        return null;

    }

    @GetMapping("/{authorName}")
    @Cacheable(value = "authors", key = "#authorName", condition = "#authorName != null")
    public Author fetchAuthorDetailsByAuthorName(@PathVariable("authorName") String authorName)
    {
        return authorService.fetchAuthorDetailsByAuthorName(authorName);
    }

    @GetMapping("/usingParam")
    @Cacheable(value = "authors", key = "#authorName")
    public Author fetchAuthorByNameUsingParam(@RequestParam("authorName") String authorName)
    {
        if(!isBlank(authorName))
            return authorService.fetchAuthorByNameUsingParam(authorName);
        throw new BadRequestException("please send the author name");

    }

    public boolean isBlank(String string)
    {
        return string==null || string.isBlank();
    }



    @GetMapping
    public List<Author> fetchAllAvailAuthors()
    {
        return authorService.fetchAllAvailAuthors();
    }

    @PutMapping
    @CachePut(value = "authors", key = "#updateAddressDto.id")
    public Author updateAuthorAddress(@RequestBody @Valid UpdateAddressDto updateAddressDto)
    {
        return authorService.updateAuthorAddress(updateAddressDto);
    }



    @DeleteMapping("/{authorId}")
    @CacheEvict(value = "authors", allEntries = true)
    public void deleteAuthorById( @PathVariable("authorId") Integer authorId)
    {
        authorService.deleteAuthorById(authorId);
    }

    @PostMapping("/upload-csv")
    public void uploadAuthorsDataToDatabase(@RequestPart("file") MultipartFile multipartFile ) throws Exception
    {
        String fileContent= new String(multipartFile.getBytes());
        authorService.uploadAuthorsDataToDatabase(fileContent);
    }




}
