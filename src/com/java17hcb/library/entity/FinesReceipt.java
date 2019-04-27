package com.java17hcb.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FINES_RECEIPT")
public class FinesReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="TAKED_BY")
    private Staff takedBy;
    
    @ManyToOne
    @JoinColumn(name="PAYED_BY")
    private LibraryCard payedBy;
    
    @Column(name="CURRENT_FINES_FEE")
    private long currentFinesFee;
    
    @Column(name="PAYMENT")
    private long payment;

    public FinesReceipt() {
    }

    public FinesReceipt(Staff takedBy, LibraryCard payedBy, long currentFinesFee, long payment) {
        this.takedBy = takedBy;
        this.payedBy = payedBy;
        this.currentFinesFee = currentFinesFee;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getTakedBy() {
        return takedBy;
    }

    public void setTakedBy(Staff takedBy) {
        this.takedBy = takedBy;
    }

    public LibraryCard getPayedBy() {
        return payedBy;
    }

    public void setPayedBy(LibraryCard payedBy) {
        this.payedBy = payedBy;
    }

    public long getCurrentFinesFee() {
        return currentFinesFee;
    }

    public void setCurrentFinesFee(long currentFinesFee) {
        this.currentFinesFee = currentFinesFee;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "FinesReceipt{" + "id=" + id + ", takedBy=" + takedBy + ", payedBy=" 
                + payedBy + ", currentFinesFee=" + currentFinesFee + ", payment=" 
                + payment + '}';
    }
    
    
}
