package com.example.ProjectChatBot.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectChatBot.DTO.LoginRequest;
import com.example.ProjectChatBot.Repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

 @Autowired
 private UserRepository userRepository;

 @PostMapping("/login")
 public Object login(@RequestBody LoginRequest req) {
  return userRepository.findByEmail(req.getEmail())
    .filter(u -> u.getPassword().equals(req.getPassword()))
    .map(u -> Map.of("id", u.getId(), "name", u.getName(), "role", u.getRole()))
    .orElse(Map.of("error","Invalid Credentials"));
 }
}
