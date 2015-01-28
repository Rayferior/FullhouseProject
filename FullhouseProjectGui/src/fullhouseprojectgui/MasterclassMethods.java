/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Menno
 */
public class MasterclassMethods {

    static ArrayList<String> kolomnamenM = new ArrayList<String>();

    public static void MasterclassTonen() {
        DefaultTableModel model = new DefaultTableModel();
        String Query = "select * from Masterclass;";

        try {
            PreparedStatement stat = (PreparedStatement) (Statement) FullhouseProjectGui.con.prepareStatement(Query);
            ResultSet rs = stat.executeQuery();
            Masterclass.MasterclassTabel.removeAll();
            int Ccount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= Ccount; i++) {
                String name = rs.getMetaData().getColumnName(i);
                kolomnamenM.add(name);
            }
            String[] cnamen = (String[]) new String[kolomnamenM.size()];
            cnamen = kolomnamenM.toArray(cnamen);
            model.setColumnIdentifiers(cnamen);
            model.setRowCount(0);
            model.setColumnCount(Ccount);

            rs.first();
            do {
                ModelItemMasterclass test1 = new ModelItemMasterclass();
                test1.mcode = rs.getString("m_code");
                test1.aantal_spelers = rs.getString("aantalSpelers");
                test1.minimaleRating = rs.getString("minimaleRating");
                test1.inschrijfgeld = rs.getString("inschrijfGeld");
                test1.locatie = rs.getString("locatie");
                test1.datum = rs.getString("datum");
                test1.masterclassGever = rs.getString("masterclassGever");
                Object rowData[] = {test1.mcode, test1.aantal_spelers, test1.minimaleRating, test1.inschrijfgeld, test1.locatie, test1.datum, test1.masterclassGever};
                model.addRow(rowData);
            } while (rs.next());
            Masterclass.MasterclassTabel.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void MasterclassLijstTonen() {
        DefaultListModel model = new DefaultListModel();


        String Query = "select * from Masterclass";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) FullhouseProjectGui.con.prepareStatement(Query);

            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                ModelItemMasterclass test1 = new ModelItemMasterclass();
                test1.mcode = rs.getString("m_code");
                test1.datum = rs.getString("datum");
                test1.locatie = rs.getString("locatie");
                model.addElement(test1);
            } while (rs.next());
            Masterclass.masterclassLijst.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void MasterclassToevoegen() {
        String insertQuery = "insert into Masterclass(m_code,aantalSpelers, minimaleRating,inschrijfGeld, locatie, datum, masterclassGever) values( ?,?,?,?,?,?,?)";
        String toonHoogste = "select max(m_code) as hoogste from Masterclass";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(insertQuery);
            PreparedStatement Toonstat = FullhouseProjectGui.con.prepareStatement(toonHoogste);
            ResultSet rs = Toonstat.executeQuery();
            rs.first();
            String naam = rs.getString("hoogste");
            int Hoogste = (int) (Integer.parseInt(naam));
            Hoogste = Hoogste + 1;
            java.sql.Date sqlDate = FullhouseProjectGui.dateStringToMySqlDate(MasterclassInvoeren.datumT.getText());


            stat.setInt(1, Hoogste);
            stat.setString(2, MasterclassInvoeren.aantalSpelersT.getText());
            stat.setString(3, MasterclassInvoeren.minimaleRatingT.getText());
            stat.setString(4, MasterclassInvoeren.inschrijfGeldT.getText());
            stat.setString(5, MasterclassInvoeren.locatieT.getText());
            stat.setDate(6, sqlDate);
            stat.setString(7, MasterclassInvoeren.MasterclassGeverT.getText());
            stat.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void tekstVullenMasterclass() throws ParseException {
        ModelItemMasterclass masterclass = (ModelItemMasterclass) Masterclass.masterclassLijst.getSelectedValue();
        String selectQuery = "select * from Masterclass where datum = ?";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(selectQuery);
            stat.setString(1, masterclass.datum);
            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                String aantalSpelers = rs.getString("aantalSpelers");
                String minimaleRating = rs.getString("minimaleRating");
                String inschrijfGeld = rs.getString("inschrijfGeld");
                String locatie = rs.getString("locatie");
                Date datum = rs.getDate("datum");
                String mGever = rs.getString("masterclassGever");
                String datumT = FullhouseProjectGui.mySqlDateToString(datum);


                MasterclassWijzigen.aantalSpelersT.setText(aantalSpelers);
                MasterclassWijzigen.minimaleRatingT.setText(minimaleRating);
                MasterclassWijzigen.inschrijfGeldT.setText(inschrijfGeld);
                MasterclassWijzigen.locatieT.setText(locatie);
                MasterclassWijzigen.datumT.setText(datumT);
                MasterclassWijzigen.masterclassGever.setText(mGever);
            } while (rs.next());


        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void masterclassWijzigen() throws ParseException {
        ModelItemMasterclass mcode = (ModelItemMasterclass) Masterclass.masterclassLijst.getSelectedValue();
        String updateQuery = "update Masterclass set aantalSpelers = ?, minimaleRating = ?, inschrijfGeld = ?, locatie = ?, datum = ?, masterclassGever = ? WHERE m_code = ?";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(updateQuery);
            String aantalSpelers = MasterclassWijzigen.aantalSpelersT.getText();
            String minimaleRating = MasterclassWijzigen.minimaleRatingT.getText();
            String inschrijfGeld = MasterclassWijzigen.inschrijfGeldT.getText();
            String locatie = MasterclassWijzigen.locatieT.getText();
            String masterclassGever = MasterclassWijzigen.masterclassGever.getText();
            java.sql.Date sqlDate = FullhouseProjectGui.dateStringToMySqlDate(MasterclassWijzigen.datumT.getText());



            stat.setString(1, aantalSpelers);
            stat.setString(2, minimaleRating);
            stat.setString(3, inschrijfGeld);
            stat.setString(4, locatie);
            stat.setDate(5, sqlDate);
            stat.setString(6, masterclassGever);
            stat.setString(7, mcode.mcode);
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void verwijderMasterclass() {
        ModelItemMasterclass masterclass = (ModelItemMasterclass) Masterclass.masterclassLijst.getSelectedValue();
        String deleteQuery = "Delete from Masterclass where datum = ?";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(deleteQuery);
            stat.setString(1, masterclass.datum);
            stat.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
