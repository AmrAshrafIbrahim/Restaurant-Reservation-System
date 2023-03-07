/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlWrite;

import XmlRead.TablesXml;
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
public class Reservations {
    
    @XmlElement(name = "tables")
     TablesXmlWrite tables;

    public TablesXmlWrite getTables() {
        return tables;
    }

    public void setTables(TablesXmlWrite tables) {
        this.tables = tables;
    }

   

   
    
    
}
