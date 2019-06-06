package com.java17hcb.library.entity;

import java.util.ArrayList;
import java.util.Calendar;
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
public class LibraryCard implements Cloneable {
    
    public static class Type{
        public static final int X = 0;
        public static final int Y = 1;
    }
    
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
    
    @OneToMany(mappedBy="card")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<LostHistory> lostHistory;
    
    @OneToMany(mappedBy="payedBy")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<FinesReceipt> finesReceipts;
    
    public LibraryCard() {}
    
    public LibraryCard(String fullName, int type, Date dateOfBirth, 
            String address, String email) {
        this.fullName = fullName;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.createDate = new Date();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(createDate);
        cal.add(Calendar.MONTH, 6);
        this.expireDate = cal.getTime();
        
        this.finesFee = 0;
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

    public List<LostHistory> getLostHistory() {
        return lostHistory;
    }

    public void setLostHistory(List<LostHistory> lostHistory) {
        this.lostHistory = lostHistory;
    }

    public List<FinesReceipt> getFinesReceipts() {
        return finesReceipts;
    }

    public void setFinesReceipts(List<FinesReceipt> finesReceipts) {
        this.finesReceipts = finesReceipts;
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
    
    public void addLostRecord(LostHistory record){
        if(this.lostHistory == null){
            this.lostHistory = new ArrayList<>();
        }
        this.lostHistory.add(record);
    }
     
    public void addFinesReceipt(FinesReceipt receipt){
        if(this.finesReceipts == null) {
            this.finesReceipts = new ArrayList<>();
        }
        this.finesReceipts.add(receipt);
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
