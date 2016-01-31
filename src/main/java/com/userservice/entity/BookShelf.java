package com.userservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book_shelf" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"book_shelf_name", "user_id"})})
public class BookShelf extends BaseEntity {
	
	@Column(name="BOOK_SHELF_NAME")
	private String bookShelfName;
	private boolean isActive;
	
	@OneToMany(cascade = CascadeType.ALL)
    private Set<BookInBookShelf> booksInBookShelves;

	@ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User userId;
	
	/*@Column(name = "BOOK_SHELF_ID", unique = true)
    @NotNull
    private Integer bookShelfId;*/
	

	
	public BookShelf() {}

	public BookShelf(boolean isActive) {
		super();
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	public Set<BookInBookShelf> getBooksInBookShelves() {
		return booksInBookShelves;
	}

	public void setBooksInBookShelves(Set<BookInBookShelf> booksInBookShelves) {
		this.booksInBookShelves = booksInBookShelves;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getBookShelfName() {
		return bookShelfName;
	}

	public void setBookShelfName(String bookShelfName) {
		this.bookShelfName = bookShelfName;
	}
	
	
}
