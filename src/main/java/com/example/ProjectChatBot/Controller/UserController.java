package com.example.ProjectChatBot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ProjectChatBot.Entity.User;
import com.example.ProjectChatBot.Repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }
    
    
    @GetMapping
    public List<User> getAll() {
    	 System.out.println("Fetching all users");
        return userRepository.findAll();
    }
    
    
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    
    // GET USER BY NAME
    @GetMapping("/search/{name}")
    public List getByName(@PathVariable String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
    
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User req) {

        User u = userRepository.findById(id).orElseThrow();

        if(req.getName()!=null) u.setName(req.getName());
        if(req.getEmail()!=null) u.setEmail(req.getEmail());
        if(req.getPassword()!=null) u.setPassword(req.getPassword());
        if(req.getRole()!=null) u.setRole(req.getRole());

        return userRepository.save(u);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
    
}