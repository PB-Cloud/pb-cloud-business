///*
// * SOLines.java
// *
// * Created on September 22, 2007, 7:34 PM
// */
//package sunwell.permaisuri.bus.dto.sales;
//
//import java.sql.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import sunwell.permaisuri.bus.dto.StandardDTO;
//import sunwell.permaisuri.bus.dto.inventory.ItemDTO;
//import sunwell.permaisuri.core.entity.inventory.Item;
//import sunwell.permaisuri.core.entity.inventory.ItemShipmentInfo;
//import sunwell.permaisuri.core.entity.inventory.Metrics;
//import sunwell.permaisuri.core.entity.sales.SOItem;
//import sunwell.permaisuri.core.entity.sales.SOService;
//
//public class SOServiceDTO extends StandardDTO
//{
//    private String service ;
//    private Double qty;
//    private Double discount ;
//    private Double hargaJual ;
//    private String reqNote;
////    private List<AppliedSOItemDiscounts> discounts ;
//    
//    
//    /** Creates a new instance of SOItem */
//    public SOServiceDTO ()
//    {
//    }
//    
//    public SOServiceDTO (SOService _service)
//    {
//    	setData(_service);
//    }
//    
//    public void setData(SOService _service) {
//    	if(_service.getService() != null)
//    		service = _service.getService().getName();
//    	
//    	hargaJual = _service.getHargaJual();
////    	discount = _service.getDiscount();
////    	reqNote = _service.getReqNote();
//    }
//    
//    public SOItem getData() {
//    	SOItem sItem = new SOItem();
//    	sItem.setReqNote(reqNote);
//    	if(hargaJual != null)
//    		sItem.setHargaJual(hargaJual);
//    	if(discount != null)
//    		sItem.setDiscount(discount);
//    	return sItem;
////    	if(qtyInMetricUsed != null)
////    		sItem.setQty(_qty);
//    }
//         
//    public String getService () { return service; }
//
//    public void setService (String _service)
//    {
//        this.service = _service;
//    }
//
//    public Double getQty () { return qty; }
//
//    /**
//     * Penggunaan method ini akan meng-override nilai qty yang sebelumnya diset
//     * melalui {@link #setQtyInSalesMetric(double, sunwell.xrp.inventory.Metrics)}.
//     * 
//     * @param m_qty 
//     */
//    public void setQty (Double _qty)
//    {
//        this.qty = _qty;
//    }
//    
//    
//    public Double getHargaJual () { return hargaJual; }
//
//    public void setHargaJual (Double m_harga_jual)
//    {
//        this.hargaJual = m_harga_jual;
//    }
//
//	public Double getDiscount ()
//	{
//		return discount;
//	}
//
//	public void setDiscount (Double _discount)
//	{
//		discount = _discount;
//	}
//
//	public String getReqNote ()
//	{
//		return reqNote;
//	}
//
//	public void setReqNote (String _reqNote)
//	{
//		reqNote = _reqNote;
//	}
//    
////    public List<AppliedSOItemDiscounts> getDiscounts () 
////    { 
////        return discounts; 
////    }
////
////    public void setItemDiscs (List<AppliedSOItemDiscounts> _discounts)
////    {
////        discounts = _discounts;
////     }
//    
////    public ItemShipmentInfo getShipmentUsed () { return shipmentUsed ; }
////
////    public void setShipmentUsed (ItemShipmentInfo _shipment)
////    {
////        this.shipmentUsed = _shipment;
////    }
//     
//}
