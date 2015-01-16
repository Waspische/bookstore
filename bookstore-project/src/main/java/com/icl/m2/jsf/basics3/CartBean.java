package com.icl.m2.jsf.basics3;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bookstore.entities.Book;

@ManagedBean(name="cartBean")
@SessionScoped
public class CartBean {

	private List<Book> books = new ArrayList<Book>();
	private float totalPrice;
	
	private Book selectedBook;
	
	public void addToCart(Book book)
	{
		System.out.println("book to add : " + book.getTitle());
		books.add(book);
		for (Book b : books) {
			System.out.println(b.getTitle());
		}
		totalPrice += book.getUnitPrice();
	}

	public String deleteBook(){
		System.out.println("book to remove : " + selectedBook.getTitle());
		books.remove(selectedBook);
		for (Book b : books) {
			System.out.println(b.getTitle());
		}
		totalPrice -= selectedBook.getUnitPrice();
		
		if(totalPrice < 0.01){
			totalPrice = 0;
		}
		
		return null;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}
	
}