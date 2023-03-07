/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlRead;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amr Ashraf
 */
@XmlRootElement(name = "dishes")
@XmlAccessorType(XmlAccessType.FIELD)
public class DishesXml {
    
    
     @XmlElement(name = "dish")
    List<DishXml> dishlist;

    public List<DishXml> getDishlist() {
        return dishlist;
    }

    public void setDishlist(List<DishXml> dishlist) {
        this.dishlist = dishlist;
    }
     
     
    
}
