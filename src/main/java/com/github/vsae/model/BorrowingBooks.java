package com.github.vsae.model;

import java.util.Date;

public class BorrowingBooks {

    private Integer id;

    private Integer bookId;

    private Integer userId;

    private Date borrowDate;

    private Date borrowingDeadLine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getBorrowingDeadLine() {
        return borrowingDeadLine;
    }

    public void setBorrowingDeadLine(Date borrowingDeadLine) {
        this.borrowingDeadLine = borrowingDeadLine;
    }
}
