package com.alpha.solutionspkv.Model;

public class Task {
    private int taskId;
    private Project project;
    private Employee assignedTo;
    private String name;
    private String description;
    private double estimatedHours;
    private double actualHours;
    private double cost;

    public Task() {}

    public Task(int taskId, Project project, Employee assignedTo, String name, String description, double estimatedHours, double actualHours, double cost) {
        this.taskId = taskId;
        this.project = project;
        this.assignedTo = assignedTo;
        this.name = name;
        this.description = description;
        this.estimatedHours = estimatedHours;
        this.actualHours = actualHours;
        this.cost = cost;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
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

    public double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public double getActualHours() {
        return actualHours;
    }

    public void setActualHours(double actualHours) {
        this.actualHours = actualHours;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}