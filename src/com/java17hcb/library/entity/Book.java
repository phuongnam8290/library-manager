package com.java17hcb.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="book")
public class Book implements Cloneable{
    
    public static class Type{
        public static final int A = 0;
        public static final int B = 1;
        public static final int C = 2;
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TYPE")
    private int type;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PUBLISH_YEAR")
    private Date publishYear;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "REMAIN_COPY")
    private int remainCopy;

    @OneToMany(mappedBy = "importedBook")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<BookImport> bookImportRecords;
    
    @OneToMany(mappedBy = "book")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<BookRentReceipt> bookRentReceipts;
    
    @OneToMany(mappedBy = "book")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<LostHistory> lostHistory;
    public Book() {}
    
    @OneToMany(mappedBy = "book")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<LiquidateHistory> liquidateHistory;
    
    public Book(String title, int type, String author, Date publishYear,
            String publisher, long price, int remainCopy) {
        this.title = title;
        this.type = type;
        this.author = author;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.price = price;
        this.remainCopy = remainCopy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Date publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getRemainCopy() {
        return remainCopy;
    }

    public void setRemainCopy(int remainCopy) {
        this.remainCopy = remainCopy;
    }

    public List<BookImport> getBookImportRecords() {
        return bookImportRecords;
    }

    public void setBookImportRecords(List<BookImport> bookImportRecords) {
        this.bookImportRecords = bookImportRecords;
    }

    public List<BookRentReceipt> getBookRentReceipts() {
        return bookRentReceipts;
    }

    public void setBookRentReceipts(List<BookRentReceipt> bookRentReceipts) {
        this.bookRentReceipts = bookRentReceipts;
    }

    public List<LostHistory> getLostHistory() {
        return lostHistory;
    }

    public void setLostHistory(List<LostHistory> lostHistory) {
        this.lostHistory = lostHistory;
    }

    public List<LiquidateHistory> getLiquidateHistory() {
        return liquidateHistory;
    }

    public void setLiquidateHistory(List<LiquidateHistory> liquidateHistory) {
        this.liquidateHistory = liquidateHistory;
    }
    
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", type=" + type + ", author=" 
                + author + ", publishYear=" + publishYear + ", publisher=" + publisher 
                + ", price=" + price + ", remainCopy=" + remainCopy + ", bookImportRecords=" 
                + bookImportRecords + '}';
    }
    
    public void importBook (BookImport record){
        if(this.bookImportRecords == null){
            this.bookImportRecords = new ArrayList<>();
        }
        this.bookImportRecords.add(record);
    }
    
    public void addLostRecord(LostHistory record){
        if(this.lostHistory == null) {
            this.lostHistory = new ArrayList<>();
        }
        this.lostHistory.add(record);
    }
    
    public void addLiquidateRecord(LiquidateHistory record){
        if(this.liquidateHistory == null) {
            this.liquidateHistory = new ArrayList<>();
        }
        this.liquidateHistory.add(record);
    }

    @Override
    public Object clone(){
        Object cloneObject;
        try{
            return super.clone();
        } catch(CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }
    
    
}
