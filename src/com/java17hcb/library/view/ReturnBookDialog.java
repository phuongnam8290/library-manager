/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java17hcb.library.view;

import com.java17hcb.library.bus.BusLibraryCard;
import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.LibraryCard;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author labyr
 */
public class ReturnBookDialog extends javax.swing.JDialog {

    private java.awt.Frame parent; 
    private LibraryCard card;
    private List<Book> rentedBooks;      

    
    public ReturnBookDialog(java.awt.Frame parent, boolean modal, LibraryCard card) {
        super(parent, modal);
        this.parent = parent;
        this.card = card;
        
        initComponents();
        setupComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnInfo = new javax.swing.JPanel();
        lbReader = new javax.swing.JLabel();
        lbCreatedDate = new javax.swing.JLabel();
        tfReader = new javax.swing.JTextField();
        tfReturnDate = new javax.swing.JTextField();
        pnRentedBook = new javax.swing.JPanel();
        chkbBook1 = new javax.swing.JCheckBox();
        chkbBook2 = new javax.swing.JCheckBox();
        chkbBook3 = new javax.swing.JCheckBox();
        chkbBook4 = new javax.swing.JCheckBox();
        chkbBook5 = new javax.swing.JCheckBox();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Receipt Info"));

        lbReader.setText("Reader");

        lbCreatedDate.setText("Return Date");

        tfReader.setEditable(false);

        tfReturnDate.setEditable(false);

        javax.swing.GroupLayout pnInfoLayout = new javax.swing.GroupLayout(pnInfo);
        pnInfo.setLayout(pnInfoLayout);
        pnInfoLayout.setHorizontalGroup(
            pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbReader, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbCreatedDate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfReader)
                    .addComponent(tfReturnDate))
                .addContainerGap())
        );
        pnInfoLayout.setVerticalGroup(
            pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInfoLayout.createSequentialGroup()
                .addGroup(pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbReader)
                    .addComponent(tfReader, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCreatedDate)
                    .addComponent(tfReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pnRentedBook.setBorder(javax.swing.BorderFactory.createTitledBorder("Rented Book"));

        chkbBook1.setText("Empty");
        chkbBook1.setEnabled(false);

        chkbBook2.setText("Empty");
        chkbBook2.setEnabled(false);

        chkbBook3.setText("Empty");
        chkbBook3.setEnabled(false);

        chkbBook4.setText("Empty");
        chkbBook4.setEnabled(false);

        chkbBook5.setText("Empty");
        chkbBook5.setEnabled(false);

        javax.swing.GroupLayout pnRentedBookLayout = new javax.swing.GroupLayout(pnRentedBook);
        pnRentedBook.setLayout(pnRentedBookLayout);
        pnRentedBookLayout.setHorizontalGroup(
            pnRentedBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRentedBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnRentedBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkbBook1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkbBook2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkbBook3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkbBook4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkbBook5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnRentedBookLayout.setVerticalGroup(
            pnRentedBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRentedBookLayout.createSequentialGroup()
                .addComponent(chkbBook1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkbBook2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkbBook3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkbBook4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkbBook5))
        );

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnRentedBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 152, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnOK)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnOK});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnRentedBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        List<Book> selectedBooks = new ArrayList();
        
        // Get all selected book
        int chkbPos = 0;
        for(Component c : pnRentedBook.getComponents()){
            if (c instanceof JCheckBox){
                JCheckBox chkb = (JCheckBox)c;
                if(chkb.isSelected()){
                    selectedBooks.add(rentedBooks.get(chkbPos));
                }
                chkbPos++;
            }
        }
        
        // Check if user select any book
        if(selectedBooks.isEmpty()){
            JOptionPane.showMessageDialog(this, 
                        "Please choose some book before commit.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        // Create return receipt based on card & selected book
        List<Integer> bookIds = new ArrayList();
        for (Book book : selectedBooks){
            bookIds.add(book.getId());
        }
        
        boolean success = BusLibraryCard.getInstance().returnBook(card.getId(), bookIds);
        if(success){
            JOptionPane.showMessageDialog(this, 
                                    "Create new return receipt for reader " + card.getFullName() + " success!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                        "Some error happen. Please try again later!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
        ((MainFrame)parent).setupCardTable();
        dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox chkbBook1;
    private javax.swing.JCheckBox chkbBook2;
    private javax.swing.JCheckBox chkbBook3;
    private javax.swing.JCheckBox chkbBook4;
    private javax.swing.JCheckBox chkbBook5;
    private javax.swing.JLabel lbCreatedDate;
    private javax.swing.JLabel lbReader;
    private javax.swing.JPanel pnInfo;
    private javax.swing.JPanel pnRentedBook;
    private javax.swing.JTextField tfReader;
    private javax.swing.JTextField tfReturnDate;
    // End of variables declaration//GEN-END:variables

    private void setupComponents() {
        setTitle("Create Return Receipt");
        setLocationRelativeTo(null);
        
        // Set reader's name
        tfReader.setText(card.getFullName());
        
        // Set rent date
        Date rentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tfReturnDate.setText(dateFormat.format(rentDate));
        
        
        // Set text for checkbox
        int visibleCheckbox = 0;   
        rentedBooks = BusLibraryCard.getInstance().getRentedBook(card.getId());
        for(Component c : pnRentedBook.getComponents()){
            if (c instanceof JCheckBox && visibleCheckbox < rentedBooks.size()){
                JCheckBox chkb = (JCheckBox)c;
                chkb.setEnabled(true);
                chkb.setText(rentedBooks.get(visibleCheckbox).getTitle());
                
                visibleCheckbox++;
            }
        }
    }
}
