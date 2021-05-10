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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import static GUI.Authentication.currentUserId;

  

/**
 *
 * @author alilmalki
 */
public class VolunteerProfile extends javax.swing.JFrame {

    /**
     * Creates new form VolunteerProfile
     */
        String url;
        String uid;
        String pw;
    private Object previousForm;
    private int volunteerId;
    private int positionId;
    public VolunteerProfile() {
        initComponents();
        jTextArea1.setEditable(false);
    }
    
    public VolunteerProfile(Object frm, int volunteerId){
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
        jTextArea1.setEditable(false);
        this.previousForm = frm;
        this.volunteerId = volunteerId;
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        
        if(this.previousForm instanceof ManageVolunteers){
            jButton2.setVisible(true);
        }
        else if(this.previousForm instanceof VolunteersForPosition){
            jButton3.setVisible(true);
        }
        else if(this.previousForm instanceof ManageApplications){
            jButton4.setVisible(true);
            jButton5.setVisible(true);
        }
        else if(this.previousForm instanceof VolunteerMenu){
            jButton6.setVisible(true);
            jButton7.setVisible(true);
        }
       
        
        
        try (Connection conn = DriverManager.getConnection(url, uid, pw); Statement stmt = conn.createStatement()) {
            String qry = "SELECT firstname, lastname, birthdate, gender, address from Volunteer where volunteerId = "+this.volunteerId+";";
        
            // Result set get the result of the SQL query
            ResultSet rs = stmt.executeQuery(qry);
            if(rs.next()){
                jTextField1.setText(rs.getString(1));
                jTextField2.setText(rs.getString(2));
                jTextField3.setText(rs.getString(3));
                jTextField4.setText(rs.getString(4));
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
        System.out.println("Hello this constructor was called");
        this.positionId = positionId;
        this.volunteerId = volunteerId;
        this.previousForm = frm;
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        
        jTextArea1.setEditable(false);
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        
        if(this.previousForm instanceof VolunteersForPosition  || this.previousForm instanceof RequestVolunteers){
            jButton3.setVisible(true);
        }
        else if(this.previousForm instanceof ManageVolunteers){
            jButton2.setVisible(true);
        }
        else if(this.previousForm instanceof ManageApplications){
            jButton4.setVisible(true);
            jButton5.setVisible(true);
        }
        
        try (Connection conn = DriverManager.getConnection(url, uid, pw); Statement stmt = conn.createStatement()) {
            String qry = "SELECT firstname, lastname, birthdate, gender, address from Volunteer where volunteerId = "+this.volunteerId+";";
        
            // Result set get the result of the SQL query
            ResultSet rs = stmt.executeQuery(qry);
            if(rs.next()){
                jTextField1.setText(rs.getString(1));
                jTextField2.setText(rs.getString(2));
                jTextField3.setText(rs.getString(3));
                jTextField4.setText(rs.getString(4));
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Volunteer Profile", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(86, 122, 152))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(86, 122, 152));
        jLabel1.setText("First Name:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(86, 122, 152));
        jLabel2.setText("LastName:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(86, 122, 152));
        jLabel3.setText("Birth Date:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(86, 122, 152));
        jLabel4.setText("Gender:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(86, 122, 152));
        jLabel5.setText("Address:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setBackground(new java.awt.Color(156, 189, 229));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(156, 189, 229));
        jButton2.setText("Endorse");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(86, 122, 152));
        jLabel10.setText("Average Rate:");

        jLabel11.setText("jLabel11");

        jButton3.setBackground(new java.awt.Color(156, 189, 229));
        jButton3.setText("Send Request");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(156, 189, 229));
        jButton4.setText("Accept");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(156, 189, 229));
        jButton5.setText("Reject");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jTextField4.setText("jTextField4");

        jButton6.setBackground(new java.awt.Color(156, 189, 229));
        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(156, 189, 229));
        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel10))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
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
        else if(this.previousForm instanceof ManageApplications){
            ManageApplications frm = new ManageApplications();
            frm.setLocation(getLocation());
            frm.setSize(getSize());
            setVisible(false);
            frm.setVisible(true);
            dispose(); 
        }
        else if(this.previousForm instanceof RequestVolunteers){
            RequestVolunteers frm = new RequestVolunteers();
            frm.setLocation(getLocation());
            frm.setSize(getSize());
            setVisible(false);
            frm.setVisible(true);
            dispose();
        }
        else if(this.previousForm instanceof VolunteerMenu){
            VolunteerMenu frm = new VolunteerMenu();
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
            + " (positionId, volunteerId, requestStatus)"
           + " VALUES (?, ?, 'pending');";
            
            

            
            
            PreparedStatement prepStmt = conn.prepareStatement(qry);
            
            prepStmt.setInt (1, this.positionId);
            prepStmt.setInt (2, this.volunteerId);
            
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try (Connection conn = DriverManager.getConnection(url, uid, pw)){
            System.out.println(this.volunteerId);
           
            String qry = "UPDATE Application"
            + " SET applicationStatus = 'accepted'"
            + " WHERE volunteerId = "+this.volunteerId+" AND positionId = "+this.positionId+"";
            PreparedStatement prepStmt = conn.prepareStatement(qry);
            prepStmt.execute();
            JOptionPane.showMessageDialog(this, "Application has been accepted");
            if(this.previousForm instanceof RequestVolunteers){
                RequestVolunteers frm = new RequestVolunteers();
                frm.setLocation(getLocation());
                frm.setSize(getSize());
                setVisible(false);
                frm.setVisible(true);
                dispose();
            }
            else if(this.previousForm instanceof ManageApplications){
                ManageApplications frm = new ManageApplications();
                frm.setLocation(getLocation());
                frm.setSize(getSize());
                setVisible(false);
                frm.setVisible(true);
                dispose();
            }
            
        }
        catch (SQLException ex){
            System.err.println("SQLException: " + ex);
        }
        catch (Exception e) {
            System.err.println("Exception: " + e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try (Connection conn = DriverManager.getConnection(url, uid, pw)){
            
            String qry = "UPDATE Application"
            + " SET applicationStatus = 'rejected'"
            + " WHERE volunteerId = "+this.volunteerId+" AND positionId = "+this.positionId+"";
            PreparedStatement prepStmt = conn.prepareStatement(qry);
            prepStmt.execute();
            JOptionPane.showMessageDialog(this, "Application has been rejected");
            ManageApplications frm = new ManageApplications();
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
        jTextField1.setEditable(true);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jTextField4.setEditable(true);
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try (Connection conn = DriverManager.getConnection(url, uid, pw); ) {
           
            String firstname = jTextField1.getText();
            String lastname = jTextField2.getText();
            
            SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = formatdate.parse(jTextField3.getText());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String gender = jTextField4.getText();
            
            String address = jTextArea1.getText();
            
            String qry = "UPDATE Volunteer set firstname = '"+firstname+"', lastname = '"+lastname+"', birthdate = '"+sqlDate.toString()+"', gender = '"+gender+"', address = '"+address+"' WHERE VolunteerId = "+currentUserId+";";
            PreparedStatement prepStmt = conn.prepareStatement(qry);
            
            
            prepStmt.execute();
            
            
            jTextField1.setText(firstname);
            jTextField2.setText(lastname);
            jTextField3.setText(sqlDate.toString());
            jTextField4.setText(gender);
            jTextArea1.setText(address);
            
            jTextField1.setEditable(false);
            jTextField2.setEditable(false);
            jTextField3.setEditable(false);
            jTextField4.setEditable(false);
            jTextArea1.setEditable(false);
            
            JOptionPane.showMessageDialog(this, "Saved Successfully");
            return;
 

            
        }
        catch (SQLException ex){
            System.err.println("SQLException: " + ex);
        }
        catch(Exception ex){
            
        }
    }//GEN-LAST:event_jButton7ActionPerformed

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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
