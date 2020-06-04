package sunwell.permaisuri.bus.service;


import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

public interface SalesService
{
	public SalesOrder findSalesOrder(@NotNull(message="{error_no_id}") Long _id) throws Exception ;
	public Page<SalesOrder> findAllSalesOrder(Pageable _page) throws Exception ;
	public Page<SalesOrder> findSalesOrdersByCustId(Long _id, Pageable _page) throws Exception;
	public Page<SalesOrder> findSalesOrders(Filters _f, Pageable _page) throws Exception ;
	public List<SalesOrder> findAllSalesOrder() throws Exception ;
	public SalesOrder addSalesOrder(@Valid @NotNull(message="{error_no_so}") SalesOrder _so) throws Exception ;
	public SalesOrder editSalesOrder(@Valid @NotNull(message="{error_no_so}") SalesOrder _so) throws Exception ;
    public SalesOrder deleteSalesOrder(@NotNull(message="{error_no_id}") Long _id) throws Exception ;
	public SalesOrderItem findSOItem(@NotNull(message="{error_no_id}") SalesOrderItemPK _pk) throws Exception ;
	public Page<SalesOrderItem> findSOItemBySalesOrder(SalesOrder _so, Pageable _page) throws Exception ;
	public SalesInvoice findSalesInvoice(@NotNull(message="{error_no_id}") Long _id) throws Exception ;
	public SalesInvoice findSalesInvoiceByNo(String _no) throws Exception ;
	public Page<SalesInvoice> findAllSalesInvoice(Pageable _page) throws Exception ;
	public Page<SalesInvoice> findSalesInvoicesByCustId(Long _id, Pageable _page) throws Exception ;
	public Page<SalesInvoice> findSalesInvoices(Filters _f, Pageable _page) throws Exception ;
	public SalesInvoice deleteSalesInvoice(@NotNull(message="{error_no_id}") Long _id) throws Exception ;
	public SalesInvoiceItem findSalesInvoiceItem(@NotNull(message="{error_no_id}")SalesInvoiceItemPK _pk) throws Exception ;
	public Page<SalesInvoiceItem> findSalesInvoiceItemByParent(SalesInvoice _si, Pageable _page) throws Exception ;
	public CartDetail findCartDetail(@NotNull(message="{error_no_id}")CartDetailPK _pk) throws Exception ;
	public Page<CartDetail> findCartDetailByCustomer(Customer _cs, Pageable _page) throws Exception ;
	public Page<CartDetail> findCartDetailByCustomerId(Long _cs, Pageable _page) throws Exception ;
	public Page<CartDetail> findCartDetails(Filters _f, Pageable _page) throws Exception ;
    public CartDetail addCartDetail(@Valid @NotNull(message="{error_no_cart_detail}") CartDetail _cartDetail) throws Exception ;
    public CartDetail editCartDetail(@Valid @NotNull(message="{error_no_cart_detail}") CartDetail _cartDetail)  throws Exception ;
    public CartDetail deleteCartDetail(@NotNull(message="{error_no_id}") Long _custId, @NotNull(message="{error_no_id}")Integer _itemId) throws Exception ;
	public SalesInvoice createPayment(@Valid @NotNull(message="{error_no_payment}")Payment _payment) throws Exception ;
	public SalesInvoice editPayment(@Valid @NotNull(message="{error_no_payment}") Payment _payment) throws Exception ;
    public SalesOrder checkOut(@NotNull(message="{error_no_customer}")Customer _cust) throws Exception;
    public PaymentImage findPaymentImage(@NotNull(message="{error_no_si}")SalesInvoice _si) ;
    public PaymentImage findPaymentImageByInvoiceId(@NotNull(message="{error_no_id}")Long _id) ;
    public PaymentImage addPaymentImage(@Valid @NotNull(message="{error_no_payment_image}") PaymentImage _pi);
    public PaymentImage editPaymentImage(@Valid @NotNull(message="{error_no_payment_image}") PaymentImage _pi) ;
    public PaymentImage deletePaymentImage(@NotNull(message="{error_no_si}")SalesInvoice _si) ;
//    public void testAsynch() throws Exception ;    
}
