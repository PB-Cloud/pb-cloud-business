package sunwell.permaisuri.bus.dto.hr;



/*
 * Customer.java
 *
 * Created 22 April 2014
 */

import java.io.Serializable;




import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import sunwell.permaisuri.bus.dto.StandardDTO;
import sunwell.permaisuri.bus.dto.contact.ContactDTO;
import sunwell.permaisuri.bus.dto.cred.UserCredentialDTO;
import sunwell.permaisuri.bus.dto.sales.CartDetailDTO;
//import sunwell.permaisuri.core.entity.contact.AddressType;
import sunwell.permaisuri.core.entity.contact.ContactAddress;
import sunwell.permaisuri.core.entity.cred.UserCredential;
import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.hr.SalesOfficer;
import sunwell.permaisuri.core.entity.sales.CartDetail;


public class SalesOfficerDTO extends ContactDTO
{
    private UserCredentialDTO userCredential;
     
    
    public SalesOfficerDTO() {
    	
    }
    
    public SalesOfficerDTO(SalesOfficer _so) {
    	setData(_so);
    }
    
    public void setData(SalesOfficer _so) {
		super.setData(_so);
		
		if(_so.getUserCredential() != null)
			userCredential = new UserCredentialDTO(_so.getUserCredential());
		
    }
    
    public SalesOfficer getData() {
    	SalesOfficer salesOfficer = new SalesOfficer();
		if(getSystemId() != null)
			salesOfficer.setSystemId(getSystemId());
		salesOfficer.setFirstName(getFirstName());
		salesOfficer.setLastName(getLastName());
		salesOfficer.setEmail(getEmail());
		if(getAddress() != null) {
//			AddressType at = new AddressType();
//			at.setSystemId(1);
			
			ContactAddress ca = new ContactAddress();
//			ca.setAddressType(at);
			ca.setStreet(getAddress());
			ca.setOwner(salesOfficer);
			
			List<ContactAddress> listCA = new LinkedList<>();
			listCA.add(ca);
			
			salesOfficer.setAddresses(listCA);
		}
		
		if(userCredential != null)
			salesOfficer.setUserCredential(userCredential.getData());
		
		return salesOfficer;
    }
        

    public UserCredentialDTO getUserCredential() {
		return userCredential;
	}

    public void setUserCredential(UserCredentialDTO userCredential) {
		this.userCredential = userCredential;
	}	
}
