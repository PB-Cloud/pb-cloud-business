package sunwell.permaisuri.bus.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sunwell.permaisuri.core.entity.customer.Customer;
//
//import sunwell.permaisuri.core.entity.cred.AccessRights;
//import sunwell.permaisuri.core.entity.cred.AccessRightsPK;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.inventory.ItemCategory;

public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Integer>, JpaSpecificationExecutor<ItemCategory> {
	public ItemCategory findByName(String _name);
	public ItemCategory findByCode(String _code);
}
