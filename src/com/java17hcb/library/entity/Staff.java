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
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "DIPLOMA")
    private int diploma;

    @Column(name = "POSITION")
    private int position;

    @Column(name = "DIVISION")
    private int division;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;
    
    @OneToMany(mappedBy = "importBy")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<BookImport> bookImportRecords;
    
    @OneToMany(mappedBy = "createdBy")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<LibraryCard> listCreatedCards;
    
    public Staff() {}

    public Staff(String username, String password, String fullName,
            Date dateOfBirth, int diploma, int position, int division,
            String address, String phone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.diploma = diploma;
        this.position = position;
        this.division = division;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDiploma() {
        return diploma;
    }

    public void setDiploma(int diploma) {
        this.diploma = diploma;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BookImport> getBookImportRecords() {
        return bookImportRecords;
    }

    public void setBookImportRecords (List<BookImport> bookImportRecords) {
        this.bookImportRecords = bookImportRecords;
    }

    public List<LibraryCard> getListCreatedCards() {
        return listCreatedCards;
    }

    public void setListCreatedCards(List<LibraryCard> listCreatedCards) {
        this.listCreatedCards = listCreatedCards;
    }
    
    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", username=" + username + ", password=" 
                + password + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth 
                + ", diploma=" + diploma + ", position=" + position + ", division=" 
                + division + ", address=" + address + ", phone=" + phone + ", bookImportRecords=" 
                + bookImportRecords + '}';
    }

    public void importBook (BookImport record){
        if(this.bookImportRecords == null){
            this.bookImportRecords = new ArrayList<>();
        }
        this.bookImportRecords.add(record);
    }
    
    public void createCard (LibraryCard card){
        if(this.listCreatedCards == null){
            this.listCreatedCards = new ArrayList<>();
        }
        this.listCreatedCards.add(card);
        card.setCreatedBy(this);
    }
}
