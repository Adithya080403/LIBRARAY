package com.jbdl63.DigitalLibrary.repository;

import com.jbdl63.DigitalLibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

    User findByUserName(String userName);
}
