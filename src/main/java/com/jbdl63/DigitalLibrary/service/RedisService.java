package com.jbdl63.DigitalLibrary.service;

import com.jbdl63.DigitalLibrary.dto.RangeDataDto;
import com.jbdl63.DigitalLibrary.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.util.*;

@Service
public class RedisService {


    @Autowired
    private RedisTemplate redisTemplate;

    private final String KEY = "AUTHOR::";

    //string
    public void addNewData(Author author)
    {
        redisTemplate.opsForValue().setIfAbsent(KEY+author.getAuthorId(), author);
    }

    public Author getAuthorDetailsUsingId(Integer id)
    {
        return (Author) redisTemplate.opsForValue().get(KEY+id);
    }


    public Author getAndDeleteById(Integer id) {
        return (Author) redisTemplate.opsForValue().getAndDelete(KEY+id);
    }


    //list
    public void addNewDataToList(Author author)
    {
        redisTemplate.opsForList().leftPush(KEY+"List",author);
    }

    public List<Author> getRangeOfAuthorData(Integer start, Integer stop)
    {
        return redisTemplate.opsForList().range(KEY+"List",start,stop);
    }

    public RangeDataDto getRangeDataDto(Integer start, Integer stop)
    {
        List<Author> range=redisTemplate.opsForList().range(KEY+"List",start,stop);
        return RangeDataDto.builder().dataSize(range.size()).data(range).build();
    }

    //set
    public void addNewDataToSet(Author author)
    {
        redisTemplate.opsForSet().add(KEY+"Set",author);
    }

    public void addNewAuthorToSetUsingRandm(Author author)
    {
        Random random=new Random();
        author.setAuthorId(random.nextInt(1000));
        redisTemplate.opsForSet().add(KEY+"Set",author);

    }

    public List<Author> returnRandomAuthorsDataFromSet(Integer count)
    {
        return redisTemplate.opsForSet().randomMembers(KEY+"Set",count);
    }

    //hashes
    public void addNewDataToHash(Author author)
    {
        redisTemplate.opsForHash().put(KEY+"Hash",author.getAuthorId(),author);
    }

    public LinkedHashMap<Integer, Author> returnAuthorsDataFromMap()
    {
        LinkedHashMap<Integer,Author> entries = (LinkedHashMap<Integer, Author>) redisTemplate.opsForHash().entries(KEY + "Hash");
        Set<Map.Entry<Integer, Author>> mp = entries.entrySet();
        for(Map.Entry<Integer, Author> e:mp)
        {
            System.out.println(e.getKey()+"\t"+e.getValue());
        }
        return entries;
    }

    //hashes
}
