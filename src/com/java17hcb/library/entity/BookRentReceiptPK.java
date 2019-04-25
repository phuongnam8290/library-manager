package com.java17hcb.library.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookRentReceiptPK implements Serializable {
    @Column(name="RENT_RECEIPT_ID")
    private int receiptId;
    
    @Column(name="BOOK_ID")
    private int bookId;
    
    public BookRentReceiptPK(){}

    public BookRentReceiptPK(int receiptId, int bookId) {
        this.receiptId = receiptId;
        this.bookId = bookId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
