package com.alpha.solutionspkv.Model;

import java.util.List;

public class Project {
    private int projectId;
    private String name;
    private String description;
    private double budget;
    private List<ProjectMember> projectMembers;
    private List<Task> tasks;

    public Project() {}

    public Project(int projectId, String name, String description, double budget, List<Task> tasks) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.tasks = tasks;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<ProjectMember> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<ProjectMember> projectMembers) {
        this.projectMembers = projectMembers;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
