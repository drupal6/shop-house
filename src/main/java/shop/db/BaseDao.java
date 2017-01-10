package shop.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public abstract class BaseDao {

	protected int execNoneQuery(String sql) {
		return execNoneQuery(sql, null);
	}

	protected int execNoneQuery(String sql, Map<Integer, DbParameter> params) {
		DbWatch watch = new DbWatch();
		int result = -1;
		Connection conn = openConn();
		watch.getPool();
		if (conn == null) {
			watch.commit(sql);
			return result;
		}
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			prepareCommand(pstmt, params);
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeConn(conn, pstmt);
			watch.commit(sql);
		}
		return result;
	}

	protected PreparedStatement execQuery(String sql) {
		return execQuery(sql, null);
	}

	protected PreparedStatement execQuery(String sql, Map<Integer, DbParameter> params) {
		DbWatch watch = new DbWatch();
		Connection conn = openConn();
		watch.getPool();
		if (conn == null) {
			watch.commit(sql);
			return null;
		}
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			prepareCommand(pstmt, params);
		} catch (Exception ex) {
			ex.printStackTrace();
			closeConn(conn);
		} finally {
			watch.commit(sql);
		}
		return pstmt;
	}

	protected int execLastId(String sql, Map<Integer, DbParameter> params) {
		DbWatch watch = new DbWatch();
		int result = -1;
		Connection conn = openConn();
		watch.getPool();
		if (conn == null) {
			watch.commit(sql);
			return result;
		}
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prepareCommand(pstmt, params);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		} finally {
			closeConn(conn, pstmt, rs);
			watch.commit(sql);
		}
		return result;
	}

	protected int execLastId(String sql) {
		return execLastId(sql, null);
	}

	protected boolean sqlBatch(String tableName, List<String> sqlComm) {
		if (sqlComm == null || sqlComm.size() == 0)
			return true;

		DbWatch watch = new DbWatch();
		Connection conn = openConn();
		watch.getPool();
		if (conn == null) {
			watch.commit(tableName);
			return false;
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			for (String sqlString : sqlComm) {
				stmt.addBatch(sqlString);
			}
			stmt.executeBatch();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(conn, stmt);
			watch.commit(tableName);
		}
		return false;
	}

	protected boolean runProcePrepared(String procName) {
		DbWatch watch = new DbWatch();
		boolean result = false;
		Connection conn = openConn();
		watch.getPool();
		if (conn == null) {
			watch.commit(procName);
			return result;
		}
		String sql = ProcedureName(procName, 0);
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeConn(conn, statement);
			watch.commit(procName);
		}
		return result;
	}
	
	protected boolean runCallProcPrepared(String strProc) {
		DbWatch watch = new DbWatch();
		boolean result = false;
		Connection conn = openConn();
		watch.getPool();
		if (conn == null) {
			watch.commit(strProc);
			return result;
		}
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(strProc);
			statement.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeConn(conn, statement);
			watch.commit(strProc);
		}
		return result;
	}

	protected int execCountQuery(String sql, Map<Integer, DbParameter> params) {
		DbWatch watch = new DbWatch();
		int result = -1;
		Connection conn = openConn();
		watch.getPool();
		if (conn == null) {
			watch.commit(sql);
			return result;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			prepareCommand(pstmt, params);
			rs = pstmt.executeQuery();
			if ((rs != null) && (rs.next())) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		} finally {
			closeConn(conn, pstmt, rs);
			watch.commit(sql);
		}
		return result;
	}

	protected String getDbName() {
		DbWatch watch = new DbWatch();		
		Connection conn = openConn();
		watch.getPool();
		if (conn == null) {
			watch.commit("get DataBase Name");
			return "";
		}
		String temp = "";
		try {
			temp = conn.getCatalog();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			closeConn(conn);
		}
		return temp;

	}

	protected abstract Connection openConn();

	private void closeConn(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void closeConn(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.clearParameters();
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn(conn);
	}

	private void closeConn(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
		}
		closeConn(conn, pstmt);
	}

	private void closeConn(Connection conn, Statement statement) {
		if (statement != null) {
			try {
				statement.clearBatch();
				statement.close();
			} catch (SQLException e) {
			}
		}
		closeConn(conn);
	}

	protected void closeConn(PreparedStatement pstmt, ResultSet rs) {
		try {
			if (pstmt != null) {
				Connection conn = pstmt.getConnection();
				closeConn(conn, pstmt, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void prepareCommand(PreparedStatement pstmt, Map<Integer, DbParameter> parms) throws SQLException {
		if (pstmt == null || parms == null)
			return;
		for (Map.Entry<Integer, DbParameter> entry : parms.entrySet()) {
			pstmt.setObject(entry.getKey(), entry.getValue().getResult());
		}
	}

	protected String getPage(String tableName, String fields, int pageSize, int pageNow, String orderString, String paraStr) {
		int beginRow = 0;
		if (pageSize <= 0)
			pageSize = 1;
		if (pageNow <= 0)
			pageNow = 1;
		String limitString;
		beginRow = (pageNow - 1) * pageSize;
		limitString = " LIMIT " + beginRow + ", " + pageSize;
		String sqlString = "SELECT " + fields + " FROM " + tableName + "  Where " + paraStr + "  Order By " + orderString + limitString + ";";
		return sqlString;
	}
	
	protected String getPageByBeginRow(String tableName, String fields, int pageSize, int beginRow, String orderString, String paraStr) {		
		if (pageSize <= 0)
			pageSize = 1;
		if (beginRow < 0)
			beginRow = 1;
		String limitString;		
		limitString = " LIMIT " + beginRow + ", " + pageSize;
		String sqlString = "SELECT " + fields + " FROM " + tableName + "  Where " + paraStr + "  Order By " + orderString + limitString + ";";
		return sqlString;
	}

	private String ProcedureName(String procName, int paraCount) {
		int paramCount = paraCount; // 参数个数
		String questionMark = ""; // 存放问号的字符串. 如?或?,?...
		// 设置问号字符串, 以逗号隔开
		for (int i = 0; i < paramCount; i++) {
			questionMark += "?,";
		}
		if (!questionMark.equals("")) {
			questionMark = questionMark.substring(0, questionMark.length() - 1);
		}
		return "{call  " + procName + "(" + questionMark + ")}";
	}
}
