package com.example.ProjectChatBot.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(unique = true)
 private String ticketCode;

 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "user_id")
 private User user;
 
 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "project_id")
 private Project project;


 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "module_id")
 private ProjectModule module;


 
 private String title;

 @Column(length = 1000)
 private String description;
 
 private String days;

 private String status;

 
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }
 
 public String getTicketCode() { return ticketCode; }
 public void setTicketCode(String ticketCode) { this.ticketCode = ticketCode; }
 
 public User getUser() { return user; }
 public void setUser(User user) { this.user = user; }

 public Project getProject() {return project;}
 public void setProject(Project project) {this.project = project;}
 
 public ProjectModule getModule() { return module; }
 public void setModule(ProjectModule module) { this.module = module; }
 
 public String getDescription() { return description; }
 public void setDescription(String description) { this.description = description; }
 
 public String getTitle() {return title;}
 public void setTitle(String title) {this.title = title;}
 
 public String getDays() { return days; }
 public void setDays(String days) { this.days = days; }
 
 public String getStatus() { return status; }
 public void setStatus(String status) { this.status = status; }
}
