package sunwell.permaisuri.bus.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.inventory.Product;
import sunwell.permaisuri.core.entity.inventory.ProductImage;
import sunwell.permaisuri.core.entity.inventory.ProductImagePK;


public interface ProductImageRepo extends JpaRepository<ProductImage, Integer> {
	ProductImage findByProduct(Product _p);
	ProductImage findByProductName(String _p);
}
