package com.example.ProjectChatBot.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "modules")
public class ProjectModule {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

@ManyToOne(fetch = FetchType.EAGER)
@JsonIgnoreProperties("modules")
@JoinColumn(name = "project_id")
 private Project project;

 private String moduleName;
 
 public ProjectModule() {}
 
 public Long getProjectId() {
	    return project != null ? project.getId() : null;
	}

 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }
 public Project getProject() { return project; }
 public void setProject(Project project) { this.project = project; }
 public String getModuleName() { return moduleName; }
 public void setModuleName(String moduleName) { this.moduleName = moduleName; }
}
