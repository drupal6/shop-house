package shop.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.beam.Product;
import shop.dao.DaoFactory;

/**
 * 商品源
 * @author Administrator
 *
 */
public class ProductProvider {

	private static ProductProvider instance = new ProductProvider();
	
	public static ProductProvider getInst() {
		return instance;
	}
	
	private Map<Integer, Product> map = new HashMap<Integer, Product>();
	
	public boolean init() {
		map.clear();
		List<Product> list = DaoFactory.getInst().getProductDao().getProductList();
		for(Product product : list) {
			map.put(product.getId(), product);
		}
		return true;
	}
	
	public List<Product> list() {
		List<Product> ret = new ArrayList<Product>();
		ret.addAll(map.values());
		Collections.sort(ret, new Comparator<Product>() {
			public int compare(Product arg0, Product arg1) {
				return arg0.getId() - arg1.getId();
			}
		});
		return ret;
	}
	public List<Product> list(int productType) {
		List<Product> ret = new ArrayList<Product>();
		for(Product product : map.values()) {
			if(product.getProductType() == productType) {
				ret.add(product);
			}
		}
		Collections.sort(ret, new Comparator<Product>() {
			public int compare(Product arg0, Product arg1) {
				return arg0.getId() - arg1.getId();
			}
		});
		return ret;
	}
	
	public Product get(int id) {
		return map.get(id);
	}
	
	public Product add(Product product) {
		boolean ret = DaoFactory.getInst().getProductDao().createProduct(product);
		if(ret) {
			map.put(product.getId(), product);
			return product;
		}
		return null;
	}
	
	public boolean update(Product product) {
		boolean ret = DaoFactory.getInst().getProductDao().updateProduct(product);
		return ret;
	}
	
	public boolean del(int id) {
		Product product = get(id);
		if(product == null) {
			return true;
		}
		product.setState(1);
		boolean ret = update(product);
		if(ret) {
			map.remove(id);
		}
		return ret;
	}
}
