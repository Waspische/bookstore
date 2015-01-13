package com.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entities.Book;
import com.bookstore.service.exception.UserAlreadyExistsException;
import com.bookstore.web.util.EMFListener;

public class BookService {
	
	
	public Book find(String isbn)
	{		
		EntityManager em = EMFListener.createEntityManager();
		Book book = em.find(Book.class, isbn);
		
		System.out.println(book);
		
		em.close();
		
		return book;
		
	}
	
	
}
