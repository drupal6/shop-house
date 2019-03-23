package shop.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import shop.beam.Product;
import shop.beam.TreeNode;
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
	
	public String getNameById(int id) {
		Product product = get(id);
		if(product == null) {
			return "";
		}
		return product.getName();
	}
	
	public List<Product> list(int productType) {
		if(productType == 0) {
			return list();
		}
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
	
	public Product getByName(String name) {
		 List<Product> list  = list();
		 for(Product product : list) {
			 if(product.getName().equals(name)) {
				 return product;
			 }
		 }
		return null;
	}
	
	public void productTotalChange(int id, float num) {
		Product product = get(id);
		if(product == null) {
			return;
		}
		product.setStock(product.getStock() + num);
		DaoFactory.getInst().getProductDao().updateProduct(product);
	}
	
	public List<TreeNode> getTreeNode(int productType) {
		List<TreeNode> ret = new ArrayList<TreeNode>();
		 List<Product> list = list(productType);
		 for(Product product : list) {
			 ret.add(new TreeNode(product.getId(), 3, product.getName()));
		 }
		 return ret;
	}
	
	private static  Vector<String> columnNameV = new Vector<String>();
	static{
		columnNameV.add("编号");
		columnNameV.add("名称");
		columnNameV.add("单位");
		columnNameV.add("出售价");
		columnNameV.add("库存");
	}
	
	public static Vector<String> getTitle() {
		return columnNameV;
	}
	
	public static Vector getListValue(List<Product> list) {
		Vector vv = new Vector();
		if(list != null) {
			for(Product product : list) {
				Vector rv = getValue(product);
				if(rv != null) {
					vv.add(rv);
				}
			}
		}
		return vv;
	}
	
	public static Vector getValue(Product product) {
		if(product == null) {
			return null;
		}
		Vector rowV = new Vector();
		rowV.add(product.getId());
		rowV.add(product.getName());
		rowV.add(product.getProductUnit());
		rowV.add(product.getOutPrice());
		rowV.add(product.getStock());
		return rowV;
	}
}
