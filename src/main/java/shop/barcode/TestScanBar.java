package shop.barcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Gene
 * 2019年3月24日
 *
 */
public class TestScanBar {
	public static void main(String[] args) throws Exception {
      BarcodeProducter producter=new BarcodeProducter();
      BarcodeConsumer consumer=new BarcodeConsumer();
       
      producter.startProduct();
      consumer.startConsume();
       
      BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
      System.out.println("输入 '<exit>' 退出程序");
      String line=reader.readLine();
      while(line!=null){
          if("exit".equals(line)){
              producter.stopProduct();
              consumer.stopConsume();
              System.exit(0);
          }
          line=reader.readLine();
      }
  }
}
