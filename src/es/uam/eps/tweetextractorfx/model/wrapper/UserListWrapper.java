package es.uam.eps.tweetextractorfx.model.wrapper;

import java.util.List;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import es.uam.eps.tweetextractorfx.model.User;

/**
 * 
 * 
 * @author Jose Antonio García del Saz
 */

@XmlRootElement(name = "users")
public class UserListWrapper {

    private List<User> users;

    @XmlElement(name = "user")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}