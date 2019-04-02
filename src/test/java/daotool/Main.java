package daotool;

import java.sql.SQLException;

import shop.db.pool.DBPoolMgr;

public class Main {

	// 数据库(db_gal_base,db_gal_main,db_gal_log)
	private static String DATA_BASE = "shop";
	// 表名
	private static String TABLE_NAME = "t_test";
	// 是否覆盖文件
	private static boolean IS_OVER_RIDE = true;
	// 主键是否自增长
	private static boolean ID_AUTOINCREATE = true;

	public static void main(String[] args) throws SQLException {
		// 类生成的路径
		String outPath = "shop";
		init();
		new GenEntityAndDao(DATA_BASE, TABLE_NAME, outPath, IS_OVER_RIDE, ID_AUTOINCREATE);
	}

	private static void init() {
//		DBPoolMgr.getInst().init("127.0.0.1", 3306, "shop", "root", "12345", 30, 10);		
	}
}