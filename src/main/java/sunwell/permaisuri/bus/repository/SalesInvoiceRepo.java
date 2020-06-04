package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sunwell.permaisuri.core.entity.sales.SalesInvoice;
import sunwell.permaisuri.core.entity.sales.SalesOrder;

public interface SalesInvoiceRepo extends JpaRepository<SalesInvoice, Long>, JpaSpecificationExecutor<SalesInvoice> {
	
	SalesInvoice findByInvoiceNo(String _no);
	Page<SalesInvoice> findByCustomer_SystemId(long _id, Pageable _page);
	Page<SalesInvoice> findByCustomer_FirstName(String _custName, Pageable _page);
//	SalesInvoice findBySoNo(String _no);
//	Page<SalesInvoice> findByCustomer_CustGroup_SystemId(int _id, Pageable _page);
}
