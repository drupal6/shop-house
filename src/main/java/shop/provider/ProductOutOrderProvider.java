package shop.provider;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import shop.Constance;
import shop.beam.OutOrder;
import shop.beam.Product;
import shop.beam.ProductOutInfo;
import shop.dao.DaoFactory;

/**
 * 出售订单记录源
 * @author Administrator
 *
 */
public class ProductOutOrderProvider {

	private static ProductOutOrderProvider instance = new ProductOutOrderProvider();
	
	private static  Vector<String> columnNameV = new Vector<String>();
	static{
		columnNameV.add("编号");
		columnNameV.add("出单时间");
		columnNameV.add("金额");
		columnNameV.add("操作员");
		columnNameV.add("备注");
	}
	
	
	public static ProductOutOrderProvider getInst() {
		return instance;
	}
	
	public OutOrder add(OutOrder inOrder) {
		boolean ret = DaoFactory.getInst().getOutOrderDao().createOutOrder(inOrder);
		if(ret) {
			return inOrder;
		}
		return null;
	}
	
	public OutOrder update(OutOrder inOrder) {
		boolean ret = DaoFactory.getInst().getOutOrderDao().updateOutOrder(inOrder);
		if(ret) {
			return inOrder;
		}
		return null;
	}
	
	public List<OutOrder> query(Date startTime, Date endTime, int userId) {
		 return DaoFactory.getInst().getOutOrderDao().getoutOrderList(startTime, endTime, userId);
	}
	public OutOrder query(int orderId) {
		return DaoFactory.getInst().getOutOrderDao().getoutOrdert(orderId);
	}
	
	public static Vector<String> getTitle() {
		return columnNameV;
	}
	
	public boolean delOrder(int orderId, int userId) {
		OutOrder outOrder = query(orderId);
		if(outOrder == null || outOrder.getState() == 2) {
			return true;
		}
		outOrder.setState(2);
		outOrder.setCash(0);
		DaoFactory.getInst().getOutOrderDao().updateOutOrder(outOrder);
		
		Date now = new Date();
		
		List<ProductOutInfo> productOutInfoList = ProductOutInfoProvider.getInst().queryByOrderId(orderId);
		for(ProductOutInfo productInfo : productOutInfoList) {
			productInfo.setState(2);
			productInfo.setUserId(userId);
			productInfo.setOpTime(now);
			productInfo.setReturnNum(productInfo.getNum());
			ProductOutInfoProvider.getInst().update(productInfo);
			ProductProvider.getInst().productTotalChange(productInfo.getProductId(), productInfo.getNum());
		}
		return true;
	}
	
	public static Vector getListValue(List<OutOrder> list) {
		Vector vv = new Vector();
		if(list != null) {
			for(OutOrder type : list) {
				Vector rv = getValue(type);
				if(rv != null) {
					vv.add(rv);
				}
			}
		}
		return vv;
	}
	
	public static Vector getValue(OutOrder outOrder) {
		if(outOrder == null) {
			return null;
		}
		Vector rowV = new Vector();
		rowV.add(outOrder.getId());
		rowV.add(Constance.dateFormt.format(outOrder.getOpTime()));
		rowV.add(outOrder.getCash());
		rowV.add(outOrder.getUserId());
		rowV.add(outOrder.getMark());
		return rowV;
	}
}
