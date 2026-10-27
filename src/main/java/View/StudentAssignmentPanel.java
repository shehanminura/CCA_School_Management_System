/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.StudentAssignmentController;
import Model.dto.AssignmentDto;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */

public class StudentAssignmentPanel extends javax.swing.JPanel {
        private StudentAssignmentController controller;

    public StudentAssignmentPanel() {
        initComponents();

        // Student Can't Edit
        txttitle.setEditable(false);
        txtdate.setEditable(false);
        txtlink.setEditable(false);
        txtdis.setEditable(false);
        subjectid.setEnabled(false);

        controller = new StudentAssignmentController(this);
        controller.loadSubjectIds();
        controller.loadTableData();

        // click table row and set it data filds
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow != -1) {
                    Assigementid.setText(jTable.getValueAt(selectedRow, 0).toString());
                    subjectid.setSelectedItem(jTable.getValueAt(selectedRow, 1).toString());
                    txttitle.setText(jTable.getValueAt(selectedRow, 2).toString());
                    txtdate.setText(jTable.getValueAt(selectedRow, 3).toString());
                    txtlink.setText(jTable.getValueAt(selectedRow, 4).toString());
                    txtdis.setText(jTable.getValueAt(selectedRow, 5).toString());
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtlink = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txttitle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnlink = new javax.swing.JButton();
        jbtnseartch = new javax.swing.JButton();
        Assigementid = new javax.swing.JLabel();
        txtsertchid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtclear = new javax.swing.JButton();
        txtdis = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        subjectid = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        jLabel1.setText("Assignment ID: ");

        txtlink.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setText("subject_id");

        jLabel4.setText("description");

        txttitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setText("due_date");

        txtdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setText("title");

        btnlink.setBackground(new java.awt.Color(0, 0, 153));
        btnlink.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnlink.setForeground(new java.awt.Color(255, 255, 255));
        btnlink.setText("Open");
        btnlink.addActionListener(this::btnlinkActionPerformed);

        jbtnseartch.setBackground(new java.awt.Color(255, 153, 0));
        jbtnseartch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnseartch.setForeground(new java.awt.Color(255, 255, 255));
        jbtnseartch.setText("Seartch by Id");
        jbtnseartch.setToolTipText("");
        jbtnseartch.addActionListener(this::jbtnseartchActionPerformed);

        Assigementid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Assigementid.setText("Assi_ID: ");

        txtsertchid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setText("Enter Studnt ID: ");

        txtclear.setBackground(new java.awt.Color(0, 0, 0));
        txtclear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtclear.setForeground(new java.awt.Color(255, 255, 255));
        txtclear.setText("Clear");
        txtclear.addActionListener(this::txtclearActionPerformed);

        txtdis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel15.setText("assignment_link");

        subjectid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        subjectid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "subjectid" }));
        subjectid.addActionListener(this::subjectidActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Assigementid)
                                        .addGap(31, 31, 31)
                                        .addComponent(subjectid, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txttitle, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtlink, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnlink, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbtnseartch, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtsertchid, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtclear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(txtdis, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txttitle)
                        .addComponent(txtlink)
                        .addComponent(btnlink))
                    .addComponent(Assigementid)
                    .addComponent(subjectid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtclear, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnseartch)
                            .addComponent(txtsertchid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnlinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlinkActionPerformed
        String url = txtlink.getText().trim();
        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an assignment or enter a link first!", "No Link Found", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Protocol එක (http/https) නොමැති නම් එක් කිරීම
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                // Windows fallback
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open link in browser: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnlinkActionPerformed

    private void jbtnseartchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnseartchActionPerformed
        controller.searchAssignment();
    }//GEN-LAST:event_jbtnseartchActionPerformed

    private void txtclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclearActionPerformed
        clearFields();
        controller.loadTableData();
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclearActionPerformed

    private void subjectidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectidActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Assigementid;
    private javax.swing.JButton btnlink;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JButton jbtnseartch;
    private javax.swing.JComboBox<String> subjectid;
    private javax.swing.JButton txtclear;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtdis;
    private javax.swing.JTextField txtlink;
    private javax.swing.JTextField txtsertchid;
    private javax.swing.JTextField txttitle;
    // End of variables declaration//GEN-END:variables
public javax.swing.JTable getTable() {
        return jTable; 
    }

    public javax.swing.JComboBox<String> getSubjectComboBox() {
        return subjectid;
    }
    
    public void clearFields() {
        Assigementid.setText("Assi_ID: ");
        txttitle.setText("");
        txtlink.setText("");
        txtdate.setText("");
        txtdis.setText("");
        txtsertchid.setText("");
        if (subjectid.getItemCount() > 0) {
            subjectid.setSelectedIndex(0);
        }
    }
    
    public void setAssignmentData(AssignmentDto dto) {
        Assigementid.setText(dto.getAssignmentId());
        subjectid.setSelectedItem(dto.getSubjectId());
        txttitle.setText(dto.getTitle());
        txtdis.setText(dto.getDescription());
        txtlink.setText(dto.getAssignmentLink());
        txtdate.setText(dto.getDueDate());
    }
    
    public String getSearchId() {
        return txtsertchid.getText(); 
    }
}