package com.springsecurity.springsecurity.controller;

import com.springsecurity.springsecurity.model.User;
import com.springsecurity.springsecurity.service.MyUserDetailsService;
import com.springsecurity.springsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class Controller {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String sayHello() {
        return "Hii";
    }

    @GetMapping("/session")
    public String getSession(HttpServletRequest httpServletRequest){
        return httpServletRequest.getSession().getId();
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrf(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDetailsService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }
}
