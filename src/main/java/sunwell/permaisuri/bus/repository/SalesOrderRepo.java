package sunwell.permaisuri.bus.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sunwell.permaisuri.core.entity.sales.SalesOrder;;

public interface SalesOrderRepo extends JpaRepository<SalesOrder, Long>, JpaSpecificationExecutor<SalesOrder> {
	
	Page<SalesOrder> findByCustomer_SystemId(long _id, Pageable _page);
	Page<SalesOrder> findByCustomer_FirstName(String _custName, Pageable _page);
//	SalesOrder findByNo(String _no);
//	Page<SalesOrder> findByCustomer_CustGroup_SystemId(int _id, Pageable _page);
}
