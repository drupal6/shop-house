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

import shop.bean.ProductInInfo;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class ProductInInfoDao extends BaseDao {

	public boolean createProductInInfo(ProductInInfo productOption) {
		boolean result = false;
		String sql = "insert into t_product_out_info (productId,userId,num,num1,price,opTime,remark,state,inId) values (?,?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, productOption.getProductId()));
		param.put(2, new DbParameter(Types.INTEGER, productOption.getUserId()));
		param.put(3, new DbParameter(Types.FLOAT, productOption.getNum()));
		param.put(4, new DbParameter(Types.FLOAT, productOption.getNum1()));
		param.put(5, new DbParameter(Types.FLOAT, productOption.getPrice()));
		param.put(6, new DbParameter(Types.TIMESTAMP, productOption.getOpTime()));
		param.put(7, new DbParameter(Types.VARCHAR, productOption.getRemark()));
		param.put(8, new DbParameter(Types.INTEGER, productOption.getState()));
		param.put(9, new DbParameter(Types.INTEGER, productOption.getInId()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			productOption.setId(id);
		}
		return result;
	}
	
	public boolean updateProductInInfo(ProductInInfo productOption) {
		String sql = "update t_product_out_info set productId=?,userId=?,num=?,num1=?,price=?,opTime=?,remark=?,state=?,inId=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.INTEGER, productOption.getProductId()));
		param.put(2, new DbParameter(Types.INTEGER, productOption.getUserId()));
		param.put(3, new DbParameter(Types.FLOAT, productOption.getNum()));
		param.put(4, new DbParameter(Types.FLOAT, productOption.getNum1()));
		param.put(5, new DbParameter(Types.FLOAT, productOption.getPrice()));
		param.put(6, new DbParameter(Types.TIMESTAMP, productOption.getOpTime()));
		param.put(7, new DbParameter(Types.VARCHAR, productOption.getRemark()));
		param.put(8, new DbParameter(Types.INTEGER, productOption.getState()));
		param.put(9, new DbParameter(Types.INTEGER, productOption.getInId()));
		param.put(10, new DbParameter(Types.INTEGER, productOption.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<ProductInInfo> getProductOutInfoList() {
		String sql = "select * from t_product_out_info where state=0;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<ProductInInfo> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<ProductInInfo>();
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
	
	public ProductInInfo resultToBean(ResultSet rs) throws SQLException {
		ProductInInfo productOption = new ProductInInfo();
		productOption.setId(rs.getInt("id"));
		productOption.setProductId(rs.getInt("productId"));
		productOption.setUserId(rs.getInt("userId"));
		productOption.setNum(rs.getFloat("num"));
		productOption.setNum1(rs.getFloat("num1"));
		productOption.setPrice(rs.getFloat("price"));
		productOption.setOpTime(rs.getTimestamp("opTime"));
		productOption.setState(rs.getInt("state"));
		productOption.setRemark(rs.getString("remark"));
		productOption.setInId(rs.getInt("inId"));
		return productOption;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
