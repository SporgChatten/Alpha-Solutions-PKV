package com.alpha.solutionspkv.service;

import com.alpha.solutionspkv.model.Project;
import com.alpha.solutionspkv.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    

    public List<Project> getProjectsForUser(int userId) {
        return projectRepository.findAllForUser(userId);
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id);
    }
    
    public void saveProject(Project project) {
        projectRepository.save(project);
    }
    
    public void updateProject(Project project) {
        projectRepository.update(project);
    }
    
    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }
}