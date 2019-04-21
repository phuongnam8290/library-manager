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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

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
    
    public Book() {}

    public Book(String name, int type, String author, Date publishYear,
            String publisher, long price, int remainCopy) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", type=" + type + ", author=" 
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
}
