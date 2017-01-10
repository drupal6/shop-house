package shop.provider;

import shop.beam.ProductOutInfo;
import shop.dao.DaoFactory;

/**
 * 出售记录源
 * @author Administrator
 *
 */
public class ProductOutInfoProvider {

	private static ProductOutInfoProvider instance = new ProductOutInfoProvider();
	
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
	
}
