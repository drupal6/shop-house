package shop.barcode;

public class BarcodeProvider {

	private static BarcodeProvider instance = new BarcodeProvider();

	public static BarcodeProvider getInst() {
		return instance;
	}

	private BarcodeProducter barcodeProducter = new BarcodeProducter();

	private BarcodeConsumer barcodeConsumer = new BarcodeConsumer();

	public void start() {
		barcodeProducter.startProduct();
		barcodeConsumer.startConsume();
	}

	public void setCallBack(BarcodeCallBack callBack) {
		barcodeConsumer.setCallBack(callBack);
	}

	public void stop() {
		barcodeProducter.stopProduct();
		barcodeConsumer.stopConsume();
		barcodeConsumer.updateCanScan(false);
	}

	public void updateScanState(boolean canScan) {
		barcodeConsumer.updateCanScan(canScan);
	}

}
