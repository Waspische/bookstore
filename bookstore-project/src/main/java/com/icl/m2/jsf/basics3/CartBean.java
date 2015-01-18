package com.icl.m2.jsf.basics3;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bookstore.entities.Book;

@ManagedBean(name="cartBean")
@SessionScoped
public class CartBean {

	private List<BookInCart> books = new ArrayList<BookInCart>();
	private float totalPrice;
	
	private BookInCart selectedBook;
	
	public void addToCart(Book book)
	{
		BookInCart bookInCart = getBookInCart(book);
		if(bookInCart != null){
			bookInCart.setQuantity(bookInCart.getQuantity()+1);
		} else {
			System.out.println("book to add : " + book.getTitle());
			books.add(new BookInCart(book, 1));
			for (BookInCart b : books) {
				System.out.println(b.getBook().getTitle());
			}
		}
		totalPrice += book.getUnitPrice();
	}

	public String deleteBook(){
		System.out.println("book to remove : " + selectedBook.getBook().getTitle());
		for (BookInCart b : books) {
			System.out.println(b.getBook().getTitle());
		}
		totalPrice -= (selectedBook.getBook().getUnitPrice() * selectedBook.getQuantity());
		
		if(totalPrice < 0.01){
			totalPrice = 0;
		}

		books.remove(selectedBook);
		
		return null;
	}
	
	public String addOneQtt(){
		
		selectedBook.setQuantity(selectedBook.getQuantity() + 1);
		
		totalPrice += selectedBook.getBook().getUnitPrice();
		
		return null;
	}
	
	public String removeOneQtt(){
		
		if(selectedBook.getQuantity() == 1){
			deleteBook();
		} else {
			selectedBook.setQuantity(selectedBook.getQuantity() - 1);
			
			totalPrice -= selectedBook.getBook().getUnitPrice();
			
			if(totalPrice < 0.01){
				totalPrice = 0;
			}
		}
		
		return null;
	}
	
	public int getNbBooks(){
		return books.size();
	}
	
	private BookInCart getBookInCart(Book book){
		for (BookInCart bookInCart : books) {
			if(bookInCart.getBook().getIsbn().equals(book.getIsbn())){
				return bookInCart;
			}
		}
		return null;
	}
	
	public List<BookInCart> getBooks() {
		return books;
	}

	public void setBooks(List<BookInCart> books) {
		this.books = books;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BookInCart getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(BookInCart selectedBook) {
		this.selectedBook = selectedBook;
	}
	
}