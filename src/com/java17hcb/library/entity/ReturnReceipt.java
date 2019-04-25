package com.java17hcb.library.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="return_receipt")
public class ReturnReceipt {
    @Id
    @Column(name="RENT_RECEIPT_ID")
    private int id;
    
    @Column(name="RETURN_DATE")
    private Date returnDate;
    
    @Column(name="LATE_FEE")
    private long lateFee;
    
    @Column(name="LOST_FEE")
    private long lostFee;
    
    @OneToOne
    @JoinColumn(name="RENT_RECEIPT_ID")
    @MapsId
    private RentReceipt rentReceipt;

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

    @Override
    public String toString() {
        return "ReturnReceipt{" + "id=" + id + ", returnDate=" + returnDate + ", lateFee=" 
                + lateFee + ", lostFee=" + lostFee + ", rentReceipt=" + rentReceipt + '}';
    }  
}
