/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Menno
 */
public class Speler {

    static ArrayList<String> kolomnamenS = new ArrayList<String>();

    public static void SpelerTonen() {
        DefaultTableModel model = new DefaultTableModel();
        String Query = "select * from Speler;";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) FullhouseProjectGui.con.prepareStatement(Query);
            ResultSet rs = stat.executeQuery();
            SpelersFrame.jTableSpelerWeergave.removeAll();
            int Ccount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= Ccount; i++) {
                String name = rs.getMetaData().getColumnName(i);
                kolomnamenS.add(name);
            }
            String[] cnamen = (String[]) new String[kolomnamenS.size()];
            cnamen = kolomnamenS.toArray(cnamen);
            model.setColumnIdentifiers(cnamen);
            model.setRowCount(0);
            model.setColumnCount(Ccount);

            rs.first();
            do {
                ModelItem test1 = new ModelItem();
                test1.s_code = rs.getString("s_code");
                test1.naam = rs.getString("naam");
                test1.adres = rs.getString("adres");
                test1.postcode = rs.getString("postcode");
                test1.plaats = rs.getString("plaats");
                test1.telefoonnummer = rs.getString("telefoonnummer");
                test1.email = rs.getString("email");
                test1.rating = rs.getString("rating");
                test1.kanMasterclassGeven = rs.getString("kanMasterclassGeven");
                Object rowData[] = {test1.s_code, test1.naam, test1.adres, test1.postcode, test1.plaats, test1.telefoonnummer, test1.email, test1.rating, test1.kanMasterclassGeven};
                model.addRow(rowData);
            } while (rs.next());
            SpelersFrame.jTableSpelerWeergave.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void SpelerLijstTonen() {
        DefaultListModel model = new DefaultListModel();

        String Query = "select * from Speler";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) FullhouseProjectGui.con.prepareStatement(Query);

            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                ModelItem test1 = new ModelItem();
                test1.s_code = rs.getString("s_code");
                test1.naam = rs.getString("naam");
                model.addElement(test1);
            } while (rs.next());
            SpelersFrame.SpelerLijst.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void SpelerToevoegen() {
        String insertQuery = "insert into Speler(s_code, naam, adres, postcode,plaats, telefoonnummer, email, rating, kanMasterclassGeven) values( ?,?,?,?,?,?,?,?,?)";
        String toonHoogste = "select max(s_code) as hoogste from Speler";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(insertQuery);
            PreparedStatement Toonstat = FullhouseProjectGui.con.prepareStatement(toonHoogste);
            ResultSet rs = Toonstat.executeQuery();
            rs.first();
            String naam = rs.getString("hoogste");
            int Hoogste = (int) (Integer.parseInt(naam));
            Hoogste = Hoogste + 1;
            stat.setInt(1, Hoogste);
            stat.setString(2, SpelerInvoerFrame.naamT.getText());
            stat.setString(3, SpelerInvoerFrame.adresT.getText());
            stat.setString(4, SpelerInvoerFrame.postcodeT.getText());
            stat.setString(5, SpelerInvoerFrame.WoonplaatsT.getText());
            stat.setString(7, SpelerInvoerFrame.emailT.getText());
            stat.setString(6, SpelerInvoerFrame.telefoonnummerT.getText());
            stat.setString(9, SpelerInvoerFrame.MasterclassLeraarT.getText());
            stat.setString(8, SpelerInvoerFrame.ratingT.getText());
            stat.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void tekstVullen() {
        ModelItem speler = (ModelItem) SpelersFrame.SpelerLijst.getSelectedValue();
        String selectQuery = "select * from Speler where s_code = ?";

        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(selectQuery);
            stat.setString(1, speler.s_code);
            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                String naam = rs.getString("naam");
                String adres = rs.getString("adres");
                String postcode = rs.getString("postcode");
                String plaats = rs.getString("plaats");
                String telefoonnummer = rs.getString("telefoonnummer");
                String email = rs.getString("email");
                String rating = rs.getString("rating");
                String kanMasterclassGeven = rs.getString("kanMasterclassGeven");

                SpelerWijzigFrame.jTextFieldWijzigSpelerNaam.setText(naam);
                SpelerWijzigFrame.jTextFieldSpelersAdres.setText(adres);
                SpelerWijzigFrame.jTextFieldSpelersPostcode.setText(postcode);
                SpelerWijzigFrame.jTextFieldSpelersWoonplaats.setText(plaats);
                SpelerWijzigFrame.jTextFieldSpelersEmail.setText(email);
                SpelerWijzigFrame.jTextFieldTelefoon.setText(telefoonnummer);
                SpelerWijzigFrame.jTextFieldRating.setText(rating);
                SpelerWijzigFrame.jTextFieldMasterclass.setText(kanMasterclassGeven);

            } while (rs.next());


        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void spelerWijzigen() {
        ModelItem s_code = (ModelItem) SpelersFrame.SpelerLijst.getSelectedValue();
        String updateQuery = "update Speler set naam = ?, adres = ?, postcode = ?, plaats = ?, telefoonnummer = ?, email = ?, rating = ?, kanMasterclassGeven = ? WHERE s_code = ?";


        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(updateQuery);
            String naam = SpelerWijzigFrame.jTextFieldWijzigSpelerNaam.getText();
            String adres = SpelerWijzigFrame.jTextFieldSpelersAdres.getText();
            String postcode = SpelerWijzigFrame.jTextFieldSpelersPostcode.getText();
            String plaats = SpelerWijzigFrame.jTextFieldSpelersWoonplaats.getText();
            String text1 = SpelerWijzigFrame.jTextFieldTelefoon.getText();
            String text2 = SpelerWijzigFrame.jTextFieldSpelersEmail.getText();
            String rating = SpelerWijzigFrame.jTextFieldRating.getText();
            String text3 = SpelerWijzigFrame.jTextFieldMasterclass.getText();

            stat.setString(1, naam);
            stat.setString(2, adres);
            stat.setString(3, postcode);
            stat.setString(4, plaats);
            stat.setString(5, text1);
            stat.setString(6, text2);
            stat.setString(7, rating);
            stat.setString(8, text3);
            stat.setString(9, s_code.s_code);
            stat.execute();

        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
