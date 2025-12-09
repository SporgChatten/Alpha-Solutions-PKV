package com.alpha.solutionspkv.model;

import org.springframework.lang.Nullable;

public class Task {
    private int id;
    private int projectId;
    private @Nullable Integer parentTaskId;

    public enum Status {IN_PROGRESS, COMPLETED, CANCELLED, PAUSED, NOT_STARTED}
    public Task() {}

    public Task(int id, int projectId, String name, String description, Status status, @Nullable Integer parentTaskId) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.parentTaskId = parentTaskId;
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
                '}';
    }
}
