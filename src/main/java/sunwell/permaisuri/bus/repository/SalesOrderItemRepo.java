package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sunwell.permaisuri.core.entity.sales.SalesOrder;
import sunwell.permaisuri.core.entity.sales.SalesOrderItem;
import sunwell.permaisuri.core.entity.sales.SalesOrderItemPK;

public interface SalesOrderItemRepo extends JpaRepository<SalesOrderItem, SalesOrderItemPK> {
	Page<SalesOrderItem> findBySalesOrder(SalesOrder _so, Pageable _page);
}
