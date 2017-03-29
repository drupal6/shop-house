package shop.dao;

public class DaoFactory {
	
	private static DaoFactory instance = new DaoFactory();
	
	public static DaoFactory getInst() {
		return instance;
	}

	private ProductTypeDao productTypeDao = new ProductTypeDao();
	private ProductUnitDao productUnitDao = new ProductUnitDao();
	private ProductDao productDao = new ProductDao();
	
	private OutOrderDao outOrderDao = new OutOrderDao();
	private ProductOutInfoDao productOutInfoDao = new ProductOutInfoDao();
	
	private InOrderDao inOrderDao = new InOrderDao();
	private ProductInInfoDao productInInfoDao = new ProductInInfoDao();
	
	private UserDao userDao = new UserDao();
	
	private CashDao cashDao = new CashDao();

	public static DaoFactory getInstance() {
		return instance;
	}

	public ProductTypeDao getProductTypeDao() {
		return productTypeDao;
	}

	public ProductUnitDao getProductUnitDao() {
		return productUnitDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public OutOrderDao getOutOrderDao() {
		return outOrderDao;
	}

	public ProductOutInfoDao getProductOutInfoDao() {
		return productOutInfoDao;
	}

	public InOrderDao getInOrderDao() {
		return inOrderDao;
	}

	public ProductInInfoDao getProductInInfoDao() {
		return productInInfoDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public CashDao getCashDao() {
		return cashDao;
	}
}
