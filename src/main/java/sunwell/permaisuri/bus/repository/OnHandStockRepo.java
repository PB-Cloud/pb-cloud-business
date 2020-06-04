package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.warehouse.Gudang;
import sunwell.permaisuri.core.entity.warehouse.OnHandStock;
import sunwell.permaisuri.core.entity.warehouse.OnHandStockPK;

public interface OnHandStockRepo extends JpaRepository<OnHandStock, OnHandStockPK>, JpaSpecificationExecutor<OnHandStock> {
	public OnHandStock findByItemAndWarehouse(Item _item, Gudang _warehouse) ;
	public OnHandStock findAllByItem_SystemIdAndWarehouse_SystemId(int _pId, int _wId) ;
	public Page<OnHandStock> findByItem(Item _item, Pageable _page) ;
	public Page<OnHandStock> findByWarehouse(Gudang _w, Pageable _page) ;
	public Page<OnHandStock> findByItem_SystemId(int _id, Pageable _page) ;
	public Page<OnHandStock> findByWarehouse_SystemId(int _id, Pageable _page) ;
	public Page<OnHandStock> findByItem_SystemIdAndWarehouse_SystemId(int _pId, int _wId, Pageable _page) ;
    public double getQtyByItem(@Param("item") Item _item) ;
    public double getQtyByItemAndWarehouse(@Param("item") Item _item, @Param("warehouse") Gudang _warehouse);
    public double getQtyByItemAndWarehouseId(@Param("itemId") int _item, @Param("warehouseId") int _warehouse);
}
