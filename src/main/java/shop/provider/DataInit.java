package shop.provider;

public class DataInit {
	
	private static DataInit instance = new DataInit();
	
	public static DataInit getInst() {
		return instance;
	}
	
	public boolean initData() {
		ProductTypeProvider.getInst().init();
		ProductUnitProvider.getInst().init();
		ProductProvider.getInst().init();
		return true;
	}

}
