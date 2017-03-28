package shop.provider;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import shop.beam.ProductOutInfo;
import shop.dao.DaoFactory;

/**
 * 出售记录源
 * @author Administrator
 *
 */
public class ProductOutInfoProvider {

	private static ProductOutInfoProvider instance = new ProductOutInfoProvider();
	
	private static  Vector<String> columnNameV = new Vector<String>();
	static{
		columnNameV.add("编号");
		columnNameV.add("产品");
		columnNameV.add("数量");
		columnNameV.add("原价");
		columnNameV.add("实价");
		columnNameV.add("总额");
		columnNameV.add("退货操作员");
		columnNameV.add("退货数量");
		columnNameV.add("退货时间");
		columnNameV.add("备注");
	}
	
	public static ProductOutInfoProvider getInst() {
		return instance;
	}
	
	
	public ProductOutInfo add(ProductOutInfo productOption) {
		boolean ret = DaoFactory.getInst().getProductOutInfoDao().createProductOutInfo(productOption);
		if(ret) {
			return productOption;
		}
		return null;
	}
	
	public boolean del(int id) {
		return DaoFactory.getInst().getProductOutInfoDao().del(id);
	}
	
	public ProductOutInfo update(ProductOutInfo productOption) {
		boolean ret = DaoFactory.getInst().getProductOutInfoDao().updateProductOutInfo(productOption);
		if(ret) {
			return productOption;
		}
		return null;
	}
	
	public List<ProductOutInfo> queryByOrderId(int orderId) {
		return DaoFactory.getInst().getProductOutInfoDao().getProductOutInfoList(orderId);
	}
	
	public List<ProductOutInfo> queryByOrder(int typeId, int productId, Date startTime, Date endTime) {
		return DaoFactory.getInst().getProductOutInfoDao().getProductOutInfoList(typeId, productId, startTime, endTime);
	}
	
	public ProductOutInfo getProductOutInfo(int id) {
		return DaoFactory.getInst().getProductOutInfoDao().getProductOutInfo(id);
	}
	
	public static Vector<String> getTitle() {
		return columnNameV;
	}
	
	public static Vector getListValue(List<ProductOutInfo> list) {
		Vector vv = new Vector();
		if(list != null) {
			for(ProductOutInfo type : list) {
				Vector rv = getValue(type);
				if(rv != null) {
					vv.add(rv);
				}
			}
		}
		return vv;
	}
	
	public static Vector getValue(ProductOutInfo outInfo) {
		if(outInfo == null) {
			return null;
		}
		Vector rowV = new Vector();
		rowV.add(outInfo.getId());
		rowV.add(ProductProvider.getInst().getNameById(outInfo.getProductId()));
		rowV.add(outInfo.getNum());
		rowV.add(outInfo.getPrice());
		rowV.add(outInfo.getPrice1());
		rowV.add(outInfo.getNum() * outInfo.getPrice1());
		if(outInfo.getUserId() > 0) {
			rowV.add(outInfo.getUserId());
			rowV.add(outInfo.getReturnNum());
			rowV.add(outInfo.getReturnTime());
			rowV.add(outInfo.getReturnMark());
		}
		return rowV;
	}
}
