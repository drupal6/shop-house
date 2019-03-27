package shop.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import shop.bean.ProductType;
import shop.bean.ProductUnit;
import shop.bean.TreeNode;
import shop.dao.DaoFactory;

/**
 * 商品单位源
 * @author Administrator
 *
 */
public class ProductUnitProvider {

	private static  Vector<String> columnNameV = new Vector<String>();
	static{
		columnNameV.add("编号");
		columnNameV.add("名称");
	}
	
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
	
	public ProductUnit getByName(String name) {
		 List<ProductUnit> list  = list();
		 for(ProductUnit unit : list) {
			 if(unit.getName().equals(name)) {
				 return unit;
			 }
		 }
		return null;
	}
	
	public String getNameById(int id) {
		ProductUnit unit = get(id);
		if(unit == null) {
			return "空";
		}
		return unit.getName();
	}
	
	public List<TreeNode> toTreeNodeList() {
		List<TreeNode> ret = new ArrayList<TreeNode>();
		List<ProductUnit> unitList = list();
		for(ProductUnit unit : unitList) {
			TreeNode node = new TreeNode(unit.getId(), 2, unit.getName());
			ret.add(node);
		}
		return ret;
	}
	
	public TreeNode toTreeNode(int id) {
		ProductUnit unit = get(id);
		TreeNode node = new TreeNode(unit.getId(), 2, unit.getName());
		return node;
	}
	
	public static Vector<String> getTitle() {
		return columnNameV;
	}
	
	public static Vector getListValue(List<ProductUnit> list) {
		Vector vv = new Vector();
		if(list != null) {
			for(ProductUnit unit : list) {
				Vector rv = getValue(unit);
				if(rv != null) {
					vv.add(rv);
				}
			}
		}
		return vv;
	}
	
	public static Vector getValue(ProductUnit productUnit) {
		if(productUnit == null) {
			return null;
		}
		Vector rowV = new Vector();
		rowV.add(productUnit.getId());
		rowV.add(productUnit.getName());
		return rowV;
	}
}
