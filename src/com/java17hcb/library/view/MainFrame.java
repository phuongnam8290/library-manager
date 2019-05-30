/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java17hcb.library.view;

import com.java17hcb.library.bus.BusBook;
import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.tablemodel.BookTableModel;
import com.java17hcb.library.utils.CurrentStaff;
import com.java17hcb.library.utils.NumberRenderer;
import java.awt.Image;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author labyr
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
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
        lbAvatar = new javax.swing.JLabel();
        lbDivision = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        pnBanner = new javax.swing.JPanel();
        lbBanner = new javax.swing.JLabel();
        tpnMain = new javax.swing.JTabbedPane();
        pnBook = new javax.swing.JPanel();
        spnBook = new javax.swing.JScrollPane();
        tbBook = new javax.swing.JTable();
        btnImport = new javax.swing.JButton();
        pnCard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnInfo.setBackground(new java.awt.Color(230, 230, 250));
        pnInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(161, 214, 241), 3));
        pnInfo.setForeground(new java.awt.Color(230, 230, 250));

        lbAvatar.setToolTipText("");

        lbDivision.setBackground(new java.awt.Color(243, 74, 162));
        lbDivision.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbDivision.setForeground(new java.awt.Color(243, 74, 162));
        lbDivision.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDivision.setText("Admin");
        lbDivision.setToolTipText("");
        lbDivision.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbName.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Xin chào");
        lbName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnInfoLayout = new javax.swing.GroupLayout(pnInfo);
        pnInfo.setLayout(pnInfoLayout);
        pnInfoLayout.setHorizontalGroup(
            pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInfoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnInfoLayout.createSequentialGroup()
                        .addComponent(lbAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnInfoLayout.setVerticalGroup(
            pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInfoLayout.createSequentialGroup()
                .addGroup(pnInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnInfoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lbAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbDivision)
                        .addGap(28, 28, 28)))
                .addComponent(lbName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnBanner.setBackground(new java.awt.Color(230, 230, 250));
        pnBanner.setForeground(new java.awt.Color(230, 230, 250));

        javax.swing.GroupLayout pnBannerLayout = new javax.swing.GroupLayout(pnBanner);
        pnBanner.setLayout(pnBannerLayout);
        pnBannerLayout.setHorizontalGroup(
            pnBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnBannerLayout.setVerticalGroup(
            pnBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tpnMain.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tpnMainStateChanged(evt);
            }
        });

        tbBook.setColumnSelectionAllowed(true);
        spnBook.setViewportView(tbBook);
        tbBook.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnBookLayout = new javax.swing.GroupLayout(pnBook);
        pnBook.setLayout(pnBookLayout);
        pnBookLayout.setHorizontalGroup(
            pnBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnBook, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addGroup(pnBookLayout.createSequentialGroup()
                        .addComponent(btnImport)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnBookLayout.setVerticalGroup(
            pnBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImport)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        tpnMain.addTab("Book", pnBook);

        javax.swing.GroupLayout pnCardLayout = new javax.swing.GroupLayout(pnCard);
        pnCard.setLayout(pnCardLayout);
        pnCardLayout.setHorizontalGroup(
            pnCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        pnCardLayout.setVerticalGroup(
            pnCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        tpnMain.addTab("Library Card", pnCard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpnMain)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tpnMain)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        JDialog dialog = new ImportDialog(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnImportActionPerformed

    private void tpnMainStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tpnMainStateChanged
        switch(tpnMain.getSelectedIndex()){
            case 0:
                setupBookTable();
                break;
        }
    }//GEN-LAST:event_tpnMainStateChanged

    /**
     * @param args the command line arguments
     */
    public static void showScreen(String... args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImport;
    private javax.swing.JLabel lbAvatar;
    private javax.swing.JLabel lbBanner;
    private javax.swing.JLabel lbDivision;
    private javax.swing.JLabel lbName;
    private javax.swing.JPanel pnBanner;
    private javax.swing.JPanel pnBook;
    private javax.swing.JPanel pnCard;
    private javax.swing.JPanel pnInfo;
    private javax.swing.JScrollPane spnBook;
    private javax.swing.JTable tbBook;
    private javax.swing.JTabbedPane tpnMain;
    // End of variables declaration//GEN-END:variables

    private void setupComponents() {
         // Set attribute for main panel
        setTitle("Login");
        setLocationRelativeTo(null);
        
        if(!(CurrentStaff.getCurrentStaff().getDivision() == Staff.Division.THU_KHO)){
            btnImport.setEnabled(false);
        }
        
        // Set main image
        ImageIcon imageAvatar = new ImageIcon(getClass().getResource("/com/java17hcb/library/resource/user_avatar.png"));
        ImageIcon imageBanner = new ImageIcon(getClass().getResource("/com/java17hcb/library/resource/banner.jpg"));
        lbAvatar.setIcon(new ImageIcon(imageAvatar.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        lbBanner.setIcon(new ImageIcon(imageBanner.getImage().getScaledInstance(750, 140, Image.SCALE_SMOOTH)));
        
        Staff currentStaff = CurrentStaff.getCurrentStaff();
        if(currentStaff != null){
            String name = currentStaff.getFullName();
            String division = "";
            switch(currentStaff.getDivision()){
                case Staff.Division.THU_THU:
                    division = "Thủ thư";
                    break;
                case Staff.Division.THU_KHO:
                    division = "Thủ kho";
                    break;
                case Staff.Division.THU_QUY:
                    division = "Thủ quỹ";
                    break;
                case Staff.Division.BAN_GIAM_DOC:
                    division = "Ban giám đốc";
                    break;
            }
            
            lbName.setText("Hello " + name);
            lbDivision.setText(division);
        }
    }

    public void setupBookTable() {
        List<Book> books = BusBook.getInstance().findAllBooks();
        BookTableModel bookTableModel = new BookTableModel();
        bookTableModel.setData(books);
        bookTableModel.setJFrame(this);
        tbBook.setModel(bookTableModel);
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tbBook.getModel());
        tbBook.setRowSorter(sorter);
        
        if (tbBook.getColumnModel().getColumnCount() > 0) {
            tbBook.getColumnModel().getColumn(0).setMinWidth(50);
            tbBook.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbBook.getColumnModel().getColumn(1).setMinWidth(250);
            tbBook.getColumnModel().getColumn(1).setPreferredWidth(250);
            tbBook.getColumnModel().getColumn(2).setMinWidth(50);
            tbBook.getColumnModel().getColumn(2).setPreferredWidth(50);
            tbBook.getColumnModel().getColumn(2).setMaxWidth(100);
            tbBook.getColumnModel().getColumn(3).setMinWidth(150);
            tbBook.getColumnModel().getColumn(3).setPreferredWidth(250);
            tbBook.getColumnModel().getColumn(4).setMinWidth(100);
            tbBook.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbBook.getColumnModel().getColumn(5).setMinWidth(100);
            tbBook.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbBook.getColumnModel().getColumn(6).setMinWidth(100);
            tbBook.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbBook.getColumnModel().getColumn(7).setMinWidth(50);
            tbBook.getColumnModel().getColumn(7).setPreferredWidth(50);
        }
        
        TableColumnModel tableColumnModel = tbBook.getColumnModel();
        tableColumnModel.getColumn(6).setCellRenderer(NumberRenderer.getIntegerRenderer());
        
        TableColumn typeCol = tbBook.getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("A");
        comboBox.addItem("B");
        comboBox.addItem("C");
        typeCol.setCellEditor(new DefaultCellEditor(comboBox));
    }
}