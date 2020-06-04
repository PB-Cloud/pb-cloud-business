package sunwell.permaisuri.bus.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sunwell.permaisuri.bus.exception.OperationException;
import sunwell.permaisuri.bus.spec.GenericSpecification;
import sunwell.permaisuri.core.entity.inventory.Item;
import sunwell.permaisuri.core.entity.inventory.ItemCategory;
import sunwell.permaisuri.core.entity.inventory.Merk;
import sunwell.permaisuri.core.entity.inventory.Metrics;
import sunwell.permaisuri.core.entity.inventory.Product;
import sunwell.permaisuri.core.entity.inventory.ProductImage;
import sunwell.permaisuri.core.entity.inventory.ProductSellPrice;
import sunwell.permaisuri.core.entity.inventory.SellPriceLevel;

public interface ProductService
{
	public Item findItem(@NotNull(message="{error_no_id}") Integer _id) ;
	public Item findItemByName(String _name) ;
	public Page<Item> findAllItems(Pageable _page) ;
	public Page<Item> findItems(Filters _f, Pageable _page) throws Exception ;
	public Page<Item> findByCategory(ItemCategory _ic, Pageable _page) ;
	public Page<Item> findByCategoryId(Integer _id, Pageable _page) ;
	public List<Item> findAllItems() ;
	public Item addItem(
    		@Valid @NotNull(message="{error_no_item}") Item _i, ProductImage _pi) ;
	public Item editItem(
    		@Valid @NotNull(message="{error_no_item}") Item _i, ProductImage _pi);   
    public Item deleteItem(@NotNull(message="{error_no_id}") Integer _id) ;
	public ItemCategory findCategory(@NotNull(message="{error_no_id}") Integer _id) ;
	public ItemCategory findCategoryByName(String _name) ;
	public ItemCategory findCategoryByCode(String _code) ;
	public Page<ItemCategory> findAllCategories(Pageable _page) ;
	public Page<ItemCategory> findCategories(Filters _f, Pageable _page) throws Exception ;
	public List<ItemCategory> findAllCategories() ;
	public ItemCategory addCategory(
    		@Valid @NotNull(message="{error_no_category}") ItemCategory _ctgr) ;
    public ItemCategory editCategory(
    		@Valid @NotNull(message="{error_no_category}") ItemCategory _ctgr) ;
    public ItemCategory deleteCategory(@NotNull(message="{error_no_id}") Integer _id) ;
    public Merk findMerk(@NotNull(message="{error_no_id}")Integer _id) ;
	public Merk findMerkByName(String _name) ;
	public Page<Merk> findMerks(Filters _f, Pageable _page) throws Exception ;
	public Page<Merk> findAllMerks(Pageable _page) ;
	public Merk addMerk(
    		@Valid @NotNull(message="{error_no_merk}") Merk _merk) ;
    public Merk editMerk(
    		@Valid @NotNull(message="{error_no_category}") Merk _merk) ;
    public Merk deleteMerk(@NotNull(message="{error_no_id}") Integer _id) ;
    public ProductImage findProductImage(@NotNull(message="{error_no_product}")Product _p);
    public ProductImage findProductImageByProductName(@NotNull(message="{error_no_name}")String _p) ;
    public ProductImage addProductImage(@Valid @NotNull(message="{error_no_product_image}") ProductImage _pi) ;
    public ProductImage editProductImage(@Valid @NotNull(message="{error_no_product_image}") ProductImage _pi) ;
    public ProductImage deleteProductImage(@NotNull(message="{error_no_product}")Product _p);
	public List<Merk> findAllMerks() ;
	public Metrics findMetrics(String _name) ;
	public Page<Metrics> findAllMetrics(Pageable _page) ;
	public SellPriceLevel findSellPriceLevelByName(String _name) ;
}
