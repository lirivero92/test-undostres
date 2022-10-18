package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;


public interface userRepository {
    User findByUserAndPassword(String user, String paswMD5,String paswSHA256);
}
