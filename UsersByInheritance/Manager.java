/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsersByInheritance;

import java.util.List;

/**
 *
 * @author Amr Ashraf
 */
public class Manager extends User{
     
    public Manager(String name, String username, String password) {
        super(name, username, password);
    }

    

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
