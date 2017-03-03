package shop;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Constance {
	
	public static Font mainMenuFont = new Font("宋体", 100, 24);
	public static Font menuFont =new Font("宋体", 100, 21);
	public static Font secondTitleFont = new Font("宋体", Font.BOLD, 24);
	public static Font dialogLableFont =new Font("宋体", 100, 22);
	
	
	public static Border border = BorderFactory.createLineBorder(new Color(153, 153, 153));
	
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
