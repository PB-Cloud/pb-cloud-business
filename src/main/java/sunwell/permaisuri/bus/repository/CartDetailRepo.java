package sunwell.permaisuri.bus.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.sales.CartDetail;
import sunwell.permaisuri.core.entity.sales.CartDetailPK;
import sunwell.permaisuri.core.entity.sales.SalesInvoice;

public interface CartDetailRepo extends JpaRepository<CartDetail, CartDetailPK>, JpaSpecificationExecutor<CartDetail> {
	Page<CartDetail> findByCustomer(Customer _cust, Pageable _page);
	Page<CartDetail> findByCustomer_SystemId(Long _id, Pageable _page);
}
