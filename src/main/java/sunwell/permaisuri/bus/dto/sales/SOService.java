///*
// * SOLines.java
// *
// * Created on November 18, 2014
// */
//package sunwell.permaisuri.bus.dto.sales;
//
//
//
//import sunwell.permaisuri.core.entity.inventory.Service;
//public class SOService 
//{    
//    @NotNull(message="{error_no_service}")
//    @Id
//    @ManyToOne
//    @JoinColumn( name = "id_service")
//    private Service service ;
//    
//    @OneToMany( mappedBy = "m_service", cascade = CascadeType.ALL )
//    private List<AppliedSOServiceDiscount> discounts ;
//    
////    @Transient
////    private AppliedSOServiceDiscount[] m_arrDisc;
//    
//    @Column( name = "qty" )
//    private double qty;
//    
//    @Column( name = "harga_jual" )
//    private double hargaJual;
//    
//    
//    /** Creates a new instance of SOItem */
//    public SOService ()
//    {
//        salesOrder = null;
//        service = null;
//    }
//    
//    /** Creates a new instance of exist SOItem */
//    public SOService (SalesOrderDTO _so, Service _service)
//    {
//        salesOrder = _so;
//        service = _service;
//    }
//        
//    public Service getService () { return service; }
//
//    public void setService (Service _service)
//    {
//        this.service = _service;
//    }
//
//    public double getQty () { return qty; }
//
//    public void setQty (double _qty)
//    {
//        this.qty = _qty;
//    }
//
//    public double getHargaJual () { return hargaJual; }
//
//    public void setHargaJual (double _harga_jual)
//    {
//        this.hargaJual = _harga_jual;
//    }
//
//    public SalesOrderDTO getSalesOrder () { return salesOrder; }
//
//    public void setSalesOrder (SalesOrderDTO m_so)
//    {
//        this.salesOrder = m_so;
//    }
//    
//    public int getDiscountCount ()
//    {
//        if (discounts != null)
//            return discounts.size();
//        else
//            return 0;
//    }
//
//    public List<AppliedSOServiceDiscount> getDiscounts () 
//    { 
//        return discounts; 
//    }
//
//    public void setServiceDiscs (List<AppliedSOServiceDiscount> _discs)
//    {
//        discounts = _discs;
//    }
//   
//    
//    public void resetAttributes ()
//    {
//        salesOrder = null;
//        service = null;
//    }
//}
