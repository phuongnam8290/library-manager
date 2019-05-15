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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="return_receipt")
public class ReturnReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    
    @Column(name="RETURN_DATE")
    private Date returnDate;
    
    @Column(name="LATE_FEE")
    private long lateFee;
    
    @Column(name="LOST_FEE")
    private long lostFee;
    
    @ManyToOne
    @JoinColumn(name="RENT_RECEIPT_ID")
    private RentReceipt rentReceipt;

    @OneToMany(mappedBy="returnReceipt")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<BookRentReceipt> bookRentReceipts;
    
    public ReturnReceipt() {}

    public ReturnReceipt(RentReceipt rentReceipt) {
        this.rentReceipt = rentReceipt;
        this.returnDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public long getLateFee() {
        return lateFee;
    }

    public void setLateFee(long lateFee) {
        this.lateFee = lateFee;
    }

    public long getLostFee() {
        return lostFee;
    }

    public void setLostFee(long lostFee) {
        this.lostFee = lostFee;
    }

    public RentReceipt getRentReceipt() {
        return rentReceipt;
    }

    public void setRentReceipt(RentReceipt rentReceipt) {
        this.rentReceipt = rentReceipt;
    }

    public List<BookRentReceipt> getBookRentReceipts() {
        return bookRentReceipts;
    }

    public void setBookRentReceipts(List<BookRentReceipt> bookRentReceipts) {
        this.bookRentReceipts = bookRentReceipts;
    }
    
    @Override
    public String toString() {
        return "ReturnReceipt{" + "id=" + id + ", returnDate=" + returnDate + ", lateFee=" 
                + lateFee + ", lostFee=" + lostFee + ", rentReceipt=" + rentReceipt + '}';
    }
    
    public void addBookToReceipt(BookRentReceipt record){
        if(this.bookRentReceipts == null){
            this.bookRentReceipts = new ArrayList();
        }
        this.bookRentReceipts.add(record);
    }
}
