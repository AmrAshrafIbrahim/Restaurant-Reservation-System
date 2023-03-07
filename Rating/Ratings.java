/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rating;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amr Ashraf
 */
@XmlRootElement(name = "ratings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ratings {
     @XmlElement(name = "rating")
     List<Rating> ratinglist;

    public List<Rating> getRatinglist() {
        return ratinglist;
    }

    public void setRatinglist(List<Rating> ratinglist) {
        this.ratinglist = ratinglist;
    }
     
     
    
}
