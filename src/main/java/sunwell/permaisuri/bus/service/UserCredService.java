package sunwell.permaisuri.bus.service;

import java.util.List;


import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//import aegwyn.core.web.util.Util;
import sunwell.permaisuri.bus.exception.OperationException;
import sunwell.permaisuri.bus.spec.GenericSpecification;
import sunwell.permaisuri.core.entity.contact.Contact;
import sunwell.permaisuri.core.entity.cred.UserCredential;
import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.hr.SalesOfficer;

public interface UserCredService
{
	public UserCredential validate (
	    		@NotNull(message="{error_no_email}") String _name, 
	    		@NotNull(message="{error_no_password}") String _password ) throws Exception;
    public Customer findCustomer(Long _id) ;
    public Customer findCustomerByEmail(String _email) ;
    public Page<Customer> findAllCustomers(Pageable _page) ;
    public Page<Customer> findCustomers(Filters _f, Pageable _page) throws Exception ;
    public List<Customer> findAllCustomers() ;
    public Customer addCustomer(
    		@Valid @NotNull(message="{error_no_customer}") Customer _customer) ;
    public Customer editCustomer(
    		@Valid @NotNull(message="{error_no_customer}") Customer _customer, boolean _encodePassword) ;
    public Customer deleteCustomer(@NotNull(message="{error_no_id}") Long _id) ;
    public SalesOfficer findSalesOfficer(Long _id) ;
    public SalesOfficer findSalesOfficerByEmail(String _email) ;
    public Page<SalesOfficer> findAllSalesOfficers(Pageable _page) ;
    public List<SalesOfficer> findAllSalesOfficers() ;
    public Page<SalesOfficer> findSalesOfficers(Filters _f, Pageable _page) throws Exception ;
    public SalesOfficer addSalesOfficer(
    		@Valid @NotNull(message="{error_no_sales_officer}") SalesOfficer _so) ;
    public SalesOfficer editSalesOfficer(
    		@Valid @NotNull(message="{error_no_sales_officer}") SalesOfficer _so, boolean _encodePassword) ;
    public SalesOfficer deleteSalesOfficer(@NotNull(message="{error_no_id}") Long _id) ;
    public UserCredential findUser(Integer _id) ;
    public UserCredential findUserByToken(String _token) ;
    public UserCredential findUserByName(String _name) ;
    public Page<UserCredential> findAllUsers(Pageable _page) ;
    public List<UserCredential> findAllUsers() ;
    public UserCredential addUser(
    		@Valid @NotNull(message="{error_no_user}") UserCredential _user) ;
    public UserCredential editUser(
    		@Valid @NotNull(message="{error_no_user}") UserCredential _user, String _imgData, String _imgPath, boolean _encodePassword) ;
    public UserCredential deleteUser(@NotNull(message="{error_no_id}") Integer _id);
    public Contact findContact(Long _id);
    public Contact findContactByCred(UserCredential _uc);
    public Contact findContactByCredId(Integer _uc);
    public void flush() ;
}
