package com.java17hcb.library.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BookImportPK implements Serializable{
    @Column(name="BOOK_ID")
    private int bookId;
    
    @Column(name="IMPORT_BY")
    private int staffId;
    
    public BookImportPK(){}

    public BookImportPK(int bookId, int staffId) {
        this.bookId = bookId;
        this.staffId = staffId;
    }
    
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
