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

import shop.beam.OutOrder;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class OutOrderDao extends BaseDao {

	public boolean createOutOrder(OutOrder outOrder) {
		boolean result = false;
		String sql = "insert into t_out_order (userId,opTime,cash,mark,state) values (?,?,?,?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, outOrder.getUserId()));
		param.put(2, new DbParameter(Types.TIMESTAMP, outOrder.getOpTime()));
		param.put(3, new DbParameter(Types.FLOAT, outOrder.getCash()));
		param.put(4, new DbParameter(Types.VARCHAR, outOrder.getMark()));
		param.put(5, new DbParameter(Types.INTEGER, outOrder.getState()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			outOrder.setId(id);
		}
		return result;
	}
	
	public boolean updateOutOrder(OutOrder outOrder) {
		String sql = "update t_out_order set userId=?,opTime=?,cash=?,mark=?,state=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, outOrder.getUserId()));
		param.put(2, new DbParameter(Types.TIMESTAMP, outOrder.getOpTime()));
		param.put(3, new DbParameter(Types.FLOAT, outOrder.getCash()));
		param.put(4, new DbParameter(Types.VARCHAR, outOrder.getMark()));
		param.put(5, new DbParameter(Types.INTEGER, outOrder.getState()));
		param.put(8, new DbParameter(Types.INTEGER, outOrder.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<OutOrder> getoutOrderList() {
		String sql = "select * from t_out_order;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<OutOrder> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<OutOrder>();
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
	
	public OutOrder resultToBean(ResultSet rs) throws SQLException {
		OutOrder outOrder = new OutOrder();
		outOrder.setId(rs.getInt("id"));
		outOrder.setUserId(rs.getInt("userId"));
		outOrder.setOpTime(rs.getDate("opTime"));
		outOrder.setCash(rs.getFloat("cash"));
		outOrder.setMark(rs.getString("mark"));
		outOrder.setState(rs.getInt("state"));
		return outOrder;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
