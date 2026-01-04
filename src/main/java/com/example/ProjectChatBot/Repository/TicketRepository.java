package com.example.ProjectChatBot.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjectChatBot.Entity.Project;
import com.example.ProjectChatBot.Entity.ProjectModule;
import com.example.ProjectChatBot.Entity.Ticket;
import com.example.ProjectChatBot.Entity.User;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	

    Optional<Ticket> findByTicketCode(String ticketCode);

    List<Ticket> findByStatus(String status);
    
    List<Ticket> findByUser(User user);

    List<Ticket> findByProject(Project project);

    List<Ticket> findByModule(ProjectModule module);

    
	
}
