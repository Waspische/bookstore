package com.icl.m2.jsf.basics3;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entities.Author;
import com.bookstore.entities.Book;
import com.bookstore.service.BookService;

@ManagedBean(name="bookBean")
@RequestScoped
public class BookBean {

	private BookService bookService = new BookService();
	
	private String isbn = "3939393";

	private String title;

	private Double unitPrice;

	private Author author;
	
	private String editor = "";

	@PostConstruct
	public void init(){
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse res = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		this.isbn = req.getParameter("isbn");
		Book b = this.bookService.find(this.isbn);
		this.title = b.getTitle();
		this.author = b.getAuthor();
		this.unitPrice = b.getUnitPrice();
		this.setEditor(b.getEditor());			
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	
}
