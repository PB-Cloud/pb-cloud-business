/*
 * SOLines.java
 *
 * Created on September 22, 2007, 7:34 PM
 */
package sunwell.permaisuri.bus.dto.sales;

import java.sql.*;


import java.util.Arrays;
import java.util.List;

import sunwell.permaisuri.bus.dto.StandardDTO;
import sunwell.permaisuri.bus.dto.inventory.ItemDTO;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.inventory.Metrics;
import sunwell.permaisuri.core.entity.sales.SalesInvoiceItem;

public class SalesInvoiceItemDTO extends StandardDTO
{
    private String item ;
    private Double qty ;
    private Double discount ;
    private Double hargaJual ;
    private String reqNote;
//    private List<AppliedSOItemDiscounts> discounts ;
    
    
    /** Creates a new instance of SOItem */
    public SalesInvoiceItemDTO ()
    {
    }
    
    public SalesInvoiceItemDTO (SalesInvoiceItem _item)
    {
    	setData(_item);
    }
    
    public void setData(SalesInvoiceItem _item) {
    	if(_item.getItem() != null)
    		item = _item.getItem().getName();
    	
    	qty = _item.getQty();
    	hargaJual = _item.getHargaJual();
    	discount = _item.getDiscount();
    	reqNote = _item.getReqNote();
    }
    
    public SalesInvoiceItem getData() {
    	SalesInvoiceItem sItem = new SalesInvoiceItem();
    	sItem.setReqNote(reqNote);
    	if(item != null)
    		sItem.setItem(new Item(item));
    	if(qty != null)
    		sItem.setQty(qty);
    	if(hargaJual != null)
    		sItem.setHargaJual(hargaJual);
    	if(discount != null)
    		sItem.setDiscount(discount);
    	return sItem;
    }
         
    public String getItem () { return item; }

    public void setItem (String _item)
    {
        this.item = _item;
    }

    public Double getQty () { return qty; }

    /**
     * Penggunaan method ini akan meng-override nilai qty yang sebelumnya diset
     * melalui {@link #setQtyInSalesMetric(double, sunwell.xrp.inventory.Metrics)}.
     * 
     * @param m_qty 
     */
    public void setQty (Double _qty)
    {
        this.qty = _qty;
    }
     
    public Double getHargaJual () { return hargaJual; }

    public void setHargaJual (Double m_harga_jual)
    {
        this.hargaJual = m_harga_jual;
    }

	public Double getDiscount ()
	{
		return discount;
	}

	public void setDiscount (Double _discount)
	{
		discount = _discount;
	}

	public String getReqNote ()
	{
		return reqNote;
	}

	public void setReqNote (String _reqNote)
	{
		reqNote = _reqNote;
	}
     
}
