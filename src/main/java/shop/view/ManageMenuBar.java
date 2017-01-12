package shop.view;

import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ManageMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	
	private JMenu baseDataMenu;
	private JMenuItem productTypeMenuItem;
	private JMenuItem productUnitMenuItem;
	private JMenuItem productInfoMenuItem;
	
	public ManageMenuBar() {
		Font font=new Font("宋体", 100, 24);
		Font font1=new Font("宋体", 100, 21);
		
		baseDataMenu = new JMenu();
		baseDataMenu.setText("基础数据");
		baseDataMenu.setFont(font);
		
		productTypeMenuItem = new JMenuItem();
		productTypeMenuItem.setText("商品类别");
		productTypeMenuItem.setFont(font1);
		baseDataMenu.add(productTypeMenuItem);
		
		productUnitMenuItem = new JMenuItem();
		productUnitMenuItem.setText("商品单位");
		productUnitMenuItem.setFont(font1);
		baseDataMenu.add(productUnitMenuItem);
		
		productInfoMenuItem = new JMenuItem();
		productInfoMenuItem.setText("商品信息");
		productInfoMenuItem.setFont(font1);
		baseDataMenu.add(productInfoMenuItem);
		
		this.add(baseDataMenu);
	}
	
}
