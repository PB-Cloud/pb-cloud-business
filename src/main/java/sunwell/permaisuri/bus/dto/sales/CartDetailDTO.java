package sunwell.permaisuri.bus.dto.sales;


import sunwell.permaisuri.bus.dto.StandardDTO;


import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.sales.CartDetail;

public class CartDetailDTO extends StandardDTO
{
	private Long customer;
	private String item;
	private Double qty;
//	private Double discount;
	private String reqNote;
	
	public CartDetailDTO() {
		
	}
	
	public CartDetailDTO(CartDetail _cd) {
		setData(_cd);
	}
	
	public void setData(CartDetail _cd) {
		if(_cd.getCustomer() != null)
			customer = _cd.getCustomer().getSystemId();
		if(_cd.getItem() != null)
			item = _cd.getItem().getName();
		qty = _cd.getQty();
//		discount = _cd.getDiscount();
		reqNote = _cd.getReqNote();
	}
	
	public CartDetail getData() {
		CartDetail cd = new CartDetail();
		cd.setReqNote(reqNote);
		if(customer != null)
			cd.setCustomer(new Customer(customer));
		if(item != null)
			cd.setItem(new Item(item));
		if(qty != null)
			cd.setQty(qty);;
//		if(discount != null)
//			cd.setDiscount(discount);
		return cd;
	}
	
	public Long getCustomer ()
	{
		return customer;
	}
	public void setCustomer (Long _customer)
	{
		customer = _customer;
	}
	
	public String getItem ()
	{
		return item;
	}
	public void setItem (String _item)
	{
		item = _item;
	}
	public Double getQty ()
	{
		return qty;
	}
	public void setQty (Double _qty)
	{
		qty = _qty;
	}
	public String getReqNote ()
	{
		return reqNote;
	}
	public void setReqNote (String _memo)
	{
		reqNote = _memo;
	}

//	public Double getDiscount ()
//	{
//		return discount;
//	}
//
//	public void setDiscount (Double _discount)
//	{
//		discount = _discount;
//	}

}
