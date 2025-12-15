package com.alpha.solutionspkv.model;

import java.math.BigDecimal;

public class Task {
    private int id;
    private int projectId;
    private String name;
    private String description;
    private Status status;
    private Integer parentTaskId;
    private BigDecimal estimatedCost;

    public enum Status {IN_PROGRESS, COMPLETED, CANCELLED, PAUSED, NOT_STARTED}

    public Task() {}

    public Task(int id, int projectId, String name, String description, Status status, Integer parentTaskId, BigDecimal estimatedCost) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.parentTaskId = parentTaskId;
        this.estimatedCost = estimatedCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", parentTaskId=" + parentTaskId +
                ", estimatedCost=" + estimatedCost +
                '}';
    }
}
