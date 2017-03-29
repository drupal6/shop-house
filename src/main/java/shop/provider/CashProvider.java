package shop.provider;

import java.util.Date;
import java.util.List;

import shop.beam.Cash;
import shop.dao.DaoFactory;

/**
 * 零钱
 * @author Administrator
 *
 */
public class CashProvider {

	private static CashProvider instance = new CashProvider();
	
	public static CashProvider getInst() {
		return instance;
	}
	
	public Cash addCashLog(float num, int type, int uid, String mark) {
		Cash cash = new Cash();
		cash.setOpTime(new Date());
		cash.setType(type);
		cash.setNum(num);
		cash.setUid(uid);
		cash.setMark(mark);
		DaoFactory.getInst().getCashDao().createCash(cash);
		return cash;
	}
	
	public List<Cash> query(Date startTime, Date endTime, int type, int uid) {
		return DaoFactory.getInst().getCashDao().getCashList(startTime, endTime, type, uid);
	}
	
}
