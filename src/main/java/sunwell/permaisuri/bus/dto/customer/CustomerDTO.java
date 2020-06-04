package sunwell.permaisuri.bus.dto.customer;



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
import sunwell.permaisuri.core.entity.sales.CartDetail;


public class CustomerDTO extends ContactDTO
{
//    private CustomerGroupDTO custGroup;
    private List<CartDetailDTO> cartDetails;
    private String custNo;
    private Double creditLimit ;
    private Double disc ;
    private Date registrationDate;
    private Boolean active = true;
    private Integer idPriceLevel;  
    
    public CustomerDTO() {
    	
    }
    
    public CustomerDTO(Customer _cust) {
    	setData(_cust);
    }
    
    public void setData(Customer _cust) {
		super.setData(_cust);
		
//		if(_cust.getCustGroup() != null)
//			custGroup = new CustomerGroupDTO(_cust.getCustGroup());
		
		if(_cust.getCartDetails() != null && _cust.getCartDetails().size() > 0) {
			cartDetails = new LinkedList<>();
			for(CartDetail cd : _cust.getCartDetails()) {
				cartDetails.add(new CartDetailDTO(cd));
			}
		}
//		custNo = _cust.getCustNo();
//		creditLimit = _cust.getCreditLimit();
//		disc = _cust.getDisc();
//		registrationDate = _cust.getRegistrationDate();
//		active = _cust.isActive();
//		idPriceLevel = _cust.getIdPriceLevel();
    }
    
    public Customer getData() {
		Customer c = new Customer();
		if(getSystemId() != null)
			c.setSystemId(getSystemId());
		c.setFirstName(getFirstName());
		c.setLastName(getLastName());
		c.setEmail(getEmail());
		if(getUserCredential() != null)
			c.setUserCredential(getUserCredential().getData());
		if(getAddress() != null) {
//			AddressType at = new AddressType();
//			at.setSystemId(1);
			
			ContactAddress ca = new ContactAddress();
//			ca.setAddressType(at);
			ca.setStreet(getAddress());
			ca.setOwner(c);
			
			List<ContactAddress> listCA = new LinkedList<>();
			listCA.add(ca);
			
			c.setAddresses(listCA);
		}
		
		if(cartDetails != null && cartDetails.size() > 0) {
			List<CartDetail> listCD = new LinkedList<>();
			for(CartDetailDTO cdDTO : cartDetails) {
				listCD.add(cdDTO.getData());
			}
			c.setCartDetails(listCD);
		}
		
//		if(custGroup != null)
//			c.setCustGroup(custGroup.getData());
//		
//		c.setCustNo(custNo);
//		
//		if(creditLimit != null)
//			c.setCreditLimit(creditLimit);
//		
//		if(disc != null)
//			c.setDisc(disc);
//		
//		c.setRegistrationDate(registrationDate);
//		
//		if(active != null)
//			c.setActive(active);
//		
//		c.setIdPriceLevel(idPriceLevel);
		return c;
    }
    
   
    public String getCustNo ()
    {
        return custNo;
    }

    public void setCustNo (String _s)
    {
        custNo = _s;
    }

    public double getCreditLimit ()
    {
        return creditLimit;
    }

    public void setCreditLimit (double _creditlimit)
    {
        this.creditLimit = _creditlimit;
    }
    
    public double getDisc ()
    {
        return disc;
    }
    
    public void setDisc (double _d)
    {
        disc = _d;
    }
    
    public Date getRegistrationDate ()
    {
        return registrationDate;
    }
    
    public void setRegistrationDate (Date _d)
    {
        registrationDate = _d;
    }

    public boolean isActive ()
    {
        return active;
    }

    public void setActive (boolean _active)
    {
        this.active = _active;
    }

//    public CustomerGroupDTO getCustGroup ()
//    {
//        return custGroup;
//    }
//
//    public void setCustGroup (CustomerGroupDTO _custgroup)
//    {
//        this.custGroup = _custgroup;
//    }

    public int getIdPriceLevel ()
    {
        return idPriceLevel;
    }

    public void setIdPriceLevel (int _idPriceLevel)
    {
        this.idPriceLevel = _idPriceLevel;
    }

//    public UserCredentialDTO getUserCredential() {
//		return userCredential;
//	}
//
//    public void setUserCredential(UserCredentialDTO userCredential) {
//		this.userCredential = userCredential;
//	}

	public List<CartDetailDTO> getCartDetails ()
	{
		return cartDetails;
	}

	public void setCartDetails (List<CartDetailDTO> _cartDetails)
	{
		cartDetails = _cartDetails;
	}    
}
