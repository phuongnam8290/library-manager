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
@Table(name="library_card")
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    
    @Column(name="FULLNAME")
    private String fullName;
    
    @Column(name="TYPE")
    private int type;
    
    @Column(name="DATE_OF_BIRTH")
    private Date dateOfBirth;
    
    @Column(name="ADDRESS")
    private String address;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="CREATE_DATE")
    private Date createDate;
    
    @Column(name="EXPIRE_DATE")
    private Date expireDate;
    
    @Column(name="FINES_FEE")
    private long finesFee;

    @ManyToOne()
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name="CREATED_BY")
    private Staff createdBy;
    
    @OneToMany(mappedBy="card")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<RentReceipt> rentReceipts;
    
    public LibraryCard() {}
    
    public LibraryCard(String fullName, int type, Date dateOfBirth, 
            String address, String email, Date createDate, 
            Date expireDate, long finesFee) {
        this.fullName = fullName;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.finesFee = finesFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public long getFinesFee() {
        return finesFee;
    }

    public void setFinesFee(long finesFee) {
        this.finesFee = finesFee;
    }

    public Staff getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Staff createdBy) {
        this.createdBy = createdBy;
    }

    public List<RentReceipt> getRentReceipts() {
        return rentReceipts;
    }

    public void setRentReceipts(List<RentReceipt> rentReceipts) {
        this.rentReceipts = rentReceipts;
    }
    
    @Override
    public String toString() {
        return "LibraryCard{" + "id=" + id + ", fullName=" + fullName + ", type=" 
                + type + ", dateOfBirth=" + dateOfBirth + ", address=" + address 
                + ", email=" + email + ", createDate=" + createDate + ", expireDate=" 
                + expireDate + ", finesFee=" + finesFee + ", createdBy=" + createdBy
                + ", rentReceipts=" + rentReceipts + "}";
    }
    
    public void addRentReceipt(RentReceipt rentReceipt){
        if(this.rentReceipts == null){
            this.rentReceipts = new ArrayList<>();
        }
        this.rentReceipts.add(rentReceipt);
        rentReceipt.setCard(this);
    }
}
