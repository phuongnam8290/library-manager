package com.java17hcb.library.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LOST_HISTORY")
public class LostHistory {
    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    private Book book;
    
    @ManyToOne
    @JoinColumn(name="LIBRARY_CARD_ID")
    private LibraryCard card;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;    
    
    @ManyToOne
    @JoinColumn(name="RECORD_BY")
    private Staff recordedBy;
    
    @Column(name="LOST_DATE")
    private Date lostDate = new Date();
    
    @Column(name="FINES_FEE")
    private long finesFee;

    public LostHistory() {
    }

    public LostHistory(Book book, LibraryCard card, Staff staff, long finesFee) {
        this.book = book;
        this.card = card;
        this.recordedBy = staff;
        this.finesFee = finesFee;
    }
    
    /*public LostHistoryPK getLostHistoryPK() {
        return lostHistoryPK;
    }

    public void setLostHistoryPK(LostHistoryPK lostHistoryPK) {
        this.lostHistoryPK = lostHistoryPK;
    }*/

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibraryCard getCard() {
        return card;
    }

    public void setCard(LibraryCard card) {
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getRecordBy() {
        return recordedBy;
    }

    public void setRecordBy(Staff recordBy) {
        this.recordedBy = recordBy;
    }

    public Date getLostDate() {
        return lostDate;
    }

    public void setLostDate(Date lostDate) {
        this.lostDate = lostDate;
    }

    public long getFinesFee() {
        return finesFee;
    }

    public void setFinesFee(long finesFee) {
        this.finesFee = finesFee;
    }

    @Override
    public String toString() {
        return "LostHistory{" + ", book=" + book 
                + ", card=" + card + ", id=" + id + ", recordedBy=" + recordedBy 
                + ", lostDate=" + lostDate + ", finesFee=" + finesFee + '}';
    }        
}
