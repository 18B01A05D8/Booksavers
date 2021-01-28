package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.db.HibernateTemplate;
import com.dto.Orders;

public class OrderDAO {
	
private SessionFactory factory = null;
	
	public Orders getBook(int orderId) {
		return (Orders)HibernateTemplate.getObject(Orders.class,orderId);
	}

	public List<Orders> getAllOrders() {
		List<Orders> orders=(List)HibernateTemplate.getObjectListByQuery("From Order");
		return orders;	
	}

	public Orders getOrderById(int userId) {
		return (Orders)HibernateTemplate.getObject(Orders.class,userId);
	}

}
