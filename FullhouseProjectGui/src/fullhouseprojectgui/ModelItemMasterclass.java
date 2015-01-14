/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

/**
 *
 * @author Menno
 */
public class ModelItemMasterclass {
   
    public ModelItemMasterclass() {
    }
     public String mcode;
    public String aantal_spelers;
    public String minimale_rating;
    public String inschrijfgeld;
    public String masterclassGever;
    public String locatie;
    public String datum;
   
   
    @Override
    public String toString(){
        String description = datum +  "    " + locatie;
        return description;
    }
}

