package shop.db.pool;

import java.sql.Connection;

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
	
	private String address; 
	private int port; 
	private String dbName; 
	private String user; 
	private String password;
	private int DBMaxConn = 1;
	private int DBFllow = 1;
	
	private IDBPool dbPool;
	

	public boolean init(String address, int port, String dbName, String user, 
			String password, int DBMaxConn, int DBFllow) {
		this.address = address;
		this.port = port;
		this.dbName = dbName;
		this.user = user;
		this.password = password;
		this.DBMaxConn = DBMaxConn;
		this.DBFllow = DBFllow;
		return initPool();
	}

	private String getDBUrl() {
		return String.format(dbUrl, address, port, dbName);
	}

	public boolean initPool() {
		dbPool = createPools(dbName, getDBUrl(), user, password);
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
			IDBPool pool = new BoneCPDBPool(dbName, getDBUrl(), user, password, DBMaxConn, DBFllow);
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
