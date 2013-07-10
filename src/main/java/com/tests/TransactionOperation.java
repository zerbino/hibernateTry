package com.tests;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.utils.HibernateUtils;

/**
 * Function design pattern : to respect the advice from hibernate API without duplicating code. Every transaction
 * must be in a try catch. 
 * @author Raoul
 *
 */
public abstract class TransactionOperation {
	
	protected abstract void operationBrut(Session session, Object ... object) throws Exception;
	
	public void operation(Object ... object) throws Exception{
		Session session = HibernateUtils.getSession();
		Transaction tx=null;
		try{
			tx = session.beginTransaction();
			this.operationBrut(session, object);
			tx.commit();
			
		}
		catch(Exception e){
			if(tx!=null) tx.rollback();
			throw e;
			
		}
		finally{
			session.close();
		}
	}

}
