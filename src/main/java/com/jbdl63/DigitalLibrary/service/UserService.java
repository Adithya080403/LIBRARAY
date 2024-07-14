package com.jbdl63.DigitalLibrary.service;

import com.jbdl63.DigitalLibrary.dto.UserDto;
import com.jbdl63.DigitalLibrary.model.User;
import com.jbdl63.DigitalLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUserByUserName(String userName) {
        User user=userRepository.findByUserName(userName);
        return userRepository.findById((user.getUserId())).stream().toList();

    }

    public List<User> findAllUser() {
       return userRepository.findAll();
    }

    public void deleteByUsername(String userName) {
        User user=userRepository.findByUserName(userName);
        userRepository.deleteById(user.getUserId());
    }

    public User updateUserName(UserDto dtoUser) {
        User user=userRepository.findById(dtoUser.getId()).get();
        user.setUserName(dtoUser.getName());
        return userRepository.save(user);
    }
}
