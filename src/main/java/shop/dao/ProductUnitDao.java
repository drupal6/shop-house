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

import shop.beam.ProductUnit;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class ProductUnitDao extends BaseDao {

	public boolean createProductUnit(ProductUnit productUnit) {
		boolean result = false;
		String sql = "insert into t_product_unit (name) values (?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, productUnit.getName()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			productUnit.setId(id);
		}
		return result;
	}
	
	public boolean updateProductUnit(ProductUnit productUnit) {
		String sql = "update t_product_unit set name=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, productUnit.getName()));
		param.put(2, new DbParameter(Types.INTEGER, productUnit.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<ProductUnit> getProductUnitList() {
		String sql = "select * from t_product_unit;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<ProductUnit> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<ProductUnit>();
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
	
	public ProductUnit resultToBean(ResultSet rs) throws SQLException {
		ProductUnit productUnit = new ProductUnit();
		productUnit.setId(rs.getInt("id"));
		productUnit.setName(rs.getString("name"));
		return productUnit;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
