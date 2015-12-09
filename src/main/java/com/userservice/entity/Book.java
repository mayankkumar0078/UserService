package com.userservice.entity;

public class Book {
	private int id;
	private int isbn10;
	private int isbn13;
	private String title;
	private String author;
	private String publisher;
	private double rating;
	private int searchCount;
	
	
	public Book() {
		// TODO Auto-generated constructor stub
	}


	public Book(int id, int isbn10, int isbn13, String title, String author,
			String publisher, double rating, int searchCount) {
		super();
		this.id = id;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.rating = rating;
		this.searchCount = searchCount;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIsbn10() {
		return isbn10;
	}


	public void setIsbn10(int isbn10) {
		this.isbn10 = isbn10;
	}


	public int getIsbn13() {
		return isbn13;
	}


	public void setIsbn13(int isbn13) {
		this.isbn13 = isbn13;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public int getSearchCount() {
		return searchCount;
	}


	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
	

	
}
