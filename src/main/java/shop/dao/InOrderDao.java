package shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.bean.InOrder;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class InOrderDao extends BaseDao {

	public boolean createInOrder(InOrder inOrder) {
		boolean result = false;
		String sql = "insert into t_in_order (userId,opTime,cash,mark,state) values (?,?,?,?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, inOrder.getUserId()));
		param.put(2, new DbParameter(Types.TIMESTAMP, inOrder.getOpTime()));
		param.put(3, new DbParameter(Types.FLOAT, inOrder.getCash()));
		param.put(4, new DbParameter(Types.VARCHAR, inOrder.getMark()));
		param.put(5, new DbParameter(Types.INTEGER, inOrder.getState()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			inOrder.setId(id);
		}
		return result;
	}
	
	public boolean updateInOrder(InOrder inOrder) {
		String sql = "update t_in_order set userId=?,opTime=?,cash=?,mark=?,state=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, inOrder.getUserId()));
		param.put(2, new DbParameter(Types.TIMESTAMP, inOrder.getOpTime()));
		param.put(3, new DbParameter(Types.FLOAT, inOrder.getCash()));
		param.put(4, new DbParameter(Types.VARCHAR, inOrder.getMark()));
		param.put(5, new DbParameter(Types.INTEGER, inOrder.getState()));
		param.put(8, new DbParameter(Types.INTEGER, inOrder.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<InOrder> getInOrderList() {
		String sql = "select * from t_in_order;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<InOrder> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<InOrder>();
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
	
	public InOrder resultToBean(ResultSet rs) throws SQLException {
		InOrder inOrder = new InOrder();
		inOrder.setId(rs.getInt("id"));
		inOrder.setUserId(rs.getInt("userId"));
		inOrder.setOpTime(rs.getTimestamp("opTime"));
		inOrder.setCash(rs.getFloat("cash"));
		inOrder.setMark(rs.getString("mark"));
		inOrder.setState(rs.getInt("state"));
		return inOrder;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
