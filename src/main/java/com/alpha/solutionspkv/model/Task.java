package com.alpha.solutionspkv.model;

import org.springframework.lang.Nullable;

public class Task {
    private int id;
    private String name;
    private String description;
    private Status status;
    private int projectId;
    private @Nullable Integer parentTaskId;

    public enum Status {IN_PROGRESS, COMPLETED, CANCELLED, PAUSED, NOT_STARTED}
    public Task() {}

    public Task(int id, String name, String description, Status status, int projectId, @Nullable Integer parentTaskId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.projectId = projectId;
        this.parentTaskId = parentTaskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public @Nullable Integer getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(@Nullable Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", projectId=" + projectId +
                ", parentTaskId=" + parentTaskId +
                '}';
    }
}