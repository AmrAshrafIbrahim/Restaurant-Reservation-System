/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlRead;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amr Ashraf
 */
@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestaurantXml {
    
    @XmlElement(name = "users")
    UsersXml users; 
    
     @XmlElement(name = "tables")
     TablesXml tables;
     
     @XmlElement(name = "dishes")
     DishesXml dishes;

    public UsersXml getUsers() {
        return users;
    }

    public void setUsers(UsersXml users) {
        this.users = users;
    }

    public TablesXml getTables() {
        return tables;
    }

    public void setTables(TablesXml tables) {
        this.tables = tables;
    }

    public DishesXml getDishes() {
        return dishes;
    }

    public void setDishes(DishesXml dishes) {
        this.dishes = dishes;
    }
     
     
     
    
}
