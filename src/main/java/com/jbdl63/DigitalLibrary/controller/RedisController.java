package com.jbdl63.DigitalLibrary.controller;


import com.jbdl63.DigitalLibrary.dto.RangeDataDto;
import com.jbdl63.DigitalLibrary.model.Author;
import com.jbdl63.DigitalLibrary.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@RestController
@RequestMapping(value = "/v1/redis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisController
{

    @Autowired
    private RedisService redisService;

    // string operations
    @PostMapping
    public void addNewAuthor(@RequestBody Author author)
    {
        redisService.addNewData(author);
    }

    @GetMapping("/{id}")
    public Author getAuthorDataUsingId(@PathVariable("id") Integer id)
    {
        return redisService.getAuthorDetailsUsingId(id);
    }

    @GetMapping("/del/{id}")
    public Author getAndDeleteById(@PathVariable("id") Integer id) {
        return redisService.getAndDeleteById(id);
    }

    // List operations
    @PostMapping("/list")
    public void addNewAuthorInList(@RequestBody Author author)
    {
        redisService.addNewDataToList(author);
    }

    @GetMapping("/list/{start}/{stop}")
    public List<Author> getRangeOfAuthorData(@PathVariable("start") Integer start, @PathVariable("stop") Integer stop)
    {
        return redisService. getRangeOfAuthorData(start,stop);
    }

    @GetMapping("/listRangeDto/{start}/{stop}")
    public RangeDataDto getRangeDataDto(@PathVariable("start") Integer start, @PathVariable("stop") Integer stop)
    {
        return redisService. getRangeDataDto(start,stop);
    }

    // Set operations
    @PostMapping("/set")
    public void addNewAuthorToSet(@RequestBody Author author)
    {
        redisService.addNewDataToSet(author);
    }

    @PostMapping("/setusingrandam")
    public void addNewAuthorToSetUsingRandm(@RequestBody Author author)
    {
        redisService.addNewAuthorToSetUsingRandm(author);
    }

    @GetMapping("/set/{count}")
    public List<Author> returnRandomAuthorsDataFromSet(@PathVariable("count") Integer count)
    {
        return redisService.returnRandomAuthorsDataFromSet(count);
    }

    //hashes
    @PostMapping("/hash")
    public void addNewAuthorToHash(@RequestBody Author author)
    {
        redisService.addNewDataToHash(author);
    }

    @GetMapping("/hash")
    public LinkedHashMap<Integer, Author> returnAuthorsDataFromMap()
    {
        return redisService.returnAuthorsDataFromMap();
    }

}

