package com.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement

public class Orders {
	@Id
	@GeneratedValue
	private int orderId;
	private String orderType;
	private String orderStatus;
	private Date orderDate;
	private Date returnDate;
	
	@OneToMany(mappedBy="orders",fetch = FetchType.LAZY)
	List<OrderDetails> orderdetailsList = new ArrayList<OrderDetails>();

	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	
	public List<OrderDetails> getOrderdetailsList() {
		return orderdetailsList;
	}

	public void setOrderdetailsList(List<OrderDetails> orderdetailsList) {
		this.orderdetailsList = orderdetailsList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	

	

}
