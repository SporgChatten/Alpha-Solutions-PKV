package com.alpha.solutionspkv.Service;

import com.alpha.solutionspkv.Model.Project;
import com.alpha.solutionspkv.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(int projectId) {
        return projectRepository.findById(projectId);
    }

    public void create(Project project) {
        projectRepository.create(project);
    }

    public void update(Project project) {
        projectRepository.update(project);
    }

    public void delete(int id) {
        projectRepository.delete(id);
    }
}