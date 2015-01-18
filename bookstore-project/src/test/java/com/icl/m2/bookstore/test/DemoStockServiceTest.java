package com.icl.m2.bookstore.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.entities.Book;
import com.bookstore.service.DemoStockService;
import com.bookstore.service.exception.AuthorUnknownException;
import com.bookstore.service.exception.BookAlreadyExistsException;

public class DemoStockServiceTest {

	DemoStockService demoStockService = null;
	
	@Before
	public void init(){
		demoStockService = new DemoStockService();
	}
	
	@Test
	public void addBookToStockTest() {
		
		Book b = null;
		
		// test avec un auteur non existant
		
		String isbn = "978-1430219570";
		String title = "Mon Nouveau Livre";
		int wrongAuthorId = 42;
		
		try {
			b = demoStockService.addBookToStock(isbn, title, wrongAuthorId);
			Assert.fail("Une exception devrait être levée car l'auteur n'existe pas.");
		} catch (AuthorUnknownException e) {
			// comportement normal
		} catch (BookAlreadyExistsException e) {
			Assert.fail("L'isbn ne devrait pas déjà exister : " + isbn);
		}
		
		String existingIsbn = "978-1430219569";
		int authorId = 1;

		try {
			b = demoStockService.addBookToStock(existingIsbn, title, authorId);
			Assert.fail("Une exception devrait être levée car l'isbn existe déjà.");
		} catch (AuthorUnknownException e) {
			Assert.fail("L'auteur existe déjà");
		} catch (BookAlreadyExistsException e) {
			// comportement normal
		}
		
	}

}
