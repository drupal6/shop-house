package shop.view.sale;

import java.awt.Color;

public enum SaleOptionEnum {

	SETTLE("结算", new Color(107, 137, 213), Color.WHITE){
		@Override
		public void clickHandle() {
			SaleFrame.getInst().setLayerPanel(200, 300);
			SaleFrame.getInst().getDialogPanel().add(new SettlePanel());
		}
	}, 	//结算
	ADD("+", new Color(117, 181, 84), Color.WHITE),    		//+
	RED("-", new Color(226, 158, 67), Color.WHITE),    	//-
	NUM("数量", Color.WHITE, Color.BLACK),   	//数量
	DEL("删除", Color.WHITE, Color.BLACK),   	//数量
	ALERT("改价", Color.WHITE, Color.BLACK), 	//改价
	;
	
	private String name;
	private Color color;
	private Color fontColor;
	
	private SaleOptionEnum(String name, Color color, Color fontColor) {
		this.name = name;
		this.color = color;
		this.fontColor = fontColor;
	}
	
	public void clickHandle() {
		
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public Color getFontColor() {
		return fontColor;
	}
}
