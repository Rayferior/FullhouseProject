/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

/**
 *
 * @author Menno
 */
public class ModelItemToernooi {
    
    public ModelItemToernooi() {
    }
    public String id;
    public String datum;
    public String locatie;
    public String inschrijfgeld;
    public String aantal_spelers;
    public String eerste;
    public String tweede;
    public String derde;
   
   
    @Override
    public String toString(){
        String description = datum + "     " + locatie;
        return description;
    }
}

 