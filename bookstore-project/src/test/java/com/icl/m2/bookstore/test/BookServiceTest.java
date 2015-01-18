package com.icl.m2.bookstore.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.entities.Book;
import com.bookstore.entities.User;
import com.bookstore.service.BookService;
import com.bookstore.service.UserService;
import com.bookstore.web.util.EMFListener;

public class BookServiceTest {

	EMFListener emf = null;
	
	BookService bookService = null;
	
	@Before
	public void init(){
		// les tests ne marchent pas comme le contexte n'est pas initialisé
//		emf = new EMFListener();
//		emf.contextInitialized(null);
		
		bookService = new BookService();
	}
	
	@Test
	public void testBookService() 
	{
		//Testing one book : 978-1430219569
		
		String isbn = "978-1430219569";
		
		Book b = bookService.find(isbn);
		
		Assert.assertNotNull("The book should not be null", b);
		
		// test avec un mauvais isbn
		
		String isbn2 = "978-1430219570";
		
		Book b2 = bookService.find(isbn2);
		
		Assert.assertNull("The book should be null", b);
	}

}

