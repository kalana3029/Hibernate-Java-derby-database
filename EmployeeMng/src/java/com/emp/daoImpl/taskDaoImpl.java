/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.daoImpl;

import com.emp.dao.taskDao;
import com.emp.model.EmpTask;
import com.emp.util.SessionFactoryUtil;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Developer PC
 */
public class taskDaoImpl implements taskDao{

    @Override
    public int addTask(EmpTask task) {
        int res = -1;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.save(task);
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
    public List<EmpTask> viewTask() {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

        List<EmpTask> tasks = null;
        try {
            tx = session.beginTransaction();
            tasks = session.createQuery(
                    "select t from EmpTask as t").list();

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
        return tasks;
    }

    @Override
    public int updateTask(EmpTask task) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();
        int res =-1;
        try {
            tx = session.beginTransaction();
            session.update(task);
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
    public EmpTask viewTask(int taskid) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

        EmpTask tasks = null;
        try {
             tx = session.beginTransaction();
            String HQL = "select t from EmpTask as t where taskID =:taskID";
            Query query = session.createQuery(HQL);
            query.setParameter("taskID", taskid);
            tasks = (EmpTask) query.uniqueResult();
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
        return tasks;
    }

    @Override
    public List<EmpTask> loadTask(String task) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

        List<EmpTask> tasks = null;
        try {
             tx = session.beginTransaction();
            String HQL = "select t from EmpTask as t where description like :task";
            Query query = session.createQuery(HQL);
            query.setParameter("task", "%"+task+"%");
            tasks = (List<EmpTask>) query.list();
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
        return tasks;
    }

    @Override
    public List<EmpTask> loadTaskbyempid(int empid) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

        List<EmpTask> tasks = null;
        try {
             tx = session.beginTransaction();
            String HQL = "select t from EmpTask as t where employee =:empid";
            Query query = session.createQuery(HQL);
            query.setParameter("empid", empid);
            tasks = (List<EmpTask>) query.list();
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
        return tasks;
    }

    @Override
    public Set loadTasksbyempid(int empid) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

        Set tasks = null;
        try {
             tx = session.beginTransaction();
            String HQL = "select t from EmpTask as t where employee =:empid";
            Query query = session.createQuery(HQL);
            query.setParameter("empid", empid);
            tasks = (Set)query.list();
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
        return tasks;
    }
    
}
