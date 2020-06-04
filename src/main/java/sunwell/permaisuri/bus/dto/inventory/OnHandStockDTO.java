package sunwell.permaisuri.bus.dto.inventory;

import java.util.Calendar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import sunwell.permaisuri.bus.dto.StandardDTO;
import sunwell.permaisuri.core.entity.warehouse.Gudang;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.warehouse.OnHandStock;

public class OnHandStockDTO extends StandardDTO
{
	private String item;
	private String warehouse;
	private Calendar expiryDate ;
	private String batchNo ;
	private String serialNo ;
	private Double qty;
	
	public OnHandStockDTO() {
		
	}
	
	public OnHandStockDTO(OnHandStock _oh) {
		setData(_oh);
	}
	
	public void setData(OnHandStock _oh) {
		if(_oh.getItem() != null)
			item = _oh.getItem().getName();
		
		if(_oh.getWarehouse() != null)
			warehouse = _oh.getWarehouse().getName();
		
		expiryDate = _oh.getExpiryDate();
		batchNo = _oh.getBatchNo();
		serialNo = _oh.getSerialNo();
		
		qty = _oh.getQty();
	}
	
	public OnHandStock getData() {
		if(qty == null) qty = 0.0;
		return new OnHandStock( new Item(item), new Gudang(warehouse), qty); 
//		return new OnHandStock( new Item(item), new Gudang(warehouse), expiryDate, batchNo, serialNo, qty); 
	}
	
	public String getItem ()
	{
		return item;
	}
	public void setItem (String _item)
	{
		item = _item;
	}
	public String getWarehouse ()
	{
		return warehouse;
	}
	public void setWarehouse (String _warehouse)
	{
		warehouse = _warehouse;
	}
	public double getQty ()
	{
		return qty;
	}
	public void setQty (double _qty)
	{
		qty = _qty;
	}

	public Calendar getExpiryDate ()
	{
		return expiryDate;
	}

	public void setExpiryDate (Calendar _expiryDate)
	{
		expiryDate = _expiryDate;
	}

	public String getBatchNo ()
	{
		return batchNo;
	}

	public void setBatchNo (String _batchNo)
	{
		batchNo = _batchNo;
	}

	public String getSerialNo ()
	{
		return serialNo;
	}

	public void setSerialNo (String _no)
	{
		serialNo = _no;
	}
}
