package com.alpha.solutionspkv.service;

import com.alpha.solutionspkv.model.Project;
import com.alpha.solutionspkv.model.User;
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

    public boolean canAccessProject(User user, int projectId) {
        if (user == null) return false;

        // Admin kan se alle projekter
        if (user.getRole() == User.Role.ADMIN) return true;

        return projectRepository.isUserAssignedToProject(user.getId(), projectId);
    }

    public void assignUserToProject(int userId, int projectId) {
        projectRepository.assignUserToProject(userId, projectId);
    }

    public void unassignUserFromProject(int userId, int projectId) {
        projectRepository.unassignUserFromProject(userId, projectId);
    }
}
