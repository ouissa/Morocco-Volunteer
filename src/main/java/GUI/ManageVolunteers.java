/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import static GUI.Authentication.currentUserId;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author alilmalki
 */
public class ManageVolunteers extends javax.swing.JFrame {

    /**
     * Creates new form ManageVolunteers
     */
        String url;
        String uid;
        String pw;
    public ManageVolunteers() {
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
        //jButton2.setVisible(false);
        jComboBox2.addActionListener (new ActionListener () {
        public void actionPerformed(ActionEvent e) {
            String value = jComboBox2.getSelectedItem().toString();
            System.out.println(value);
            String qry = "";
            jComboBox3.removeAllItems();
            try (Connection conn = DriverManager.getConnection(url, uid, pw);
            Statement stmt = conn.createStatement()){
                if(!value.equals("All"))
                    qry = "SELECT DISTINCT P.role from Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId"
                        + " WHERE E.organizationId = "+currentUserId+" AND E.name = '"+jComboBox2.getSelectedItem().toString()+"';";
                else
                    qry = "SELECT DISTINCT P.role from Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId"
                        + " WHERE E.organizationId = "+currentUserId+";";
                
                ResultSet rs = stmt.executeQuery(qry);
                jComboBox3.addItem("All");
                while(rs.next()){
                    jComboBox3.addItem(rs.getString(1));
                }
            }
            catch(SQLException ex){
                System.err.println("SQLException: " + ex);
            }
        }
        });
        jComboBox1.addActionListener (new ActionListener () {
        public void actionPerformed(ActionEvent e) {
            //
        }
        });
        ((DefaultTableModel)(jTable1.getModel())).setRowCount(0);
        ((DefaultTableModel)(jTable1.getModel())).setColumnCount(0);
        try (Connection conn = DriverManager.getConnection(url, uid, pw);
            Statement stmt = conn.createStatement()){
                System.out.println(currentUserId);
                String qry = "SELECT V.volunteerId, V.firstname, V.lastname"
                + " FROM Volunteer AS V NATURAL JOIN Application AS A"
                + " NATURAL JOIN Position AS P INNER JOIN Event as E ON E.eventId = P.eventId"
                + " WHERE E.organizationId = "+currentUserId+" AND (A.applicationStatus = 'accepted' OR A.applicationStatus = 'participated')"
                + " UNION "
                + "SELECT V.volunteerId, V.firstname, V.lastname"
                + " FROM Volunteer AS V NATURAL JOIN Request AS R"
                + " NATURAL JOIN Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId"
                + " WHERE E.organizationId = "+currentUserId+" AND (R.requestStatus = 'accepted' OR R.requestStatus = 'participated');";
                System.out.println(qry); 
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
                
                //Fill Combo box
            
                String qry1 = "SELECT E.name"
                + " FROM Event AS E WHERE E.organizationId = "+currentUserId+";";
                
                
                ResultSet rs1 = stmt.executeQuery(qry1);
                
               
                while(rs1.next()){
                    jComboBox2.addItem(rs1.getString(1));
                }
                
                String qry2 = "";
                
                if(jComboBox2.getSelectedItem().toString().equals("All")){
                    
                    qry2 = "SELECT DISTINCT P.role from Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId"
                        + " WHERE E.organizationId = "+currentUserId+";";
                }
                else{
                    qry2 = "SELECT DISTINCT P.role from Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId"
                        + " WHERE E.organizationId = "+currentUserId+" AND E.name = '"+jComboBox2.getSelectedItem().toString()+"';";
                }
                
                
                
                ResultSet rs2 = stmt.executeQuery(qry2);
                

               
                while(rs2.next()){
                    jComboBox3.addItem(rs2.getString(1));
                }
            }
        catch (SQLException ex){
            System.err.println("SQLException: " + ex);
        }
        //Filling Event Combo box
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Manage Volunteers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(86, 122, 152))); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(86, 122, 152));
        jLabel1.setText("Event Name:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(86, 122, 152));
        jLabel2.setText("Search volunteers by:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(86, 122, 152));
        jLabel3.setText("Position Name:");

        jButton1.setBackground(new java.awt.Color(156, 189, 229));
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(86, 122, 152));
        jLabel4.setText("Status:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Accepted", "Participated", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(156, 189, 229));
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(156, 189, 229));
        jButton5.setText("Main menu");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        jButton6.setBackground(new java.awt.Color(156, 189, 229));
        jButton6.setText("View Profile");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(81, 81, 81))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(92, 92, 92)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String qry = "";
        if(jComboBox1.getSelectedItem().toString().equals("Accepted")){
               // jButton2.setVisible(true);
        }
        else{
            //jButton2.setVisible(false);
        }
        if(jComboBox2.getSelectedItem().toString().equals("All") && jComboBox3.getSelectedItem().toString().equals("All")){
            //none
            
            String sub_qry1 = "SELECT V.volunteerId, V.firstname, V.lastname"
                    + " FROM Volunteer AS V NATURAL JOIN Application AS A"
                    + " NATURAL JOIN Position AS P INNER JOIN Event as E ON E.eventId = P.eventId"
                    + " WHERE E.organizationId = "+currentUserId+"";
                    
            String sub_qry2 = " UNION SELECT V.volunteerId, V.firstname, V.lastname"
                    + " FROM Volunteer AS V NATURAL JOIN Request AS R"
                    + " NATURAL JOIN Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId"
                    + " WHERE E.organizationId = "+currentUserId+"";
            if(jComboBox1.getSelectedItem().toString().equals("All")){
                sub_qry1 += " AND (A.applicationStatus = 'accepted' OR A.applicationStatus = 'participated')";
                sub_qry2 += " AND (R.requestStatus = 'accepted' OR R.requestStatus = 'participated')";
                qry = sub_qry1 + sub_qry2;
                System.out.println(qry);
            }
            else if(jComboBox1.getSelectedItem().toString().equals("Accepted")){
                sub_qry1 += " AND (A.applicationStatus = 'accepted')";
                sub_qry2 += " AND (R.requestStatus = 'accepted')";
                qry = sub_qry1 + sub_qry2;
            }
            else{
                sub_qry1 += " AND (A.applicationStatus = 'participated')";
                sub_qry2 += " AND (R.requestStatus = 'participated')";
                qry = sub_qry1 + sub_qry2;
            }
        }
        else if(!jComboBox2.getSelectedItem().toString().equals("All") && !jComboBox3.getSelectedItem().toString().equals("All")){
            //both position and event are specified
                
            String sub_qry1 = "SELECT V.volunteerId, V.firstname, V.lastname"
                    + " FROM Volunteer AS V NATURAL JOIN Application AS A"
                    + " NATURAL JOIN Position AS P INNER JOIN Event as E ON E.eventId = P.eventId"
                    + " WHERE E.organizationId = "+currentUserId+" AND E.name = '"+jComboBox2.getSelectedItem().toString()+"' AND P.role = '"+jComboBox3.getSelectedItem().toString()+"'";
                    
            String sub_qry2 = " UNION SELECT V.volunteerId, V.firstname, V.lastname"
                    + " FROM Volunteer AS V NATURAL JOIN Request AS R"
                    + " NATURAL JOIN Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId "
                    + " WHERE E.organizationId = "+currentUserId+" AND E.name = '"+jComboBox2.getSelectedItem().toString()+"' AND P.role = '"+jComboBox3.getSelectedItem().toString()+"'";
            if(jComboBox1.getSelectedItem().toString().equals("All")){
                sub_qry1 += " AND (A.applicationStatus = 'accepted' OR A.applicationStatus = 'participated')";
                sub_qry2 += " AND (R.requestStatus = 'accepted' OR R.requestStatus = 'participated')";
                qry = sub_qry1 + sub_qry2;
                
            }
            else if(jComboBox1.getSelectedItem().toString().equals("Accepted")){
                sub_qry1 += " AND (A.applicationStatus = 'accepted')";
                sub_qry2 += " AND (R.requestStatus = 'accepted')";
                qry = sub_qry1 + sub_qry2;
            }
            else{
                sub_qry1 += " AND (A.applicationStatus = 'participated')";
                sub_qry2 += " AND (R.requestStatus = 'participated')";
                qry = sub_qry1 + sub_qry2;
            }
        }
        else if(!jComboBox2.getSelectedItem().toString().equals("All")){
            //event is specified and position is all
            System.out.println("Salam");
            String sub_qry1 = "SELECT V.volunteerId, V.firstname, V.lastname"
                    + " FROM Volunteer AS V NATURAL JOIN Application AS A"
                    + " NATURAL JOIN Position AS P INNER JOIN Event as E ON E.eventId = P.eventId"
                    + " WHERE E.organizationId = "+currentUserId+" AND E.name = '"+jComboBox2.getSelectedItem().toString()+"'";
                    
            String sub_qry2 = " UNION SELECT V.volunteerId, V.firstname, V.lastname"
                    + " FROM Volunteer AS V NATURAL JOIN Request AS R"
                    + " NATURAL JOIN Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId "
                    + " WHERE E.organizationId = "+currentUserId+" AND E.name = '"+jComboBox2.getSelectedItem().toString()+"'";
            if(jComboBox1.getSelectedItem().toString().equals("All")){
                sub_qry1 += " AND (A.applicationStatus = 'accepted' OR A.applicationStatus = 'participated')";
                sub_qry2 += " AND (R.requestStatus = 'accepted' OR R.requestStatus = 'participated')";
                qry = sub_qry1 + sub_qry2;
                
            }
            else if(jComboBox1.getSelectedItem().toString().equals("Accepted")){
                sub_qry1 += " AND (A.applicationStatus = 'accepted')";
                sub_qry2 += " AND (R.requestStatus = 'accepted')";
                qry = sub_qry1 + sub_qry2;
            }
            else{
                sub_qry1 += " AND (A.applicationStatus = 'participated')";
                sub_qry2 += " AND (R.requestStatus = 'participated')";
                qry = sub_qry1 + sub_qry2;
            }
        }
        else{
            //position is specified and event is all
            String sub_qry1 = "SELECT V.volunteerId, V.firstname, V.lastname"
                    + " FROM Volunteer AS V NATURAL JOIN Application AS A"
                    + " NATURAL JOIN Position AS P INNER JOIN Event as E ON E.eventId = P.eventId"
                    + " WHERE E.organizationId = "+currentUserId+" AND P.role = '"+jComboBox3.getSelectedItem().toString()+"'";
                    
            String sub_qry2 = " UNION SELECT V.volunteerId, V.firstname, V.lastname"
                    + " FROM Volunteer AS V NATURAL JOIN Request AS R"
                    + " NATURAL JOIN Position AS P INNER JOIN Event AS E ON E.eventId = P.eventId "
                    + " WHERE E.organizationId = "+currentUserId+" AND P.role = '"+jComboBox3.getSelectedItem().toString()+"'";
            if(jComboBox1.getSelectedItem().toString().equals("All")){
                sub_qry1 += " AND (A.applicationStatus = 'accepted' OR A.applicationStatus = 'participated')";
                sub_qry2 += " AND (R.requestStatus = 'accepted' OR R.requestStatus = 'participated')";
                qry = sub_qry1 + sub_qry2;
            }
            else if(jComboBox1.getSelectedItem().toString().equals("Accepted")){
                sub_qry1 += " AND (A.applicationStatus = 'accepted')";
                sub_qry2 += " AND (R.requestStatus = 'accepted')";
                qry = sub_qry1 + sub_qry2;
            }
            else{
                sub_qry1 += " AND (A.applicationStatus = 'participated')";
                sub_qry2 += " AND (R.requestStatus = 'participated')";
                qry = sub_qry1 + sub_qry2;
            }
        }
        
        ((DefaultTableModel)(jTable1.getModel())).setRowCount(0);
        ((DefaultTableModel)(jTable1.getModel())).setColumnCount(0);
        try (Connection conn = DriverManager.getConnection(url, uid, pw);
            Statement stmt = conn.createStatement()){
                
                               
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
        catch (SQLException ex){
            System.err.println("SQLException: " + ex);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        OrganizationMenu frm = new OrganizationMenu();
        frm.setLocation(getLocation());
        frm.setSize(getSize());
        setVisible(false);
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        OrganizationMenu frm = new OrganizationMenu();
        frm.setLocation(getLocation());
        frm.setSize(getSize());
        setVisible(false);
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int column = 0;
        int row1 = jTable1.getSelectedRow();
        if(row1 == -1){
            JOptionPane.showMessageDialog(this, "Please select a Volunteer");
            return;
        }
        int volunteerId = Integer.parseInt(jTable1.getModel().getValueAt(row1, column).toString());
        VolunteerProfile frm = new VolunteerProfile(this, volunteerId);
        frm.setLocation(getLocation());
        frm.setSize(getSize());
        setVisible(false);
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageVolunteers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageVolunteers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageVolunteers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageVolunteers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageVolunteers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
