package shop.barcode;

/**
 *此消费者线程会从此缓冲区中获取数据并执行数据的保存操作
 * 数据的保存调用BarcodeSaveService接口定义的save方法
 * 
 * @author ysc
 */
public class BarcodeConsumer {
    //消费者线程
    private Thread thread;
    //数据保存服务（可有多个）
    private BarcodeCallBack callBack;
    private boolean quit;
    
    private volatile boolean canScan = false;
     
    /**
     * 停止消费者线程
     * 此方法在tomcat关闭的时候被调用
     */
    public void stopConsume(){
    	canScan = false;
        if(thread!=null){
            thread.interrupt();
        	callBack.finish();
        }
    }
    
    public void setCallBack(BarcodeCallBack callBack) {
    	this.callBack = callBack;
    }
    /**
     * 启动消费者线程
     * 此方法在tomcat启动的时候被调用
     */
    public void startConsume(){
        //防止重复启动
        if(thread!=null && thread.isAlive()){
            return;
        }
        System.out.println("条形码消费者线程启动");
        thread=new Thread(){
            @Override
            public void run(){
                while(!quit){
                    try{
                        //当缓冲区没有数据的时候，此方法会阻塞
                    		String barcode=BarcodeBuffer.consume();
                    		if(barcode != null) {
                    			if(canScan) {
                    			if(callBack != null) {
                    				callBack.scan(barcode);
                    			}
                			}
                    	}
                    }catch(InterruptedException e){
                        quit=true;
                    }
                }
                System.out.println("条形码消费者线程退出");
            }
        };
        thread.setName("consumer");
        thread.start();
    }
    
    public void updateCanScan(boolean canScan) {
//    	try {
//			throw new Exception(canScan +"");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    	this.canScan = canScan;
    }
}