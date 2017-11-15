/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.daoImpl;

import com.emp.dao.roleDao;
import com.emp.model.EmpRole;
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
public class roleDaoImpl implements roleDao {

    @Override
    public int addRole(EmpRole role) {
        int res = -1;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.save(role);
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
    public List<EmpRole> viewRole() {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

        List<EmpRole> persons = null;
        try {
            tx = session.beginTransaction();
            persons = session.createQuery(
                    "select r from EmpRole as r").list();

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
        return persons;

    }

    @Override
    public int updateRole(EmpRole role) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();
        int res =-1;
        try {
            tx = session.beginTransaction();
            session.update(role);
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
    public EmpRole getRole(int roleid) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getCurrentSession();

        EmpRole Role = null;
        try {
            tx = session.beginTransaction();
            String HQL = "select r from EmpRole as r where roleid =:roleid";
            Query query = session.createQuery(HQL);
            query.setParameter("roleid", roleid);
            Role = (EmpRole) query.uniqueResult();
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
        return Role;
    }
}
