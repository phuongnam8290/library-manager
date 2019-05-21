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
@Table(name="LIQUIDATE_HISTORY")
public class LiquidateHistory {
    public static class Reason{
        public static final int LOSTED = 0;
        public static final int BROKEN = 1;
        public static final int LOSTED_BY_STAFF = 2;
    }
    
    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    private Book book;
    
    @ManyToOne
    @JoinColumn(name="LIQUIDATE_BY")
    private Staff liquidateBy;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name="LIQUIDATE_DATE")
    private Date liquidateTime = new Date();
    
    @Column(name="REASON")
    private int reason;
    
    @Column(name="COPY")
    private int copy;

    public LiquidateHistory() {
    }

    public LiquidateHistory(Book book, Staff liquidateBy, int reason, int copy) {
        this.book = book;
        this.liquidateBy = liquidateBy;
        this.reason = reason;
        this.copy = copy;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Staff getLiquidateBy() {
        return liquidateBy;
    }

    public void setLiquidateBy(Staff liquidateBy) {
        this.liquidateBy = liquidateBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLiquidateTime() {
        return liquidateTime;
    }

    public void setLiquidateTime(Date liquidateTime) {
        this.liquidateTime = liquidateTime;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    @Override
    public String toString() {
        return "LiquidateHistory{" + ", book=" + book + ", liquidateBy=" + liquidateBy 
                + ", id=" + id + ", liquidateTime=" + liquidateTime + ", reason=" 
                + reason + ", copy=" + copy + '}';
    }
}
