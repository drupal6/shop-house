package shop.view.manage;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import shop.provider.ProductOutInfoProvider;

public class ProductOutInfoDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyTable table1;
	
	public ProductOutInfoDialog(int orderId, String title) { 
		this.setSize(771, 400);
		this.setTitle(title);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		Point topLeft = ManageFrame.getInst().getLocationOnScreen();
		Dimension parentSize = ManageFrame.getInst().getSize();
		Dimension mySize = this.getSize();
		int x, y;
		if (parentSize.width > mySize.width) {
			x = ((parentSize.width - mySize.width) / 2) + topLeft.x;
		} else {
			x = topLeft.x;
		}
		if (parentSize.height > mySize.height) {
			y = ((parentSize.height - mySize.height) / 2) + topLeft.y;
		} else {
			y = topLeft.y;
		}
		this.setLocation(x, y);
		
		table1 = new MyTable(ProductOutInfoProvider.getTitle(), 
				ProductOutInfoProvider.getListValue(ProductOutInfoProvider.getInst().queryByOrderId(orderId)), 500, 300);
		add(table1);
	}
	
}
