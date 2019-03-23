package shop.provider;

import shop.beam.InOrder;
import shop.dao.DaoFactory;

/**
 * 进货订单记录源
 * @author Administrator
 *
 */
public class ProductInOrderProvider {

	private static ProductInOrderProvider instance = new ProductInOrderProvider();
	
	public static ProductInOrderProvider getInst() {
		return instance;
	}
	
	
	public InOrder add(InOrder inOrder) {
		boolean ret = DaoFactory.getInst().getInOrderDao().createInOrder(inOrder);
		if(ret) {
			return inOrder;
		}
		return null;
	}
	
	public InOrder update(InOrder inOrder) {
		boolean ret = DaoFactory.getInst().getInOrderDao().updateInOrder(inOrder);
		if(ret) {
			return inOrder;
		}
		return null;
	}
}
