/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.model;

/**
 *
 * @author Developer PC
 */
public class EmpTask {
    
    private int taskID;
    private String description;
    private int employee;

    
    public EmpTask(){
        
    }
    
    public EmpTask(Integer taskID){
        this.taskID = taskID;
    }
    /**
     * @return the taskID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the employee
     */
    public int getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(int employee) {
        this.employee = employee;
    }
    
    public String toString() {
        return "EmpTask: " + getTaskID()
                + " description: " + getDescription()
                + " employee: " + getEmployee();
    }
}
