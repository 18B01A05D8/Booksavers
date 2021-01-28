package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.db.HibernateTemplate;
import com.dto.User;

public class UserDAO {
	
	private SessionFactory factory = null;

	public User getUserByUserPass(String loginId,String password) {

		return (User)HibernateTemplate.getObjectByUserPass(loginId,password);
	}

	public int register(User user) {
		System.out.println(user); 
		return HibernateTemplate.addObject(user);
	}

	public List<User> getAllUsers() {
		List<User> users = (List)HibernateTemplate.getObjectListByQuery("From User");
		System.out.println("Inside All Users ..."+users);
		return users;
	}

	public User getUser(int id) {
		return (User)HibernateTemplate.getObject(User.class,id);
	}

	public User getUserByEmail(String emailId) {
		return (User)HibernateTemplate.getObjectByEmail(emailId);
	}

	public int updateUser(User user){
		 return HibernateTemplate.updateObject(user);
	}
}
