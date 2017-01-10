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

import shop.beam.User;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class UserDao extends BaseDao {

	public boolean createUser(User user) {
		boolean result = false;
		String sql = "insert into t_user (name,password,nickName,admin,state) values (?,?,?,?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, user.getName()));
		param.put(2, new DbParameter(Types.VARCHAR, user.getPassword()));
		param.put(3, new DbParameter(Types.VARCHAR, user.getNickName()));
		param.put(4, new DbParameter(Types.INTEGER, user.getAdmin()));
		param.put(5, new DbParameter(Types.INTEGER, user.getState()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			user.setId(id);
		}
		return result;
	}
	
	public boolean updateUser(User product) {
		String sql = "update t_user set name=?,password=?,nickName=?,admin=?,state=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, product.getName()));
		param.put(2, new DbParameter(Types.VARCHAR, product.getPassword()));
		param.put(3, new DbParameter(Types.VARCHAR, product.getNickName()));
		param.put(4, new DbParameter(Types.INTEGER, product.getAdmin()));
		param.put(5, new DbParameter(Types.INTEGER, product.getState()));
		param.put(6, new DbParameter(Types.INTEGER, product.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<User> getUserList() {
		String sql = "select * from t_user where state=0;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<User> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<User>();
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
	
	public User getUser(String userName) {
		String sql = "select * from t_user where state=0;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		User info = null;
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
	
	public User resultToBean(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setNickName(rs.getString("nickName"));
		user.setAdmin(rs.getInt("admin"));
		user.setState(rs.getInt("state"));
		return user;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
