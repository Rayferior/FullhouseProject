/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultListModel;

/**
 *
 * @author Menno
 */
public class Inschrijving {
     public static void InschrijvingLijst() {

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
                PreparedStatement stat = (PreparedStatement) (Statement) FullhouseProjectGui.con.prepareStatement(TQuery);

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
                PreparedStatement stat = (PreparedStatement) (Statement) FullhouseProjectGui.con.prepareStatement(MQuery);

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

}
