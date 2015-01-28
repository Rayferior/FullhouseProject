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

/**
 *
 * @author Guus
 */
public class FullhouseProjectGui {

    static Connection con = null;

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
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static java.sql.Date dateStringToMySqlDate(String date) throws ParseException {
        /*
         * ik verwacht de datum tekst als dd-MM-yyyy
         */
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }

    public static String mySqlDateToString(java.sql.Date date) {
        /*
         * ik schrijf de datum als dd-MM-yyyy
         */
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }
}
