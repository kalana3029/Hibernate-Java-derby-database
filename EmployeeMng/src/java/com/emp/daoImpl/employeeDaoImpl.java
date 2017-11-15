/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.daoImpl;

import com.emp.dao.employeeDao;
import com.emp.model.EmpEmployee;
import com.emp.util.SessionFactoryUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Developer PC
 */
public class employeeDaoImpl implements employeeDao{

    @Override
    public int addEmployee(EmpEmployee employee) {
        int res = -1;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
            res = 0;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                try {
// Second try catch as the rollback could fail as well
                    tx.rollback();
                } catch (HibernateException e1) {
                    System.out.println("Error rolling back transaction");
                }
// throw again the first exception
                throw e;
            }
        }
        return 0;
    }

    @Override
    public List<EmpEmployee> viewEmployee() {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

        List<EmpEmployee> empEmployees = null;
        try {
            tx = session.beginTransaction();
            empEmployees = session.createQuery(
                    "select e from EmpEmployee as e").list();

            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                try {
// Second try catch as the rollback could fail as well
                    tx.rollback();
                } catch (HibernateException e1) {
                    System.out.println("Error rolling back transaction");
                }
                throw e;
            }
        }
        return empEmployees;
    }

    @Override
    public int updateEmployee(EmpEmployee employee) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();
        int res =-1;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(employee);
            tx.commit();
            res = 0;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                try {
// Second try catch as the rollback could fail as well
                    tx.rollback();
                } catch (HibernateException e1) {
                    System.out.println("Error rolling back transaction");
                }
// throw again the first exception
                throw e;
            }
        }
        
        return res;
    }

    @Override
    public EmpEmployee getEmployee(int empid) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

       EmpEmployee tempEmployees = null;
       EmpEmployee empEmployees = null;
        try {
            tx = session.beginTransaction();
            String HQL = "select e from EmpEmployee as e where employeeID =:employeeID";
            Query query = session.createQuery(HQL);
            query.setParameter("employeeID", empid);
            empEmployees = (EmpEmployee) query.uniqueResult();
            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                try {
// Second try catch as the rollback could fail as well
                    tx.rollback();
                } catch (HibernateException e1) {
                    System.out.println("Error rolling back transaction");
                }
                throw e;
            }
        }
        return empEmployees;
    }
    
}
