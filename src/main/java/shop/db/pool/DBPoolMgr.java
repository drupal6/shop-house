package shop.db.pool;

import java.sql.Connection;

import shop.Setting;

/**
 * 数据库连接池管理类
 * 
 */
public class DBPoolMgr {
	
	
	private static DBPoolMgr instance = new DBPoolMgr();
	
	public static DBPoolMgr getInst() {
		return instance;
	}
	
	public static final String dbUrl = "jdbc:mysql://%s:%s/%s?characterEncoding=utf-8&autoReconnect=true";
	
	private Setting setting;
	private int DBMaxConn = 1;
	private int DBFllow = 1;
	
	private IDBPool dbPool;
	

	public boolean init(Setting setting, int DBMaxConn, int DBFllow) {
		this.setting = setting;
		this.DBMaxConn = DBMaxConn;
		this.DBFllow = DBFllow;
		return initPool();
	}

	private String getDBUrl() {
		return String.format(dbUrl, setting.getIp(), setting.getPort(), setting.getDbName());
	}

	public boolean initPool() {
		dbPool = createPools(setting.getDbName(), getDBUrl(), setting.getUserName(), setting.getPassword());
		if (dbPool == null) {
			return false;
		}
		return true;
	}

	/**
	 * 检查连接池状态是否挂掉，如挂了重新初始化
	 */
	public void checkConnectionPool() {
		boolean initBase = false;
		if (dbPool == null || dbPool.getCurConns() <= 0) {
			initBase = true;
		}
		if (initBase) {
			initPool();
		}
	}

	/**
	 * 根据指定属性创建连接池实例.
	 * 
	 * @param props
	 *            连接池属性
	 */
	private IDBPool createPools(String poolName, String url, String user, String password) {
		try {
			IDBPool pool = new BoneCPDBPool(poolName, url, user, password, DBMaxConn, DBFllow);
			return pool;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void closePools() {
		if(dbPool!=null){
			dbPool.shutdown();
			dbPool=null;
		}
	}

	public Connection getConn() {
		Connection conn = dbPool.getConnection();
		return conn;
	}

	public String getPoolState() {
		if (dbPool != null) {
			return dbPool.getState();
		}
		return null;
	}

}
