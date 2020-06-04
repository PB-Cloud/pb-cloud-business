/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProdCategoryListDTO.java
 *
 * Created on Oct 18, 2017, 3:27:27 PM
 */

package sunwell.permaisuri.bus.dto.cred;

import java.util.LinkedList;



import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import sunwell.permaisuri.bus.dto.StandardDTO;
import sunwell.permaisuri.core.entity.cred.UserCredential;;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserListDTO extends StandardDTO
{
    
    private List<UserCredentialDTO> listUser;
    
    public UserListDTO() {
        
    }
    
    public UserListDTO(List<UserCredential> _users) {
        setData (_users);
    }
    
    public void setData(List<UserCredential> _users) {
    	System.out.println("_users: " + _users.size());
        if(_users != null && _users.size () > 0) {
            listUser = new LinkedList<> ();
            for (UserCredential _user : _users) {
            	System.out.println("Looping " );
                listUser.add (new UserCredentialDTO (_user));
            }
        }
        else
            listUser = null;
    }

    /**
     * @return the listUser
     */
    public List<UserCredentialDTO> getListUser ()
    {
        return listUser;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListUser (List<UserCredentialDTO> listUser)
    {
        this.listUser = listUser;
    }
}
