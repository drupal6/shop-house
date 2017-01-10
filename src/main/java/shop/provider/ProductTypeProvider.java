package shop.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.beam.ProductType;
import shop.dao.DaoFactory;

/**
 * 商品类别源
 * @author Administrator
 *
 */
public class ProductTypeProvider {

	private static ProductTypeProvider instance = new ProductTypeProvider();
	
	public static ProductTypeProvider getInst() {
		return instance;
	}
	
	private Map<Integer, ProductType> map = new HashMap<Integer, ProductType>();
	
	public boolean init() {
		map.clear();
		List<ProductType> list = DaoFactory.getInst().getProductTypeDao().getProductTypeList();
		for(ProductType productType : list) {
			map.put(productType.getId(), productType);
		}
		return true;
	}
	
	public List<ProductType> list() {
		List<ProductType> ret = new ArrayList<ProductType>();
		ret.addAll(map.values());
		Collections.sort(ret, new Comparator<ProductType>() {
			public int compare(ProductType arg0, ProductType arg1) {
				return arg0.getId() - arg1.getId();
			}
		});
		return ret;
	}
	
	public ProductType get(int id) {
		return map.get(id);
	}
	
	public ProductType add(ProductType productType) {
		boolean ret = DaoFactory.getInst().getProductTypeDao().createProductType(productType);
		if(ret) {
			map.put(productType.getId(), productType);
			return productType;
		}
		return null;
	}
	
	public boolean update(ProductType productType) {
		boolean ret = DaoFactory.getInst().getProductTypeDao().updateProductType(productType);
		return ret;
	}
	
	public boolean del(int id) {
		ProductType productType = get(id);
		if(productType == null) {
			return true;
		}
		productType.setState(1);
		boolean ret = update(productType);
		if(ret) {
			map.remove(id);
		}
		return ret;
	}
}
