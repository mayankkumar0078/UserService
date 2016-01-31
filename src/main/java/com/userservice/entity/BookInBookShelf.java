package com.userservice.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "book_in_book_shelf")
public class BookInBookShelf extends BaseEntity{

	@ManyToOne
	@JsonIgnore private BookShelf bookShelfId;

	@Column(name = "BOOK_ID")
    @NotNull
    private Integer bookId;

	@Column(name = "IS_ACTIVE")
    @NotNull
    private boolean isActive;

	

	
	public Integer getBookId() {
        return bookId;
    }

	public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

	public BookShelf getBookShelfId() {
		return bookShelfId;
	}

	public void setBookShelfId(BookShelf bookShelfId) {
		this.bookShelfId = bookShelfId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

}
