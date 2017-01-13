package shop.provider;

import shop.db.pool.DBPoolMgr;

public class DataInit {
	
	private static DataInit instance = new DataInit();
	
	public static DataInit getInst() {
		return instance;
	}
	
	private DBPoolMgr dbpoolMgr;
	
	public boolean initConnect() {
		dbpoolMgr = DBPoolMgr.getInst();
		dbpoolMgr.init("192.168.2.120", 3306, "shop", "root", "123456", 3, 2);
		return true;
	}
	
	public boolean initData() {
		ProductTypeProvider.getInst().init();
		ProductUnitProvider.getInst().init();
		ProductProvider.getInst().init();
		return true;
	}

}
