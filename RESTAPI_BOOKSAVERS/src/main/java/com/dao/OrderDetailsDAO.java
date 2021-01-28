package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.db.HibernateTemplate;
import com.dto.OrderDetails;

public class OrderDetailsDAO {
	
	private SessionFactory factory = null;
	
	public List<OrderDetails> getAllOrderDetails() {
		List<OrderDetails> orderDetails=(List)HibernateTemplate.getObjectListByQuery("From OrderDetails");
		return orderDetails;	
	}

}
