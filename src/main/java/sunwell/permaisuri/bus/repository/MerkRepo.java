package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.inventory.ItemCategory;
import sunwell.permaisuri.core.entity.inventory.Merk;
import sunwell.permaisuri.core.entity.inventory.Metrics;

public interface MerkRepo extends JpaRepository<Merk, Integer>, JpaSpecificationExecutor<Merk> {
	public Merk findByName(String _name);
}
