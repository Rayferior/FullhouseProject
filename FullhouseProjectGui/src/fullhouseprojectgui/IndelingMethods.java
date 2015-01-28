/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Menno
 */
public class IndelingMethods {

    static ArrayList<String> IndelingGroep = new ArrayList<String>();

    public static Table[] deelIn() {
        String aantalQuery = "Select aantalSpelers from Toernooi where t_code = ?";
        int totaalSpelers = 0;

        String tcode = null;



        try {
            PreparedStatement aantalStat = FullhouseProjectGui.con.prepareStatement(aantalQuery);
            ModelItemToernooi toer = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
            aantalStat.setString(1, toer.id);
            ResultSet ToerRS = aantalStat.executeQuery();
            ToerRS.first();
            String aantalS = ToerRS.getString("aantalSpelers");
            totaalSpelers = (int) Integer.parseInt(aantalS);
            tcode = toer.id;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        int aantalTafels = totaalSpelers / 4;
        int aantalRest = totaalSpelers % 4;
        Table[] tables = new Table[aantalTafels];
        ModelItemToernooi toer = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        if (toer == null) {
            JOptionPane.showMessageDialog(null, "Selecteer een toernooi");
        } else {
            try {
                PreparedStatement aantalStat = FullhouseProjectGui.con.prepareStatement(aantalQuery);

                aantalStat.setString(1, toer.id);
                ResultSet ToerRS = aantalStat.executeQuery();
                ToerRS.first();
                String aantalS = ToerRS.getString("aantalSpelers");
                totaalSpelers = (int) Integer.parseInt(aantalS);
                tcode = toer.id;
            } catch (SQLException ex) {
            }
            if (totaalSpelers <= 8) {
                aantalTafels = 1;
                aantalRest = totaalSpelers - 4;
            }
            for (int i = 0; i < aantalTafels; i++) {
                int aantalSpelersPerTafel = 4;
                if (i == aantalTafels - 1) {
                    aantalSpelersPerTafel += aantalRest;
                }
                int icode = (int) Integer.parseInt(tcode);
                tables[i] = new Table(i + (icode * 100) + 1, aantalSpelersPerTafel);
            }
        }
        return tables;

    }

    public static Table[] deelWinnaars() {
        String winnaarQuery = "select count(s_code) as totaal from Tafel where tafelWinnaar = 'j'";
        int totaalSpelers = 0;


        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(winnaarQuery);

            ResultSet ToerRS = stat.executeQuery();
            ToerRS.first();
            String aantalS = ToerRS.getString("totaal");
            totaalSpelers = (int) Integer.parseInt(aantalS);
        } catch (Exception exception) {
            System.out.println(exception);
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
        if (ti == null) {
            JOptionPane.showMessageDialog(null, "Selecteer een tafel uit de lijst");
        } else {
            int nieuwnummer = Integer.parseInt(ti.rondeNummer);
            int nieuwinsert = nieuwnummer + 1;


            try {
                PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(spelerQuery);

                stat.setInt(1, nieuwnummer);
                ResultSet rs = stat.executeQuery();
                rs.beforeFirst();
                while (rs.next()) {
                    String speler = rs.getString("s_code");
                    IndelingGroep.add(speler);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

            if (IndelingGroep.size() <= 2) {
                JOptionPane.showMessageDialog(null, "De laatste ronde voor dit toernooi is al ingedeeld. U kunt geen verdere rondes indelen");
            } else {
                try {

                    PreparedStatement istat = FullhouseProjectGui.con.prepareStatement(iQuery);
                    istat.setInt(1, nieuwnummer);
                    ResultSet irs = istat.executeQuery();

                    irs.first();
                    String icode = irs.getString("hoogste");
                    int tafelID = Integer.parseInt(icode);
                    tafelID += 1;

                    Collections.shuffle(IndelingGroep);
                    String[] spelers = new String[IndelingGroep.size()];
                    int index = 0;
                    for (String S : IndelingGroep) {
                        spelers[index] = S;
                        index++;
                    }
                    PreparedStatement insertstat = FullhouseProjectGui.con.prepareStatement(insertQuery);
                    PreparedStatement TiStat = FullhouseProjectGui.con.prepareStatement(TiQuery);

                    TiStat.setString(1, ti.t_code);

                    ResultSet TiRS = TiStat.executeQuery();
                    TiRS.beforeFirst();

                    while (TiRS.next()) {
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
        if (toer == null) {
            JOptionPane.showMessageDialog(null, "Selecteer een toernooi");
        } else {
            try {
                PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(spelerQuery);
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
                PreparedStatement insertstat = FullhouseProjectGui.con.prepareStatement(insertQuery);
                PreparedStatement TiStat = FullhouseProjectGui.con.prepareStatement(TiQuery);


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
    }

    public static void toonTafelLijst() {
        DefaultListModel tafelmodel = new DefaultListModel();
        ModelItemToernooi toer = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        String toonQuery = "select distinct I.i_code, T.rondeNummer from TafelIndeling I join Tafel T ON I.i_code = T.i_code where toernooi = ? order by i_code";

        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(toonQuery);

            stat.setString(1, toer.id);
            ResultSet rs = stat.executeQuery();

            TafelIndeling.TiLijst.removeAll();
            tafelmodel.clear();

            rs.beforeFirst();
            int Toerid = Integer.parseInt(toer.id);
            if (Toerid == 1) {
                while (rs.next()) {
                    ModelItemTi test1 = new ModelItemTi();
                    test1.i_code = rs.getString("i_code");
                    test1.rondeNummer = rs.getString("rondeNummer");
                    test1.t_code = toer.id;
                    tafelmodel.addElement(test1);
                }
            } else {
                {
                    while (rs.next()) {
                        ModelItemTi test1 = new ModelItemTi();
                        String toerid = toer.id;
                        int toeridR = Integer.parseInt(toerid);
                        String icode = rs.getString("i_code");
                        int icode1 = Integer.parseInt(icode);
                        int icodeID = icode1 - (100 * toeridR);
                        test1.rondeNummer = rs.getString("rondeNummer");
                        test1.t_code = toer.id;
                        test1.i_code = "" + icodeID;
                        tafelmodel.addElement(test1);
                    }
                }
            }


            TafelIndeling.TiLijst.setModel(tafelmodel);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public static void finaleTafelVullen() {
        DefaultListModel model = new DefaultListModel();
        ModelItemToernooi toernooi = (ModelItemToernooi) Toernooi.ToernooiLijst.getSelectedValue();
        String maxRonde = "select max(rondeNummer) as finaleRonde from Ronde where t_code = ?";
        String spelersTafel = "select T.s_code, S.naam from Tafel T join Speler S on T.s_code = S.s_code where rondeNummer = ?";

        try {
            PreparedStatement stat = FullhouseProjectGui.con.prepareStatement(maxRonde);
            PreparedStatement spelerstat = FullhouseProjectGui.con.prepareStatement(spelersTafel);
            stat.setString(1, toernooi.id);
            ResultSet rs = stat.executeQuery();
            rs.first();
            String finaleRonde = rs.getString("finaleRonde");
            spelerstat.setString(1, finaleRonde);
            FinaleTafel.finaleTafel.removeAll();
            ResultSet spelerRS = spelerstat.executeQuery();

            while (spelerRS.next()) {
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
}
