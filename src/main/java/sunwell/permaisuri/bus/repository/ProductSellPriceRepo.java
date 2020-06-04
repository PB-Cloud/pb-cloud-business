package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.inventory.ProductSellPrice;
import sunwell.permaisuri.core.entity.inventory.ProductSellPricePK;
import sunwell.permaisuri.core.entity.inventory.SellPriceLevel;


public interface ProductSellPriceRepo extends JpaRepository<ProductSellPrice, ProductSellPricePK> {
	
}
