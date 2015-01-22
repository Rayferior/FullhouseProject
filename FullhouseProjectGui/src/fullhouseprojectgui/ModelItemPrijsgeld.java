/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

/**
 *
 * @author Menno
 */
public class ModelItemPrijsgeld {
    public ModelItemPrijsgeld() {
    }
    public String s_code;
    public String naam;
    public int prijsgeld;
    
   
    @Override
    public String toString(){
        String description = naam + "                                        " + prijsgeld + " Euro";
        return description;
    }
}
    

