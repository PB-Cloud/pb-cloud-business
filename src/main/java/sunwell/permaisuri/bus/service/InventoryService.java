package sunwell.permaisuri.bus.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sunwell.permaisuri.bus.exception.OperationException;
import sunwell.permaisuri.bus.spec.GenericSpecification;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.warehouse.Gudang;
import sunwell.permaisuri.core.entity.warehouse.OnHandStock;
import sunwell.permaisuri.core.entity.warehouse.OnHandStockPK;

public interface InventoryService
{
	public OnHandStock findOnHandStock(@NotNull(message="{error_no_id}") OnHandStockPK _pk) ;
	public OnHandStock findOnHandByItemAndWarehouse(@NotNull(message="{error_no_item}") Item _item, 
											  @NotNull(message="{error_no_warehouse}") Gudang _warehouse) ;
	public Page<OnHandStock> findOnHandByItemAndWarehouseId(int _item, int _gudang, Pageable _page) ;
	public Page<OnHandStock> findAllOnHandStock(Pageable _page) ;
	public Page<OnHandStock> findOnHandStocks(Filters _f, Pageable _page) throws Exception ;
	public Page<OnHandStock> findOnHandByItem(@NotNull(message="{error_no_item}") Item _item, Pageable _page) ;
	public Page<OnHandStock> findOnHandByWarehouse(@NotNull(message="{error_no_warehouse}") Gudang _gudang, Pageable _page) ;
	public Page<OnHandStock> findOnHandByItemId(int _id, Pageable _page) ;
	public Page<OnHandStock> findOnHandByWarehouseId(int _gudang, Pageable _page) ;
	public double getOnHandQtyByItem(Item _item) ;
	public double getOnHandQtyByItemId(Item _item) ;
	public OnHandStock createOnHand (@Valid @NotNull(message = "{error_no_onhand}") OnHandStock _oh);
	public OnHandStock addOnHand (@Valid @NotNull(message = "{error_no_onhand}") OnHandStock _oh);
	public OnHandStock removeOnHand (@Valid @NotNull(message = "{error_no_onhand}") OnHandStock _oh);
	public OnHandStock editOnHand (@Valid @NotNull(message = "{error_no_onhand}") OnHandStock _oh);
	public OnHandStock deleteOnHand(@NotNull(message="{error_no_onhand}") OnHandStock _oh) ;
	public OnHandStock deleteOnHand(@NotNull(message="{error_no_id}") Integer _itemId, @NotNull(message="{error_no_id}") Integer _warehouseId) ;
	public List<OnHandStock> addOnHand (
			@Valid @NotNull(message = "{error_no_item}") @Size(min = 1, message = "{error_no_item}") List<OnHandStock> _onHands);
	public List<OnHandStock> removeOnHand ( 
			@Valid @NotNull(message = "{error_no_item}") @Size(min = 1, message = "{error_no_item}") List<OnHandStock> _onHands);
	public Gudang findWarehouse(int _systemId);
	public Gudang findWarehouseByName(String _name) ;
	public Gudang findDefaultWarehouse() ;
}
