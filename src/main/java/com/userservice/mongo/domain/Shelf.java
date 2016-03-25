package com.userservice.mongo.domain;

import java.util.Calendar;
import java.util.List;

public class Shelf {

	private String shelfName;
	private Calendar date;
	private List<ShelfBook> books;
	
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public List<ShelfBook> getBooks() {
		return books;
	}
	public void setBooks(List<ShelfBook> books) {
		this.books = books;
	}
	
	@Override
	public boolean equals(Object o){
		 if (o == this) {
	            return true;
	        }
	        if (!(o instanceof Shelf)) {
	            return false;
	        }
	        Shelf c = (Shelf) o;
	        return shelfName.equals(c.getShelfName());
	}
}
