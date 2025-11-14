package com.alpha.solutionspkv.Service;

import com.alpha.solutionspkv.Model.Project;
import com.alpha.solutionspkv.Repository.ProjectRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final JdbcTemplate jdbcTemplate;
    private final ProjectRepository projectRepository;

    public ProjectService(JdbcTemplate jdbcTemplate, ProjectRepository projectRepository) {
        this.jdbcTemplate = jdbcTemplate;
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
