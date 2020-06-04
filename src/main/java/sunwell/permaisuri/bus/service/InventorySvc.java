package sunwell.permaisuri.bus.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import sunwell.permaisuri.bus.exception.OperationException;
import sunwell.permaisuri.bus.repository.OnHandStockRepo;
import sunwell.permaisuri.bus.repository.WarehouseRepo;
import sunwell.permaisuri.bus.spec.GenericSpecification;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.sales.CartDetail;
import sunwell.permaisuri.core.entity.warehouse.Gudang;
import sunwell.permaisuri.core.entity.warehouse.OnHandStock;
import sunwell.permaisuri.core.entity.warehouse.OnHandStockPK;

@Service
@Transactional
@Validated
public class InventorySvc implements InventoryService
{
	@Autowired
	OnHandStockRepo ohRepo;
	
	@Autowired
	ProductService prodSvc;	
	
	@Autowired
	WarehouseRepo wrRepo;
	
	public OnHandStockRepo getOhRepo() {
		return ohRepo;
	}

	public void setOhRepo(OnHandStockRepo ohRepo) {
		this.ohRepo = ohRepo;
	}

	public ProductService getProdSvc() {
		return prodSvc;
	}

	public void setProdSvc(ProductService prodSvc) {
		this.prodSvc = prodSvc;
	}

	public WarehouseRepo getWrRepo() {
		return wrRepo;
	}

	public void setWrRepo(WarehouseRepo wrRepo) {
		this.wrRepo = wrRepo;
	}
	
	
	public OnHandStock findOnHandStock(@NotNull(message="{error_no_id}") OnHandStockPK _pk) {
		return ohRepo.findById(_pk).orElse(null);
	}
	
	public OnHandStock findOnHandByItemAndWarehouse(@NotNull(message="{error_no_item}") Item _item, 
											  @NotNull(message="{error_no_warehouse}") Gudang _warehouse) {
		return ohRepo.findByItemAndWarehouse(_item, _warehouse);
	}
	
	public Page<OnHandStock> findOnHandByItemAndWarehouseId(int _item, int _gudang, Pageable _page) {
		return ohRepo.findByItem_SystemIdAndWarehouse_SystemId(_item, _gudang, _page);
	}
	
	public Page<OnHandStock> findAllOnHandStock(Pageable _page) {
		return ohRepo.findAll(_page);
	}
	
	public Page<OnHandStock> findOnHandStocks(Filters _f, Pageable _page) throws Exception {
		return ohRepo.findAll(new GenericSpecification<OnHandStock>(_f, OnHandStock.class), _page);
	}
	
	public Page<OnHandStock> findOnHandByItem(@NotNull(message="{error_no_item}") Item _item, Pageable _page) {
		return ohRepo.findByItem(_item, _page);
	}
	
	public Page<OnHandStock> findOnHandByWarehouse(@NotNull(message="{error_no_warehouse}") Gudang _gudang, Pageable _page) {
		return ohRepo.findByWarehouse(_gudang, _page);
	}
	
	public Page<OnHandStock> findOnHandByItemId(int _id, Pageable _page) {
		return ohRepo.findByItem_SystemId(_id, _page);
	}
	
	public Page<OnHandStock> findOnHandByWarehouseId(int _gudang, Pageable _page) {
		return ohRepo.findByWarehouse_SystemId(_gudang, _page);
	}
	
	public double getOnHandQtyByItem(Item _item) {
		Gudang g = findDefaultWarehouse();
		System.out.println("G: " + g.getName());
		return ohRepo.getQtyByItemAndWarehouse(_item, findDefaultWarehouse());
	}
	
	public double getOnHandQtyByItemId(Item _item) {
		return ohRepo.getQtyByItemAndWarehouseId(_item.getSystemId(), findDefaultWarehouse().getSystemId());
	}
	
	public OnHandStock createOnHand (@Valid @NotNull(message = "{error_no_onhand}") OnHandStock _oh)
	{
		prepareOnHand(_oh);
		return ohRepo.save(_oh);
		
		// sama seperti relasi dari relasi one to many yang harus sudah managed
    	// kemungkinan karena ini adalah id maka juga harus sudah managed
		
//		Item item = prodSvc.findItem(_oh.getItem().getSystemId());
//		if(item == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//		
//		Gudang wr = findWarehouse(_oh.getItem().getSystemId());
//		if(wr == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_WAREHOUSE, null);
//		
//		_oh.setItem(item);
//		_oh.setWarehouse(wr);
		
	}
	
	public OnHandStock addOnHand (@Valid @NotNull(message = "{error_no_onhand}") OnHandStock _oh)
	{
//		Item item = null;
//    	if(_oh.getItem().getSystemId() > 0)
//    		item = prodSvc.findItem(_oh.getItem().getSystemId());
//    	else if(_oh.getItem().getName() != null)
//    		item = prodSvc.findItemByName(_oh.getItem().getName());
//    	if(item == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//    	
//    	Gudang wrh = null;
//    	if(_oh.getItem().getSystemId() > 0)
//    		wrh = findWarehouse(_oh.getWarehouse().getSystemId());
//    	else if(_oh.getWarehouse().getName() != null)
//    		wrh = findWarehouseByName(_oh.getWarehouse().getName());
//		if(wrh == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_WAREHOUSE, null);
		prepareOnHand(_oh); 
		Optional<OnHandStock> ohs = ohRepo.findById(new OnHandStockPK(_oh.getItem().getSystemId(), _oh.getWarehouse().getSystemId()));
		OnHandStock oh = ohs.orElse(null);
//		OnHandStock oh = ohRepo.findOne(new OnHandStockPK(_oh.getItem().getSystemId(), _oh.getWarehouse().getSystemId(), 
//				_oh.getStrExpiryDate(), _oh.getSerialNo(), _oh.getBatchNo()));

		if (oh == null) {
//			prepareOnHand(_oh);
			oh = ohRepo.save(_oh);
//			// sama seperti relasi dari relasi one to many yang harus sudah managed
//	    	// kemungkinan karena ini adalah id maka juga harus sudah managed
//			
//			Item item = prodSvc.findItem(_oh.getItem().getSystemId());
//			if(item == null) 
//				throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//			
//			System.out.println("ITEM ID: " + item.getSystemId());
//			
//			Gudang wr = findWarehouse(_oh.getWarehouse().getSystemId());
//			if(wr == null)
//				throw new OperationException(StandardConstant.ERROR_CANT_FIND_WAREHOUSE, null);
//			
//			_oh.setItem(item);
//			_oh.setWarehouse(wr);
			
		}
		else 
			oh.setQty(oh.getQty() + _oh.getQty());

		return oh;
	}
	
	public OnHandStock removeOnHand (@Valid @NotNull(message = "{error_no_onhand}") OnHandStock _oh)
	{
		prepareOnHand(_oh);
		Optional<OnHandStock> ohs = ohRepo.findById(new OnHandStockPK(_oh.getItem().getSystemId(), _oh.getWarehouse().getSystemId()));
		OnHandStock oh = ohs.orElse(null);
//		OnHandStock oh = ohRepo.findOne(new OnHandStockPK(_oh.getItem().getSystemId(), _oh.getWarehouse().getSystemId(), 
//				_oh.getStrExpiryDate(), _oh.getSerialNo(), _oh.getBatchNo()));
		
		System.out.println(" ID: " + _oh.getItem().getSystemId() +
							"\n WRH: " + _oh.getWarehouse().getSystemId() +
							"\n EXP: " + _oh.getStrExpiryDate() +
							"\n SERIAL NO: " + _oh.getSerialNo() + 
							"\n BATCH NO: " + _oh.getBatchNo());

		if (oh == null) 
			throw new OperationException(StandardConstant.ERROR_NOT_ENOUGH_STOCK, null);
		else {
			if(oh.getQty() > _oh.getQty())
				oh.setQty(oh.getQty() - _oh.getQty());
			else if(oh.getQty() == _oh.getQty())
				ohRepo.delete(oh);
			else
				throw new OperationException(StandardConstant.ERROR_NOT_ENOUGH_STOCK, null);
		}

		return oh;
	}
	
	public OnHandStock editOnHand (@Valid @NotNull(message = "{error_no_onhand}") OnHandStock _oh)
	{
		prepareOnHand(_oh);
		Optional<OnHandStock> ohs = ohRepo.findById(new OnHandStockPK(_oh.getItem().getSystemId(), _oh.getWarehouse().getSystemId()));
		OnHandStock oh = ohs.orElse(null);
//		OnHandStock oh = ohRepo.findOne(new OnHandStockPK(_oh.getItem().getSystemId(), _oh.getWarehouse().getSystemId(), 
//				_oh.getStrExpiryDate(), _oh.getSerialNo(), _oh.getBatchNo()));

		if (oh == null) {
//			prepareOnHand(_oh);
			ohRepo.save(_oh);
		}
		else {
			oh.setQty(_oh.getQty());
		}

		return oh;
	}
	
	public OnHandStock deleteOnHand(@NotNull(message="{error_no_onhand}") OnHandStock _oh) {
		prepareOnHand(_oh);
		Optional<OnHandStock> ohs = ohRepo.findById(new OnHandStockPK(_oh.getItem().getSystemId(), _oh.getWarehouse().getSystemId()));
		OnHandStock oh = ohs.orElse(null);
//		OnHandStock oh = ohRepo.findOne(new OnHandStockPK(_oh.getItem().getSystemId(), _oh.getWarehouse().getSystemId(), 
//				_oh.getStrExpiryDate(), _oh.getSerialNo(), _oh.getBatchNo()));
		
//		System.out.println(" ID: " + _oh.getItem().getSystemId() +
//				"\n WRH: " + _oh.getWarehouse().getSystemId() +
//				"\n EXP: " + _oh.getStrExpiryDate() +
//				"\n SERIAL NO: " + _oh.getSerialNo() + 
//				"\n BATCH NO: " + _oh.getBatchNo());
		
		if(oh == null) 
			throw new sunwell.permaisuri.bus.exception.OperationException(StandardConstant.ERROR_CANT_FIND_STOCK, null);
	    ohRepo.delete(oh);
	    return oh;
    }
	
	public OnHandStock deleteOnHand(@NotNull(message="{error_no_id}") Integer _itemId, @NotNull(message="{error_no_id}") Integer _warehouseId) {
		OnHandStock stock = ohRepo.findAllByItem_SystemIdAndWarehouse_SystemId(_itemId, _warehouseId);
		if(stock == null) 
			throw new sunwell.permaisuri.bus.exception.OperationException(StandardConstant.ERROR_CANT_FIND_STOCK, null);
	    ohRepo.delete(stock);
	    return stock;
    }
	
	public List<OnHandStock> addOnHand (
			@Valid @NotNull(message = "{error_no_item}") @Size(min = 1, message = "{error_no_item}") List<OnHandStock> _onHands)
	{
		for (OnHandStock _ohs : _onHands) {
			_ohs = addOnHand(_ohs);
		}

		return _onHands;
	}
	
	public List<OnHandStock> removeOnHand (
			@Valid @NotNull(message = "{error_no_item}") @Size(min = 1, message = "{error_no_item}") List<OnHandStock> _onHands)
	{
		for (OnHandStock _ohs : _onHands) {
			_ohs = removeOnHand(_ohs);
		}

		return _onHands;
	}
	
	public Gudang findWarehouse(int _systemId) {
		return wrRepo.findById(_systemId).orElse(null);
	}
	
	public Gudang findWarehouseByName(String _name) {
		return wrRepo.findByName(_name);
	}
	
	public Gudang findDefaultWarehouse() {
		return wrRepo.findDefaultWarehouse();
	}
	
	private void prepareOnHand(OnHandStock _oh) {
		
		Item item = null;
		if(_oh.getItem().getSystemId() > 0)
			item = prodSvc.findItem(_oh.getItem().getSystemId());
		else if(_oh.getItem().getName() != null)
			item = prodSvc.findItemByName(_oh.getItem().getName());
		if(item == null) 
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
		
		System.out.println("ITEM ID: " + item.getSystemId());
		
		Gudang wr = null;
		if(_oh.getWarehouse().getSystemId() > 0)
			wr = findWarehouse(_oh.getWarehouse().getSystemId());
		else if(_oh.getWarehouse().getName() != null)
			wr = findWarehouseByName(_oh.getWarehouse().getName());	
		if(wr == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_WAREHOUSE, null);
		
		_oh.setItem(item);
		_oh.setWarehouse(wr);
	}
}
