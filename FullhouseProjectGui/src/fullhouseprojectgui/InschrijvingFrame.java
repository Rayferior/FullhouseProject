/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author Roy
 */
public class InschrijvingFrame extends javax.swing.JFrame {

    /**
     * Creates new form InschrijvingFrame
     */
    public InschrijvingFrame() {
        initComponents();
        FullhouseProjectGui.InschrijvingLijst();
        ButtonGroup group = new ButtonGroup();
        group.add(InschrijvingFrame.jRadioButtonM);
        group.add(InschrijvingFrame.jRadioButtonT);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListSpelers = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTenM = new javax.swing.JList();
        jButtonLijstTonen = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jRadioButtonM = new javax.swing.JRadioButton();
        jRadioButtonT = new javax.swing.JRadioButton();
        jButtonInConfirm = new javax.swing.JButton();
        jButtonTotaal = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jListSpelers);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Fullhouse Manager");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Spelers Inschrijven");

        jListTenM.setToolTipText("");
        jScrollPane2.setViewportView(jListTenM);

        jButtonLijstTonen.setText("Lijst Tonen");
        jButtonLijstTonen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLijstTonenMouseClicked(evt);
            }
        });

        jRadioButtonM.setText("Masterclass");

        jRadioButtonT.setText("Toernooi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonT)
                    .addComponent(jRadioButtonM))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonM, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLijstTonen, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(jButtonLijstTonen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonInConfirm.setText("Inschrijven");
        jButtonInConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonInConfirmMouseClicked(evt);
            }
        });

        jButtonTotaal.setText("Aantal deelnemers updaten");
        jButtonTotaal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonTotaalMouseClicked(evt);
            }
        });

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonTotaal))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jButtonInConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jLabel1)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap(12, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButtonTotaal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonInConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLijstTonenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLijstTonenMouseClicked
        FullhouseProjectGui.TInschrijvingLijstTonen();
    }//GEN-LAST:event_jButtonLijstTonenMouseClicked

    private void jButtonInConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonInConfirmMouseClicked

        String Query;
        ModelItem speler = (ModelItem) jListSpelers.getSelectedValue();
        String compareQuery;
        if (speler == null) {
            JOptionPane.showMessageDialog(null, "Selecteer een speler");
        } else {
            if (jRadioButtonT.isSelected()) {

                compareQuery = "Select s_code from ToernooiInschrijving where t_code = ?";
                Query = "Insert into ToernooiInschrijving set s_code = ?, t_code = ?, heeftBetaald = 'j'";
                ModelItemToernooi toer = (ModelItemToernooi) jListTenM.getSelectedValue();
                if (toer == null) {
                    JOptionPane.showMessageDialog(null, "Selecteer een toernooi");
                } else {
                    try {
                        PreparedStatement comparestat = (PreparedStatement) FullhouseProjectGui.con.prepareStatement(compareQuery);
                        comparestat.setString(1, toer.id);
                        ResultSet comparers = comparestat.executeQuery();
                        comparers.first();
                        String s_code;
                        boolean keuze = false;
                        while (comparers.next()) {
                            s_code = comparers.getString("s_code");
                            if (s_code.equals(speler.s_code)) {
                                keuze = true;
                            } else {
                                keuze = false;
                            }

                        }
                        if (keuze = true) {
                            JOptionPane.showMessageDialog(null, "Speler staat al ingeschreven");
                        } else {

                            PreparedStatement stat = (PreparedStatement) FullhouseProjectGui.con.prepareStatement(Query);
                            stat.setString(1, speler.s_code);
                            stat.setString(2, toer.id);
                            stat.execute();
                            JOptionPane.showMessageDialog(null, "Ingeschreven");
                        }
                    } catch (SQLException Ex) {
                        JOptionPane.showMessageDialog(null, Ex);
                    }
                }
            } else if (jRadioButtonM.isSelected()) {
                compareQuery = "Select s_code from MasterclassInschrijving where t_code = ?";
                Query = "Insert into MasterclassInschrijving set s_code = ?, m_code = ?, heeftBetaald = 'j'";
                ModelItemMasterclass mast = (ModelItemMasterclass) jListTenM.getSelectedValue();
                if (mast == null) {
                    JOptionPane.showMessageDialog(null, "Selecteer een masterclass");
                } else {
                    try {
                        PreparedStatement comparestat = (PreparedStatement) FullhouseProjectGui.con.prepareStatement(compareQuery);
                        comparestat.setString(1, mast.mcode);
                        ResultSet comparers = comparestat.executeQuery();
                        comparers.first();
                        String s_code;
                        boolean keuze = false;
                        while (comparers.next()) {
                            s_code = comparers.getString("s_code");
                            if (s_code.equals(speler.s_code)) {
                                keuze = true;
                            } else {
                                keuze = false;
                            }

                        }
                        if (keuze = true) {
                            JOptionPane.showMessageDialog(null, "Speler staat al ingeschreven");
                        } else {
                            PreparedStatement stat = (PreparedStatement) FullhouseProjectGui.con.prepareStatement(Query);
                            stat.setString(1, speler.s_code);
                            stat.setString(2, mast.mcode);
                            stat.execute();
                            JOptionPane.showMessageDialog(null, "Ingeschreven");
                        }
                    } catch (SQLException Ex) {
                        JOptionPane.showMessageDialog(null, Ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonInConfirmMouseClicked

    private void jButtonTotaalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTotaalMouseClicked
        String UQuery;

        String SQuery;

        if (jRadioButtonT.isSelected()) {
            SQuery = "Select count(s_code) as aantal from ToernooiInschrijving WHERE t_code = ? AND heeftBetaald = 'j'";
            UQuery = "Update Toernooi set aantalSpelers = ? WHERE t_code = ?";
            ModelItemToernooi toer = (ModelItemToernooi) jListTenM.getSelectedValue();
            try {
                PreparedStatement countstat = (PreparedStatement) FullhouseProjectGui.con.prepareStatement(SQuery);
                PreparedStatement updatestat = (PreparedStatement) FullhouseProjectGui.con.prepareStatement(UQuery);

                countstat.setString(1, toer.id);
                updatestat.setString(2, toer.id);
                ResultSet RS = countstat.executeQuery();
                RS.first();
                String aantal = RS.getString("aantal");
                updatestat.setString(1, aantal);
                updatestat.execute();
                JOptionPane.showMessageDialog(null, "Update voltooid");

            } catch (SQLException sQLException) {
                System.out.println(sQLException);
            }

        } else if (jRadioButtonM.isSelected()) {
            SQuery = "Select count(s_code) as aantal from MasterclassInschrijving WHERE m_code = ? AND heeftBetaald = 'j'";
            UQuery = "Update Masterclass set aantalSpelers = ? WHERE m_code = ?";
            ModelItemMasterclass mast = (ModelItemMasterclass) jListTenM.getSelectedValue();
            try {
                PreparedStatement countstat = (PreparedStatement) FullhouseProjectGui.con.prepareStatement(SQuery);
                PreparedStatement updatestat = (PreparedStatement) FullhouseProjectGui.con.prepareStatement(UQuery);

                countstat.setString(1, mast.mcode);
                updatestat.setString(2, mast.mcode);
                ResultSet RS = countstat.executeQuery();
                RS.first();
                String aantal = RS.getString("aantal");
                updatestat.setString(1, aantal);
                updatestat.execute();
                JOptionPane.showMessageDialog(null, "Update voltooid");
            } catch (SQLException sQLException) {
                System.out.println(sQLException);
            }
        }

    }//GEN-LAST:event_jButtonTotaalMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InschrijvingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InschrijvingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InschrijvingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InschrijvingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new InschrijvingFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonInConfirm;
    private javax.swing.JButton jButtonLijstTonen;
    private javax.swing.JButton jButtonTotaal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    public static javax.swing.JList jListSpelers;
    public static javax.swing.JList jListTenM;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JRadioButton jRadioButtonM;
    public static javax.swing.JRadioButton jRadioButtonT;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
