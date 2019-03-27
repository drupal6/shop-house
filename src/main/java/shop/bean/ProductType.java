package shop.bean;

public class ProductType {

	private int id;  		//商品类别编号
	
	private String name;  	//商品类别名称
	
	private int state; 		//商品类别状态

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
