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

import shop.beam.ProductType;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class ProductTypeDao extends BaseDao {

	public boolean createProductType(ProductType productType) {
		boolean result = false;
		String sql = "insert into t_product_type (name,state) values (?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, productType.getName()));
		param.put(2, new DbParameter(Types.INTEGER, productType.getState()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			productType.setId(id);
		}
		return result;
	}
	
	public boolean updateProductType(ProductType productType) {
		String sql = "update t_product_type set name=?,state=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, productType.getName()));
		param.put(2, new DbParameter(Types.INTEGER, productType.getState()));
		param.put(3, new DbParameter(Types.INTEGER, productType.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<ProductType> getProductTypeList() {
		String sql = "select * from t_product_type;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<ProductType> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<ProductType>();
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
	
	public ProductType resultToBean(ResultSet rs) throws SQLException {
		ProductType productType = new ProductType();
		productType.setId(rs.getInt("id"));
		productType.setName(rs.getString("name"));
		productType.setState(rs.getInt("state"));
		return productType;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
