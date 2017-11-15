/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.dao;

import com.emp.model.EmpTask;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Developer PC
 */
public interface taskDao {
    public int addTask(EmpTask task);
    public List<EmpTask> viewTask();
    public int updateTask(EmpTask task);
    public EmpTask viewTask(int taskid);
    public List<EmpTask> loadTask(String task);
    public List<EmpTask> loadTaskbyempid(int empid);
    public Set loadTasksbyempid(int empid);
}
