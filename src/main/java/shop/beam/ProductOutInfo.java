package shop.beam;

import java.util.Date;

public class ProductOutInfo {
	
	private int id;
	
	private int productId;  //商品id
	
	private int userId;		//操作员
	
	private float num;      //出售数量
	
	private float price;    //出售原单价
	
	private float price1;   //出售时单价格
	
	private Date opTime;    //销售时间
	
	private int state;
	
	private float returnNum;	//退货数量
	
	private Date returnTime;	//退货时间
	
	private String returnMark;	//退货备注
	
	private int outId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getNum() {
		return num;
	}

	public void setNum(float num) {
		this.num = num;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice1() {
		return price1;
	}

	public void setPrice1(float price1) {
		this.price1 = price1;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(float returnNum) {
		this.returnNum = returnNum;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getReturnMark() {
		return returnMark;
	}

	public void setReturnMark(String returnMark) {
		this.returnMark = returnMark;
	}

	public int getOutId() {
		return outId;
	}

	public void setOutId(int outId) {
		this.outId = outId;
	}
}
