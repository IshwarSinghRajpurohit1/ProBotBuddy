package com.example.ProjectChatBot.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({
    "days",
    "description",
    "id",
    "status",
    "ticketCode",
    "title",
    "module",
    "project",
    "user"
})

public class TicketResponseDTO {
	
    private Long id;
    private String title;
    private String description;
    private String days;
    private String status;
    private String ticketCode;

    private SimpleUserDTO user;
    private SimpleProjectDTO project;
    private SimpleModuleDTO module;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDays() { return days; }
    public void setDays(String days) { this.days = days; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTicketCode() { return ticketCode; }
    public void setTicketCode(String ticketCode) { this.ticketCode = ticketCode; }

    public SimpleUserDTO getUser() { return user; }
    public void setUser(SimpleUserDTO user) { this.user = user; }

    public SimpleProjectDTO getProject() { return project; }
    public void setProject(SimpleProjectDTO project) { this.project = project; }

    public SimpleModuleDTO getModule() { return module; }
    public void setModule(SimpleModuleDTO module) { this.module = module; }
}

