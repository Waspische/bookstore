package com.icl.m2.jsf.basics3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bookstore.entities.Book;

@ManagedBean(name="bookInCart")
@SessionScoped
public class BookInCart {

	private Book book;
	
	private int quantity;

	public BookInCart(Book book, int quantity) {
		super();
		this.book = book;
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
