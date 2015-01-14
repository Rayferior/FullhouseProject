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
               ModelItemToernooi test1 = new ModelItemToernooi();
                       
        String Query = "select * from Toernooi";
        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(Query);

            ResultSet rs = stat.executeQuery();
            rs.first();
            do {
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
              String insertQuery = "insert into Toernooi(t_code,aantalSpelers, inschrijfgeld, locatie, datum, 1e, 2e, 3e) values( ?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stat = (PreparedStatement) (Statement) con.prepareStatement(insertQuery);
            stat.setString(1, ToernooiToevoegen.t_codeT.getText());
            stat.setString(5, ToernooiToevoegen.datumT.getText());
            stat.setString(4, ToernooiToevoegen.locatieT.getText());
            stat.setString(3, ToernooiToevoegen.inschrijfGeldT.getText());
            stat.setString(2, null);
            stat.setString(6, null);
            stat.setString(7, null);
            stat.setString(8, null);
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
        }

          
    


