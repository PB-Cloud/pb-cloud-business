package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.sales.SalesInvoice;
import sunwell.permaisuri.core.entity.sales.SalesInvoiceItem;
import sunwell.permaisuri.core.entity.sales.SalesInvoiceItemPK;
import sunwell.permaisuri.core.entity.sales.SalesOrder;

public interface SalesInvoiceItemRepo extends JpaRepository<SalesInvoiceItem, SalesInvoiceItemPK> {
	Page<SalesInvoiceItem> findByParent(SalesInvoice _si, Pageable _page);
}
