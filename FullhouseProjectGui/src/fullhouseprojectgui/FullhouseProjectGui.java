/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

import java.sql.*;
import java.sql.DriverManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Guus
 */
public class FullhouseProjectGui {
static Connection con = null;
static ArrayList<String> kolomnamenS = new ArrayList<String>();
static ArrayList<String> kolomnamenT = new ArrayList<String>();
static ArrayList<String> kolomnamenM = new ArrayList<String>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         JFrame frame=new Gui();       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Gui.centreWindow(frame);
    }
    public Connection maakVerbinding()
    {
    
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
    
    public static void SpelerTonen()
    {
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
            } while(rs.next());
                SpelersFrame.jTableSpelerWeergave.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

        public static void ToernooiTonen()
        {
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
               Object rowData[] = {test1.id, test1.aantal_spelers, test1.inschrijfgeld, test1.locatie,test1.datum, test1.eerste, test1.tweede, test1.derde};
               model.addRow(rowData);
            }while(rs.next());
                Toernooi.jTable1.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
        }
        
        
         
          public static void MasterclassTonen()
        {
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
               Object rowData[] = {test1.mcode, test1.aantal_spelers,test1.minimale_rating, test1.inschrijfgeld, test1.locatie,test1.datum, test1.masterclassGever};
               model.addRow(rowData);
            }while(rs.next());
                Masterclass.MasterclassTabel.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
        }
          public static void ToernooiLijstTonen()
          {
               DefaultListModel model = new DefaultListModel();
               
                       
        String Query = "select * from Toernooi";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);

            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
                ModelItemToernooi test1 = new ModelItemToernooi();
                test1.datum = rs.getString("datum");
                test1.locatie = rs.getString("locatie");
                model.addElement(test1);
            } while (rs.next());
           Toernooi.ToernooiLijst.setModel(model);
           } catch (Exception e) {
            System.out.println(e);
        }
        }
          public static void SpelerLijstTonen()
          {
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
          public static void MasterclassLijstTonen()
          {
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

        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(insertQuery);
            stat.setString(1, SpelerInvoerFrame.s_codeT.getText());
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

         public static void ToernooiToevoegen() 
         {
              String insertQuery = "insert into Toernooi(t_code, datum, locatie, inschrijfGeld) values( ?,?,?,?)";

        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(insertQuery);
            stat.setString(1, ToernooiToevoegen.tcodeT.getText());
            stat.setString(2, ToernooiToevoegen.datumT.getText());
            stat.setString(3, ToernooiToevoegen.locatieT.getText());
            stat.setString(4, ToernooiToevoegen.inschrijfGeldT.getText());
            stat.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
         }
         public static void MasterclassToevoegen() 
         {
              String insertQuery = "insert into Masterclass(m_code,aantalSpelers, minimaleRating,inschrijfGeld, locatie, datum, masterclassGever) values( ?,?,?,?,?,?,?)";

        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(insertQuery);
            stat.setString(1, MasterclassInvoeren.M_codeT.getText());
            stat.setString(2, MasterclassInvoeren.aantalSpelersT.getText());
            stat.setString(3, MasterclassInvoeren.minimaleRatingT.getText());
            stat.setString(4, MasterclassInvoeren.inschrijfGeldT.getText());
            stat.setString(5, MasterclassInvoeren.locatieT.getText());
            stat.setString(6, MasterclassInvoeren.datumT.getText());
            stat.setString(7, MasterclassInvoeren.MasterclassGeverT.getText());
            stat.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
         }
         public static void tekstVullen()
         {
             ModelItem speler = (ModelItem) SpelersFrame.SpelerLijst.getSelectedValue();
             String selectQuery = "select * from Speler where s_code = ?";
            
        try {
            PreparedStatement stat = con.prepareStatement(selectQuery);
            stat.setString(1, speler.s_code);
            ResultSet rs = stat.executeQuery();
            rs.first();
            do{
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
            
            }while(rs.next());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);        
         }
        }
         public static void spelerWijzigen()
         {
             ModelItem s_code = (ModelItem) SpelersFrame.SpelerLijst.getSelectedValue();
           String updateQuery = "update Speler set naam = ?, adres = ?, postcode = ?, plaats = ?, telefoonnummer = ?, email = ?, rating = ?, kanMasterclassGeven = ? WHERE s_code = ?";
           try
           {
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
          public static void tekstVullenToernooi()
          {
              ModelItemToernooi toernooi = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
             String selectQuery = "select * from Toernooi where datum = ?";
            
        try {
            PreparedStatement stat = con.prepareStatement(selectQuery);
            stat.setString(1, toernooi.datum);
            ResultSet rs = stat.executeQuery();
            rs.first();
            do{
             String datum = rs.getString("datum");
             String locatie = rs.getString("locatie");
             String inschrijfGeld = rs.getString("inschrijfGeld");
             String aantalSpelers = rs.getString("aantalSpelers");
             String eerste = rs.getString("1e");
             String tweede = rs.getString("2e");
             String derde = rs.getString("3e");
            
            ToernooiWijzigen.datumT.setText(datum);
            ToernooiWijzigen.locatieT.setText(locatie);
            ToernooiWijzigen.inschrijfGeldT.setText(inschrijfGeld);
            ToernooiWijzigen.aantalSpelersT.setText(aantalSpelers);
            ToernooiWijzigen.eersteT.setText(eerste);
            ToernooiWijzigen.tweedeT.setText(tweede);
            ToernooiWijzigen.derdeT.setText(derde);    
            }while(rs.next());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);        
         }
          }
        public static void toernooiWijzigen()
         {
             ModelItemToernooi tcode = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
           String updateQuery = "update Toernooi set datum = ?, locatie = ?, aantalSpelers = ?,inschrijfGeld = ?, 1e = ?, 2e = ?, 3e = ? WHERE datum = ?";
           try
           {
               PreparedStatement stat = con.prepareStatement(updateQuery);
              String datum = ToernooiWijzigen.datumT.getText();
            String locatie = ToernooiWijzigen.locatieT.getText();
            String inschrijfGeld = ToernooiWijzigen.inschrijfGeldT.getText();
            String aantalSpelers = ToernooiWijzigen.aantalSpelersT.getText();
            String eerste = ToernooiWijzigen.eersteT.getText();
            String tweede = ToernooiWijzigen.tweedeT.getText();
            String derde = ToernooiWijzigen.derdeT.getText();
            
            
            stat.setString(1, datum);
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
        public static void tekstVullenMasterclass()
          {
              ModelItemMasterclass masterclass = (ModelItemMasterclass) Masterclass.masterclassLijst.getSelectedValue();
             String selectQuery = "select * from Masterclass where datum = ?";
            
        try {
            PreparedStatement stat = con.prepareStatement(selectQuery);
           stat.setString(1, masterclass.datum);
            ResultSet rs = stat.executeQuery();
            rs.first();
            do{
             String aantalSpelers = rs.getString("aantalSpelers");
             String minimaleRating = rs.getString("minimaleRating");
             String inschrijfGeld = rs.getString("inschrijfGeld");
             String locatie = rs.getString("locatie");
             String datum = rs.getString("datum");
             String mGever = rs.getString("masterclassGever");
             
            
            MasterclassWijzigen.aantalSpelersT.setText(aantalSpelers);
            MasterclassWijzigen.minimaleRatingT.setText(minimaleRating);
            MasterclassWijzigen.inschrijfGeldT.setText(inschrijfGeld);
            MasterclassWijzigen.locatieT.setText(locatie);
            MasterclassWijzigen.datumT.setText(datum);
            MasterclassWijzigen.masterclassGever.setText(mGever);
            }while(rs.next());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);        
         }
        }
        public static void masterclassWijzigen()
         {
             ModelItemMasterclass mcode = (ModelItemMasterclass) Masterclass.masterclassLijst.getSelectedValue();
           String updateQuery = "update Masterclass set aantalSpelers = ?, minimaleRating = ?, inschrijfGeld = ?, locatie = ?, datum = ?, masterclassGever = ? WHERE datum = ?";
           try
           {
               PreparedStatement stat = con.prepareStatement(updateQuery);
             String aantalSpelers = MasterclassWijzigen.aantalSpelersT.getText();
             String minimaleRating = MasterclassWijzigen.minimaleRatingT.getText();
            String inschrijfGeld = MasterclassWijzigen.inschrijfGeldT.getText();
            String locatie = MasterclassWijzigen.locatieT.getText();
            String datum = MasterclassWijzigen.datumT.getText();
            String masterclassGever = MasterclassWijzigen.masterclassGever.getText();
            
            
            stat.setString(1, aantalSpelers);
            stat.setString(2, minimaleRating);
            stat.setString(3, inschrijfGeld);
            stat.setString(4, locatie);
            stat.setString(5, datum);
            stat.setString(6, masterclassGever);
            stat.setString(7, mcode.datum);
            stat.execute();
           } catch (SQLException ex) {
            Logger.getLogger(FullhouseProjectGui.class.getName()).log(Level.SEVERE, null, ex);        
         }
         }
        
        public static void deelIn()
        {
            int restTafel = 0;
            int aantalRest = 0;
            int icodeTeller = 0;
            int spelerPerTafel = 0;
            String insertQuery = "insert into TafelIndeling set i_code = ?, spelersAantal = ?";
            String toonQuery = "select aantalSpelers from Toernooi where t_code = ?";
            try {
                PreparedStatement stat = con.prepareStatement(insertQuery);
                PreparedStatement toonstat = con.prepareStatement(toonQuery);
                ResultSet rs = toonstat.executeQuery();
                String spelersAantal = rs.getString("aantalSpelers");
                 int totaalSpelers = (int) (Integer.parseInt(spelersAantal));
                 int aantalTafels = totaalSpelers / 5;
                 if (totaalSpelers % 5 == 0)
                 {
                     restTafel = 5;
                     
                 }
                 else 
                 {
                     restTafel = 6;
                     aantalRest = totaalSpelers%5;
                 }
                 for (int i = 0; i < aantalRest; i++) {
                     icodeTeller++;
                     stat.setInt(1, icodeTeller);
                     stat.setInt(2, restTafel);
                     stat.executeQuery();
                     for (int j = 0; j < restTafel; j++) {
                     }    
                }      
                  restTafel = 5;
                  for (int i = 0; i < aantalTafels - aantalRest; i++) {
                     icodeTeller++;
                     stat.setInt(1, icodeTeller);
                     stat.setInt(2, restTafel);
                     stat.executeQuery();
                     for (int j = 0; j < restTafel; j++) {
                     }    
                }      
                 
    
                 
            } catch (Exception e) {
            }
        }
        public static void toonTafelLijst()
        {
            String toonQuery ="select * from Tafel";
            try {
                PreparedStatement stat = con.prepareStatement(toonQuery);
                
            } catch (Exception e) {
            }
        }
}