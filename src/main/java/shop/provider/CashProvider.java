package shop.provider;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import shop.Constance;
import shop.bean.Cash;
import shop.bean.OutOrder;
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
	
	private static  Vector<String> columnNameV = new Vector<String>();
	static{
		columnNameV.add("操作时间");
		columnNameV.add("数量");
		columnNameV.add("类型");
		columnNameV.add("操作员");
		columnNameV.add("备注");
	}
	
	public static Vector<String> getTitle() {
		return columnNameV;
	}
	
	public static Vector getListValue(List<Cash> list) {
		Vector vv = new Vector();
		if(list != null) {
			for(Cash type : list) {
				Vector rv = getValue(type);
				if(rv != null) {
					vv.add(rv);
				}
			}
		}
		return vv;
	}
	
	public static Vector getValue(Cash cash) {
		if(cash == null) {
			return null;
		}
		Vector rowV = new Vector();
		rowV.add(Constance.dateFormt.format(cash.getOpTime()));
		rowV.add(cash.getNum());
		if(cash.getType() == 1) {
			rowV.add("存");
		}else {
			rowV.add("取");
		}
		rowV.add(cash.getUid());
		rowV.add(cash.getMark());
		return rowV;
	}
}
