package shop.provider;

import shop.bean.ProductInInfo;
import shop.dao.DaoFactory;

/**
 * 进货记录源
 * @author Administrator
 *
 */
public class ProductInInfoProvider {

	private static ProductInInfoProvider instance = new ProductInInfoProvider();
	
	public static ProductInInfoProvider getInst() {
		return instance;
	}
	
	
	public ProductInInfo add(ProductInInfo productInInfo) {
		boolean ret = DaoFactory.getInst().getProductInInfoDao().createProductInInfo(productInInfo);
		if(ret) {
			return productInInfo;
		}
		return null;
	}
	
	public boolean del(int id) {
		return DaoFactory.getInst().getProductInInfoDao().del(id);
	}
	
	public ProductInInfo update(ProductInInfo productInInfo) {
		boolean ret = DaoFactory.getInst().getProductInInfoDao().updateProductInInfo(productInInfo);
		if(ret) {
			return productInInfo;
		}
		return null;
	}
	
}
