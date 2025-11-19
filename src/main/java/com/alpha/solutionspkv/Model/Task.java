package com.alpha.solutionspkv.Model;

public class Task {
    private int taskId;
    private String name;
    private String description;
    private double estimatedHours;
    private double actualHours;
    private Status status;
    private ProjectMember assignedTo;

    public Task() {}

    public Task(int taskId, ProjectMember assignedTo, String name, String description, double estimatedHours, double actualHours, Status status) {
        this.taskId = taskId;
        this.assignedTo = assignedTo;
        this.name = name;
        this.description = description;
        this.estimatedHours = estimatedHours;
        this.actualHours = actualHours;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public ProjectMember getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(ProjectMember assignedTo) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        TODO, IN_PROGRESS, DONE
    }
}