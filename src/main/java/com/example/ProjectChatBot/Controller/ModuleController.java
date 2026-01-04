package com.example.ProjectChatBot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectChatBot.Entity.Project;
import com.example.ProjectChatBot.Entity.ProjectModule;
import com.example.ProjectChatBot.Repository.ModuleRepository;
import com.example.ProjectChatBot.Repository.ProjectRepository;

@RestController
@RequestMapping("/api/modules")
@CrossOrigin
public class ModuleController {

 @Autowired
 private ModuleRepository moduleRepository;

 @Autowired
 private ProjectRepository projectRepository;

 @PostMapping
 public ProjectModule create(@RequestBody ProjectModule req) {

     if (req.getProject() == null || req.getProject().getId() == null) {
         throw new RuntimeException("Project id is required");
     }

     Project project = projectRepository.findById(req.getProject().getId())
             .orElseThrow(() -> new RuntimeException("Project not found"));

     req.setProject(project);

     return moduleRepository.save(req);
 }

 
 // GET ALL MODULES
 @GetMapping
 public List<ProjectModule> getAll() {
     return moduleRepository.findAll();
 }


 // GET MODULE BY ID
 @GetMapping("/{id}")
 public ProjectModule getById(@PathVariable Long id) {
     return moduleRepository.findById(id).orElseThrow();
 }
 

 @GetMapping("/project/{projectId}")
 public List<ProjectModule> getByProject(@PathVariable Long projectId) {
  Project project = projectRepository.findById(projectId).orElseThrow();
  return moduleRepository.findByProject(project);
 }


 // UPDATE MODULE
 @PutMapping("/{id}")
 public ProjectModule update(@PathVariable Long id, @RequestBody ProjectModule req) {

     ProjectModule m = moduleRepository.findById(id).orElseThrow();


     if(req.getModuleName() != null){
         m.setModuleName(req.getModuleName());
     }

     return moduleRepository.save(m);
 }


 // DELETE MODULE
 @DeleteMapping("/{id}")
 public void delete(@PathVariable Long id) {
     moduleRepository.deleteById(id);
 }
 
}
