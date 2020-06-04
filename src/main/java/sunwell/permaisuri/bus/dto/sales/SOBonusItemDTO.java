package sunwell.permaisuri.bus.dto.sales;
///*
// * SOBonusItem.java
// *
// * Created on April 28, 2009, 11:17:39 PM
// */
//package sunwell.permaisuri.bus.dto.sales;
//
//
//
//import sunwell.permaisuri.core.entity.inventory.Item;
//import sunwell.permaisuri.core.entity.inventory.Metrics;
//import sunwell.permaisuri.core.entity.inventory.MetricsConversion;
//
//public class SOBonusItem 
//{
//    private String item = null;
//    
//    @Column( name = "qty" )
//    private double qty = 0.0;
//    
//    @Column( name = "qty_in_sales_metric" )
//    private double qtyInMetricUsed ;
//    
//    @ManyToOne
//    @JoinColumn( name ="metricused" )
//    private Metrics metricUsed ;
//    
//    @Column( name = "note" )
//    private String m_note;
//    
//    public SOBonusItem ()
//    {
//        salesOrder = null;
//        item = null;
//        metricUsed = null ;
//        qty = 0.0 ;
//        qtyInMetricUsed = 0.0 ;
//        m_note = null ;
//    }
//
//
//    /**
//     * @return the m_so
//     */
//    public SalesOrderDTO getSalesOrder () { return salesOrder; }
//
//    /**
//     * @param m_so the m_so to set
//     */
//    public void setSalesOrder (SalesOrderDTO m_so)
//    {
//        this.salesOrder = m_so;
//    }
//
//    /**
//     * @return the m_item
//     */
//    public Item getItem () { return item; }
//
//    /**
//     * @param m_item the m_item to set
//     */
//    public void setItem (Item m_item)
//    {
//        this.item = m_item;
//    }
//
//    /**
//     * @return the m_qty
//     */
//    public double getQty () { return qty; }
//
//   
//    /**
//     * Penggunaan method ini akan meng-override nilai qty yang sebelumnya diset
//     * melalui {@link #setQtyInSalesMetric(double, sunwell.xrp.inventory.Metrics)}.
//     * 
//     * @param m_qty 
//     */
//    public void setQty (double m_qty)
//    {
//        this.qty = m_qty;
//        qtyInMetricUsed = 0.0;
//        metricUsed = null;
//    }
//        
//    public double getQtyInMetricUsed () { return qtyInMetricUsed; }
//    
//    public Metrics getMetricUsed ()
//    {
//        return metricUsed;
//    }
//
//    /**
//     * @return the m_note
//     */
//    public String getNote () { return m_note; }
//
//    /**
//     * @param m_note the m_note to set
//     */
//    public void setNote (String _note)
//    {
//        this.m_note = (_note != null) ? _note : "";
//    }    
//}
