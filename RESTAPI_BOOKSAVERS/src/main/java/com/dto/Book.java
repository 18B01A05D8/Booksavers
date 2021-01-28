package com.dto;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Book {
	@Id
	@GeneratedValue
	private int bookId;
	private String bookName;
	private String categoryName;
	private String authorName;
	private String bookImage;
	private double bookPrice;
	private String selltype;
	
	public String getSelltype() {
		return selltype;
	}

	public void setSelltype(String selltype) {
		this.selltype = selltype;
	}

	@OneToMany(mappedBy="book",fetch = FetchType.LAZY)
	List<OrderDetails> orderdetailsList = new ArrayList<OrderDetails>();
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetails> getOrderdetailsList() {
		return orderdetailsList;
	}

	public void setOrderdetailsList(List<OrderDetails> orderdetailsList) {
		this.orderdetailsList = orderdetailsList;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
		

}
