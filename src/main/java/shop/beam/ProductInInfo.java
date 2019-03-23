package shop.beam;

import java.util.Date;

public class ProductInInfo {
	
	private int id;
	
	private int productId;  //商品id
	
	private int userId;		//操作员
	
	private float num;      //进货数量
	
	private float num1;	    //赠送数量
	
	private float price;    //进货单价
	
	private Date opTime;    //进货时间
	
	private String remark;  //备注
	
	private int state;
	
	private int inId;

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

	public float getNum1() {
		return num1;
	}

	public void setNum1(float num1) {
		this.num1 = num1;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getInId() {
		return inId;
	}

	public void setInId(int inId) {
		this.inId = inId;
	}
}
