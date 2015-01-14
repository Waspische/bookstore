package com.icl.m2.jsf.basics3;

import java.util.List;

import com.bookstore.entities.Book;

public final class CartBean {

	private static List<Book> books = null;
	
	public final static void addToCart(Book book)
	{
		books.add(book);
		for (Book b : books) {
			System.out.println(b.getTitle());
		}
	}
	
	
}