package sunwell.permaisuri.bus.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.inventory.Product;
import sunwell.permaisuri.core.entity.inventory.ProductImage;
import sunwell.permaisuri.core.entity.inventory.ProductImagePK;
import sunwell.permaisuri.core.entity.sales.PaymentImage;
import sunwell.permaisuri.core.entity.sales.SalesInvoice;


public interface PaymentImageRepo extends JpaRepository<PaymentImage, Long> {
	PaymentImage findBySalesInvoice(SalesInvoice _s);
	PaymentImage findBySalesInvoice_SystemId(Long _p);
}
