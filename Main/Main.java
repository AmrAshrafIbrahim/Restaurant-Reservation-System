/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.Login;
import UsersByInheritance.Client;
import UsersByInheritance.Cooker;
import UsersByInheritance.Manager;
import UsersByInheritance.User;
import UsersByInheritance.Waiter;
import XmlRead.DishXml;
import XmlRead.DishesXml;
import XmlRead.RestaurantXml;
import XmlRead.TableXml;
import XmlRead.UserXml;
import XmlWrite.Reservations;
import XmlWrite.TableXmlWrite;
import XmlWrite.TablesXmlWrite;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import objects.Table;
/**
 *
 * @author Amr Ashraf
 */
public class Main {
    
    
 //////////////////////////// Amr Ashraf (6301)                 Mohamed Kamal (6331)
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException {
    
       new Login().setVisible(true);
   
    }
    
}
