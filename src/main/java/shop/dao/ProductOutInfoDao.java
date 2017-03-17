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

import shop.beam.ProductOutInfo;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class ProductOutInfoDao extends BaseDao {

	public boolean createProductOutInfo(ProductOutInfo productOption) {
		boolean result = false;
		String sql = "insert into t_product_out_info (productId,userId,num,price,price1,opTime,state,returnNum,returnTime,returnMark,outId) values (?,?,?,?,?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, productOption.getProductId()));
		param.put(2, new DbParameter(Types.INTEGER, productOption.getUserId()));
		param.put(3, new DbParameter(Types.FLOAT, productOption.getNum()));
		param.put(4, new DbParameter(Types.FLOAT, productOption.getPrice()));
		param.put(5, new DbParameter(Types.FLOAT, productOption.getPrice1()));
		param.put(6, new DbParameter(Types.TIMESTAMP, productOption.getOpTime()));
		param.put(7, new DbParameter(Types.INTEGER, productOption.getState()));
		param.put(8, new DbParameter(Types.FLOAT, productOption.getReturnNum()));
		param.put(9, new DbParameter(Types.TIMESTAMP, productOption.getReturnTime()));
		param.put(10, new DbParameter(Types.VARCHAR, productOption.getReturnMark()));
		param.put(11, new DbParameter(Types.INTEGER, productOption.getOutId()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			productOption.setId(id);
		}
		return result;
	}
	
	public boolean updateProductOutInfo(ProductOutInfo productOption) {
		String sql = "update t_product_out_info set productId=?,userId=?,num=?,price=?,price1=?,opTime=?,state=?,returnNum=?,returnTime=?,returnMark=?,outId=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, productOption.getProductId()));
		param.put(2, new DbParameter(Types.INTEGER, productOption.getUserId()));
		param.put(3, new DbParameter(Types.FLOAT, productOption.getNum()));
		param.put(4, new DbParameter(Types.FLOAT, productOption.getPrice()));
		param.put(5, new DbParameter(Types.FLOAT, productOption.getPrice1()));
		param.put(6, new DbParameter(Types.TIMESTAMP, productOption.getOpTime()));
		param.put(7, new DbParameter(Types.INTEGER, productOption.getState()));
		param.put(8, new DbParameter(Types.FLOAT, productOption.getReturnNum()));
		param.put(9, new DbParameter(Types.TIMESTAMP, productOption.getReturnTime()));
		param.put(10, new DbParameter(Types.VARCHAR, productOption.getReturnMark()));
		param.put(11, new DbParameter(Types.INTEGER, productOption.getOutId()));
		param.put(12, new DbParameter(Types.INTEGER, productOption.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<ProductOutInfo> getProductOutInfoList() {
		String sql = "select * from t_product_out_info where state=0;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<ProductOutInfo> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<ProductOutInfo>();
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
	
	public boolean del(int id) {
		String sql = "update t_product_out_info set state=1 where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, id));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public ProductOutInfo resultToBean(ResultSet rs) throws SQLException {
		ProductOutInfo productOption = new ProductOutInfo();
		productOption.setId(rs.getInt("id"));
		productOption.setProductId(rs.getInt("productId"));
		productOption.setUserId(rs.getInt("userId"));
		productOption.setNum(rs.getFloat("num"));
		productOption.setPrice(rs.getFloat("price"));
		productOption.setPrice1(rs.getFloat("price1"));
		productOption.setOpTime(rs.getDate("opTime"));
		productOption.setState(rs.getInt("state"));
		productOption.setReturnNum(rs.getFloat("returnNum"));
		productOption.setReturnTime(rs.getDate("returnTime"));
		productOption.setReturnMark(rs.getString("returnMark"));
		productOption.setOutId(rs.getInt("outId"));
		return productOption;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
