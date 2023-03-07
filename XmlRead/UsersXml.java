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
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersXml {
    
    @XmlElement(name = "user")
    List<UserXml> userList; 

    public List<UserXml> getUserList() {
        return userList;
    }

    public void setUserList(List<UserXml> userList) {
        this.userList = userList;
    }

    
    
    
    
}
