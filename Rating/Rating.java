/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rating;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amr Ashraf
 */
@XmlRootElement(name = "rating")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rating {
    @XmlElement(name = "table_number")
    int tablenumber;
    @XmlElement(name = "client")
    String client;
    @XmlElement(name = "foodrate")
    int foodrate;
    @XmlElement(name = "waiterrate")
    int waiterrate;
    @XmlElement(name = "servicerate")
    int servicerate;
    @XmlElement(name = "comment")
    String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    

    public int getTablenumber() {
        return tablenumber;
    }

    public void setTablenumber(int tablenumber) {
        this.tablenumber = tablenumber;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getFoodrate() {
        return foodrate;
    }

    public void setFoodrate(int foodrate) {
        this.foodrate = foodrate;
    }

    public int getWaiterrate() {
        return waiterrate;
    }

    public void setWaiterrate(int waiterrate) {
        this.waiterrate = waiterrate;
    }

    public int getServicerate() {
        return servicerate;
    }

    public void setServicerate(int servicerate) {
        this.servicerate = servicerate;
    }
    
    
    
}
