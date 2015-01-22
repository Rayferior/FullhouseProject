/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

import java.sql.*;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Guus
 */
public class FullhouseProjectGui {

    static Connection con = null;
    static ArrayList<String> kolomnamenS = new ArrayList<String>();
    static ArrayList<String> kolomnamenT = new ArrayList<String>();
    static ArrayList<String> kolomnamenM = new ArrayList<String>();
    static ArrayList<String> IndelingGroep = new ArrayList<String>();
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new Gui();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Gui.centreWindow(frame);
    }

    public Connection maakVerbinding() {

        String cs = "jdbc:mysql://meru.hhs.nl/14076942";
        String user = "14076942";
        String password = "roowiePieC";
        try {
            con = DriverManager.getConnection(cs, user, password);
            JOptionPane.showMessageDialog(null, "Connected");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public static java.sql.Date dateStringToMySqlDate (String date) throws ParseException {
        /* ik verwacht de datum tekst als dd-MM-yyyy */
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }

    public static String mySqlDateToString (java.sql.Date date) {
        /* ik schrijf de datum als dd-MM-yyyy */
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

    public static void SpelerTonen() {
        DefaultTableModel model = new DefaultTableModel();
        String Query = "select * from Speler;";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);
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

    public static void ToernooiTonen() {
        DefaultTableModel model = new DefaultTableModel();
        String Query = "select * from Toernooi;";

        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);
            ResultSet rs = stat.executeQuery();
            Toernooi.jTable1.removeAll();
            int Ccount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= Ccount; i++) {
                String name = rs.getMetaData().getColumnName(i);
                kolomnamenT.add(name);
            }
            String[] cnamen = (String[]) new String[kolomnamenT.size()];
            cnamen = kolomnamenT.toArray(cnamen);
            model.setColumnIdentifiers(cnamen);
            model.setRowCount(0);
            model.setColumnCount(Ccount);

            rs.first();
            do {
                ModelItemToernooi test1 = new ModelItemToernooi();
                test1.id = rs.getString("t_code");
                test1.aantal_spelers = rs.getString("aantalSpelers");
                test1.inschrijfgeld = rs.getString("inschrijfGeld");
                test1.locatie = rs.getString("locatie");
                test1.datum = rs.getString("datum");
                test1.eerste = rs.getString("1e");
                test1.tweede = rs.getString("2e");
                test1.derde = rs.getString("3e");
                Object rowData[] = {test1.id, test1.aantal_spelers, test1.inschrijfgeld, test1.locatie, test1.datum, test1.eerste, test1.tweede, test1.derde};
                model.addRow(rowData);
            } while (rs.next());
            Toernooi.jTable1.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void MasterclassTonen() {
        DefaultTableModel model = new DefaultTableModel();
        String Query = "select * from Masterclass;";

        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);
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
                test1.minimale_rating = rs.getString("minimaleRating");
                test1.inschrijfgeld = rs.getString("inschrijfGeld");
                test1.locatie = rs.getString("locatie");
                test1.datum = rs.getString("datum");
                test1.masterclassGever = rs.getString("masterclassGever");
                Object rowData[] = {test1.mcode, test1.aantal_spelers, test1.minimale_rating, test1.inschrijfgeld, test1.locatie, test1.datum, test1.masterclassGever};
                model.addRow(rowData);
            } while (rs.next());
            Masterclass.MasterclassTabel.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void ToernooiLijstTonen() {
        DefaultListModel model = new DefaultListModel();


        String Query = "select * from Toernooi";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);
            model.clear();
            Toernooi.ToernooiLijst.removeAll();
            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                ModelItemToernooi test1 = new ModelItemToernooi();
                test1.id = rs.getString("t_code");
                test1.datum = rs.getString("datum");
                test1.locatie = rs.getString("locatie");
                model.addElement(test1);
            } while (rs.next());
            Toernooi.ToernooiLijst.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void SpelerLijstTonen() {
        DefaultListModel model = new DefaultListModel();

        String Query = "select * from Speler";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);

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

    public static void MasterclassLijstTonen() {
        DefaultListModel model = new DefaultListModel();


        String Query = "select * from Masterclass";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);

            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                ModelItemMasterclass test1 = new ModelItemMasterclass();
                test1.datum = rs.getString("datum");
                test1.locatie = rs.getString("locatie");
                model.addElement(test1);
            } while (rs.next());
            Masterclass.masterclassLijst.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void SpelerToevoegen() {
        String insertQuery = "insert into Speler(s_code, naam, adres, postcode,plaats, telefoonnummer, email, rating, kanMasterclassGeven) values( ?,?,?,?,?,?,?,?,?)";
        String toonHoogste = "select max(s_code) as hoogste from Speler";
        try {
            PreparedStatement stat = con.prepareStatement(insertQuery);
            PreparedStatement Toonstat = con.prepareStatement(toonHoogste);
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

    public static void ToernooiToevoegen() {
        String insertQuery = "insert into Toernooi(t_code, datum, locatie, inschrijfGeld) values( ?,?,?,?)";
        String toonHoogste = "select max(t_code) as hoogste from Toernooi";

        try {
            PreparedStatement stat = con.prepareStatement(insertQuery);
            PreparedStatement Toonstat = con.prepareStatement(toonHoogste);
            ResultSet rs = Toonstat.executeQuery();
            rs.first();
            String naam = rs.getString("hoogste");
            int Hoogste = (int) (Integer.parseInt(naam));
            Hoogste = Hoogste + 1;
            java.sql.Date sqlDate = dateStringToMySqlDate(ToernooiToevoegen.datumT.getText());
   

            stat.setInt(1, Hoogste);
            stat.setDate(2, sqlDate);
            stat.setString(3, ToernooiToevoegen.locatieT.getText());
            stat.setString(4, ToernooiToevoegen.inschrijfGeldT.getText());
            stat.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void MasterclassToevoegen() {
        String insertQuery = "insert into Masterclass(m_code,aantalSpelers, minimaleRating,inschrijfGeld, locatie, datum, masterclassGever) values( ?,?,?,?,?,?,?)";
        String toonHoogste = "select max(m_code) as hoogste from Masterclass";
        try {
            PreparedStatement stat = con.prepareStatement(insertQuery);
            PreparedStatement Toonstat = con.prepareStatement(toonHoogste);
            ResultSet rs = Toonstat.executeQuery();
            rs.first();
            String naam = rs.getString("hoogste");
            int Hoogste = (int) (Integer.parseInt(naam));
            Hoogste = Hoogste + 1;
            java.sql.Date sqlDate = dateStringToMySqlDate(MasterclassInvoeren.datumT.getText());
            

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

    public static void tekstVullen() {
        ModelItem speler = (ModelItem) SpelersFrame.SpelerLijst.getSelectedValue();
        String selectQuery = "select * from Speler where s_code = ?";

        try {
            PreparedStatement stat = con.prepareStatement(selectQuery);
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
        
        if(SpelersFrame.SpelerLijst.getSelectedValue() == null){
           JOptionPane.showMessageDialog(null, "Selecteer eerst een speler uit de lijst");   }
        try {
            PreparedStatement stat = con.prepareStatement(updateQuery);
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
            stat.setString(5, text2);
            stat.setString(6, text1);
            stat.setString(7, rating);
            stat.setString(8, text3);
            stat.setString(9, s_code.s_code);
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void tekstVullenToernooi() throws ParseException {
        ModelItemToernooi toernooi = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        String selectQuery = "select * from Toernooi where datum = ?";

        if(Toernooi.ToernooiLijst.getSelectedValue() == null){
           JOptionPane.showMessageDialog(null, "Selecteer eerst een toernooi uit de lijst");   }
        
        try {
            PreparedStatement stat = con.prepareStatement(selectQuery);
            stat.setString(1, toernooi.datum);
            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                Date datum = rs.getDate("datum");
                String locatie = rs.getString("locatie");
                String inschrijfGeld = rs.getString("inschrijfGeld");
                String aantalSpelers = rs.getString("aantalSpelers");
                String eerste = rs.getString("1e");
                String tweede = rs.getString("2e");
                String derde = rs.getString("3e");
                String datumT = mySqlDateToString(datum);

                ToernooiWijzigen.datumT.setText(datumT);
                ToernooiWijzigen.locatieT.setText(locatie);
                ToernooiWijzigen.inschrijfGeldT.setText(inschrijfGeld);
                ToernooiWijzigen.aantalSpelersT.setText(aantalSpelers);
                ToernooiWijzigen.eersteT.setText(eerste);
                ToernooiWijzigen.tweedeT.setText(tweede);
                ToernooiWijzigen.derdeT.setText(derde);
            } while (rs.next());


        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void toernooiWijzigen() throws ParseException {
        ModelItemToernooi tcode = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        String updateQuery = "update Toernooi set datum = ?, locatie = ?, aantalSpelers = ?,inschrijfGeld = ?, 1e = ?, 2e = ?, 3e = ? WHERE datum = ?";
        try {
            PreparedStatement stat = con.prepareStatement(updateQuery);
            String locatie = ToernooiWijzigen.locatieT.getText();
            String inschrijfGeld = ToernooiWijzigen.inschrijfGeldT.getText();
            String aantalSpelers = ToernooiWijzigen.aantalSpelersT.getText();
            String eerste = ToernooiWijzigen.eersteT.getText();
            String tweede = ToernooiWijzigen.tweedeT.getText();
            String derde = ToernooiWijzigen.derdeT.getText();
            java.sql.Date sqlDate = dateStringToMySqlDate(ToernooiWijzigen.datumT.getText());

            stat.setDate(1, sqlDate);
            stat.setString(2, locatie);
            stat.setString(3, aantalSpelers);
            stat.setString(4, inschrijfGeld);
            stat.setString(5, eerste);
            stat.setString(6, tweede);
            stat.setString(7, derde);
            stat.setString(8, tcode.datum);
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void tekstVullenMasterclass() throws ParseException {
        ModelItemMasterclass masterclass = (ModelItemMasterclass) Masterclass.masterclassLijst.getSelectedValue();
        String selectQuery = "select * from Masterclass where datum = ?";

       if(Masterclass.masterclassLijst.getSelectedValue() == null){
           JOptionPane.showMessageDialog(null, "Selecteer eerst een masterclass uit de lijst");   }
          
           try {
            PreparedStatement stat = con.prepareStatement(selectQuery);
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
                String datumT = mySqlDateToString(datum);


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
        String updateQuery = "update Masterclass set aantalSpelers = ?, minimaleRating = ?, inschrijfGeld = ?, locatie = ?, datum = ?, masterclassGever = ? WHERE datum = ?";
        try {
            PreparedStatement stat = con.prepareStatement(updateQuery);
            String aantalSpelers = MasterclassWijzigen.aantalSpelersT.getText();
            String minimaleRating = MasterclassWijzigen.minimaleRatingT.getText();
            String inschrijfGeld = MasterclassWijzigen.inschrijfGeldT.getText();
            String locatie = MasterclassWijzigen.locatieT.getText();
            String masterclassGever = MasterclassWijzigen.masterclassGever.getText();
           java.sql.Date sqlDate = dateStringToMySqlDate(MasterclassInvoeren.datumT.getText());
                


            stat.setString(1, aantalSpelers);
            stat.setString(2, minimaleRating);
            stat.setString(3, inschrijfGeld);
            stat.setString(4, locatie);
            stat.setDate(5, sqlDate);
            stat.setString(6, masterclassGever);
            stat.setString(7, mcode.datum);
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Table[] deelIn() {
        String aantalQuery = "Select aantalSpelers from Toernooi where t_code = ?";
        int totaalSpelers = 0;
        try {
            PreparedStatement aantalStat = con.prepareStatement(aantalQuery);
            ModelItemToernooi toer = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
            aantalStat.setString(1, toer.id);
            ResultSet ToerRS = aantalStat.executeQuery();
            ToerRS.first();
            String aantalS = ToerRS.getString("aantalSpelers");
            totaalSpelers = (int) Integer.parseInt(aantalS);
        } catch (SQLException ex) {
            System.out.println(ex);
        }


        int aantalTafels = totaalSpelers / 4;
        int aantalRest = totaalSpelers % 4;
        Table[] tables = new Table[aantalTafels];

        if (totaalSpelers <= 8) {
            aantalTafels = 1;
            aantalRest = totaalSpelers - 4;
        }
        for (int i = 0; i < aantalTafels; i++) {
            int aantalSpelersPerTafel = 4;
            if (i == aantalTafels - 1) {
                aantalSpelersPerTafel += aantalRest;
            }
            tables[i] = new Table(i + 1, aantalSpelersPerTafel);
        }
        return tables;
    }

    public static Table[] deelWinnaars() {
        String winnaarQuery = "select count(s_code) as totaal from Tafel where tafelWinnaar = 'j'";
        int totaalSpelers = 0;
        try {
            PreparedStatement stat = con.prepareStatement(winnaarQuery);

            ResultSet ToerRS = stat.executeQuery();
            ToerRS.first();
            String aantalS = ToerRS.getString("totaal");
            totaalSpelers = (int) Integer.parseInt(aantalS);
        } catch (SQLException E) {
            System.out.println(E);
        }

        int aantalTafels = totaalSpelers / 4;
        int aantalRest = totaalSpelers % 4;
        Table[] tables = new Table[aantalTafels];

        if (totaalSpelers <= 8) {
            aantalTafels = 1;
            aantalRest = totaalSpelers - 4;
        }
        for (int i = 0; i < aantalTafels; i++) {
            int aantalSpelersPerTafel = 4;
            if (i == aantalTafels - 1) {
                aantalSpelersPerTafel += aantalRest;
            }
            tables[i] = new Table(i + 1, aantalSpelersPerTafel);
        }

        return tables;
    }

    public static void WinnaarIndeling() {
        String spelerQuery = "Select i_code, s_code from Tafel where rondeNummer = ? AND tafelWinnaar = 'j'";
        String iQuery = "Select MAX(i_code) as hoogste from Tafel where rondeNummer = ?";
        String insertQuery = "Insert into Tafel set s_code = ?, i_code = ?, rondeNummer = ?";
        String TiQuery = "select T.i_code, T.spelersAantal, R.rondeNummer from TafelIndeling T JOIN Ronde R ON R.t_code = T.toernooi "
                + "where T.toernooi = ? AND R.rondeNummer NOT IN (SELECT rondeNummer FROM Tafel WHERE rondeNummer IS NOT NULL) order by i_code";

        int tafelAantal;
        
        int spelerint = 0;
        ModelItemTi ti = (ModelItemTi) TafelIndeling.TiLijst.getSelectedValue();
        int nieuwnummer = Integer.parseInt(ti.rondeNummer);
        int nieuwinsert = nieuwnummer + 1;

        try {
            PreparedStatement stat = con.prepareStatement(spelerQuery);
            PreparedStatement istat = con.prepareStatement(iQuery);
            istat.setInt(1, nieuwnummer);
            stat.setInt(1, nieuwnummer);
            ResultSet rs = stat.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                String speler = rs.getString("s_code");
                IndelingGroep.add(speler);
            }
            ResultSet irs = istat.executeQuery();
            irs.first();
            String icode = irs.getString("hoogste");
            int tafelID = Integer.parseInt(icode);
            tafelID += 1;

        Collections.shuffle(IndelingGroep);
        String[] spelers = new String[IndelingGroep.size()];
        int index = 0;
        for(String S : IndelingGroep){
            spelers[index] = S;
            index++;
        }
            PreparedStatement insertstat = con.prepareStatement(insertQuery);
            PreparedStatement TiStat = con.prepareStatement(TiQuery);

            TiStat.setString(1, ti.t_code);

            ResultSet TiRS = TiStat.executeQuery();
            TiRS.beforeFirst();
           
            while (TiRS.next()){
                 String aantal = TiRS.getString("spelersAantal");
                insertstat.setInt(2, tafelID);
                insertstat.setInt(3, nieuwinsert);

                tafelAantal = Integer.parseInt(aantal);
                while (spelerint < tafelAantal) {
                    int insert = Integer.parseInt(spelers[spelerint]);
                    insertstat.setInt(1, insert);
                    spelerint++;
                    insertstat.execute();
                };

            };
            JOptionPane.showMessageDialog(null, "Spelers Ingedeeld");
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void deelSpelerIn() {
        String spelerQuery = "Select S.s_code, S.naam from Speler S JOIN ToernooiInschrijving T ON S.s_code = T.s_code where t_code = ?";
        String insertQuery = "Insert into Tafel set s_code = ?, i_code = ?, rondeNummer = ?";
        String TiQuery = "select T.i_code, T.spelersAantal, R.rondeNummer from TafelIndeling T JOIN Ronde R ON R.t_code = T.toernooi "
                + "where T.toernooi = ? order by i_code";
        int tafelAantal;
        int spelerint = 0;
        ModelItemToernooi toer = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();

        try {
            PreparedStatement stat = con.prepareStatement(spelerQuery);
            stat.setString(1, toer.id);
            ResultSet rs = stat.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                String speler = rs.getString("s_code");
                IndelingGroep.add(speler);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        Collections.shuffle(IndelingGroep);
        String[] spelers = (String[]) new String[IndelingGroep.size()];
        spelers = IndelingGroep.toArray(spelers);

        try {
            PreparedStatement insertstat = con.prepareStatement(insertQuery);
            PreparedStatement TiStat = con.prepareStatement(TiQuery);


            TiStat.setString(1, toer.id);

            ResultSet TiRS = TiStat.executeQuery();
            TiRS.first();
            do {
                String aantal = TiRS.getString("spelersAantal");
                String icode = TiRS.getString("i_code");
                String ronde = TiRS.getString("rondeNummer");

                int rondeID = Integer.parseInt(ronde);
                int tafelID = Integer.parseInt(icode);
                insertstat.setInt(2, tafelID);
                insertstat.setInt(3, rondeID);

                tafelAantal = Integer.parseInt(aantal);
                tafelAantal = spelerint + tafelAantal;
                while (spelerint < tafelAantal) {
                    int insert = Integer.parseInt(spelers[spelerint]);
                    insertstat.setInt(1, insert);
                    spelerint++;
                    insertstat.execute();

                }
            } while (TiRS.next());
            JOptionPane.showMessageDialog(null, "Spelers Ingedeeld");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void toonTafelLijst() {
        DefaultListModel tafelmodel = new DefaultListModel();
        ModelItemToernooi toer = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        String toonQuery = "select distinct I.i_code, T.rondeNummer from TafelIndeling I join Tafel T ON I.i_code = T.i_code where toernooi = ? order by i_code";
        
        try {
            PreparedStatement stat = con.prepareStatement(toonQuery);
            
            stat.setString(1, toer.id);
            ResultSet rs = stat.executeQuery();
            
            TafelIndeling.TiLijst.removeAll();
            tafelmodel.clear();
            
            rs.beforeFirst();
            
            while (rs.next()) {
                ModelItemTi test1 = new ModelItemTi();
                test1.i_code = rs.getString("i_code");
                test1.rondeNummer = rs.getString("rondeNummer");
                test1.t_code = toer.id;
                tafelmodel.addElement(test1);
            }
            TafelIndeling.TiLijst.setModel(tafelmodel);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public static void vulOpenBetalingen() {
        ModelItemToernooi tcode = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        DefaultTableModel model = new DefaultTableModel();
        String Query = "select S.naam,Toer.inschrijfGeld, Toer.datum, S.email from Speler S join ToernooiInschrijving T ON S.s_code = T.S_code join Toernooi Toer on T.t_code = Toer.t_code"
                + " where Toer.t_code = ? AND T.heeftBetaald != 'j' ";
        try {
            PreparedStatement stat = con.prepareStatement(Query);
            stat.setString(1, tcode.id);
            ResultSet rs = stat.executeQuery();
            Toernooi.OpenBet.removeAll();
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
                ModelItem test1 = new ModelItem();
                test1.naam = rs.getString("naam");
                test1.email = rs.getString("email");
                ModelItemToernooi test2 = new ModelItemToernooi();
                test2.inschrijfgeld = rs.getString("inschrijfGeld");
                test2.datum = rs.getString("datum");
                Object rowData[] = {test1.naam, test2.inschrijfgeld, test2.datum, test1.email};
                model.addRow(rowData);
            } while (rs.next());
            Toernooi.OpenBet.setModel(model);

        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public static void vulTiTabel() {
        ModelItemTi ticode = (ModelItemTi) TafelIndeling.TiLijst.getSelectedValue();
        DefaultTableModel model = new DefaultTableModel();
        DefaultListModel model1 = new DefaultListModel();
        String Query = "select T.rondeNummer, T.s_code, S.naam, T.i_code from Tafel T join Speler S on T.s_code = S.S_code where i_code = ?";
        try {
            PreparedStatement stat = con.prepareStatement(Query);
            stat.setString(1, ticode.i_code);
            ResultSet rs = stat.executeQuery();
            TafelIndeling.TiLijst.removeAll();
            int Ccount = rs.getMetaData().getColumnCount();
            for (int i = 1; i < Ccount; i++) {
                String name = rs.getMetaData().getColumnName(i);
                kolomnamenM.add(name);
            }
            String[] cnamen = (String[]) new String[kolomnamenM.size()];
            cnamen = kolomnamenM.toArray(cnamen);
            model.setColumnIdentifiers(cnamen);
            model.setRowCount(0);
            model.setColumnCount(Ccount-1);
            rs.first();
            do {
                ModelItemTi test1 = new ModelItemTi();
                ModelItem test = new ModelItem();
                test1.rondeNummer = rs.getString("rondeNummer");
                test1.s_code = rs.getString("s_code");
                test1.i_code = rs.getString("i_code");
                test.naam = rs.getString("naam");
                test.s_code = rs.getString("s_code");
                Object rowData[] = {test1.rondeNummer, test1.s_code, test.naam};
                model.addRow(rowData);
                model1.addElement(test);
            } while (rs.next());
            TafelIndeling.TiTabel.setModel(model);
            TafelIndeling.TsLijst.setModel(model1);
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public static void selecteerWinaar() {
        ModelItem ticode = (ModelItem) TafelIndeling.TsLijst.getSelectedValue();
        String Query = "UPDATE Tafel SET tafelWinnaar = ? WHERE i_code = ?";
        String spelerQuery = "UPDATE Tafel SET tafelWinnaar = ? WHERE s_code = ?";
        String selectQuery = "Select I.spelersAantal, I.i_code from TafelIndeling I JOIN Tafel T on I.i_code = T.i_code where T.s_code = ?";

        try {
            PreparedStatement stat = con.prepareStatement(Query);
            PreparedStatement spelerStat = con.prepareStatement(spelerQuery);
            PreparedStatement selectStat = con.prepareStatement(selectQuery);

            selectStat.setString(1, ticode.s_code);
            spelerStat.setString(2, ticode.s_code);

            ResultSet Selectrs = selectStat.executeQuery();
            Selectrs.first();
            int i_code = Selectrs.getInt("i_code");
            int aantalSpelers = Selectrs.getInt("spelersAantal");
            stat.setInt(2, i_code);

            for (int i = 0; i < aantalSpelers; i++) {
                stat.setString(1, "n");
                stat.execute();
            }
            spelerStat.setString(1, "j");
            spelerStat.execute();
            JOptionPane.showMessageDialog(null, "Winnaar bevestigd");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void InschrijvingLijst() {

        DefaultListModel model = new DefaultListModel();

        String Query = "select * from Speler";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);

            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                ModelItem test1 = new ModelItem();
                test1.s_code = rs.getString("s_code");
                test1.naam = rs.getString("naam");
                model.addElement(test1);
            } while (rs.next());
            InschrijvingFrame.jListSpelers.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void TInschrijvingLijstTonen() {
        DefaultListModel model = new DefaultListModel();
        
        
        if (InschrijvingFrame.jRadioButtonT.isSelected()) {
            String TQuery = "select * from Toernooi";
            try {
                PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(TQuery);

                ResultSet rs = stat.executeQuery();
                rs.first();
                do {
                    ModelItemToernooi test1 = new ModelItemToernooi();
                    test1.id = rs.getString("t_code");
                    test1.datum = rs.getString("datum");
                    test1.locatie = rs.getString("locatie");
                    model.addElement(test1);
                } while (rs.next());
                InschrijvingFrame.jListTenM.setModel(model);
            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (InschrijvingFrame.jRadioButtonM.isSelected()) {
            String MQuery = "select * from Masterclass";
            try {
                PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(MQuery);

                ResultSet rs = stat.executeQuery();
                rs.first();
                do {
                    ModelItemMasterclass test1 = new ModelItemMasterclass();
                    test1.mcode = rs.getString("m_code");
                    test1.datum = rs.getString("datum");
                    test1.locatie = rs.getString("locatie");
                    model.addElement(test1);
                } while (rs.next());
                InschrijvingFrame.jListTenM.setModel(model);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
    public static void veranderRating()
    {
        int verschilRating;
         int nieuwRating = 0;
         int somRating = 0;
        ModelItem ticode = (ModelItem) TafelIndeling.TsLijst.getSelectedValue();
        String JaQuery = "Select T.i_code, S.rating from Tafel T join Speler S on T.s_code = S.s_code where T.s_code = ? and tafelWinnaar = 'j'";
        String NeeQuery = "Select T.s_code, S.rating from Tafel  T join Speler S on T.s_code = S.s_code where i_code = ? and tafelWinnaar = 'n'";
        String updateQuery = "Update Speler set rating = ? where s_code =?";
       
        
        try {
            PreparedStatement Jastat = con.prepareStatement(JaQuery);
            PreparedStatement Neestat = con.prepareStatement(NeeQuery);
            PreparedStatement updatestat = con.prepareStatement(updateQuery);
            
 
            Jastat.setString(1, ticode.s_code);
            ResultSet Jars = Jastat.executeQuery();
            Jars.first();
            String iCodeN = Jars.getString("i_code"); 
            Neestat.setString(1, iCodeN);
          
            
            ResultSet Neers = Neestat.executeQuery();
            Neers.beforeFirst();
    
            
            int winnaarRating = Jars.getInt("rating");
            while(Neers.next()) {
           
            
            int verliezerRating = Neers.getInt("rating");
            int speler = Neers.getInt("s_code");
            int beginRating = verliezerRating;
            double percentageverliezer = verliezerRating/10000;
            if (winnaarRating > verliezerRating){
            verschilRating = winnaarRating - verliezerRating;
                    }
            else
            {
                verschilRating = verliezerRating - winnaarRating;
            }
               updatestat.setInt(2, speler);
               verliezerRating = (int) (percentageverliezer * verschilRating) + 10;
               verliezerRating = verliezerRating / 2;
                System.out.println(verliezerRating);
               nieuwRating = beginRating - verliezerRating;
               somRating = somRating + (verliezerRating * 2);
               updatestat.setInt(1, nieuwRating);
               updatestat.executeUpdate();
            }
            System.out.println(somRating);
            int winnaar = Integer.parseInt(ticode.s_code);
            updatestat.setInt(2, winnaar);
            updatestat.setInt(1, somRating + winnaarRating);
            updatestat.executeUpdate(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void finaleTafelVullen()
            {
             DefaultListModel model = new DefaultListModel();
             ModelItemToernooi toernooi = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
            String maxRonde = "select max(rondeNummer) as finaleRonde from Ronde where t_code = ?";
            String spelersTafel = "select T.s_code, S.naam from Tafel T join Speler S on T.s_code = S.s_code where rondeNummer = ?";
            
        try {
            PreparedStatement stat = con.prepareStatement(maxRonde);
            PreparedStatement spelerstat = con.prepareStatement(spelersTafel);
            stat.setString(1, toernooi.id);
            ResultSet rs = stat.executeQuery();
            rs.first();
            String finaleRonde = rs.getString("finaleRonde");
            spelerstat.setString(1, finaleRonde);
            FinaleTafel.finaleTafel.removeAll();
            ResultSet spelerRS = spelerstat.executeQuery();
            
            while (spelerRS.next())
            {
                ModelItem test1 = new ModelItem();
                test1.naam = spelerRS.getString("naam");
                test1.s_code = spelerRS.getString("s_code");
                model.addElement(test1);
            } 
            FinaleTafel.finaleTafel.setModel(model);
        
        } catch (Exception e) {
            System.out.println(e);
        }
            }
    
    public static void totaalBetaaldInschrijfgeld()
    {
        ModelItemToernooi toernooi = (ModelItemToernooi) Overzichten.jListOverzichtTenM.getSelectedValue();
        DefaultListModel model = new DefaultListModel();
        String totaalGeld = "select sum(inschrijfGeld * aantalSpelers) as prijsgeld from Toernooi where t_code = ?";
        try {
        PreparedStatement stat = con.prepareStatement(totaalGeld);
        stat.setString(1, toernooi.id);
        ResultSet rs = stat.executeQuery();
            rs.first();
            String totaalTgeld = rs.getString("prijsgeld");
            model.addElement(totaalTgeld + " Euro");
            Overzichten.jListResultaat.setModel(model);
            } catch (Exception e) {
                System.out.println(e);
        }
    }
}

