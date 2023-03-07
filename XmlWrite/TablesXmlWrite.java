/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlWrite;

import XmlRead.TableXml;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amr Ashraf
 */
@XmlRootElement(name = "tables")
@XmlAccessorType(XmlAccessType.FIELD)
public class TablesXmlWrite {
    @XmlElement(name = "table")
    List<TableXmlWrite> TableList;

    public List<TableXmlWrite> getTableList() {
        return TableList;
    }

    public void setTableList(List<TableXmlWrite> TableList) {
        this.TableList = TableList;
    }

    
    
    
}
