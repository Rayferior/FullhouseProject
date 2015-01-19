/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

/**
 *
 * @author Menno
 */
public class ModelItemTi {
    
    public ModelItemTi() {
    }
    public String i_code;
    public String spelersAantal;
    
    
   
    @Override
    public String toString(){
        String description = "Tafel " + i_code;
        return description;
    }
}
