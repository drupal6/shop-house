package shop;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Constance {
	
	public static Font fontB35 = new Font("宋体", Font.BOLD, 35);
	public static Font fontB30 = new Font("宋体", Font.BOLD, 30);
	public static Font fontB24 = new Font("宋体", Font.BOLD, 24);
	public static Font font24 = new Font("宋体", 100, 24);
	public static Font font22 =new Font("宋体", 100, 22);
	public static Font font21 =new Font("宋体", 100, 21);
	public static Font font15 =new Font("宋体", 100, 15);
	public static Font fontB10 =new Font("宋体", Font.BOLD, 10);
	
	public static DecimalFormat df = new DecimalFormat("0.0");
	
	public static SimpleDateFormat dateFormt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
