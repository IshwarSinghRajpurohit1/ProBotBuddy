package com.example.ProjectChatBot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjectChatBot.Entity.Project;
import com.example.ProjectChatBot.Entity.ProjectModule;

import java.util.List;

public interface ModuleRepository extends JpaRepository<ProjectModule, Long> {
 List<ProjectModule> findByProject(Project project);
 List<ProjectModule> findByModuleNameContainingIgnoreCase(String name);
}
