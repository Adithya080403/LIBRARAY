package com.jbdl63.DigitalLibrary.controller;

import com.jbdl63.DigitalLibrary.dto.UserDto;
import com.jbdl63.DigitalLibrary.model.User;
import com.jbdl63.DigitalLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<List<User>> findAllUserByUserName(@PathVariable("userName") String userName)
    {
        return new ResponseEntity<>(userService.findAllUserByUserName(userName),HttpStatus.OK);
    }

    @GetMapping
    public List<User> findAllUsers()
    {
        return userService.findAllUser();
    }

    @DeleteMapping("/{userName}")
    public void deleteByUserName(@PathVariable("userName") String userName)
    {
        userService.deleteByUsername(userName);
    }

    @PutMapping
    public User updateUserName(@RequestBody UserDto dtoUser)
    {
        return userService.updateUserName(dtoUser);
    }
}
