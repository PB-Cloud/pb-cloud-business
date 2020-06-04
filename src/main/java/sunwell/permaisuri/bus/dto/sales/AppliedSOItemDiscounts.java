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
// * @author Irfin A
// * 
// * @version 1.0 - Sep 22, 2007 ; initial version
// * @version 1.5 - Mar 13, 2010 ; perubahan interface dari PersistentObject ke
//                  EntityObject.
// */
//@Entity
//@Table( name = "appliedsoitemdiscount")
//@IdClass( AppliedSOItemDiscountsPK.class )
//public class AppliedSOItemDiscounts implements IAppliedDiscounts
//{
//	@NotNull(message="{no_so_item_specified}")
//    @Id
//    @ManyToOne()
//    @JoinColumns({
//        @JoinColumn( name = "id_so", referencedColumnName = "id_so"),
//        @JoinColumn( name = "id_product", referencedColumnName = "id_product") })
//    private SOItemDTO soItem;
//    
////	@NotNull(message="{no_level_specified}")
//    @Id
//    @Column( name = "level" )
//    private int level ;
//    
//    /** Nilainya harus berupa {@link IAppliedDiscounts#TYPE_CASH} atau {@link IAppliedDiscounts#TYPE_PERCENT} */
//    @Column( name = "disctype" )
//    private int discType = IAppliedDiscounts.TYPE_PERCENT;
//    
//    @Column( name = "discvalue" )
//    private double discValue = 0.0;
//    
//    
//    
//    /** Creates a new instance of AppliedSOLinesDiscounts */
//    public AppliedSOItemDiscounts ()
//    {
//        soItem = null;
//        level = IAppliedDiscounts.TYPE_PERCENT;
//    }
//    
//    public SOItemDTO getSoItem () { return soItem; }
//    
//    public void setSoItem (SOItemDTO _soi)
//    {
//        this.soItem = _soi;
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
//    public void setDiscValue (double _disc)
//    {
//        this.discValue = _disc;
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
//        soItem = null;
//        level = IAppliedDiscounts.TYPE_PERCENT;
//    }    
//}
