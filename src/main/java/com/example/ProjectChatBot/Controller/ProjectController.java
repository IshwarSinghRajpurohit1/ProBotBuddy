package com.example.ProjectChatBot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ProjectChatBot.Entity.Project;
import com.example.ProjectChatBot.Repository.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {

 @Autowired
 private ProjectRepository projectRepository;

 @PostMapping
 public Project create(@RequestBody Project project) {
  return projectRepository.save(project);
 }

 @GetMapping
 public List<Project> list() {
  return projectRepository.findAll();
 }
 
 // GET PROJECT BY ID
 @GetMapping("/{id}")
 public Project getById(@PathVariable Long id) {
     return projectRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("Project not found"));
 }


 // SEARCH PROJECT BY NAME (case-insensitive)
 @GetMapping("/search/{name}")
 public List<Project> searchByName(@PathVariable String name) {
     return projectRepository.findByNameContainingIgnoreCase(name);
 }


 // UPDATE PROJECT
 @PutMapping("/{id}")
 public Project update(@PathVariable Long id, @RequestBody Project req) {

     Project p = projectRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("Project not found"));

     if(req.getName() != null) {
         p.setName(req.getName());
     }

     if(req.getUrl() != null) {
         p.setUrl(req.getUrl());
     }

     if(req.getDomain() != null) {
         p.setDomain(req.getDomain());
     }

     return projectRepository.save(p);
 }


 // DELETE PROJECT
 @DeleteMapping("/{id}")
 public void delete(@PathVariable Long id) {
     projectRepository.deleteById(id);
 }

}
