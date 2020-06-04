package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.inventory.SellPriceLevel;;


public interface SellPriceLevelRepo extends JpaRepository<SellPriceLevel, Integer> {
	SellPriceLevel findByName(String _name);
}
