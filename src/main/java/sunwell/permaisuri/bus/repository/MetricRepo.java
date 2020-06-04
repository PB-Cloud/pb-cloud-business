package sunwell.permaisuri.bus.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.inventory.ItemCategory;
import sunwell.permaisuri.core.entity.inventory.Metrics;

public interface MetricRepo extends JpaRepository<Metrics, String> {
	public Metrics findByName(String _name);
	public Metrics findDefaultMetric();
}
