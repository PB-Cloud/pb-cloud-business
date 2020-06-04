package sunwell.permaisuri.bus.service;


import java.lang.reflect.Field;


import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;




import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

//import sunwell.permaisuri.bus.dto.sales.AppliedSODiscountsDTO;
import sunwell.permaisuri.bus.dto.sales.SalesOrderItemDTO;
import sunwell.permaisuri.bus.exception.OperationException;
//import sunwell.permaisuri.bus.repository.AppliedSoDiscountsRepo;
import sunwell.permaisuri.bus.repository.CartDetailRepo;
import sunwell.permaisuri.bus.repository.OnHandStockRepo;
import sunwell.permaisuri.bus.repository.PaymentImageRepo;
import sunwell.permaisuri.bus.repository.SalesOrderItemRepo;
import sunwell.permaisuri.bus.repository.SalesInvoiceItemRepo;
import sunwell.permaisuri.bus.repository.SalesInvoiceRepo;
import sunwell.permaisuri.bus.repository.SalesOrderRepo;
import sunwell.permaisuri.bus.repository.SellPriceLevelRepo;
import sunwell.permaisuri.bus.service.Filters.Filter;
import sunwell.permaisuri.bus.spec.GenericSpecification;
import sunwell.permaisuri.bus.spec.SalesInvoiceSpecification;
import sunwell.permaisuri.bus.spec.SalesOrderSpecification;
import sunwell.permaisuri.bus.util.Util;
import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.inventory.ItemCategory;
import sunwell.permaisuri.core.entity.inventory.Product;
import sunwell.permaisuri.core.entity.inventory.ProductImage;
import sunwell.permaisuri.core.entity.inventory.ProductSellPrice;
import sunwell.permaisuri.core.entity.inventory.SellPriceLevel;
//import sunwell.permaisuri.core.entity.sales.AppliedSODiscounts;
import sunwell.permaisuri.core.entity.sales.CartDetail;
import sunwell.permaisuri.core.entity.sales.CartDetailPK;
//import sunwell.permaisuri.core.entity.sales.CustomerTest;
//import sunwell.permaisuri.core.entity.sales.CustomerTest2;
import sunwell.permaisuri.core.entity.sales.Payment;
import sunwell.permaisuri.core.entity.sales.PaymentImage;
import sunwell.permaisuri.core.entity.sales.SalesInvoice;
import sunwell.permaisuri.core.entity.sales.SalesInvoiceItem;
import sunwell.permaisuri.core.entity.sales.SalesInvoiceItemPK;
import sunwell.permaisuri.core.entity.sales.SalesOrder;
import sunwell.permaisuri.core.entity.sales.SalesOrderItem;
import sunwell.permaisuri.core.entity.sales.SalesOrderItemPK;
import sunwell.permaisuri.core.entity.warehouse.Gudang;
import sunwell.permaisuri.core.entity.warehouse.OnHandStock;

@Service
@Transactional
@Validated
public class SalesSvc implements SalesService
{
	@Autowired
	InventoryService invSvc;
	
	@Autowired
	ProductService productSvc;
	
	@Autowired
	UserCredService userCredSvc;
	
	@Autowired
	SalesOrderRepo soRepo;
	
	@Autowired
	SalesInvoiceRepo siRepo;	
	
	@Autowired
	SalesOrderItemRepo soItemRepo;
	
	@Autowired
	SalesInvoiceItemRepo siItemRepo;
	
	@Autowired
	CartDetailRepo cdRepo;
	
	@Autowired
	PaymentImageRepo payImgRepo;
	
//	@PersistenceContext
//	EntityManager em;
	
	
//	@Autowired
//	AppliedSoDiscountsRepo asdRepo;	
	
	public SalesOrder findSalesOrder(@NotNull(message="{error_no_id}")Long _id) {
		return soRepo.findById(_id).orElse(null); 
	}
	
	public Page<SalesOrder> findAllSalesOrder(Pageable _page) {
		return soRepo.findAll(_page);
	}
	
	public Page<SalesOrder> findSalesOrdersByCustId(Long _id, Pageable _page) {
		return soRepo.findByCustomer_SystemId(_id, _page);
	}
	
	public Page<SalesOrder> findSalesOrders(Filters _f, Pageable _page) throws Exception {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery cq = cb.createQuery(SalesOrder.class);
//		Root<SalesOrder> root = cq.from(SalesOrder.class);
//		Predicate p = cb.like(root.get("customer").get("userCredential").<String>get("userName"), "%john%");
//		cq.where(p);
//		TypedQuery<SalesOrder> tq = em.createQuery(cq);
//		List<SalesOrder> listSO = tq.getResultList();
//		System.out.println("SIZE Of SO: " + listSO.size());
		return soRepo.findAll(new SalesOrderSpecification(_f), _page);
	}
	
	public List<SalesOrder> findAllSalesOrder() {
		return soRepo.findAll();
	}
	
	public SalesOrder addSalesOrder(
    		@Valid @NotNull(message="{error_no_so}") SalesOrder _so) 
    {
		prepareSalesOrder(_so);
		return soRepo.save(_so);

//		Customer cust = userCredSvc.findCustomer(_so.getCustomer().getSystemId());
//		if(cust == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
//		_so.setCustomer(cust);
//		
//		// untuk relasi one to many yang cascade persist, kalau relasi tersebut meiliki relasi
//		// lain lagi, maka objek relasi tersebut harus objek yang dimanage JPA
//		if(_so.getItems() != null && _so.getItems().size() > 0) {
//        	for(SalesOrderItem sItem : _so.getItems()) {
//        		System.out.println("SItem id: " + sItem.getItem().getSystemId() + " name: " + sItem.getItem().getName());
//        		Item item = productSvc.findItemByName(sItem.getItem().getName());
//        		sItem.setItem(item);
//        	}
//        }	
    }
    
	public SalesOrder editSalesOrder(
    		@Valid @NotNull(message="{error_no_so}") SalesOrder _so) 
    {
		SalesOrder so = soRepo.findById(_so.getSystemId()).orElse(null);
		if(so == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_ORDER, null);
		prepareSalesOrder(_so);
		
		if(_so.getItems() != null && _so.getItems().size () > 0) {
            for (SalesOrderItem sItem : _so.getItems()) {
                sItem.setSalesOrder(so);
            }
        }
		
		if(so.getItems() != null) {
			soItemRepo.deleteAll(so.getItems()); // mesti didelete dulu kalau objek ug diambil pakai spring data jpa
			so.setItems(null);
			soItemRepo.flush();
		}
		
		so.setCustomer(_so.getCustomer());
		so.setItems(_so.getItems());
		so.setIssueDate(_so.getIssueDate());
		so.setDeliveryStatus(_so.getDeliveryStatus());
		so.setDiscount(_so.getDiscount());
		so.setDiscountMemo(_so.getDiscountMemo());
		so.setMiscCharges(_so.getMiscCharges());
		so.setMiscChargesMemo(_so.getMiscChargesMemo());
		so.setMemo(_so.getMemo());
		so.setVAT(_so.getVAT());
		so.setVATInclusive(_so.isVATInclusive());
		so.setCanceledStatus(_so.getCanceledStatus());
		so.setPaymentStatus(_so.getPaymentStatus());
		so.setPromoCodeUsed(_so.getPromoCodeUsed());
		so.setShippingLine(_so.getShippingLine());
		
		return so;
		
		// untuk relasi one to many yang cascade persist, kalau relasi tersebut meiliki relasi
		// lain lagi, maka objek relasi tersebut harus objek yang dimanage JPA
//		if(so.getItems() != null && so.getItems().size () > 0) {
//            items = new LinkedList<>();
//            for (SalesOrderItem sItem : _so.getItems()) {
//        		Item item = productSvc.findItemByName(sItem.getItem().getName());
//        		sItem.setItem(item);
//                sItem.setSalesOrder(so);
//                items.add(sItem);
//            }
//        }
		
//		if(so.getDiscounts() != null && so.getDiscounts().size () > 0) {
//            discounts = new LinkedList<>();
//            for (AppliedSODiscounts sDisc : _so.getDiscounts()) {
//                sDisc.setSalesOrder(so);
//                discounts.add(sDisc);
//            }
//        }
		
		
		
//		if(so.getDiscounts() != null) {
//			asdRepo.delete(so.getDiscounts()); // mesti didelete dulu kalau objek ug diambil pakai spring data jpa
//			so.setDiscounts(null);
//			asdRepo.flush();
//		}
		
		
    }
    
    public SalesOrder deleteSalesOrder(@NotNull(message="{error_no_id}") Long _id) {
    	SalesOrder so = soRepo.findById(_id).orElse(null);
		if(so == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_ORDER, null);
	    soRepo.delete(so);
	    return so;
    }
	
	public SalesOrderItem findSOItem(@NotNull(message="{error_no_id}")SalesOrderItemPK _pk) {
		return soItemRepo.findById(_pk).orElse(null);
	}
	
	public Page<SalesOrderItem> findSOItemBySalesOrder(SalesOrder _so, Pageable _page) {
		return soItemRepo.findBySalesOrder(_so, _page);
	}
	
	public SalesInvoice findSalesInvoice(@NotNull(message="{error_no_id}")Long _id) {
		return siRepo.findById(_id).orElse(null);
	}
	
	public SalesInvoice findSalesInvoiceByNo(String _no) {
		return siRepo.findByInvoiceNo(_no);
	}
	
	public Page<SalesInvoice> findAllSalesInvoice(Pageable _page) {
		return siRepo.findAll(_page);
	}
	
	public Page<SalesInvoice> findSalesInvoicesByCustId(Long _id, Pageable _page) {
		return siRepo.findByCustomer_SystemId(_id, _page);
	}
	
	public Page<SalesInvoice> findSalesInvoices(Filters _f, Pageable _page) throws Exception {
		return siRepo.findAll(new SalesInvoiceSpecification(_f), _page);
	}
	
	public SalesInvoice deleteSalesInvoice(@NotNull(message="{error_no_id}") Long _id) {
    	SalesInvoice si = siRepo.findById(_id).orElse(null);
		if(si == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_INVOICE, null);
	    siRepo.delete(si);
	    return si;
    }
	
	public SalesInvoiceItem findSalesInvoiceItem(@NotNull(message="{error_no_id}")SalesInvoiceItemPK _pk) {
		return siItemRepo.findById(_pk).orElse(null);
	}
	
	public Page<SalesInvoiceItem> findSalesInvoiceItemByParent(SalesInvoice _si, Pageable _page) {
		return siItemRepo.findByParent(_si, _page);
	}
		
	public CartDetail findCartDetail(@NotNull(message="{error_no_id}")CartDetailPK _pk) {
		return cdRepo.findById(_pk).orElse(null);
	}	
	
	public Page<CartDetail> findCartDetailByCustomer(Customer _cs, Pageable _page) {
		return cdRepo.findByCustomer(_cs, _page);
	}
	
	public Page<CartDetail> findCartDetailByCustomerId(Long _cs, Pageable _page) {
		return cdRepo.findByCustomer_SystemId(_cs, _page);
	}
	
	public Page<CartDetail> findCartDetails(Filters _f, Pageable _page) throws Exception {
		return cdRepo.findAll(new GenericSpecification<CartDetail>(_f, CartDetail.class), _page);
	}
	
	// note fungsi ini bisa juga untuk mengedit cart detail yang sudah ada
    public CartDetail addCartDetail(
    		@Valid @NotNull(message="{error_no_cart_detail}") CartDetail _cartDetail) 
    {
    	prepareCartDetail(_cartDetail);
		return cdRepo.save(_cartDetail);
//    	// sama seperti relasi dari relasi one to many yang harus sudah managed
//    	// kemungkinan karena ini adalah id maka juga harus sudah managed
//    	Customer cust = userCredSvc.findCustomer(_cartDetail.getCustomer().getSystemId());
//		if(cust == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
//		
//		Item item = productSvc.findItem(_cartDetail.getItem().getSystemId());
//		if(item == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//		
//		_cartDetail.setCustomer(cust);
//		_cartDetail.setItem(item);
    	
    }
    
    public CartDetail editCartDetail(
    		@Valid @NotNull(message="{error_no_cart_detail}") CartDetail _cartDetail) 
    {
//    	Item item = null;
//    	if(_cartDetail.getItem().getSystemId() > 0)
//    		item = productSvc.findItem(_cartDetail.getItem().getSystemId());
//    	else if(_cartDetail.getItem().getName() != null)
//    		item = productSvc.findItemByName(_cartDetail.getItem().getName());
//    	if(item == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
    	CartDetail cd = cdRepo.findById(new CartDetailPK(_cartDetail.getCustomer().getSystemId(), _cartDetail.getItem().getSystemId()))
    			.orElse(null);
		if(cd == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CART_DETAIL, null);
		
		prepareCartDetail(_cartDetail);
		return cdRepo.save(_cartDetail);
		
//		// sama seperti relasi dari relasi one to many yang harus sudah managed
//    	// kemungkinan karena ini adalah id maka juga harus sudah managed
//		
//		Customer cust = userCredSvc.findCustomer(_cartDetail.getCustomer().getSystemId());
//		if(cust == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
//		
//		Item item = productSvc.findItem(_cartDetail.getItem().getSystemId());
//		if(item == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//		
//		_cartDetail.setCustomer(cust);
//		_cartDetail.setItem(item);
//		
//		return cdRepo.save(_cartDetail);
    }
    
    public CartDetail deleteCartDetail(
    		@NotNull(message="{error_no_id}") Long _custId,
    		@NotNull(message="{error_no_id}") Integer _itemId) 
    {
    	CartDetail cd = cdRepo.findById(new CartDetailPK(_custId, _itemId)).orElse(null);
//    	System.out.println("CustId: " + _custId + " itemId: " + _itemId + " cd: " + cd.getCustomer().getFirstName());
		if(cd == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CART_DETAIL, null);
		List<CartDetail> cds = cd.getCustomer().getCartDetails();
		cds.remove(cd);
	    cdRepo.delete(cd);
	    return cd;
    }
    
    public PaymentImage findPaymentImage(@NotNull(message="{error_no_si}")SalesInvoice _si) {
		return payImgRepo.findById(_si.getSystemId()).orElse(null);
	}
    
    public PaymentImage findPaymentImageByInvoiceId(@NotNull(message="{error_no_id}")Long _id) {
		return payImgRepo.findBySalesInvoice_SystemId(_id);
	}
    
    public PaymentImage addPaymentImage(@Valid @NotNull(message="{error_no_payment_image}") PaymentImage _pi) {
    	preparePaymentImage(_pi);
		return payImgRepo.save(_pi);
	}
    
    public PaymentImage editPaymentImage(@Valid @NotNull(message="{error_no_payment_image}") PaymentImage _pi) {
    	PaymentImage pi = payImgRepo.findBySalesInvoice(_pi.getSalesInvoice());
		if(pi == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PAYMENT_IMAGE, null);
		
		preparePaymentImage(_pi);
		
		payImgRepo.save(pi);
		return pi;
	}
    
    public PaymentImage deletePaymentImage(@NotNull(message="{error_no_si}")SalesInvoice _si) {
    	PaymentImage pi = payImgRepo.findById(_si.getSystemId()).orElse(null);
		if(pi == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PAYMENT_IMAGE, null);
		payImgRepo.delete(pi);
	    return pi;
    }
	
//	public SalesInvoice findSalesInvoiceBySoNo(String _no) {
//		return siRepo.findBySoNo(_no);
//	}
	
//	public SalesOrder findSalesOrderByNo(String _no) {
//		return soRepo.findByNo(_no);
//	}
	
	public SalesInvoice createPayment(
    		@Valid @NotNull(message="{error_no_payment}") Payment _payment) 
    {
		SalesOrder so = findSalesOrder(_payment.getSoId());
		List<SalesInvoiceItem> items = new LinkedList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(so == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_ORDER, null);
		
		SalesInvoice si = new SalesInvoice();
		si.setCustomer(so.getCustomer());
		si.setDeliveryStatus(so.getDeliveryStatus());
		si.setDiscount(so.getDiscount());
		si.setDiscountMemo(so.getDiscountMemo());
		si.setIssueDate(so.getIssueDate());
		si.setMemo(so.getMemo());
		si.setMiscCharges(so.getMiscCharges());
		si.setMiscChargesMemo(so.getMiscChargesMemo());
		si.setPaymentStatus(so.getPaymentStatus());
		si.setPromoCodeUsed(so.getPromoCodeUsed());
		si.setShippingLine(so.getShippingLine());
		si.setCanceledStatus(so.getCanceledStatus());
		si.setVAT(so.getVAT());
		si.setVATInclusive(so.isVATInclusive());
		si.setNoFakPajak(_payment.getNoFakPajak());
		si.setInvoiceNo(sdf.format(Calendar.getInstance().getTime()));
		si.setPaymentAmount(_payment.getPaymentAmount());
		si.setPaymentDetail(_payment.getPaymentDetail());
		si.setPaymentType(_payment.getPaymentType());
		
		
		// untuk relasi one to many yang cascade persist, kalau relasi tersebut meiliki relasi
		// lain lagi, maka objek relasi tersebut harus objek yang dimanage JPA
		if(so.getItems() != null && so.getItems().size() > 0) {
        	for(SalesOrderItem sItem : so.getItems()) {
        		SalesInvoiceItem siItem = new SalesInvoiceItem();
        		siItem.setDiscount(sItem.getDiscount());
        		siItem.setHargaJual(sItem.getHargaJual());
        		siItem.setQty(sItem.getQty());
        		siItem.setItem(sItem.getItem());
        		siItem.setParent(si);
        		items.add(siItem);
        	}
        }	
		
		si.setItems(items);
		soRepo.deleteById(so.getSystemId());
		siRepo.save(si);
		
		if(_payment.getImageData() != null) {
			PaymentImage pi = new PaymentImage();
			pi.setSalesInvoice(si);
			pi.setName("" + si.getSystemId());
			pi.setImageData(Base64.getDecoder ().decode (_payment.getImageData()));
			addPaymentImage(pi);
		}
		
//		if(_pi != null)
//			addPaymentImage(_pi);
		return si;
    }
    
	public SalesInvoice editPayment(
    		@Valid @NotNull(message="{error_no_payment}") Payment _payment) 
    {
		SalesInvoice si = findSalesInvoiceByNo(_payment.getInvoiceNo());
		if(si == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_INVOICE, null);
		
		si.setNoFakPajak(_payment.getNoFakPajak());
		si.setInvoiceNo(_payment.getInvoiceNo());
		si.setPaymentAmount(_payment.getPaymentAmount());
		si.setPaymentDetail(_payment.getPaymentDetail());
		si.setPaymentType(_payment.getPaymentType());
		if(_payment.getImageData() != null) {
			PaymentImage pi = new PaymentImage();
			pi.setSalesInvoice(si);
			pi.setName("" + si.getSystemId());
			pi.setImageData(Base64.getDecoder ().decode (_payment.getImageData()));
			editPaymentImage(pi);
		}
		else {
			PaymentImage pi = findPaymentImage(si);
			if(pi != null)
				deletePaymentImage(si);
		}
//		if(_pi != null)
//			editPaymentImage(_pi);
//		else
//			deletePaymentImage(_pi.getSalesInvoice());
		
		return si;
    }
    
    public SalesOrder checkOut(@NotNull(message="{error_no_customer}") Customer _cust) {
    	if(_cust.getCartDetails() == null || _cust.getCartDetails().size() <= 0 ) {
    		throw new OperationException(StandardConstant.ERROR_NO_ITEM_IN_THE_CART, null);
    	}
    	// mesti diambil ulang customernya karena ada kemungkinan belum dimanage
    	Customer cust = userCredSvc.findCustomer(_cust.getSystemId());
    	if(cust == null)
    		throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
    	
    	Gudang defWarehouse = invSvc.findDefaultWarehouse();
    	if(defWarehouse == null)
    		throw new OperationException(StandardConstant.ERROR_CANT_FIND_WAREHOUSE, null);
    	
    	SalesOrder so = new SalesOrder();
    	Calendar now = Calendar.getInstance();
    	List<SalesOrderItem> items = new LinkedList<>();
    	List<OnHandStock> removedStocks = new LinkedList<>();
    	List<CartDetail> cds = cust.getCartDetails();
    	cust.setCartDetails(null);
    	System.out.println("CDS SIZE: " + cds.size());
//    	for(CartDetail cd : cds) {
//    		System.out.println("CD ITEM : " + cd.getItem().getName() + " systemId: " + cd.getItem().getSystemId());
//    		cdRepo.delete(cd);
//    	}
    	cdRepo.deleteAll(cds);
    	
    	so.setCustomer(cust);
    	for(CartDetail cd : cds) {
    		SalesOrderItem soItem = new SalesOrderItem();
    		// mesti managed entity untuk relasi dari relasi yang dicascade persist
    		Item item = cd.getItem(); 
    		ProductSellPrice psp = item.getDefaultSellPrice();
    		soItem.setSalesOrder(so);
    		soItem.setItem(item);
    		soItem.setQty(cd.getQty());
    		soItem.setHargaJual(psp != null ? psp.getPrice() : 0);
    		items.add(soItem);
    		
//    		OnHandStock oh = new OnHandStock(item, defWarehouse, null, "", "", cd.getQty());
    		OnHandStock oh = new OnHandStock(item, defWarehouse, cd.getQty());
    		removedStocks.add(oh);
    	}
    	so.setItems(items);
    	soRepo.save(so);
    	
    	invSvc.removeOnHand(removedStocks);
    	
    	return so;
    }
    
//    @Async
//    public void testAsynch() {
//    	System.out.print("Async task executed: ");
//    	try {
//    		Thread.sleep(6000);
//    		System.out.print("Async task finished: ");
//    	}
//    	catch(InterruptedException e) {
//    		System.out.println("Error: " + e.getMessage());
//    		e.printStackTrace();
//    	}
//    }
    
    private void prepareSalesOrder(SalesOrder _so) {
    	Customer cust = userCredSvc.findCustomer(_so.getCustomer().getSystemId());
		if(cust == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
		_so.setCustomer(cust);
		
		// untuk relasi one to many yang cascade persist, kalau relasi tersebut meiliki relasi
		// lain lagi, maka objek relasi tersebut harus objek yang dimanage JPA
		if(_so.getItems() != null && _so.getItems().size() > 0) {
        	for(SalesOrderItem sItem : _so.getItems()) {
        		System.out.println("SItem id: " + sItem.getItem().getSystemId() + " name: " + sItem.getItem().getName());
        		Item item = productSvc.findItemByName(sItem.getItem().getName());
        		if(item == null)
        			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
        		sItem.setItem(item);
        	}
        }	
    }
    
    private void prepareCartDetail(CartDetail _cartDetail) {
    	// sama seperti relasi dari relasi one to many yang harus sudah managed
    	// kemungkinan karena ini adalah id maka juga harus sudah managed
    	Customer cust = userCredSvc.findCustomer(_cartDetail.getCustomer().getSystemId());
		if(cust == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
		
		Item item = null;
		if(_cartDetail.getItem().getSystemId() > 0)
			item = productSvc.findItem(_cartDetail.getItem().getSystemId());
		else if(_cartDetail.getItem().getName() != null)
			item = productSvc.findItemByName(_cartDetail.getItem().getName());
		if(item == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
		
		_cartDetail.setCustomer(cust);
		_cartDetail.setItem(item);
    }
    
    private void preparePaymentImage(PaymentImage _pi) {
    	SalesInvoice si = findSalesInvoice(_pi.getSalesInvoice().getSystemId());
    	if(si == null)
    		throw new OperationException(StandardConstant.ERROR_CANT_FIND_INVOICE, null);
    	
    	_pi.setSalesInvoice(si);
    }

//	@Override
//	public Page<SalesOrder> findSalesOrdersByCustId (Long _id, Pageable _page) throws Exception
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Page<SalesInvoice> findSalesInvoicesByCustId (Long _id, Pageable _page) throws Exception
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PaymentImage findPaymentImageByInvoiceId (Long _id)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
    
}
