package shop.provider;

import java.util.List;
import java.util.Vector;

import shop.bean.User;
import shop.dao.DaoFactory;

/**
 * 用户源
 * @author Administrator
 *
 */
public class UserProvider {

	private static UserProvider instance = new UserProvider();
	
	public static UserProvider getInst() {
		return instance;
	}
	
	public boolean add(User user) {
		return DaoFactory.getInst().getUserDao().createUser(user);
	}
	
	public User getUser(int id) {
		return DaoFactory.getInst().getUserDao().getUser(id);
	}
	
	public User getUser(String userName) {
		return DaoFactory.getInst().getUserDao().getUser(userName);
	}
	
	public User update(User user) {
		boolean ret = DaoFactory.getInst().getUserDao().updateUser(user);
		if(ret) {
			return user;
		} 
		return null;
	}
	
	public boolean del(User user) {
		boolean ret = DaoFactory.getInst().getUserDao().del(user);
		return ret;
	}
	
	private static  Vector<String> columnNameV = new Vector<String>();
	static{
		columnNameV.add("编号");
		columnNameV.add("账号");
		columnNameV.add("密码");
		columnNameV.add("名称");
		columnNameV.add("管理员");
		columnNameV.add("状态");
	}
	
	public static Vector<String> getTitle() {
		return columnNameV;
	}
	
	public static Vector getListValue() {
		Vector vv = new Vector();
		List<User> list = DaoFactory.getInst().getUserDao().getUserList();
		for(User user : list) {
			Vector rv = getValue(user);
			if(rv != null) {
				vv.add(rv);
			}
		}
		return vv;
	}
	
	public static Vector getValue(User user) {
		if(user == null) {
			return null;
		}
		Vector rowV = new Vector();
		rowV.add(user.getId());
		rowV.add(user.getName());
		rowV.add(user.getPassword());
		rowV.add(user.getNickName());
		rowV.add(user.getAdmin());
		rowV.add(user.getState());
		return rowV;
	}
	
}
