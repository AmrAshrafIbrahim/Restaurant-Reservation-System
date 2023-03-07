/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculations;

import XmlRead.DishXml;
import XmlRead.RestaurantXml;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Amr Ashraf
 */
public class Calculations {
    
    public Calculations() {
    }

    
    
  
   
   public  static double calculateMain(String name , int n) throws JAXBException
   {
        double cost=getCostOfDish(name);
       return (cost+cost*0.15)*n;
   }
   
   
   
   
   
   
   public static double calculateAppetizer(String name , int n) throws JAXBException
   {
         double cost=getCostOfDish(name);
       return (cost+cost*0.10)*n;
   }
 
   
   
   
   
   
   public static double calculateDesert(String name , int n) throws JAXBException
   {
       double cost=getCostOfDish(name);
       return (cost+cost*0.20)*n;
   }
   
   
   
   
   public static double getCostOfDish(String name) throws JAXBException
       {

          
           JAXBContext jax = JAXBContext.newInstance(RestaurantXml.class);
        Unmarshaller unm= jax.createUnmarshaller();
         RestaurantXml r = (RestaurantXml) unm.unmarshal(new File ("Restaurant.xml"));
         
         List<DishXml> d=r.getDishes().getDishlist();
           for (DishXml x : d) {
               if(x.getName().equals(name))
                   return x.getPrice();
           }
          
          return 0;
       }
    
   
   
   
    
}
