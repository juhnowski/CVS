package com.juhnowski.test2.db;


import java.util.ArrayList;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class DAOTemplate<T> {
        
	protected Class<T> typeParameterClass;

	public DAOTemplate(){
	}
	public DAOTemplate(Class<T> typeParameterClass)
	{
        this.typeParameterClass = typeParameterClass;
    }

	protected Session tryOpenSession(){
		Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                return session;
	}
	
	protected void closeSession(Session session) {
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
	}

    public void delete(Long id) {
       
    	Session session = tryOpenSession();
       try{
        T del = (T) session.createCriteria(typeParameterClass)  
                .add(Restrictions.idEq(id)).uniqueResult();
        session.delete(del);
       } catch(HibernateException ex){
           ex.printStackTrace(System.out);
       } finally{
        closeSession(session);
       }
    }

    public ArrayList<T> getAll() {
    	Session session = tryOpenSession();
        ArrayList<T> result = null;
        try {
            result = (ArrayList<T>) session.createCriteria(typeParameterClass).list(); 
        } catch (HibernateException ex){
            ex.printStackTrace(System.out);
        } finally {
            closeSession(session);
        }

        return result;
    }
    
    public ArrayList<T> getByField(String fieldName, Object fieldValue) {
    	Session session = tryOpenSession();
        ArrayList<T> result = null;
        try {
            result = (ArrayList<T>) session.createCriteria(typeParameterClass).add(Restrictions.eq(fieldName, fieldValue)).list(); 
        } catch (HibernateException ex){
            ex.printStackTrace(System.out);
        } finally {
            closeSession(session);
        }
        return result;
    }

    public T getById(long id) {
    	Session session = tryOpenSession();
        T result = (T) session.get(typeParameterClass, id);
        closeSession(session);
        return result;
    }


    public void update(T object) {
        Session session = null;
        try{
            session = tryOpenSession();
            session.update(object);            
        } catch (Exception ex){
            ex.printStackTrace(System.out);
        } finally{
            if (session != null){
                closeSession(session);
            }
        }
    }


    public void add(T object) {
        Session session = null;
        try{
            session = tryOpenSession();
            session.save(object);
        } catch (Exception ex){
            ex.printStackTrace(System.out);
        } finally{
            if (session != null){
                closeSession(session);
            }
        }
    }

    public void clear()
    {
        Session session = null;
        try{
            session = tryOpenSession();
            String hql = String.format("delete from %s",typeParameterClass.getCanonicalName());
            Query query = session.createQuery(hql);
            query.executeUpdate();
        } catch (Exception ex){
            ex.printStackTrace(System.out);
        } finally{
            if (session != null){
                closeSession(session);
            }
        }
    }
}
