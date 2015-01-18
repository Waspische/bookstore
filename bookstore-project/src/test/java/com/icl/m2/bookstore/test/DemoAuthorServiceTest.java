package com.icl.m2.bookstore.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.entities.Author;
import com.bookstore.service.DemoAuthorService;

public class DemoAuthorServiceTest {

	DemoAuthorService authorService = null;
	
	@Before
	public void init(){
		authorService = new DemoAuthorService();
	}
	
	
	@Test
	public void test() {
		
		// il y a des auteurs dans la base
		
		List<Author> result = authorService.findAll();
		
		Assert.assertNotNull("Il y a des auteurs dans la base, le résultat ne devrait pas être null", result);
		Assert.assertNotEquals("Il y a des auteurs dans la base, le résultat ne devrait pas être vide", 0, result.size());
		
	}

}
