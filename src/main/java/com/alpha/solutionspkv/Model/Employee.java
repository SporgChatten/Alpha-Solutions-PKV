package com.alpha.solutionspkv.Model;

public class Employee {
    private int employeeId;
    private User user;
    private String firstName;
    private String lastName;
    private String position;
    private double hourlyRate;


    public Employee() {}

    public Employee(int employeeId, User user, String firstName, String lastName, String position, double hourlyRate) {
        this.employeeId = employeeId;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.hourlyRate = hourlyRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
