package shop.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.beam.ProductUnit;
import shop.dao.DaoFactory;

/**
 * 商品单位源
 * @author Administrator
 *
 */
public class ProductUnitProvider {

	private static ProductUnitProvider instance = new ProductUnitProvider();
	
	public static ProductUnitProvider getInst() {
		return instance;
	}
	
	private Map<Integer, ProductUnit> map = new HashMap<Integer, ProductUnit>();
	
	public boolean init() {
		map.clear();
		List<ProductUnit> list = DaoFactory.getInst().getProductUnitDao().getProductUnitList();
		for(ProductUnit productUnit : list) {
			map.put(productUnit.getId(), productUnit);
		}
		return true;
	}
	
	public List<ProductUnit> list() {
		List<ProductUnit> ret = new ArrayList<ProductUnit>();
		ret.addAll(map.values());
		Collections.sort(ret, new Comparator<ProductUnit>() {
			public int compare(ProductUnit arg0, ProductUnit arg1) {
				return arg0.getId() - arg1.getId();
			}
		});
		return ret;
	}
	
	public ProductUnit get(int id) {
		return map.get(id);
	}
	
	public ProductUnit add(ProductUnit productUnit) {
		boolean ret = DaoFactory.getInst().getProductUnitDao().createProductUnit(productUnit);
		if(ret) {
			map.put(productUnit.getId(), productUnit);
			return productUnit;
		}
		return null;
	}
	
	public boolean update(ProductUnit productUnit) {
		boolean ret = DaoFactory.getInst().getProductUnitDao().updateProductUnit(productUnit);
		return ret;
	}
}
