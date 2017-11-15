/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.dao;

import com.emp.model.EmpRole;
import java.util.List;

/**
 *
 * @author Developer PC
 */
public interface roleDao {
    
    public int addRole(EmpRole role);
    public List<EmpRole> viewRole();
    public int updateRole(EmpRole role);
    public EmpRole getRole(int roleid);
}
