//
//package sunwell.permaisuri.bus.dto.inventory;
//
//import java.io.Serializable;
//
//
//import sunwell.permaisuri.bus.dto.StandardDTO;
////import sunwell.permaisuri.core.entity.inventory.ProductDiscounts;
//import sunwell.permaisuri.core.entity.inventory.ProductPurchasePrice;
//
//
//
//public class ProductPurchasePriceDTO extends StandardDTO
//{
//    private String memo;
//    private Double price;
//    
//    
//    /** Creates a new instance of ProductPurchasePrice */
//    public ProductPurchasePriceDTO ()
//    {
//    }
//    
//    public ProductPurchasePriceDTO (ProductPurchasePrice _p)
//    {
//        setData(_p);
//    }
//    
//    public void setData(ProductPurchasePrice _p) {
//    	memo = _p.getMemo();
//    	price = _p.getPrice();
//    }
//    
//    public ProductPurchasePrice getData() {
//    	ProductPurchasePrice ppp = new ProductPurchasePrice();
//    	ppp.setMemo(memo);
//    	if(price != null)
//    		ppp.setPrice(price);
//    	return ppp;
//    }
//   
//    public Double getPrice () { return price; }
//
//    public void setPrice (Double m_price)
//    {
//        this.price = m_price;
//    }
//
//    public String getMemo () { return memo; }
//
//    /**
//     * Character length of parameter _memo must not exceeds 100 chars.
//     * 
//     * @param _memo
//     */
//    public void setMemo (String _memo)
//    {
//        this.memo = (_memo != null) ?
//                                ((_memo.length () < 101) ?
//                                    _memo : _memo.substring (0, 101))
//                           : "";
//    }   
//}
