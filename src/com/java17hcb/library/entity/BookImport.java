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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="book_import")
public class BookImport {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="IMPORT_BY")
    private Staff importBy;
    
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name="BOOK_ID")
    private Book importedBook;
    
    @Column(name="IMPORT_DATE")
    private Date importDate;
    
    @Column(name="COPY")
    private int copy;
    
    public BookImport() {}

    public BookImport(Staff importBy, Book importedBook) {
        this.importBy = importBy;
        this.importedBook = importedBook;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Staff getImportBy() {
        return importBy;
    }

    public void setImportBy(Staff importBy) {
        this.importBy = importBy;
    }

    public Book getImportedBook() {
        return importedBook;
    }

    public void setImportedBook(Book book) {
        this.importedBook = book;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    @Override
    public String toString() {
        return "BookInport{" + "importBy=" + 
                importBy + ", importedBook=" + importedBook + ", importDate=" + importDate + 
                ", copy=" + copy + '}';
    }  
}
