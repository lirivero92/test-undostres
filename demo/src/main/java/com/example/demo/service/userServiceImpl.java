package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exceptions.EtAuthExcepcion;
import com.example.demo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userRepository userRepository;


    @Override
    public User validateUser(String user, String paswMD5,String paswSHA256) throws EtAuthExcepcion {
        if (user!=null){
            user=user.toLowerCase();
            return userRepository.findByUserAndPassword(user, paswMD5,paswSHA256);
        }else {
            throw new EtAuthExcepcion("Usuario desconocido");
        }
    }
}
