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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Menno
 */
public class ToernooiMethods {

    static ArrayList<String> kolomnamenT = new ArrayList<String>();
    static ArrayList<String> kolomnamenM = new ArrayList<String>();

    public static void ToernooiTonen() {
        DefaultTableModel model = new DefaultTableModel();
        String Query = "select * from Toernooi;";

        try {
            PreparedStatement stat = (PreparedStatement) (Statement) FullhouseProjectGui.con.prepareStatement(Query);
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

    public static void ToernooiLijstTonen() {
        DefaultListModel model = new DefaultListModel();


        String Query = "select * from Toernooi";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) FullhouseProjectGui.con.prepareStatement(Query);
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

    public static void ToernooiToevoegen() {
        String insertQuery = "insert into Toernooi(t_code, datum, locatie, inschrijfGeld) values( ?,?,?,?)";
        String toonHoogste = "select max(t_code) as hoogste from Toernooi";

        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(insertQuery);
            PreparedStatement Toonstat = FullhouseProjectGui.con.prepareStatement(toonHoogste);
            ResultSet rs = Toonstat.executeQuery();
            rs.first();
            String naam = rs.getString("hoogste");
            int Hoogste = (int) (Integer.parseInt(naam));
            Hoogste = Hoogste + 1;
            java.sql.Date sqlDate = FullhouseProjectGui.dateStringToMySqlDate(ToernooiToevoegen.datumT.getText());


            stat.setInt(1, Hoogste);
            stat.setDate(2, sqlDate);
            stat.setString(3, ToernooiToevoegen.locatieT.getText());
            stat.setString(4, ToernooiToevoegen.inschrijfGeldT.getText());
            stat.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void tekstVullenToernooi() throws ParseException {
        ModelItemToernooi toernooi = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        String selectQuery = "select * from Toernooi where datum = ?";

        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(selectQuery);
            stat.setString(1, toernooi.datum);
            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                Date datum = rs.getDate("datum");
                String locatie = rs.getString("locatie");
                String inschrijfGeld = rs.getString("inschrijfGeld");
                String aantalSpelers = rs.getString("aantalSpelers");

                String datumT = FullhouseProjectGui.mySqlDateToString(datum);

                ToernooiWijzigen.datumT.setText(datumT);
                ToernooiWijzigen.locatieT.setText(locatie);
                ToernooiWijzigen.inschrijfGeldT.setText(inschrijfGeld);
                ToernooiWijzigen.aantalSpelersT.setText(aantalSpelers);

            } while (rs.next());


        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void toernooiWijzigen() throws ParseException {
        ModelItemToernooi tcode = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        String updateQuery = "update Toernooi set datum = ?, locatie = ?, aantalSpelers = ?,inschrijfGeld = ? WHERE datum = ?";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(updateQuery);
            String locatie = ToernooiWijzigen.locatieT.getText();
            String inschrijfGeld = ToernooiWijzigen.inschrijfGeldT.getText();
            String aantalSpelers = ToernooiWijzigen.aantalSpelersT.getText();
            java.sql.Date sqlDate = FullhouseProjectGui.dateStringToMySqlDate(ToernooiWijzigen.datumT.getText());

            stat.setDate(1, sqlDate);
            stat.setString(2, locatie);
            stat.setString(3, aantalSpelers);
            stat.setString(4, inschrijfGeld);
            stat.setString(5, tcode.datum);
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void vulOpenBetalingen() {
        ModelItemToernooi tcode = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        DefaultTableModel model = new DefaultTableModel();
        String Query = "select S.naam,Toer.inschrijfGeld, Toer.datum, S.email from Speler S join ToernooiInschrijving T ON S.s_code = T.S_code join Toernooi Toer on T.t_code = Toer.t_code"
                + " where Toer.t_code = ? AND T.heeftBetaald != 'j' ";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(Query);
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
            JOptionPane.showMessageDialog(null, e);

        }

    }

    public static void vulTiTabel() {
        ModelItemTi ticode = (ModelItemTi) TafelIndeling.TiLijst.getSelectedValue();
        DefaultTableModel model = new DefaultTableModel();
        DefaultListModel model1 = new DefaultListModel();
        String Query = "select T.rondeNummer, T.s_code, S.naam, T.i_code from Tafel T join Speler S on T.s_code = S.S_code where i_code = ?";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(Query);
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
            model.setColumnCount(Ccount - 1);
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
        String selectQuery = "Select I.spelersAantal, I.i_code, I.toernooi from TafelIndeling I JOIN Tafel T on I.i_code = T.i_code where T.s_code = ?";
        String maxQuery = "Select max(i_code) as hoogste from TafelIndeling where toernooi = ?";
        String plaatsQuery = "Update Toernooi set 1e = ? where t_code =?";

        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(Query);
            PreparedStatement spelerStat = FullhouseProjectGui.con.prepareStatement(spelerQuery);
            PreparedStatement selectStat = FullhouseProjectGui.con.prepareStatement(selectQuery);
            PreparedStatement maxStat = FullhouseProjectGui.con.prepareStatement(maxQuery);
            PreparedStatement winstat = FullhouseProjectGui.con.prepareStatement(plaatsQuery);

            selectStat.setString(1, ticode.s_code);
            spelerStat.setString(2, ticode.s_code);


            ResultSet Selectrs = selectStat.executeQuery();
            Selectrs.first();
            int i_code = Selectrs.getInt("i_code");
            int aantalSpelers = Selectrs.getInt("spelersAantal");
            String tcode = Selectrs.getString("toernooi");
            maxStat.setString(1, tcode);
            stat.setInt(2, i_code);

            ResultSet maxRs = maxStat.executeQuery();
            maxRs.first();
            int max = maxRs.getInt("hoogste");


            for (int i = 0; i < aantalSpelers; i++) {
                stat.setString(1, "n");
                stat.execute();
            }
            spelerStat.setString(1, "j");
            spelerStat.execute();

            if (max == i_code) {
                winstat.setString(1, ticode.s_code);
                winstat.setString(2, tcode);
                winstat.executeUpdate();
                JOptionPane.showMessageDialog(null, "De winnaar van het toernooi is bekend");
            } else {
                JOptionPane.showMessageDialog(null, "Winnaar bevestigd");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void veranderRating() {
        int verschilRating;
        int nieuwRating = 0;
        int somRating = 0;
        ModelItem ticode = (ModelItem) TafelIndeling.TsLijst.getSelectedValue();
        String JaQuery = "Select T.i_code, S.rating from Tafel T join Speler S on T.s_code = S.s_code where T.s_code = ? and tafelWinnaar = 'j'";
        String NeeQuery = "Select T.s_code, S.rating from Tafel  T join Speler S on T.s_code = S.s_code where i_code = ? and tafelWinnaar = 'n'";
        String updateQuery = "Update Speler set rating = ? where s_code =?";


        try {
            PreparedStatement Jastat = FullhouseProjectGui.con.prepareStatement(JaQuery);
            PreparedStatement Neestat = FullhouseProjectGui.con.prepareStatement(NeeQuery);
            PreparedStatement updatestat = FullhouseProjectGui.con.prepareStatement(updateQuery);


            Jastat.setString(1, ticode.s_code);
            ResultSet Jars = Jastat.executeQuery();
            Jars.first();
            String iCodeN = Jars.getString("i_code");
            Neestat.setString(1, iCodeN);


            ResultSet Neers = Neestat.executeQuery();
            Neers.beforeFirst();


            int winnaarRating = Jars.getInt("rating");
            while (Neers.next()) {


                int verliezerRating = Neers.getInt("rating");
                int speler = Neers.getInt("s_code");
                int beginRating = verliezerRating;
                double percentageverliezer = verliezerRating / 10000;
                if (winnaarRating > verliezerRating) {
                    verschilRating = winnaarRating - verliezerRating;
                } else {
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

    public static void verwijderToernooi() {
        ModelItemToernooi toernooi = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        String deleteQuery = "Delete from Toernooi where t_code = ?";
        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(deleteQuery);
            stat.setString(1, toernooi.id);
            stat.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
