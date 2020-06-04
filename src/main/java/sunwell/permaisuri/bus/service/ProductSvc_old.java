//package sunwell.permaisuri.bus.service;
//
//import java.io.File;
//
//
//import java.util.Base64;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.annotation.Validated;
//
//import sunwell.permaisuri.bus.exception.OperationException;
//import sunwell.permaisuri.bus.repository.ItemCategoryRepo;
//import sunwell.permaisuri.bus.repository.ItemRepo;
//import sunwell.permaisuri.bus.repository.MerkRepo;
//import sunwell.permaisuri.bus.repository.MetricRepo;
//import sunwell.permaisuri.bus.repository.ProductSellPriceRepo;
//import sunwell.permaisuri.bus.repository.SellPriceLevelRepo;
//import sunwell.permaisuri.bus.util.Util;
//import sunwell.permaisuri.core.entity.customer.Customer;
//import sunwell.permaisuri.core.entity.inventory.Item;
//import sunwell.permaisuri.core.entity.inventory.ItemCategory;
//import sunwell.permaisuri.core.entity.inventory.Merk;
//import sunwell.permaisuri.core.entity.inventory.Metrics;
//import sunwell.permaisuri.core.entity.inventory.Product;
//import sunwell.permaisuri.core.entity.inventory.ProductSellPrice;
//import sunwell.permaisuri.core.entity.inventory.ProductSellPricePK;
//import sunwell.permaisuri.core.entity.inventory.SellPriceLevel;
//
//@Service
//@Transactional
//@Validated
//public class ProductSvc_old
//{	
//	@Autowired
//	ItemRepo itemRepo;
//	
//	@Autowired
//	ProductSellPriceRepo pspRepo;
//	
//	@Autowired
//	ItemCategoryRepo icRepo;
//	
//	@Autowired
//	MerkRepo merkRepo;
//	
//	@Autowired
//	MetricRepo metricRepo;;
//	
//	@Autowired
//	SellPriceLevelRepo splRepo;
//	
////	@Autowired
////	ItemShipmentInfoRepo shipmentRepo;	
//	
//	public Item findItem(Integer _id) {
//		return itemRepo.findOne(_id);
//	}
//	
//	public Item findItemByName(String _name) {
//		return itemRepo.findByName(_name);
//	}
//	
//	public Page<Item> findAllItems(Pageable _page) {
//		return itemRepo.findAll(_page);
//	}
//		
//	public Page<Item> findByCategory(ItemCategory _ic, Pageable _page) {
//		return itemRepo.findByCategoriesIn(_ic, _page);
//	}
//	
//	public Page<Item> findByCategoryId(Integer _id, Pageable _page) {
//		return itemRepo.findByCategories_SystemIdIn(_id, _page);
//	}
//	
//	public List<Item> findAllItems() {
//		return itemRepo.findAll();
//	}	
//	
//	public Item addItem(
//    		@Valid @NotNull(message="{error_no_item}") Item _i) 
//    {        
//		// karena nested attribute maka harus dimanage
//		// dari luar mungkin udah diambil entity utuh tapi jangan berasumsi entity tersebut sudah dimanage
//		// kalau diambil di kontroler maka kontroler tersebut tidak berada dalam transaksi sehingga entitynya bukan managed entity
//		
//		if(_i.getSellPrices() != null && _i.getSellPrices().size() > 0) {
//        	for(ProductSellPrice psp : _i.getSellPrices()) {
//        		System.out.println("Item: " + psp.getProduct().getName() + " sell price lv: " + psp.getPriceLevel().getSystemId());
//        		psp.setPriceLevel(splRepo.findOne(psp.getPriceLevel().getSystemId()));
//        	}
//        }
//        return itemRepo.save(_i);
//    }
//	
//	public Item editItem(
//    		@Valid @NotNull(message="{error_no_item}") Item _i) 
//    {
//		Item item = itemRepo.findOne(_i.getSystemId());
//		
//		if(item == null) 
//			throw new sunwell.permaisuri.bus.exception.OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);		
//		
//		if(item.getCategories() != null) {
//			item.setCategories(null);
//		}
//		
//		if(item.getSellPrices() != null) {
//			pspRepo.delete(item.getSellPrices());
//			item.setSellPrices(null);
//		}
//		
//		itemRepo.flush();		
//		
//		if(_i.getSellPrices() != null && _i.getSellPrices().size() > 0) {
//        	for(ProductSellPrice psp : _i.getSellPrices()) {
//        		System.out.println("Item: " + psp.getProduct().getName() + " sell price lv: " + psp.getPriceLevel().getSystemId());
//        		psp.setProduct(item);
//        		psp.setPriceLevel(splRepo.findOne(psp.getPriceLevel().getSystemId()));
//        	}
//        	item.setSellPrices(_i.getSellPrices());
//        }
//		
//		return item;
//		
////		return itemRepo.save(item);
//    }
//        
//    public Item deleteItem(@NotNull(message="{error_no_id}") Integer _id) {
//		try {
//    		Item item = itemRepo.findOne(_id);
//    		if(item == null) 
//    			throw new sunwell.permaisuri.bus.exception.OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//		    itemRepo.delete(_id);
//		    return item;
//		}
//		catch(IllegalArgumentException e) {
//			throw new OperationException(e, StandardConstant.ERROR_CANT_FIND_PRODUCT);
//		}
//    }
//	
//	public ItemCategory findCategory(Integer _id) {
//		return icRepo.findOne(_id);
//	}
//	
//	public ItemCategory findCategoryByName(String _name) {
//		return icRepo.findByName(_name);
//	}
//	
//	public ItemCategory findCategoryByCode(String _code) {
//		return icRepo.findByCode(_code);
//	}
//	
//	public Page<ItemCategory> findAllCategories(Pageable _page) {
//		return icRepo.findAll(_page);
//	}
//	
//	public List<ItemCategory> findAllCategories() {
//		return icRepo.findAll();
//	}
//	
//	public ItemCategory addCategory(
//    		@Valid @NotNull(message="{error_no_category}") ItemCategory _ctgr) 
//    {
//		return icRepo.save(_ctgr);
//    }
//    
//    public ItemCategory editCategory(
//    		@Valid @NotNull(message="{error_no_category}") ItemCategory _ctgr) 
//    {
//		ItemCategory ctgr = icRepo.findOne(_ctgr.getSystemId());
//		if(ctgr == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CATEGORY, null);
//		return icRepo.save(_ctgr);
//    }
//    
//    public ItemCategory deleteCategory(@NotNull(message="{error_no_id}") Integer _id) {
//    	ItemCategory ctgr = icRepo.findOne(_id);
//		if(ctgr == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CATEGORY, null);
//	    icRepo.delete(ctgr);
//	    return ctgr;
//    }
//    
//    public Merk findMerk(Integer _id) {
//		return merkRepo.findOne(_id);
//	}
//	
//	public Merk findMerkByName(String _name) {
//		return merkRepo.findByName(_name);
//	}
//	
//	public Page<Merk> findAllMerks(Pageable _page) {
//		return merkRepo.findAll(_page);
//	}
//	
//	public List<Merk> findAllMerks() {
//		return merkRepo.findAll();
//	}
//	
//	public Metrics findMetrics(String _name) {
//		return metricRepo.findOne(_name);
//	}
//	
//	public Page<Metrics> findAllMetrics(Pageable _page) {
//		return metricRepo.findAll(_page);
//	}
//	
//	public SellPriceLevel findSellPriceLevelByName(String _name) {
//		return splRepo.findByName(_name);
//	}
//		
//	
////	public Item addItem(
////    		@Valid @NotNull(message="{error_no_item}") Item _i, String _imgData, String _imgPath) 
////    {        
////        if(_imgData != null) {
////	    		String uploadedFileLocation = _imgPath + "items/" + _i.getAlternativeToItem();
////			File dir = new File(_imgPath + "items/");
////			if(!dir.exists ()) {
////			    dir.mkdir ();
////			}
////			
////	      Util.writeToFile(Base64.getDecoder ().decode (
////	        		_imgData), uploadedFileLocation);
////        }
////        
////        return itemRepo.save(_i);
////    }
//	
////	public ItemShipmentInfo findShipment(Integer _id) {
////		return shipmentRepo.findOne(_id);
////	}		
////	
////	public ItemShipmentInfo findShipmentByName(String _name) {
////		return shipmentRepo.findByName(_name);
////	}
////	
////	public List<ItemShipmentInfo> findAllShipments() {
////		return shipmentRepo.findAll();
////	}
////	
////	public Page<ItemShipmentInfo> findAllShipments(Pageable _page) {
////		return shipmentRepo.findAll(_page);
////	}
//	
////  public Item editItem(
////	@Valid @NotNull(message="{error_no_item}") Item _i, String _imgData, String _imgPath) 
////{
////Item item = itemRepo.findOne(_i.getSystemId());
////
////if(item == null) 
////	throw new sunwell.permaisuri.bus.exception.OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
////				
////if(_imgData != null) {
////		String uploadedFileLocation = _imgPath + "items/"  + _i.getImage();
////	File dir = new File(_imgPath + "products/");
////	if(!dir.exists ()) {
////	    dir.mkdir ();
////	}
////    Util.writeToFile(Base64.getDecoder ().decode (
////    		_imgData), uploadedFileLocation);
////}	
////
////if(item.getCategories() != null) {
////	item.setCategories(null);
////	itemRepo.flush();
////}
////
////return itemRepo.save(_i);
////}
//		
//	
//}
