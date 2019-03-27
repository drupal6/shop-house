package shop.bean;

public class Product {
	
	private int id;
	
	private String name;
	
	private int productType;  //商品类别
	
	private int productUnit;  //商品单位
	
	private String barCode;   //商品条形码
	
	private float inPrice;    //进货价
	
	private float outPrice;   //出售价
	
	private float stock;      //库存
	
	private int state;        //状态 0整除  1停售

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public int getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(int productUnit) {
		this.productUnit = productUnit;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public float getInPrice() {
		return inPrice;
	}

	public void setInPrice(float inPrice) {
		this.inPrice = inPrice;
	}

	public float getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(float outPrice) {
		this.outPrice = outPrice;
	}
	
	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
