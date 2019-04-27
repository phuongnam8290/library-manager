package com.java17hcb.library.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="book_rent_receipt")
public class BookRentReceipt {
    
    public static class Status{
        public static final int NOT_RETURN = 0;
        public static final int RETURNED = 1;
        public static final int LOSTED = 1;
    }
    
    @EmbeddedId
    private BookRentReceiptPK bookRentReceiptPK = new BookRentReceiptPK();
    
    @ManyToOne
    @MapsId("receiptId")
    @JoinColumn(name="RENT_RECEIPT_ID")
    private RentReceipt rentReceipt;
    
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name="BOOK_ID")
    private Book book;
    
    @Column(name="STATUS")
    private int status;
    
    public BookRentReceipt(){}

    public BookRentReceipt(RentReceipt rentReceipt, Book book) {
        this.rentReceipt = rentReceipt;
        this.book = book;
        this.status = 0;
    }

    public BookRentReceiptPK getBookRentReceiptPK() {
        return bookRentReceiptPK;
    }

    public void setBookRentReceiptPK(BookRentReceiptPK bookRentReceiptPK) {
        this.bookRentReceiptPK = bookRentReceiptPK;
    }

    public RentReceipt getRentReceipt() {
        return rentReceipt;
    }

    public void setRentReceipt(RentReceipt rentReceipt) {
        this.rentReceipt = rentReceipt;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookRentReceipt{" + "bookRentReceiptPK=" + bookRentReceiptPK 
                + ", rentReceipt=" + rentReceipt + ", book=" + book + ", status=" 
                + status + '}';
    }
}
