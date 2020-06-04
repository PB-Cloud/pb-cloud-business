package sunwell.permaisuri.bus.repository;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import sunwell.permaisuri.core.entity.warehouse.Gudang;;

public interface WarehouseRepo extends JpaRepository<Gudang, Integer> {
	Gudang findDefaultWarehouse(); 
	Gudang findByName(String _name);
}
