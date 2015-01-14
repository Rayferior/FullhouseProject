/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouseprojectgui;

class ModelItem {
 
    public ModelItem() {
    }
    public String s_code;
    public String naam;
    public String adres;
    public String postcode;
    public String plaats;
    public String telefoonnummer;
    public String email;
    public String rating;
    public String kanMasterclassGeven;
    
   
    @Override
    public String toString(){
        String description = naam;
        return description;
    }
}