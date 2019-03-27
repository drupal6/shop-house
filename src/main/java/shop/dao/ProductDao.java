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

import shop.bean.Product;
import shop.db.BaseDao;
import shop.db.DbParameter;
import shop.db.pool.DBPoolMgr;

public class ProductDao extends BaseDao {

	public boolean createProduct(Product product) {
		boolean result = false;
		String sql = "insert into t_product (name,productType,productUnit,barCode,inPrice,outPrice,stock,state) values (?,?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, product.getName()));
		param.put(2, new DbParameter(Types.INTEGER, product.getProductType()));
		param.put(3, new DbParameter(Types.INTEGER, product.getProductUnit()));
		param.put(4, new DbParameter(Types.VARCHAR, product.getBarCode()));
		param.put(5, new DbParameter(Types.FLOAT, product.getInPrice()));
		param.put(6, new DbParameter(Types.FLOAT, product.getOutPrice()));
		param.put(7, new DbParameter(Types.FLOAT, product.getStock()));
		param.put(8, new DbParameter(Types.INTEGER, product.getState()));
		int id = execLastId(sql, param);
		if(id > 0) {
			result = true;
			product.setId(id);
		}
		return result;
	}
	
	public boolean updateProduct(Product product) {
		String sql = "update t_product set name=?,productType=?,productUnit=?,barCode=?,inPrice=?,outPrice=?,stock=?,state=? where id=?;";
		Map<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();
		param.put(1, new DbParameter(Types.VARCHAR, product.getName()));
		param.put(2, new DbParameter(Types.INTEGER, product.getProductType()));
		param.put(3, new DbParameter(Types.INTEGER, product.getProductUnit()));
		param.put(4, new DbParameter(Types.VARCHAR, product.getBarCode()));
		param.put(5, new DbParameter(Types.FLOAT, product.getInPrice()));
		param.put(6, new DbParameter(Types.FLOAT, product.getOutPrice()));
		param.put(7, new DbParameter(Types.FLOAT, product.getStock()));
		param.put(8, new DbParameter(Types.INTEGER, product.getState()));
		param.put(9, new DbParameter(Types.INTEGER, product.getId()));
		boolean result = execNoneQuery(sql, param) > -1;
		return result;
	}
	
	public List<Product> getProductList() {
		String sql = "select * from t_product;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<Product> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<Product>();
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
	
	public Product resultToBean(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setProductType(rs.getInt("productType"));
		product.setProductUnit(rs.getInt("productUnit"));
		product.setBarCode(rs.getString("barCode"));
		product.setInPrice(rs.getFloat("inPrice"));
		product.setOutPrice(rs.getFloat("outPrice"));
		product.setStock(rs.getFloat("stock"));
		product.setState(rs.getInt("state"));
		return product;
	}
	
	@Override
	protected Connection openConn() {
		return DBPoolMgr.getInst().getConn();
	}

}
