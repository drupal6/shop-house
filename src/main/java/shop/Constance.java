package shop;

import java.util.HashMap;
import java.util.Map;

public class Constance {
	
	enum ORDERSTATE {
		NORMAL("正常"),  
		PAY("付款"),
		RETURN("退货"),
		DEL("删除"),
		;
		
		private String name;
		
		private ORDERSTATE(String name) {
			this.name();
		}
		public String getName() {
			return name;
		}
		
		private static Map<Integer, ORDERSTATE> map = new HashMap<Integer, Constance.ORDERSTATE>();
		static {
			for(ORDERSTATE state : ORDERSTATE.values()) {
				map.put(state.ordinal(), state);
			}
		}
		public ORDERSTATE getByOrdinal(int ordinal) {
			return map.get(ordinal);
		}
	}
	
	enum STATE {
		NORMAL("正常"),  
		STOP("停用"),
		DEL("删除"),     
		;
		
		private String name;
		
		private STATE(String name) {
			this.name();
		}
		public String getName() {
			return name;
		}
		
		private static Map<Integer, STATE> map = new HashMap<Integer, Constance.STATE>();
		static {
			for(STATE state : STATE.values()) {
				map.put(state.ordinal(), state);
			}
		}
		public STATE getByOrdinal(int ordinal) {
			return map.get(ordinal);
		}
	}
	
}
