/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.dao;

import com.emp.model.EmpEmployee;
import java.util.List;

/**
 *
 * @author Developer PC
 */
public interface employeeDao {
    public int addEmployee(EmpEmployee employee);
    public List<EmpEmployee> viewEmployee();
    public EmpEmployee getEmployee(int empid);
    public int updateEmployee(EmpEmployee employee);
}
