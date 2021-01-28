package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;


import com.db.HibernateTemplate;
import com.dto.Book;
import com.dto.User;

public class BookDAO {
	
	private SessionFactory factory = null;
	
	public int register(Book book) {		
		return HibernateTemplate.addObject(book);
	}

	public Book getBook(int bookId) {
		return (Book)HibernateTemplate.getObject(Book.class,bookId);
	}

	public List<Book> getAllBooks() {
		List<Book> books=(List)HibernateTemplate.getObjectListByQuery("From Book");
		return books;	
	}

	public List<Book> getBookByName(String bookName) {	
		return (List)HibernateTemplate.getObjectListByName(Book.class,"bookName",bookName);
	}
	
	public List<Book> getBookByCategoryName(String categoryName) {	
		return (List)HibernateTemplate.getObjectListByName(Book.class,"categoryName",categoryName);
	}
	
	public List<Book> getBookByColumns(String bookName, String selltype) {	
		return (List)HibernateTemplate.getObjectByColumns(bookName, selltype);
	}
	
	public List<Book> getBookById(User user) {	
		return (List)HibernateTemplate.getObjectListById(Book.class,"userId",user);
	}
	
}
