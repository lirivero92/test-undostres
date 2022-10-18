package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exceptions.EtAuthExcepcion;
import com.example.demo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface userService {
    public User validateUser(String user,  String paswMD5,String paswSHA256) throws EtAuthExcepcion;

}
