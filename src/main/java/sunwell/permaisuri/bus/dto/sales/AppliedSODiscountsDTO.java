///*
// * AppliedSODiscounts.java
// *
// * Created on September 22, 2007, 7:35 PM
// */
//package sunwell.permaisuri.bus.dto.sales;
//
//import sunwell.permaisuri.bus.dto.StandardDTO;
//import sunwell.permaisuri.core.entity.sales.AppliedSODiscounts;
//import sunwell.permaisuri.core.entity.util.IAppliedDiscounts;
//
//public class AppliedSODiscountsDTO extends StandardDTO
//{
//   
//    private Integer level ;
//    private Integer discType = IAppliedDiscounts.TYPE_PERCENT;
//    private Double discValue = 0.0;
//    
//    
//    
//    /** Creates a new instance of AppliedSODiscounts */
//    public AppliedSODiscountsDTO ()
//    {
//        
//    }
//    
//    public AppliedSODiscountsDTO (AppliedSODiscounts _disc)
//    {
//        setData(_disc);
//    }
//    
//    public void setData(AppliedSODiscounts _disc) {
//    	level = _disc.getLevel();
//    	discType = _disc.getDiscType();
//    	discValue = _disc.getDiscValue() ;
//    }
//    
//    public AppliedSODiscounts getData() {
//    	AppliedSODiscounts asd = new AppliedSODiscounts();
//    	
//    	if(level != null)
//    		asd.setLevel(level);
//    	if(discType != null)
//    		asd.setDiscType(discType);
//    	if(discValue != null)
//    		asd.setDiscValue(discValue);
//    	
//    	return asd;
//    }
//    
//    public Integer getLevel () { return level; }
//
//    public void setLevel (Integer _level)
//    {
//        this.level = _level;
//    }
//
//    public Double getDiscValue () { return discValue; }
//
//    public void setDiscValue (Double _discvalue)
//    {
//        this.discValue = _discvalue;
//    }
//
//    public void setDiscType(Integer _discType) { discType = _discType; }
//    public Integer getDiscType () { return discType; }
//
//   
//}
