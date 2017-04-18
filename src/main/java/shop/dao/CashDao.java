package shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.beam.Cash;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class CashDao extends BaseDao {

	public boolean createCash(Cash cash) {
		boolean result = false;
		String sql = "insert into t_cash (opTime,type,uid,num,mark) values (?,?,?,?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.TIMESTAMP, cash.getOpTime()));
		param.put(2, new DbParameter(Types.INTEGER, cash.getType()));
		param.put(3, new DbParameter(Types.INTEGER, cash.getUid()));
		param.put(4, new DbParameter(Types.FLOAT, cash.getNum()));
		param.put(5, new DbParameter(Types.VARCHAR, cash.getMark()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			cash.setId(id);
		}
		return result;
	}
	
	public boolean updateCash(Cash cash) {
		String sql = "update t_cash set opTime=?,type=?,uid=?,num=?,mark=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.TIMESTAMP, cash.getOpTime()));
		param.put(2, new DbParameter(Types.INTEGER, cash.getType()));
		param.put(3, new DbParameter(Types.INTEGER, cash.getUid()));
		param.put(4, new DbParameter(Types.FLOAT, cash.getNum()));
		param.put(5, new DbParameter(Types.VARCHAR, cash.getMark()));
		param.put(6, new DbParameter(Types.INTEGER, cash.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<Cash> getCashList(Date startTime, Date endTime, int type, int uid) {
		StringBuffer buff = new StringBuffer();
		buff.append("select * from t_cash");
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		boolean first = true;
		int count = 1;
		if(startTime != null) {
			buff.append(" where opTime > ?");
			param.put(count, new DbParameter(Types.TIMESTAMP, startTime));
			first = false;
			count++;
		}
		if(endTime != null) {
			if(first) {
				buff.append(" where opTime < ?");
				param.put(count, new DbParameter(Types.TIMESTAMP, endTime));
				first = false;
				count++;
			}else {
				buff.append(" and opTime < ?");
				param.put(count, new DbParameter(Types.TIMESTAMP, endTime));
				count++;
			}
		}
		if(type > 0) {
			if(first) {
				buff.append(" where type = ?");
				param.put(count, new DbParameter(Types.INTEGER, type));
				first = false;
				count++;
			}else {
				buff.append(" and type = ?");
				param.put(count, new DbParameter(Types.INTEGER, type));
				count++;
			}
		}
		if(uid > 0) {
			if(first) {
				buff.append(" where uid = ?");
				param.put(count, new DbParameter(Types.INTEGER, uid));
				first = false;
				count++;
			}else {
				buff.append(" and uid = ?");
				param.put(count, new DbParameter(Types.INTEGER, uid));
				count++;
			}
		}
		buff.append(";");
		PreparedStatement pstmt = execQuery(buff.toString(), param);
		ResultSet rs = null;
		List<Cash> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<Cash>();
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
	
	public Cash getCash(String userName) {
		String sql = "select * from t_user where state=0;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		Cash info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				info = resultToBean(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return info;
	}
	
	public Cash resultToBean(ResultSet rs) throws SQLException {
		Cash cash = new Cash();
		cash.setId(rs.getInt("id"));
		cash.setOpTime(rs.getTimestamp("opTime"));
		cash.setType(rs.getInt("type"));
		cash.setUid(rs.getInt("uid"));
		cash.setNum(rs.getFloat("num"));
		cash.setMark(rs.getString("mark"));
		return cash;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
