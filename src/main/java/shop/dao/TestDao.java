package shop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.sql.Types;
import shop.db.DbParameter;
import java.util.HashMap;
import java.util.ArrayList;
import shop.db.pool.DBPoolMgr;
import shop.bean.Test;
import shop.db.BaseDao;

/**
* t_test dao实现类的生成
* @author 
* @date Wed Mar 27 18:00:20 CST 2019
*/ 
public class TestDao extends BaseDao {

	public boolean createTest(Test test) {
		boolean result = false;
		String sql = "insert into t_test (id,name) values (?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, test.getId()));
		param.put(2, new DbParameter(Types.VARCHAR, test.getName()));
		int id = execLastId(sql, param);
		if(id > 0) {
			test.setId(id);
			result = true;
		}
		return result;
	}

	public List<Test> getTestList() {
		String sql = "select * from t_test;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<Test> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<Test>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					infos.add(resultToBean(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}

	public Test getTestById(int id) {
		String sql = "select * from t_test where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, id));
		PreparedStatement pstmt = execQuery(sql, param);
		ResultSet rs = null;
		Test test = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				if(rs.next()){
					test = resultToBean(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();;
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return test;
	}

	public boolean updateTest(Test test) {
		boolean result = false;
		String sql = "update t_test set name=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, test.getName()));
		param.put(2, new DbParameter(Types.INTEGER, test.getId()));
		result = execNoneQuery(sql, param) > -1;
		return result;
	}

	public Test resultToBean(ResultSet rs) throws SQLException {
		Test test = new Test();
		test.setId(rs.getInt("id"));
		test.setName(rs.getString("name"));
		return test;
	}

	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}
}
