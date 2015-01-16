package com.icl.m2.jsf.basics3;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	private	String bookMessage = "";
	
	private String isbn;

	private String title;

	private Double unitPrice;

	private String author;
	
	private String editor = "";

	@ManagedProperty(value="#{cartBean}")
	private CartBean cartBean;

	@PostConstruct
	public void init(){
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse res = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		String req_isbn = req.getParameter("isbn");
		String req_action = req.getParameter("action");
		if(req_isbn != null)
		{
			this.isbn = req_isbn;
		}
		System.out.println(req_action);
		if(req_action.equals("cart"))
		{
			this.addToCart();
			this.bookMessage = "Livre ajouté au panier !";
		}
		
		System.out.println(this.isbn);
		Book b = this.bookService.find(this.isbn);
		System.out.println(b);
		if(b != null)
		{
			this.title = b.getTitle();
			this.author = b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName();
			this.unitPrice = b.getUnitPrice();
			this.setEditor(b.getEditor());
		}
		else
		{
			this.title = "";
			this.author = "";
			this.unitPrice = 0.0;
			this.setEditor("");
			this.bookMessage = "Aucun livre trouvé ! ";
		}
		 
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getBookMessage() {
		return bookMessage;
	}

	public void setBookMessage(String bookMessage) {
		this.bookMessage = bookMessage;
	}

	public void addToCart()
	{
		System.out.println("add!");
		System.out.println(this.isbn);
		cartBean.addToCart(this.bookService.find(this.isbn));
		//return "/pages/cart.xhtml";
	}

	public CartBean getCartBean() {
		return cartBean;
	}

	public void setCartBean(CartBean cartBean) {
		this.cartBean = cartBean;
	}
}
