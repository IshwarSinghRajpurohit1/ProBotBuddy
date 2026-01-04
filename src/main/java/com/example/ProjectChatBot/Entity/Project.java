package com.example.ProjectChatBot.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String name;
 private String url;
 private String domain;
 
 @OneToMany(
         mappedBy = "project",
         cascade = CascadeType.ALL,
         orphanRemoval = true
 )
 @JsonIgnoreProperties("project")
 private List<ProjectModule> modules = new ArrayList<>();

 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }
 
 public String getName() { return name; }
 public void setName(String name) { this.name = name; }
 
 public String getUrl() { return url; }
 public void setUrl(String url) { this.url = url; }
 
 public String getDomain() { return domain; }
 public void setDomain(String domain) { this.domain = domain; }
 
 public List<ProjectModule> getModules() { return modules; }
 public void setModules(List<ProjectModule> modules) { this.modules = modules; }
}
