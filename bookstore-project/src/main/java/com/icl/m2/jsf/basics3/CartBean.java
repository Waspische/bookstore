package com.icl.m2.jsf.basics3;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.entities.Book;

public final class CartBean {

	private static List<Book> books = new ArrayList<Book>();
	private static float totalPrice;
	
	public final static void addToCart(Book book)
	{
		System.out.println("book to add : " + book.getTitle());
		books.add(book);
		for (Book b : books) {
			System.out.println(b.getTitle());
		}
		totalPrice += book.getUnitPrice();
	}

	public static List<Book> getBooks() {
		return books;
	}

	public static void setBooks(List<Book> books) {
		CartBean.books = books;
	}
	
}