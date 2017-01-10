package shop.provider;

import shop.beam.OutOrder;
import shop.dao.DaoFactory;

/**
 * 出售订单记录源
 * @author Administrator
 *
 */
public class ProductOutOrderProvider {

	private static ProductOutOrderProvider instance = new ProductOutOrderProvider();
	
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
}
