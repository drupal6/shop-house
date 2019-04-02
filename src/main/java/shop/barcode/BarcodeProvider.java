package shop.barcode;

public class BarcodeProvider {
	
	private static BarcodeProvider instance = new BarcodeProvider();
	
	public static BarcodeProvider getInst() {
		return instance;
	}

	private BarcodeProducter barcodeProducter;
	
	private BarcodeConsumer barcodeConsumer;
	
	public void start(BarcodeCallBack callBack) {
		barcodeProducter = new BarcodeProducter();
		barcodeConsumer = new BarcodeConsumer();
		barcodeProducter.startProduct();
		barcodeConsumer.startConsume(callBack);
	}
	
	public void stop() {
		barcodeProducter.stopProduct();
		barcodeConsumer.stopConsume();
	}
	
	public void updateScanState(boolean canScan) {
		barcodeConsumer.updateCanScan(canScan);
	}

}
