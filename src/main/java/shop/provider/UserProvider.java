package shop.provider;

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
		user.setState(1);
		boolean ret = DaoFactory.getInst().getUserDao().updateUser(user);
		return ret;
	}
	
}
