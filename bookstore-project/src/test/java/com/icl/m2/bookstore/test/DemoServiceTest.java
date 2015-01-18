package com.icl.m2.bookstore.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.entities.Book;
import com.bookstore.service.DemoService;

public class DemoServiceTest {

	DemoService demoService = null;
	
	@Before
	public void init(){
		demoService = new DemoService();
	}
	
	
	@Test
	public void test() {
		
		// il y a des livres dans la base
		
		List<Book> result = demoService.findAll();
		
		Assert.assertNotNull("Il y a des livres dans la base, le résultat ne devrait pas être null", result);
		Assert.assertNotEquals("Il y a des livres dans la base, le résultat ne devrait pas être vide", 0, result.size());
		
	}
}
