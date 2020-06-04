package sunwell.permaisuri.bus.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//import sunwell.permaisuri.core.entity.cred.UserGroup;
import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.sales.CartDetail;;
//import sunwell.permaisuri.core.entity.customer.CustomerGroup;


public interface CustomerRepo extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
//	public Customer findByName(String _name) ;
	public Customer findByEmail(String _name) ;
//	public Page<Customer> findByCustGroup(CustomerGroup _cg, Pageable _page);
//	public Page<Customer> findByCustGroup_SystemId(Integer _id, Pageable _page);
}
