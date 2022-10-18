package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.userService;
import com.example.demo.util.MD5PasswordEncoderByLibrary;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.AlgorithmMethod;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@RequestMapping("app/v1/login/")
public class loginController {

    @Autowired
    private userService userService;

@GetMapping("dologin")
public ResponseEntity<User> login(@RequestParam("email") String username, @RequestParam ("password") String password){
        /* MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        System.out.println("MD5PasswordEncoderByLibrary.MD5PasswordEncoderByLibrary ============ "+MD5PasswordEncoderByLibrary.encodePasswordWhithoutSalt(password));
        System.out.println("MD5PasswordEncoderByLibrary.sha-256 ============ "+encoded);
        */
        String paswMD5=password;
        String paswSHA256=password;
        User user=userService.validateUser(username, paswMD5,paswSHA256);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
}


}
