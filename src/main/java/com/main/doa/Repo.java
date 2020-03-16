package com.main.doa;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Repo {
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public List getAll() {
		return sessionFactory.getCurrentSession().createQuery("from ModelObject").getResultList();
	}
}
