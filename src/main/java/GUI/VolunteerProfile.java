/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author alilmalki
 */
public class VolunteerProfile extends javax.swing.JFrame {

    /**
     * Creates new form VolunteerProfile
     */
    String url = "jdbc:postgresql://localhost/lmalkia";
    String uid = "lmalkia";
    String pw = "Lmalki15";
    private Object previousForm;
    private int volunteerId;
    private int positionId;
    public VolunteerProfile() {
        initComponents();
        jTextArea1.setEditable(false);
    }
    
    public VolunteerProfile(Object frm, int volunteerId){
        initComponents();
        jTextArea1.setEditable(false);
        this.previousForm = frm;
        this.volunteerId = volunteerId;
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        if(this.previousForm instanceof ManageVolunteers){
            jButton2.setVisible(true);
        }
        else if(this.previousForm instanceof VolunteersForPosition){
            jButton3.setVisible(true);
        }
        
        try (Connection conn = DriverManager.getConnection(url, uid, pw); Statement stmt = conn.createStatement()) {
            String qry = "SELECT firstname, lastname, birthdate, gender, address from Volunteer where volunteerId = "+this.volunteerId+";";
        
            // Result set get the result of the SQL query
            ResultSet rs = stmt.executeQuery(qry);
            if(rs.next()){
                jLabel6.setText(rs.getString(1));
                jLabel7.setText(rs.getString(2));
                jLabel8.setText(rs.getString(3));
                jLabel9.setText(rs.getString(4));
                jTextArea1.setText(rs.getString(5));
            }
            else{
                JOptionPane.showMessageDialog(this, "VolunteerId Not Found");
                return;
            }
            
            String qry1 = "SELECT CAST(AVG(volunteerRating) AS decimal(8,2))" +
                            " FROM Volunteer AS V LEFT JOIN VolunteerEndorsement AS VE" +
                            " ON V.volunteerId = VE.volunteerId" +
                            " Group By V.volunteerID" +
                            " HAVING V.volunteerId = "+this.volunteerId+";";
            ResultSet rs1 = stmt.executeQuery(qry1);
            if(rs1.next()){
                if(rs1.getString(1) == null){
                    jLabel11.setText("None");
                }
                else{
                    jLabel11.setText(rs1.getString(1));
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "VolunteerId Not Found");
                return;
            }
            
            

            
        }
        catch (SQLException ex){
            System.err.println("SQLException: " + ex);
        }
        
        
        
    }
    public VolunteerProfile(Object frm, int volunteerId, int positionId){
        initComponents();
        System.out.println("Hello this constructor was called");
        this.positionId = positionId;
        this.volunteerId = volunteerId;
        this.previousForm = frm;
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jTextArea1.setEditable(false);
        if(this.previousForm instanceof VolunteersForPosition){
            jButton3.setVisible(true);
        }
        else if(this.previousForm instanceof ManageVolunteers){
            jButton2.setVisible(true);
        }
        try (Connection conn = DriverManager.getConnection(url, uid, pw); Statement stmt = conn.createStatement()) {
            String qry = "SELECT firstname, lastname, birthdate, gender, address from Volunteer where volunteerId = "+this.volunteerId+";";
        
            // Result set get the result of the SQL query
            ResultSet rs = stmt.executeQuery(qry);
            if(rs.next()){
                jLabel6.setText(rs.getString(1));
                jLabel7.setText(rs.getString(2));
                jLabel8.setText(rs.getString(3));
                jLabel9.setText(rs.getString(4));
                jTextArea1.setText(rs.getString(5));
            }
            else{
                JOptionPane.showMessageDialog(this, "VolunteerId Not Found");
                return;
            }
            
            String qry1 = "SELECT CAST(AVG(volunteerRating) AS decimal(8,2))" +
                            " FROM Volunteer AS V LEFT JOIN VolunteerEndorsement AS VE" +
                            " ON V.volunteerId = VE.volunteerId" +
                            " Group By V.volunteerID" +
                            " HAVING V.volunteerId = "+this.volunteerId+";";
            ResultSet rs1 = stmt.executeQuery(qry1);
            if(rs1.next()){
                if(rs1.getString(1) == null){
                    jLabel11.setText("None");
                }
                else{
                    jLabel11.setText(rs1.getString(1));
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "VolunteerId Not Found");
                return;
            }
            
            

            
        }
        catch (SQLException ex){
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Volunteer Profile"));

        jLabel1.setText("First Name:");

        jLabel2.setText("LastName:");

        jLabel3.setText("Birth Date:");

        jLabel4.setText("Gender:");

        jLabel5.setText("Address:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Endorse");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        jLabel9.setText("jLabel9");

        jLabel10.setText("Average Rate:");

        jLabel11.setText("jLabel11");

        jButton3.setText("Send Request");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(138, 138, 138)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(this.previousForm instanceof ManageVolunteers){
            ManageVolunteers frm = new ManageVolunteers();
            frm.setLocation(getLocation());
            frm.setSize(getSize());
            setVisible(false);
            frm.setVisible(true);
            dispose();
        }
        else if(this.previousForm instanceof VolunteersForPosition){
            VolunteersForPosition frm = new VolunteersForPosition(this.positionId);
            frm.setLocation(getLocation());
            frm.setSize(getSize());
            setVisible(false);
            frm.setVisible(true);
            dispose();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EndorseVolunteer frm = new EndorseVolunteer(this.previousForm, volunteerId);
        frm.setLocation(getLocation());
        frm.setSize(getSize());
        setVisible(false);
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try (Connection conn = DriverManager.getConnection(url, uid, pw)) {

            String qry = "INSERT INTO Request "
            + " (requestNumber, positionId, volunteerId, requestStatus)"
           + " VALUES (?, ?, ?, 'pending');";

            int requestNumber = 0;
            Statement tmpStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            ResultSet rs = tmpStmt.executeQuery("SELECT requestNumber FROM Request");
            if(rs.last()){
                requestNumber = rs.getInt("requestNumber")+1;
            }
            else{
                System.out.println("Error");
                System.exit(0);
            }
            
            PreparedStatement prepStmt = conn.prepareStatement(qry);
            prepStmt.setInt(1, requestNumber);
            prepStmt.setInt (2, this.positionId);
            prepStmt.setInt (3, this.volunteerId);
            
            prepStmt.execute();
            JOptionPane.showMessageDialog(this, "The request has been sent to the volunteer");
            }
            catch (SQLException ex) {
                System.err.println("SQLException: " + ex);
            }
            catch (Exception e) {
                System.err.println("Exception: " + e);
            }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(VolunteerProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VolunteerProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VolunteerProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VolunteerProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VolunteerProfile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
