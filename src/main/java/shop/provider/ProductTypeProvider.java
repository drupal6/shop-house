package shop.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import shop.beam.ProductType;
import shop.beam.TreeNode;
import shop.dao.DaoFactory;

/**
 * 商品类别源
 * @author Administrator
 *
 */
public class ProductTypeProvider {

	private static  Vector<String> columnNameV = new Vector<String>();
	static{
		columnNameV.add("编号");
		columnNameV.add("名称");
		columnNameV.add("状态");
	}
	
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
	
	public ProductType getByName(String name) {
		 List<ProductType> list  = list();
		 for(ProductType type : list) {
			 if(type.getName().equals(name)) {
				 return type;
			 }
		 }
		return null;
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
	
	public List<TreeNode> toTreeNodeList() {
		List<TreeNode> ret = new ArrayList<TreeNode>();
		List<ProductType> typeList = list();
		for(ProductType type : typeList) {
			TreeNode node = new TreeNode(type.getId(), 1, type.getName());
			ret.add(node);
		}
		return ret;
	}
	
	public TreeNode toTreeNode(int id) {
		ProductType type = get(id);
		TreeNode node = new TreeNode(type.getId(), 1, type.getName());
		return node;
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
	
	public static Vector<String> getTitle() {
		return columnNameV;
	}
	
	public static Vector getListValue(List<ProductType> list) {
		Vector vv = new Vector();
		if(list != null) {
			for(ProductType type : list) {
				Vector rv = getValue(type);
				if(rv != null) {
					vv.add(rv);
				}
			}
		}
		return vv;
	}
	
	public static Vector getValue(ProductType productType) {
		if(productType == null) {
			return null;
		}
		Vector rowV = new Vector();
		rowV.add(productType.getId());
		rowV.add(productType.getName());
		rowV.add(productType.getState());
		return rowV;
	}
}
