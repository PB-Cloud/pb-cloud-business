///*
// * AppliedSOItemDiscounts.java
// *
// * Created on September 22, 2007, 7:35 PM
// */
//package sunwell.permaisuri.bus.dto.sales;
//
//import java.sql.*;
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//import sunwell.permaisuri.core.entity.util.IAppliedDiscounts;
//
///**
// *
// * @author Benny
// */
//@Entity
//@Table( name = "appliedsoitemdiscount")
//@IdClass( AppliedSOServiceDiscountPK.class )
//public class AppliedSOServiceDiscount implements IAppliedDiscounts
//{
//    /** Nilainya harus berupa {@link IAppliedDiscounts#TYPE_CASH} atau {@link IAppliedDiscounts#TYPE_PERCENT} */
//    
//	@NotNull(message="{no_so_service_specified}")
//    @Id
//    @ManyToOne()
//    @JoinColumns({
//        @JoinColumn( name = "id_so", referencedColumnName = "id_so"),
//        @JoinColumn( name = "id_service", referencedColumnName = "id_service") })
//    private SOService soService;
//    
////	@NotNull(message="{no_level_specified}")
//    @Id
//    @Column( name  = "level" )
//    private int level ;
//    
//    @Column( name = "disctype" )
//    private int discType = IAppliedDiscounts.TYPE_PERCENT;
//    
//    @Column( name = "discvalue" )
//    private double discValue = 0.0;
//    
//    
//    /** Creates a new instance of AppliedSOLinesDiscounts */
//    public AppliedSOServiceDiscount ()
//    {
//        soService = null;
//        discType = IAppliedDiscounts.TYPE_PERCENT;
//    }
//    
//    public SOService getSoService () { return soService; }
//    
//    public void setSoservice (SOService _soi)
//    {
//        this.soService = _soi;
//    }
//    
//    public int getLevel () { return level; }
//
//    public void setLevel (int _level)
//    {
//        this.level = _level;
//    }
//
//    public double getDiscValue () { return discValue; }
//
//    public void setDiscValue (double _discvalue)
//    {
//        this.discValue = _discvalue;
//    }
//
//    /**
//     * @see IAppliedDiscounts#TYPE_PERCENT
//     * @see IAppliedDiscounts#TYPE_CASH
//     */
//    public int getDiscType () { return discType; }
//    
//    /**
//     * @see IAppliedDiscounts#TYPE_PERCENT
//     * @see IAppliedDiscounts#TYPE_CASH
//     */
//    public void setDiscType (int _type)
//    {
//        if (_type != IAppliedDiscounts.TYPE_PERCENT && _type != IAppliedDiscounts.TYPE_CASH)
//            this.discType = IAppliedDiscounts.TYPE_CASH;
//        else
//            this.discType = _type;
//    }
//
//    public int compareTo (IAppliedDiscounts _o)
//    {
//        if (this.getLevel () == _o.getLevel ())
//            return 0;
//        else if (this.getLevel () < _o.getLevel ())
//            return -1;
//        else
//            return +1;
//    }
//
//    public void resetAttributes ()
//    {
//        soService = null;
//        level = IAppliedDiscounts.TYPE_PERCENT;
//    }    
//}
