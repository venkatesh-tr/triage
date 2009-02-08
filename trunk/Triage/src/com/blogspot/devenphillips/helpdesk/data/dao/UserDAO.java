package com.blogspot.devenphillips.helpdesk.data.dao;

import java.util.List;

import org.hibernate.Session;
import com.blogspot.devenphillips.helpdesk.data.User;
import com.blogspot.devenphillips.helpdesk.zk.hibernate.HibernateUtil;

public class UserDAO {

	Session _session = null ;

	public UserDAO() {
		_session = HibernateUtil.getSessionFactory().getCurrentSession() ;
	}

	public void saveOrUpdate(User currUser) {
		_session.saveOrUpdate(currUser) ;
	}

	public void delete(User aUser) {
		_session.delete(aUser) ;
	}

	public User findByUsername(String username) {
		return (User)_session.createQuery("From User where username=:username").setString("username",username).list().get(0) ;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return _session.createQuery("From User").list() ;
	}
}
