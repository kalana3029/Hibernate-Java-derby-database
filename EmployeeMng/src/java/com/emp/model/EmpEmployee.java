/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Developer PC
 */
public class EmpEmployee {

    private int employeeID;
    private String name;
    private int role;
    private Set tasks;

    /**
     * @return the employeeID
     */
    public EmpEmployee(){
        tasks = new HashSet();
    }
    
    public EmpEmployee(Integer employeeID){
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @return the tasks
     */
    public Set getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(Set tasks) {
        this.tasks = tasks;
    }

    public String toString() {
        return "EmpEmployee: " + getEmployeeID()
                + " name: " + getName()
                + " EmpRole: " + getRole()
                + " tasks: " + getTasks();
    }
}
