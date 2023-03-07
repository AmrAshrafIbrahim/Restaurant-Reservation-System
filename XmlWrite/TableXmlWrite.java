/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlWrite;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amr Ashraf
 */
@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.FIELD)
public class TableXmlWrite {
    
    @XmlElement(name = "number")
    int number;
    @XmlElement(name = "Client_name")
    String name;
    @XmlElement(name = "Order")
    String order;
    
    @XmlElement(name = "Paid")
    double paid;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }
    
    
}
