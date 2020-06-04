package sunwell.permaisuri.bus.dto.contact;



/*
 * Customer.java
 *
 * Created 22 April 2014
 */

import java.io.Serializable;


import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import sunwell.permaisuri.bus.dto.StandardDTO;
import sunwell.permaisuri.bus.dto.cred.UserCredentialDTO;
//import sunwell.permaisuri.core.entity.contact.AddressType;
import sunwell.permaisuri.core.entity.contact.Contact;
import sunwell.permaisuri.core.entity.contact.ContactAddress;
import sunwell.permaisuri.core.entity.cred.UserCredential;
import sunwell.permaisuri.core.entity.customer.Customer;


public class ContactDTO extends StandardDTO
{
    private UserCredentialDTO userCredential;
    private Long systemId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Calendar birthDate;
    private String phone;
    private String citizenId;
    private Integer citizenIdType;
   
    
    public ContactDTO() {
    	
    }
    
    public ContactDTO(Contact _con) {
    	setData(_con);
    }
    
    public void setData(Contact _con) {
		systemId = _con.getSystemId();
		firstName = _con.getFirstName();
		lastName = _con.getLastName();
		email = _con.getEmail();
		birthDate = _con.getBirthDate();
		phone = _con.getPhone();
		citizenId = _con.getCitizenId();
		citizenIdType = _con.getCitizenIdType();
		
		if(_con.getUserCredential() != null)
			setUserCredential(new UserCredentialDTO(_con.getUserCredential()));
		
		if(_con.getAddresses() != null && _con.getAddresses().size() > 0) {
			ContactAddress ca = _con.getAddresses().get(0);
			address = ca.getStreet();
		}
    }
    
    public Contact getData() {
		Contact c = new Contact();
		if(systemId != null)
			c.setSystemId(systemId);
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEmail(email);
		c.setBirthDate(birthDate);
		c.setPhone(phone);
		c.setCitizenId(citizenId);
		
		if(citizenIdType != null)
			c.setCitizenIdType(citizenIdType);
		
		if(getUserCredential() != null)
			c.setUserCredential(getUserCredential().getData());
		
		if(address != null) {
//			AddressType at = new AddressType();
//			at.setSystemId(1);
			
			ContactAddress ca = new ContactAddress();
//			ca.setAddressType(at);
			ca.setStreet(address);
			ca.setOwner(c);
			
			List<ContactAddress> listCA = new LinkedList<>();
			listCA.add(ca);
			
			c.setAddresses(listCA);
		}
		return c;
    }
    
    public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}    

    public String getEmail() {
		return email;
	}

    public void setEmail(String email) {
		this.email = email;
	}

    public String getFirstName() {
		return firstName;
	}

    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    public String getLastName() {
		return lastName;
	}

    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    public String getAddress() {
		return address;
	}

    public void setAddress(String _addr) {
		this.address = _addr;
	}

	public UserCredentialDTO getUserCredential ()
	{
		return userCredential;
	}

	public void setUserCredential (UserCredentialDTO _userCredential)
	{
		userCredential = _userCredential;
	}

	public Calendar getBirthDate ()
	{
		return birthDate;
	}

	public void setBirthDate (Calendar _birthDate)
	{
		birthDate = _birthDate;
	}

	public String getPhone ()
	{
		return phone;
	}

	public void setPhone (String _phone)
	{
		phone = _phone;
	}

	public String getCitizenId ()
	{
		return citizenId;
	}

	public void setCitizenId (String _citizenId)
	{
		citizenId = _citizenId;
	}

	public int getCitizenIdType ()
	{
		return citizenIdType;
	}

	public void setCitizenIdType (int _citizenIdType)
	{
		citizenIdType = _citizenIdType;
	}
    
}
