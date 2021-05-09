/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.Authentication.currentUserId;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author supernova
 */
public class ManageRequests extends javax.swing.JFrame {

    String url;
    String uid;
    String pw;
    /**
     * Creates new form ManageRequests
     */
    public ManageRequests() {
        try{
            JSONParser parser = new JSONParser();
            String pathToHome= System.getProperty("user.home");
            Object obj = parser.parse(new FileReader(pathToHome + "/NetBeansProjects/VolunteerMorocco/src/main/java/environment_variables/db_credentials.json"));
            JSONObject db_credentials = (JSONObject)obj;

            url = (String) db_credentials.get("url");
            uid = (String) db_credentials.get("username");
            pw = (String) db_credentials.get("password");
            
        } catch (IOException e) {
            System.out.println(e);
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println(e);
        }
        
        initComponents();
        
        ((DefaultTableModel)(jTable1.getModel())).setRowCount(0);
        ((DefaultTableModel)(jTable1.getModel())).setColumnCount(0);
        
        try (Connection conn = DriverManager.getConnection(url, uid, pw);
        Statement stmt = conn.createStatement())
            
        {
            System.out.println("the selected value is: " + jComboBox1.getSelectedItem().toString());
            String qry = "SELECT P.positionId, O.organizationName, E.name, P.role, P.description" +
                " FROM Organization AS O INNER JOIN Event AS E ON O.organizationId = E.organizationId INNER JOIN Position AS P ON E.eventId = P.eventId INNER JOIN Request AS R ON P.positionId = R.positionId" +
                " WHERE R.requestStatus = '" + jComboBox1.getSelectedItem().toString().toLowerCase() +"' AND R.volunteerId = '" + currentUserId + "';";
                   
            // Result set get the result of the SQL query
            ResultSet rs = stmt.executeQuery(qry);
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            DefaultTableModel dtm = new DefaultTableModel();
            for (int i = 1; i <= c; i++)
            dtm.addColumn(rsmd.getColumnName(i));
            Object[] row;
            while (rs.next()) {
                row = new Object[c];
                for (int i = 0; i < c; i++)
                row[i] = rs.getString(i + 1);
                dtm.addRow(row);
            }
            jTable1.setModel(dtm);
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Manage Requests"));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 700));

        jLabel1.setText("Search by organization");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Accepted", "Rejected" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Clear");

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Accept Request");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Reject Request");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setText("View Position Info");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)
                        .addComponent(jButton1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // When the request status is changes
        try (Connection conn = DriverManager.getConnection(url, uid, pw);
        Statement stmt = conn.createStatement())
            
        {
            System.out.println("the selected value is: " + jComboBox1.getSelectedItem().toString());
            String qry = "SELECT P.positionId, O.organizationName, E.name, P.role, P.description" +
                " FROM Organization AS O INNER JOIN Event AS E ON O.organizationId = E.organizationId INNER JOIN Position AS P ON E.eventId = P.eventId INNER JOIN Request AS R ON P.positionId = R.positionId" +
                " WHERE R.requestStatus = '" + jComboBox1.getSelectedItem().toString().toLowerCase() +"' AND R.volunteerId = '" + currentUserId + "';";
                   
            // Result set get the result of the SQL query
            ResultSet rs = stmt.executeQuery(qry);
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            DefaultTableModel dtm = new DefaultTableModel();
            for (int i = 1; i <= c; i++)
            dtm.addColumn(rsmd.getColumnName(i));
            Object[] row;
            while (rs.next()) {
                row = new Object[c];
                for (int i = 0; i < c; i++)
                row[i] = rs.getString(i + 1);
                dtm.addRow(row);
            }
            jTable1.setModel(dtm);
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try (Connection conn = DriverManager.getConnection(url, uid, pw);
        Statement stmt = conn.createStatement()){
            if(jTextField1.getText().equals("")){
                ManageRequests frm = new ManageRequests();
                frm.setLocation(getLocation());
                frm.setSize(getSize());
                setVisible(false);
                frm.setVisible(true);
                dispose();
            }
                
            System.out.println("the selected value is: " + jComboBox1.getSelectedItem().toString());
            String qry = "SELECT P.positionId, O.organizationName, E.name, P.role" +
                " FROM Organization AS O INNER JOIN Event AS E ON O.organizationId = E.organizationId INNER JOIN Position AS P ON E.eventId = P.eventId INNER JOIN Request AS R ON P.positionId = R.positionId" +
                " WHERE O.organizationName = '" + jTextField1.getText() + "' AND R.requestStatus = '" + jComboBox1.getSelectedItem().toString().toLowerCase() +"' AND R.volunteerId = '" + currentUserId + "';";
                   
            // Result set get the result of the SQL query
            ResultSet rs = stmt.executeQuery(qry);
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            DefaultTableModel dtm = new DefaultTableModel();
            for (int i = 1; i <= c; i++)
            dtm.addColumn(rsmd.getColumnName(i));
            Object[] row;
            while (rs.next()) {
                row = new Object[c];
                for (int i = 0; i < c; i++)
                row[i] = rs.getString(i + 1);
                dtm.addRow(row);
            }
            jTable1.setModel(dtm);
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        VolunteerMenu frm = new VolunteerMenu();
        frm.setLocation(getLocation());
        frm.setSize(getSize());
        setVisible(false);
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int row = jTable1.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please select a request");
            return;
        }
        int volunteerId = currentUserId;
        int positionId = Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString());
        
        try (Connection conn = DriverManager.getConnection(url, uid, pw)){
            System.out.println(volunteerId);
           
            String qry = "UPDATE Request"
            + " SET requestStatus = 'accepted'"
            + " WHERE volunteerId = "+volunteerId+" AND positionId = "+positionId+"";
            PreparedStatement prepStmt = conn.prepareStatement(qry);
            prepStmt.execute();
            JOptionPane.showMessageDialog(this, "Request has been accepted");
            ManageRequests frm = new ManageRequests();
            frm.setLocation(getLocation());
            frm.setSize(getSize());
            setVisible(false);
            frm.setVisible(true);
            dispose();
            
        }
        catch (SQLException ex){
            System.err.println("SQLException: " + ex);
        }
        catch (Exception e) {
            System.err.println("Exception: " + e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        int row = jTable1.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please select a request");
            return;
        }
        int volunteerId = currentUserId;
        int positionId = Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString());
        
        try (Connection conn = DriverManager.getConnection(url, uid, pw)){
            System.out.println(volunteerId);
           
            String qry = "UPDATE Request"
            + " SET requestStatus = 'rejected'"
            + " WHERE volunteerId = "+volunteerId+" AND positionId = "+positionId+"";
            PreparedStatement prepStmt = conn.prepareStatement(qry);
            prepStmt.execute();
            JOptionPane.showMessageDialog(this, "Request has been rejected");
            ManageRequests frm = new ManageRequests();
            frm.setLocation(getLocation());
            frm.setSize(getSize());
            setVisible(false);
            frm.setVisible(true);
            dispose();
            
        }
        catch (SQLException ex){
            System.err.println("SQLException: " + ex);
        }
        catch (Exception e) {
            System.err.println("Exception: " + e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int column = 0;
        int row = jTable1.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please select a request");
            return;
        }
        int positionId = Integer.parseInt(jTable1.getModel().getValueAt(row, column).toString());
        
        PositionInfoV frm = new PositionInfoV(this, positionId);
        frm.setLocation(getLocation());
        frm.setSize(getSize());
        setVisible(false);
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageRequests().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
