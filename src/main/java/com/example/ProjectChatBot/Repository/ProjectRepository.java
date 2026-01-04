package com.example.ProjectChatBot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjectChatBot.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	 List<Project> findByNameContainingIgnoreCase(String name);
}
