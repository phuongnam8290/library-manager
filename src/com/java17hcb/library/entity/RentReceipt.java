package com.java17hcb.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="rent_receipt")
public class RentReceipt {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name="LIBRARY_CARD_ID")
    private LibraryCard card;
    
    @Column(name="RENT_DATE")
    private Date rentDate;
    
    @OneToMany(mappedBy = "rentReceipt")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<BookRentReceipt> bookRentReceipts;
    
//    @OneToMany(mappedBy = "rentReceipt")
//    @Cascade({CascadeType.SAVE_UPDATE})
//    private List<ReturnReceipt> returnReceipts;

    public RentReceipt() {}

    public RentReceipt(LibraryCard card, Date rentDate) {
        this.card = card;
        this.rentDate = rentDate;
    }
    
    public LibraryCard getCard() {
        return card;
    }

    public void setCard(LibraryCard card) {
        this.card = card;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public List<BookRentReceipt> getBookRentReceipts() {
        return bookRentReceipts;
    }

    public void setBookRentReceipts(List<BookRentReceipt> bookRentReceipts) {
        this.bookRentReceipts = bookRentReceipts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public List<ReturnReceipt> getReturnReceipts() {
//        return returnReceipts;
//    }
//
//    public void setReturnReceipts(List<ReturnReceipt> returnReceipts) {
//        this.returnReceipts = returnReceipts;
//    }
    
    @Override
    public String toString() {
        return "RentReceipt{" + "id=" + id + ", card=" + card + ", rentDate=" 
                + rentDate + '}';
    }
    
    public void addBookToReceipt (BookRentReceipt record){
        if(this.bookRentReceipts == null){
            this.bookRentReceipts = new ArrayList();
        }
        this.bookRentReceipts.add(record);
    }
}
